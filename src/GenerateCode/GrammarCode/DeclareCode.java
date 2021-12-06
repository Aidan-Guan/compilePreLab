package GenerateCode.GrammarCode;

import AST.AstNode;
import ErrorSolution.ErrorSolu;
import GenerateCode.ExpressionCode.CondExpCode;
import GrammarAnal.Expression.ExpValue;
import TokenUtils.Ident;
import TokenUtils.IdentMapList;
import TokenUtils.IdentType;
import TokenUtils.ValueType;

import java.util.ArrayList;
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

    public static void CodeConstDef(AstNode parent){
        String identName = parent.children.get(0).value;
//        if(!isInTable(Ident));
//        SymbolTable table = tables.size() == 0 ? globalTable : tables.get(tables.size()-1);

        if(isDefGlobal){
            HashMap<String, Ident> globalMap = IdentMapList.getGlobalMap();

            if(parent.children.size() == 1 || parent.children.get(1).value.equals("=")){
                Ident newSym = new Ident(identName, IdentType.GLOBAL_CONST, ValueType.INT, regIndex++);

                int value = 0;
                if(parent.children.size() > 1) {
                    isArray = false;
                    value = CodeConstInitVal(parent.children.get(2)).value;
                    isArray = true;
                }
                outStr.append("@" + identName + " = dso_local global i32 " + value + "\n");
                newSym.value = value;
                globalMap.put(identName, newSym);
                return;
            }
            else if(parent.children.get(1).value.equals("[")){
                int dim = 0;
                boolean isAssign = false;
                ArrayList<Integer> dimSize = new ArrayList<>();

                for(AstNode child : parent.children){
                    if (child.type.equals("<ConstExp>")) {
                        ExpValue newValue = CodeConstExp(child);
                        isDefConst = true;
                        dimSize.add(newValue.value);
                    }
                    else if (child.value.equals("["))  dim++;
                    else if (child.value.equals("="))  isAssign = true;
                }

                Ident newSym = new Ident(identName, IdentType.GLOBAL_ARRAY_CONST, ValueType.INT, dim, dimSize, regIndex++);

                globalMap.put(identName, newSym);
                String arrayType = "";
                for(int i = 0; i < dim; i++){
                    arrayType += " [" + dimSize.get(i) + " x";
                }

                arrayType += " i32";
                for (int i = 0; i < dim; i++){
                    arrayType += "]";
                }
                newSym.arrayType = arrayType;

                outStr.append("@" + identName + " = dso_local constant" + arrayType + " ");
                if (!isAssign){
                    outStr.append("zeroinitializer\n");
                }
                else {
                    for(AstNode child: parent.children){
                        if(child.type.equals("<ConstInitVal>")){
                            child.arraySym = newSym;
                            CodeConstInitVal(child);
                            outStr.append("\n");
                        }
                    }
                }
            }
            return;
        }

        HashMap<String, Ident> identMap = IdentMapList.getCurrentMap();

        if(parent.children.size() == 1 || parent.children.get(1).type.equals("ASSIGN")){
            Ident newSym = new Ident(identName, IdentType.CONST, ValueType.INT, regIndex++);
            identMap.put(identName, newSym);

            outStr.append("\t" + newSym.out() + " = alloca i32\n");

            isDefConst = true;
            if(parent.children.size() > 1){
                isArray = false;
                ExpValue newVal = CodeConstInitVal(parent.children.get(2));
                isArray = true;
                int registerComing = newVal.register;
                newSym.value = newVal.value;
                outStr.append("\tstore i32 " + "%x" + registerComing + ", i32* " + newSym.out() + "\n");
            }
            isDefConst = false;
        }
        else if(parent.children.get(1).value.equals("[")){
            int dim = 0;
            boolean isAssign = false;
            ArrayList<Integer> dimSize = new ArrayList<>();

            for(AstNode child : parent.children){
                if (child.type.equals("<ConstExp>")) {
                    ExpValue newVal = CodeConstExp(child);
                    dimSize.add(newVal.value);
                }
                else if (child.value.equals("[")) {
                    dim++;
                }
                else if (child.value.equals("=")) {
                    isAssign = true;
                }
            }

            Ident newSym = new Ident(identName, IdentType.ARRAY_CONST, ValueType.INT, dim, dimSize, regIndex++);
            identMap.put(identName, newSym);

            outStr.append("\t" + newSym.out() + " = alloca");

            String arrayType = "";
            for(int i = 0; i < dim; i++){
                outStr.append(" [" + dimSize.get(i) + " x");
                arrayType += " [" + dimSize.get(i) + " x";
            }

            outStr.append(" i32");
            arrayType += " i32";
            for (int i = 0; i < dim; i++){
                outStr.append("]");
                arrayType += "]";
            }
            outStr.append("\n");
            newSym.arrayType = arrayType;

            int arraySize = 1;
            for(int i = 0; i < dim; i++){
                arraySize *= dimSize.get(i);
            }

            // 对数组进行赋值
            if(isAssign){
                for(AstNode child : parent.children){
                    if(child.type.equals("<ConstInitVal>")){
                        child.arraySym = newSym;
                        child.dep = 0;
                        isDefConst = true;
                        CodeConstInitVal(child);
                        isDefConst = false;
                    }
                }
            }
        }

    }

//    static void CodeConstDef (AstNode parent) {
//        String identName = parent.children.get(0).value;
//
//        if (isDefGlobal) {
//            Ident ident = new Ident(identName, IdentType.GLOBAL_CONST, ValueType.INT, regIndex++);
//            HashMap<String, Ident> globalMap = IdentMapList.getGlobalMap();
//
//            int value = 0;
//            if (parent.children.size()>1) {
//                value = CodeConstInitVal(parent.children.get(2)).value;
//            }
//
//            outStr.append( "@" + identName + " = dso_local global i32 " + value + "\n");
//            ident.value = value;
//
//            globalMap.put(identName, ident);
//        }
//        else {
//            Ident ident = new Ident(identName, IdentType.CONST, ValueType.INT, regIndex++);
//            HashMap<String, Ident> currMap = IdentMapList.getCurrentMap();
//
//            outStr.append("\t" + ident.out() + " = alloca i32\n");
//            isDefConst = true;
//            if (parent.children.size() > 1) {
//                ExpValue value = CodeConstInitVal(parent.children.get(2));
//                outStr.append("\tstore i32 " + value.out() + ", i32* " + ident.out() + "\n");
//            }
//            isDefConst = false;
//
//            currMap.put(identName, ident);
//        }
//    }

    static ExpValue CodeConstInitVal (AstNode parent) {
        return CodeExp(parent.children.get(0));
    }

}
