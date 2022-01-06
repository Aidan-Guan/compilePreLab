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
import java.util.Objects;

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

    static void CodeVarDef(AstNode parent){
        String Ident = parent.children.get(0).value;
        if(isDefGlobal){
            HashMap<String, Ident> globalMap = IdentMapList.getGlobalMap();
            if(parent.children.size() == 1 || parent.children.get(1).value.equals("=")){
                Ident newSym = new Ident(Ident, IdentType.GLOBAL_VAR, ValueType.INT, regIndex++);
                globalMap.put(Ident, newSym);
                int value = 0;

                if(parent.children.size() > 1) {
                    isArray = false;
                    value = Objects.requireNonNull(CodeInitVal(parent.children.get(2))).value;
                    isArray = true;
                }
                outStr.append("@").append(Ident).append(" = dso_local global i32 ").append(value).append("\n");
                newSym.value = value;
            }
            else if (parent.children.get(1).value.equals("[")){
                int dim = 0;
                boolean isAssign = false;
                ArrayList<Integer> dimSize = new ArrayList<>();
                for(AstNode child : parent.children){

                    if (child.type.equals("<ConstExp>")) {
                        ExpValue newVal = CodeConstExp(child);
                        dimSize.add(newVal.value);
                    }
                    else if (child.value!=null && child.value.equals("[")) {
                        dim++;
                    }
                    else if (child.value!=null && child.value.equals("=")) {
                        isAssign = true;
                    }
                }
                Ident newSym = new Ident(Ident, IdentType.GLOBAL_ARRAY_VAR, ValueType.INT, dim, dimSize, regIndex++);
                globalMap.put(Ident, newSym);

                String arrayType = "";
                for(int i = 0; i < dim; i++){
                    arrayType += " [" + dimSize.get(i) + " x";
                }
                arrayType += " i32";
                for (int i = 0; i < dim; i++){
                    arrayType += "]";
                }
                newSym.arrayType = arrayType;

                outStr.append("@").append(Ident).append(" = dso_local global").append(arrayType).append(" ");
                if (!isAssign){
                    outStr.append("zeroinitializer\n");
                }
                else {
                    for(AstNode child: parent.children){
                        if(child.type != null && child.type.equals("<InitVal>")){
                            child.arraySym = newSym;
                            CodeInitVal(child);
                            outStr.append("\n");
                        }
                    }
                }
            }
            return;
        }

        HashMap<String, Ident> currentMap = IdentMapList.getCurrentMap();
        if(parent.children.size() == 1 || parent.children.get(1).value.equals("=")){
            Ident newSym = new Ident(Ident, IdentType.VAR, ValueType.INT, regIndex++);
            currentMap.put(Ident, newSym);
            outStr.append("\t" + newSym.out() + " = alloca i32\n");

            if(parent.children.size() > 1){
                ExpValue newVal = CodeInitVal(parent.children.get(2));
                int registerComing = newVal.register;
                newSym.value = newVal.value;
                outStr.append("\tstore i32 " + "%x" + registerComing + ", i32* " + newSym.out() + "\n");
            }
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
                else if (child.value!=null && child.value.equals("[")) {
                    dim++;
                }
                else if (child.value!=null && child.value.equals("=")) {
                    isAssign = true;
                }
            }

            Ident newSym = new Ident(Ident, IdentType.ARRAY_VAR, ValueType.INT, dim, dimSize, regIndex++);
            newSym.dimElse = newSym.dimSize;

            currentMap.put(Ident, newSym);
            outStr.append("\t" + newSym.out() + " = alloca");
            String arrayType = "";
            for(int i = 0; i < dim; i++){
                if (dimSize.get(i)<0) {
                    ErrorSolu.error();
                }
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

            // 对数组进行赋值
            if(isAssign){
                for(AstNode child : parent.children){
                    if(child.type.equals("<InitVal>")){
                        child.arraySym = newSym;
                        child.dep = 0;
                        CodeInitVal(child);
                    }
                }
            }
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
                    else if (child.value!=null && child.value.equals("["))  dim++;
                    else if (child.value!=null && child.value.equals("="))  isAssign = true;
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


    public static ExpValue CodeConstInitVal(AstNode node){
        if (!isDefGlobal) {
            if(node.children.size() == 1){
                return CodeConstExp(node.children.get(0));
            }
            else {
                int pos = 0;
                int dep = node.dep;
                ArrayList <Integer> arrayInfo = node.arrayInfo;
                for(AstNode child : node.children){

                    if (child.type.equals("<ConstInitVal>")) {
                        child.arraySym = node.arraySym;
                        if (child.children.size() > 1){
                            arrayInfo.set(node.dep, pos);
                            child.arrayInfo = arrayInfo;
                            child.dep = node.dep+1;
                            CodeConstInitVal(child);
                        }
                        else {
                            arrayInfo.set(arrayInfo.size() - 1, pos);
                            child.arrayInfo = arrayInfo;
                            child.dep = node.dep+1;
                            int dim = node.arraySym.dim;
                            int registerNew = regIndex++;
                            Ident arraySym = node.arraySym;
                            outStr.append("\t%x" +registerNew + " = getelementptr " + arraySym.arrayType + ", " + arraySym.arrayType + "* " + arraySym.out() + ", i32 0");
                            for(int i = 0; i < dim; i++){
                                outStr.append(", i32 " + arrayInfo.get(i));
                            }
                            outStr.append("\n");

                            ExpValue expValue = CodeConstInitVal(child);
                            outStr.append("\tstore i32 %x" + expValue.register + ", i32* %x" + registerNew + "\n");
                        }

                    }
                    else if (child.value.equals("[")) {
                        dep++;
                        if(arrayInfo.size() < dep)
                            arrayInfo.add(-1);
                    }
                    else if (child.value.equals(",")) {
                        pos += 1;
                    }
                }
            }

        }
        else {
            if(node.children.size() == 1){
                ExpValue exp = CodeExp(node.children.get(0));
                if (isArray)
                    outStr.append(" " +  exp.value);
                return exp;
            }
            else {
                Ident arraySym = node.arraySym;
                int pos = 0, dep = node.dep;
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

                for(AstNode child : node.children){
                    if(child.type.equals("<ConstInitVal>")){
                        child.arraySym = node.arraySym;
                        child.dep = node.dep+1;
                        if(pos != 0)
                            outStr.append(",");
                        outStr.append(arrayType);
                        CodeConstInitVal(child);
                        thisDimSize--;
                    }
                    else if(child.value.equals("[")){
                        dep++;
                    }
                    else if(child.value.equals(",")){
                        pos += 1;
                    }
                }
                while(thisDimSize > 0){
                    if(pos != 0){
                        outStr.append(",");
                    }
                    if(!arrayType.equals(" i32"))
                        outStr.append(arrayType + " zeroinitializer");
                    else
                        outStr.append( arrayType + " " + 0);
                    thisDimSize--;
                    pos++;
                }
                outStr.append("]");
            }
        }
        return null;
    }


//    static ExpValue CodeConstInitVal (AstNode parent) {
//        return CodeExp(parent.children.get(0));
//    }

}
