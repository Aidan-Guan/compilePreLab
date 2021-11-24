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

public class ASTToCode {
    public static AstNode root;
    public static int regIndex = 0;

    static boolean isDefConst = false;
    static boolean isDefGlobal = false;
    public static StringBuilder outStr = new StringBuilder();


    public static void generateIntermediateCode (AstNode ASTroot) {
        root = ASTroot;
        identMapInit();

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

    static void CodeVarDecl(AstNode parent) {
        for (AstNode child: parent.children) {
            if (child.type.equals("<VarDef>")) {
                CodeVarDef(child);
            }
        }
    }

    static void CodeVarDef(AstNode parent) {
        String identName = parent.children.get(0).value;

        if (isDefGlobal) {
            HashMap<String, Ident> globalMap = IdentMapList.getGlobalMap();
            Ident ident = new Ident(identName, IdentType.GLOBAL_VAR, ValueType.INT, regIndex++);

            int identValue = 0;
            if (parent.children.size() > 1) {
                identValue = CodeInitVal();
            }

            outStr.append("@" + identName + " = dso_local global i32 " + identValue + "\n");
            ident.value = identValue;
            globalMap.put(identName, ident);
            return;
        }
        else {
            HashMap<String, Ident> identMap = IdentMapList.getCurrentMap();
            if (IdentMapList.getIdentInCurrMap(identName)!=null) ErrorSolu.error();


            Ident ident = new Ident(identName, IdentType.VAR, ValueType.INT, regIndex++);
            outStr.append("\t" + ident.out() + " = alloca i32\n");

            if (parent.children.size()>1) {
                ExpValue value1 = CodeInitVal();
                ident.value = value1.value;

                outStr.append("\tstore i32 " + value1.out() + ", i32* " + ident.out() + "\n");
            }

            identMap.put(identName, ident);
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
