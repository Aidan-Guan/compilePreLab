package GenerateCode.GrammarCode;

import AST.*;
import ErrorSolution.ErrorSolu;
import GrammarAnal.Expression.*;
import TokenUtils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public static boolean isFuncBlock = false;
    public static StringBuilder outStr = new StringBuilder();


    public static StringBuilder generateIntermediateCode (AstNode ASTroot) {
        root = ASTroot;
        identMapInit();

        CodeCompUnit(root);
        return outStr;
    }

    static void CodeCompUnit (AstNode parent) {
        for (AstNode child: parent.children) {
            switch (child.type) {
                case "<MainFuncDef>" -> CodeMainFuncDef(child);
                case "<VarDecl>" -> {
                    isDefGlobal = true;
                    isDefConst = true;
                    CodeVarDecl(child);
                    isDefGlobal = false;
                    isDefConst = false;
                }
                case "<ConstDecl>" -> {
                    isDefGlobal = true;
                    isDefConst = true;
                    CodeConstDecl(child);
                    isDefGlobal = false;
                    isDefConst = false;
                }
                case "<FuncDef>" -> CodeFuncDef(child);
                default -> ErrorSolu.error();
            }
        }
    }

    static void CodeMainFuncDef(AstNode parent) {
        blockIndex = 0;
        isReturn = false;
        regIndex = 1;
        outStr.append("define dso_local i32 @main(){\n");
        CodeBlock(parent.children.get(4));

        outStr.append("}\n");
    }


    static String CodeBType(AstNode node){
        if (node.children.get(0).value.equals("int"))
            return "i32";
        else if (node.children.get(0).value.equals("void"))
            return "void";
        else
            throw new java.lang.Error("unknown func type");
    }

    static void CodeFuncDef(AstNode parent) {
        isReturn = false;
        blockIndex = 0;
        regIndex = 0;

        HashMap<String, Ident> newMap = new HashMap<>();
        IdentMapList.addMap( "",newMap);
        String type = CodeBType(parent.children.get(0));
        String ident = parent.children.get(1).value;
        ArrayList<String> params = new ArrayList<>();

        outStr.append("define dso_local ").append(type).append(" @").append(ident).append("(");
        ValueType funcValType = (type.equals("i32")? ValueType.INT : ValueType.VOID);

        isDefGlobal = true;
        if (parent.children.size() == 6) {
            params = CodeFuncFParams(parent.children.get(3));
        }
        isDefGlobal = false;

        Ident newFunc = new Ident(ident, IdentType.FUNC, funcValType, params);
        newFunc.isDeclared = true;

        IdentMapList.getGlobalMap().put(ident, newFunc);
        outStr.append("){\n");
        regIndex++;

        Set<Map.Entry<String, Ident>> entrySet = IdentMapList.getCurrentMap().entrySet();

        for (Map.Entry<String, Ident>entry : entrySet) {
            Ident sym = entry.getValue();
            if (sym.identType == IdentType.FUNC_VAR) {
                int newRegIndex = regIndex++;

                outStr.append("\t%x").append(newRegIndex).append(" = alloca i32\n");
                outStr.append("\tstore i32 %x").append(sym.regIndex).append(", i32* %x").append(newRegIndex).append("\n");

                sym.regIndex = newRegIndex;
                sym.identType = IdentType.VAR;
            }
            else if (sym.identType == IdentType.FUNC_ARRAY) {
                if(!sym.isTransformed) {
                    sym.dim++;
                    String arrayType = sym.arrayType;
                    int regArray = regIndex++;
                    outStr.append("\t%x").append(regArray).append(" = alloca ").append(arrayType).append("*\n");
                    outStr.append("\tstore ").append(arrayType).append("* %x").append(sym.regIndex).append(", ")
                            .append(arrayType).append("* * %x").append(regArray).append("\n");
                    sym.regIndex = regArray;
                    sym.isTransformed = true;
                }
            }
        }

        isFuncBlock = true;
        CodeBlock(parent.children.get(parent.children.size()-1));
        isFuncBlock = false;
        IdentMapList.removeFisrtMap();
        if(funcValType == ValueType.VOID)
            outStr.append("\tret void\n");
        outStr.append("}\n");

    }

    public static ArrayList<String> CodeFuncFParams(AstNode parent) {
        ArrayList<String> params = new ArrayList<>();

        for(AstNode child: parent.children){
            if(child.type.equals("<FuncFParam>")){
                params.add(FuncFParam(child));
            }
        }
        return params;
    }

    public static String FuncFParam(AstNode parent) {

        if(parent.children.size() == 2){
            String ident = parent.children.get(1).value;
            int regIdent = regIndex++;
            Ident newSym = new Ident(ident, IdentType.FUNC_VAR, ValueType.INT, regIdent);
            IdentMapList.getCurrentMap().put(ident, newSym);
            if(regIdent == 0)
                outStr.append("i32 %x").append(regIdent);
            else
                outStr.append(", i32 %x").append(regIdent);
            regIndex = regIdent+1;
            return "i32";

        }
        else if (parent.children.size() > 2){
            String ident = parent.children.get(1).value;
            int regIdent = regIndex++;
            int dim = 0;
            ArrayList<Integer> dimSize = new ArrayList<>();
            for(AstNode child: parent.children){
                if(child.type.equals("<ConstExp>")){
                    dim++;
                    ExpValue expValue = CodeExp(child);
                    dimSize.add(expValue.value);
                }
            }

            // get array type;
            StringBuilder arrayType = new StringBuilder();
            for(int i = 0; i < dim; i++){
                if (i == 0)
                    arrayType.append("[").append(dimSize.get(i)).append(" x");
                else
                    arrayType.append(" [").append(dimSize.get(i)).append(" x");
            }
            if(dim == 0)
                arrayType.append("i32");
            else
                arrayType.append(" i32");
            arrayType.append("]".repeat(Math.max(0, dim)));

            if(regIdent == 0)
                outStr.append(arrayType).append("* %x").append(regIdent);
            else
                outStr.append(", ").append(arrayType).append("* %x").append(regIdent);

            Ident newSym = new Ident(ident, IdentType.FUNC_ARRAY, ValueType.INT, dim, dimSize, regIdent);
            IdentMapList.getCurrentMap().put(ident, newSym);
            newSym.arrayType = arrayType.toString();
            regIndex = regIdent+1;
            return arrayType + "*";
        }
        return null;
    }

    static void CodeBlock(AstNode parent) {

        if (!isFuncBlock) {
            IdentMapList.addMap(new HashMap<>());
        }
        for (AstNode child: parent.children) {
            if (child.type != null && child.type.equals("<BlockItem>"))
                CodeBlockItem(child);
        }

        if (!isFuncBlock) {
            IdentMapList.removeFisrtMap();
        }

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
                            outStr.append("\t%x").append(registerNew).append(" = getelementptr ").append(arraySym.arrayType).append(", ").append(arraySym.arrayType).append("* ").append(arraySym.out()).append(", i32 0");
                            for(int i = 0; i < dim; i++){
                                outStr.append(", i32 ").append(arrayInfo.get(i));
                            }
                            outStr.append("\n");

                            ExpValue expValue = CodeInitVal(child);
                            assert expValue != null;
                            outStr.append("\tstore i32 %x").append(expValue.register).append(", i32* %x").append(registerNew).append("\n");
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
                    outStr.append(" ").append(exp.value);
                return exp;
            }
            else {
                Ident arraySym = parent.arraySym;
                int pos = 0, dep = parent.dep;
                int thisDimSize = arraySym.dimSize.get(dep);
                int dimSum = arraySym.dim;
                StringBuilder arrayType = new StringBuilder();
                for(int i = dep + 1; i < dimSum; i++){
                    arrayType.append(" [").append(arraySym.dimSize.get(i)).append(" x");
                }
                arrayType.append(" i32");
                arrayType.append("]".repeat(Math.max(0, dimSum - (dep + 1))));
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
                    if(!arrayType.toString().equals(" i32"))
                        outStr.append(arrayType).append(" zeroinitializer");
                    else
                        outStr.append(arrayType).append(" ").append(0);
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
            outStr.append("\t%x").append(reg).append(" = icmp ne i32 ").append(value.out()).append(", 0\n");
            return reg;
        }
        else {
            return value.register;
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

        params = new ArrayList<>();
        params.add("i32*");
        globalMap.put("getarray", new Ident("getarray", IdentType.FUNC, ValueType.INT, params));

        params = new ArrayList<>();
        params.add("i32");
        params.add("i32*");
        globalMap.put("putarray", new Ident("putarray", IdentType.FUNC, ValueType.VOID, params));

        IdentMapList.addMap(globalMap);
    }
}
