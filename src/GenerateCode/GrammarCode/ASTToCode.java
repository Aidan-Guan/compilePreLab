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
    public static int blockIndex = 0;

    public static boolean isBreak = false;
    public static boolean isContinue = false;
    public static boolean isReturn = false;
    public static boolean isDefConst = false;
    public static boolean isDefGlobal = false;
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
            if (isBreak) { return; }

//            if (isContinue) { isContinue = false; }


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


    static ExpValue CodeInitVal (AstNode parent) {
        return CodeExp(parent.children.get(0));
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
        globalMap.put("putint", new Ident("putint", IdentType.FUNC, ValueType.INT, params));
        globalMap.put("putch", new Ident("putch", IdentType.FUNC, ValueType.INT, params));

        IdentMapList.addMap(globalMap);
    }
}
