package GenerateCode.GrammarCode;

import AST.AstNode;
import ErrorSolution.ErrorSolu;
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
<<<<<<< HEAD
=======

        // global
>>>>>>> all-arrs
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

                StringBuilder arrayType = new StringBuilder();
                for(int i = 0; i < dim; i++){
                    arrayType.append(" [").append(dimSize.get(i)).append(" x");
                }
                arrayType.append(" i32");
                arrayType.append("]".repeat(Math.max(0, dim)));
                newSym.arrayType = arrayType.toString();

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
            outStr.append("\t").append(newSym.out()).append(" = alloca i32\n");

            if(parent.children.size() > 1){
                ExpValue newVal = CodeInitVal(parent.children.get(2));
<<<<<<< HEAD
=======
                assert newVal != null;
>>>>>>> all-arrs
                int registerComing = newVal.register;
                newSym.value = newVal.value;
                outStr.append("\tstore i32 " + "%x").append(registerComing).append(", i32* ").append(newSym.out()).append("\n");
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
            outStr.append("\t").append(newSym.out()).append(" = alloca");
            StringBuilder arrayType = new StringBuilder();
            for(int i = 0; i < dim; i++){
                if (dimSize.get(i)<0) {
                    ErrorSolu.error();
                }
                outStr.append(" [").append(dimSize.get(i)).append(" x");
                arrayType.append(" [").append(dimSize.get(i)).append(" x");
            }
            outStr.append(" i32");
            arrayType.append(" i32");

            for (int i = 0; i < dim; i++){
                outStr.append("]");
                arrayType.append("]");
            }
            outStr.append("\n");
            newSym.arrayType = arrayType.toString();

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
        if(isDefGlobal){
            HashMap<String, Ident> globalMap = IdentMapList.getGlobalMap();

            if(parent.children.size() == 1 || parent.children.get(1).value.equals("=")){
                Ident newSym = new Ident(identName, IdentType.GLOBAL_CONST, ValueType.INT, regIndex++);

                int value = 0;
                if(parent.children.size() > 1) {
                    isArray = false;
                    value = Objects.requireNonNull(CodeConstInitVal(parent.children.get(2))).value;
                    isArray = true;
                }
                outStr.append("@").append(identName).append(" = dso_local global i32 ").append(value).append("\n");
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
                StringBuilder arrayType = new StringBuilder();
                for(int i = 0; i < dim; i++){
                    arrayType.append(" [").append(dimSize.get(i)).append(" x");
                }

                arrayType.append(" i32");
                arrayType.append("]".repeat(Math.max(0, dim)));
                newSym.arrayType = arrayType.toString();

                outStr.append("@").append(identName).append(" = dso_local constant").append(arrayType).append(" ");
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

        if(parent.children.size() == 1 || parent.children.get(1).value.equals("=")){
            Ident newSym = new Ident(identName, IdentType.CONST, ValueType.INT, regIndex++);
            identMap.put(identName, newSym);

            outStr.append("\t").append(newSym.out()).append(" = alloca i32\n");

            isDefConst = true;
            if(parent.children.size() > 1){
                isArray = false;
                ExpValue newVal = CodeConstInitVal(parent.children.get(2));
                isArray = true;
                assert newVal != null;
                int registerComing = newVal.register;
                newSym.value = newVal.value;
                outStr.append("\tstore i32 " + "%x").append(registerComing).append(", i32* ").append(newSym.out()).append("\n");
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
                else if (child.value!=null && child.value.equals("[")) {
                    dim++;
                }
                else if (child.value!=null && child.value.equals("=")) {
                    isAssign = true;
                }
            }

            Ident newSym = new Ident(identName, IdentType.ARRAY_CONST, ValueType.INT, dim, dimSize, regIndex++);
            identMap.put(identName, newSym);

            outStr.append("\t").append(newSym.out()).append(" = alloca");

            StringBuilder arrayType = new StringBuilder();
            for(int i = 0; i < dim; i++){
                outStr.append(" [").append(dimSize.get(i)).append(" x");
                arrayType.append(" [").append(dimSize.get(i)).append(" x");
            }

            outStr.append(" i32");
            arrayType.append(" i32");
            for (int i = 0; i < dim; i++){
                outStr.append("]");
                arrayType.append("]");
            }
            outStr.append("\n");
            newSym.arrayType = arrayType.toString();


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


    public static ExpValue CodeConstInitVal(AstNode parent){
        if (!isDefGlobal) {
            if(parent.children.size() == 1){
                return CodeConstExp(parent.children.get(0));
            }
            else {
                int pos = 0;
                int dep = parent.dep;
                ArrayList <Integer> arrayInfo = parent.arrayInfo;
                for(AstNode child : parent.children){

                    if (child.type.equals("<ConstInitVal>")) {
                        child.arraySym = parent.arraySym;
                        if (child.children.size() > 1){
                            arrayInfo.set(parent.dep, pos);
                            child.arrayInfo = arrayInfo;
                            child.dep = parent.dep+1;
                            CodeConstInitVal(child);
                        }
                        else {
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

                            ExpValue expValue = CodeConstInitVal(child);
                            assert expValue != null;
                            outStr.append("\tstore i32 %x").append(expValue.register).append(", i32* %x").append(registerNew).append("\n");
                        }

                    }
                    else if (child.value.equals("{")) {
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
            if(parent.children.size() == 1){
                ExpValue exp = CodeExp(parent.children.get(0));
                if (isArray)
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
                    if(child.type.equals("<ConstInitVal>")){
                        child.arraySym = parent.arraySym;
                        child.dep = parent.dep+1;
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



}
