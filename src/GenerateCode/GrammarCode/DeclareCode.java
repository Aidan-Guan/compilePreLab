package GenerateCode.GrammarCode;

import AST.AstNode;
import ErrorSolution.ErrorSolu;
import GrammarAnal.Expression.ExpValue;
import TokenUtils.Ident;
import TokenUtils.IdentMapList;
import TokenUtils.IdentType;
import TokenUtils.ValueType;

import java.util.HashMap;

import static GenerateCode.ExpressionCode.CalculateExpCode.*;
import static GenerateCode.GrammarCode.ASTToCode.*;

public class DeclareCode {

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
                identValue = CodeInitVal(parent.children.get(2)).value;
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
                ExpValue value1 = CodeInitVal(parent.children.get(2));
                ident.value = value1.value;

                outStr.append("\tstore i32 " + value1.out() + ", i32* " + ident.out() + "\n");
            }

            identMap.put(identName, ident);
        }
    }


    static void CodeConstDecl (AstNode parent) {
        for (AstNode child: parent.children) {
            if (child.type.equals("<ConstDef>")) {
                CodeConstDef(child);
            }
        }
    }

    static void CodeConstDef (AstNode parent) {
        String identName = parent.children.get(0).value;

        if (isDefGlobal) {
            Ident ident = new Ident(identName, IdentType.GLOBAL_CONST, ValueType.INT, regIndex++);
            HashMap<String, Ident> globalMap = IdentMapList.getGlobalMap();

            int value = 0;
            if (parent.children.size()>1) {
                value = CodeConstInitVal(parent.children.get(2)).value;
            }

            outStr.append( "@" + identName + " = dso_local global i32 " + value + "\n");
            ident.value = value;

            globalMap.put(identName, ident);
        }
        else {
            Ident ident = new Ident(identName, IdentType.CONST, ValueType.INT, regIndex++);
            HashMap<String, Ident> currMap = IdentMapList.getCurrentMap();

            outStr.append("\t" + ident.out() + " = alloca i32\n");
            isDefConst = true;
            if (parent.children.size() > 1) {
                ExpValue value = CodeConstInitVal(parent.children.get(2));
                outStr.append("\tstore i32 " + value.out() + ", i32* " + ident.out() + "\n");
            }
            isDefConst = false;
        }
    }

    static ExpValue CodeConstInitVal (AstNode parent) {
        return CodeExp(parent.children.get(0));
    }

}
