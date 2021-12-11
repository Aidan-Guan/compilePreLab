package GenerateCode.GrammarCode;

import AST.*;
import ErrorSolution.ErrorSolu;
import GrammarAnal.Expression.*;
import TokenUtils.Ident;
import TokenUtils.IdentMapList;
import TokenUtils.IdentType;
import TokenUtils.ValueType;

import java.util.ArrayList;
import java.util.HashMap;

import static GenerateCode.ExpressionCode.CalculateExpCode.CodeExp;
import static GenerateCode.GrammarCode.DeclareCode.*;
import static GenerateCode.GrammarCode.StatementCode.*;

public class ASTToCode {
    public static AstNode root;
    public static int regIndex = 1;
    public static int blockIndex = 1;

    public static boolean isReturn = false;
    public static boolean isDefConst = false;
    public static boolean isDefGlobal = false;
    public static boolean isArray = true;
    public static StringBuilder outStr = new StringBuilder();


    public static StringBuilder generateIntermediateCode (AstNode ASTroot) {
        root = ASTroot;
        identMapInit();

        CodeCompUnit(root);
        return outStr;
    }

    static void CodeCompUnit (AstNode parent) {
        for (AstNode child: parent.children) {
            if (child.type.equals("<VarDecl>")) {
                isDefGlobal = true;
                isDefConst = true;
                CodeVarDecl(child);
                isDefGlobal = false;
                isDefConst = false;
            }
            else if (child.type.equals("<ConstDecl>")) {
                isDefGlobal = true;
                isDefConst = true;
                CodeConstDecl(child);
                isDefGlobal = false;
                isDefConst = false;
            }
            else if (child.type.equals("<FuncDef>")) {
                CodeFuncDef(child);
            }
            else { ErrorSolu.error(); }
        }
    }

    static void CodeFuncDef(AstNode parent) {
        outStr.append("define dso_local i32 @main(){\n");

        CodeBlock(parent.children.get(4));

        outStr.append("}\n");
    }

    static void CodeBlock(AstNode parent) {
        HashMap<String, Ident> currMap = new HashMap<>();
        IdentMapList.addMap(currMap);

        for (AstNode child: parent.children) {

            if (child.type.equals("<BlockItem>")) {
                CodeBlockItem(child);
            }
        }

        IdentMapList.removeFisrtMap();
    }

    static void CodeBlockItem (AstNode parent) {
        switch (parent.children.get(0).type) {
            case "<VarDecl>" -> CodeVarDecl(parent.children.get(0));
            case "<ConstDecl>" -> CodeConstDecl(parent.children.get(0));
            case "<Stmt>" -> CodeStmt(parent.children.get(0));
        }
    }

    static ExpValue CodeInitVal(AstNode parent){

        if (!isDefGlobal) {

            if(parent.children.size() == 1)
                return CodeExp(parent.children.get(0));
            else {
                int pos = 0;
                int dep = parent.dep;
                ArrayList <Integer> arrayInfo = parent.arrayInfo;
                for(AstNode child : parent.children){
                    if (child.type.equals("<InitVal>")) {

                        child.arraySym = parent.arraySym;
                        if (child.children.size() > 1){
                            arrayInfo.set(parent.dep, pos);
                            child.arrayInfo = arrayInfo;
                            child.dep = parent.dep+1;
                            CodeInitVal(child);
                        } else {
                            arrayInfo.set(arrayInfo.size() - 1, pos);
                            child.arrayInfo = arrayInfo;
                            child.dep = parent.dep+1;
                            int dim = parent.arraySym.dim;
                            int registerNew = regIndex++;
                            Ident arraySym = parent.arraySym;
                            outStr.append("\t%x" +registerNew + " = getelementptr " + arraySym.arrayType + ", " + arraySym.arrayType + "* " + arraySym.out() + ", i32 0");
                            for(int i = 0; i < dim; i++){
                                outStr.append(", i32 " + arrayInfo.get(i));
                            }
                            outStr.append("\n");

                            ExpValue expValue = CodeInitVal(child);
                            outStr.append("\tstore i32 %x" + expValue.register + ", i32* %x" + registerNew + "\n");
                        }
                    }
                    else if (child.value.equals(",")) {
                        pos += 1;
                    }
                    else if (child.value.equals("{")) {
                        dep++;
                        if(arrayInfo.size() < dep)
                            arrayInfo.add(-1);
                    }
                }
            }
        }
        else {
            if(parent.children.size() == 1){
                ExpValue exp = CodeExp(parent.children.get(0));
                if(isArray)
                    outStr.append(" " +  exp.value);
                return exp;
            }
            else {
                Ident arraySym = parent.arraySym;
                int pos = 0, dep = parent.dep;
                int thisDimSize = arraySym.dimSize.get(dep);
                int dimSum = arraySym.dim;
                String arrayType = "";
                for(int i = dep + 1; i < dimSum; i++){
                    arrayType += " [" + arraySym.dimSize.get(i) + " x";
                }
                arrayType += " i32";
                for (int i = dep + 1; i < dimSum; i++){
                    arrayType += "]";
                }
                outStr.append("[");
                for(AstNode child : parent.children){

                    if(child.type.equals("<InitVal>")){
                        child.arraySym = parent.arraySym;
                        child.dep = parent.dep+1;
                        if(pos != 0)
                            outStr.append(",");
                        outStr.append(arrayType);
                        CodeInitVal(child);
                        thisDimSize--;
                        pos += 1;
                    }
                    else if(child.value.equals("[")){
                        dep++;
                    }
                }
                while(thisDimSize > 0){
                    if(pos != 0){
                        outStr.append(",");
                    }
                    if(!arrayType.equals(" i32"))
                        outStr.append(arrayType + " zeroinitializer");
                    else
                        outStr.append(arrayType + " " + 0);
                    thisDimSize--;
                    pos++;
                }
                outStr.append("]");
            }
        }
        return null;
    }

    public static int i32Toi1(ExpValue value) {
        if (value.valueType.equals("i32")) {
            int reg = regIndex++;
            outStr.append("\t%x" + reg + " = icmp ne i32 " + value.out() + ", 0\n");
            return reg;
        }
        else {
            return value.register;
        }
    }

    public static AstNode getWhileBlock (AstNode node) {
        AstNode child = node;
        while (true) {
            AstNode parent = child.parent;
            if (parent.children.size()>0 && parent.children.get(0).value!=null && parent.children.get(0).value.equals("while")) {
                return parent;
            }
            child = parent;
        }
    }


    private static void identMapInit () {
        HashMap<String, Ident> globalMap = new HashMap<>();

        ArrayList<String> params = new ArrayList<>();
        params.add("i32");

        globalMap.put("getint", new Ident("getint", IdentType.FUNC, ValueType.INT, new ArrayList<>()));
        globalMap.put("getch", new Ident("getch", IdentType.FUNC, ValueType.INT, new ArrayList<>()));
        globalMap.put("putint", new Ident("putint", IdentType.FUNC, ValueType.VOID, params));
        globalMap.put("putch", new Ident("putch", IdentType.FUNC, ValueType.VOID, params));

        IdentMapList.addMap(globalMap);
    }
}
