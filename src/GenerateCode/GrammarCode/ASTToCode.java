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

public class ASTToCode {
    public static AstNode root;
    public static int regIndex = 0;

    public static boolean isDefConst = false;
    public static boolean isDefGlobal = false;
    public static StringBuilder outStr = new StringBuilder();


    public static void generateIntermediateCode (AstNode ASTroot) {
        root = ASTroot;
        identMapInit();

        CodeCompUnit(root);
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



    static ExpValue CodeInitVal (AstNode parent) {
        return CodeExp(parent.children.get(0));
    }


    private static void identMapInit () {
        HashMap<String, Ident> globalMap = new HashMap<>();

        ArrayList<String> params = new ArrayList<>();
        params.add("i32");

        globalMap.put("getint", new Ident("getint", IdentType.FUNC, ValueType.INT, new ArrayList<>()));
        globalMap.put("getch", new Ident("getint", IdentType.FUNC, ValueType.INT, new ArrayList<>()));
        globalMap.put("putint", new Ident("getint", IdentType.FUNC, ValueType.INT, params));
        globalMap.put("putch", new Ident("getint", IdentType.FUNC, ValueType.INT, params));
    }
}
