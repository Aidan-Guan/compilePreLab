package GenerateCode.ExpressionCode;

import AST.AstNode;
import ErrorSolution.ErrorSolu;
import GrammarAnal.Expression.*;
import TokenUtils.Ident;
import TokenUtils.IdentMapList;
import TokenUtils.IdentType;
import TokenUtils.ValueType;

import java.util.ArrayList;
import java.util.Objects;

import static GenerateCode.GrammarCode.ASTToCode.*;

public class CalculateExpCode {
    public static boolean isFuncParam = false;
    public static boolean isFuncParamArray = false;

    public static ExpValue CodeExp(AstNode parent) {
        return CodeAddExp(parent.children.get(0));
    }


    public static ExpValue CodeConstExp (AstNode parent) {
        isDefConst = true;
        ExpValue value = CodeAddExp(parent.children.get(0));
        isDefConst = false;
        return value;
    }

    public static ExpValue CodeAddExp(AstNode parent) {
        int registerPre = -1;
        int valuePre = 0;
        String op = "";
        boolean flg = true;

        for(AstNode child: parent.children){
            if (child.type.equals("<MulExp>")) {
                ExpValue value = CodeMulExp(child);
                if(value == null) return null;
                int reg = value.register;

                if (flg) {
                    registerPre = reg;
                    valuePre = value.value;
                    flg = false;
                }

                if (op.equals("+")) {
                    int newReg = regIndex++;
                    if(!isDefGlobal)
                        outStr.append("\t%x" + newReg + " = add i32 %x" + registerPre + ", %x" + reg + "\n");
                    registerPre = newReg;
                    valuePre = valuePre + value.value;
                }
                else if (op.equals("-")) {
                    int newReg = regIndex++;
                    if(!isDefGlobal)
                        outStr.append("\t%x" + newReg + " = sub i32 %x" + registerPre + ", %x" + reg + "\n");
                    registerPre = newReg;
                    valuePre = valuePre - value.value;
                }
            }
            else if (child.value.equals("+") || child.value.equals("-")) {
                op = child.value;
            }
        }
        return new ExpValue(registerPre, "i32", valuePre);
    }


    static ExpValue CodeMulExp(AstNode parent) {
        int registerPre = -1;
        int valuePre = 0;
        String op = "";
        boolean flag = true;
        for(AstNode child: parent.children){
            if (child.type.equals("<UnaryExp>")) {
                ExpValue registerValue = CodeUnaryExp(child);
                if(registerValue == null) return null;
                int registerNow = registerValue.register;
                if (flag) {
                    registerPre = registerNow;
                    valuePre = registerValue.value;
                    flag = false;
                }
                switch (op) {
                    case "*" -> {
                        int newReg = regIndex++;
                        if(!isDefGlobal)
                            outStr.append("\t%x" + newReg + " = mul i32 %x" + registerPre + ", %x" + registerNow + "\n");
                        registerPre = newReg;
                        valuePre = valuePre * registerValue.value;
                    }
                    case "/" -> {
                        int newReg = regIndex++;
                        if(!isDefGlobal)
                            outStr.append("\t%x" + newReg + " = sdiv i32 %x" + registerPre + ", %x" + registerNow + "\n");
                        registerPre = newReg;
                        if(registerValue.value != 0)
                            valuePre = valuePre / registerValue.value;
                    }
                    case "%" -> {
                        int newReg = regIndex++;
                        if(!isDefGlobal)
                            outStr.append("\t%x" + newReg + " = srem i32 %x" + registerPre + ", %x" + registerNow + "\n");
                        registerPre = newReg;
                        if(registerValue.value != 0)
                            valuePre = valuePre % registerValue.value;
                    }
                }
            }
            else if (child.value.equals("*") || child.value.equals("/") || child.value.equals("%")) {
                op = child.value;
            }
        }
        return new ExpValue(registerPre, "i32", valuePre);
    }


    static ExpValue CodeUnaryExp (AstNode parent) {
        String type = parent.children.get(0).type;

        if (type.equals("<PrimaryExp>")) {
            return CodePrimaryExp(parent.children.get(0));
        }
        else if (type.equals("<UnaryOp>")) {
            String op = CodeUnaryOp(parent.children.get(0));
            switch (op) {
                case "-" -> {
                    ExpValue value1 = CodeUnaryExp(parent.children.get(1));
                    int regBefore = value1.register;
                    int regNew = regIndex++;
                    int value = value1.value;
                    if(!isDefGlobal)
                        outStr.append("\t%x").append(regNew).append(" = sub i32 0, %x").append(regBefore).append("\n");
                    return new ExpValue(regNew, "i32", 0-value);
                }
                case "+" -> {
                    return CodeUnaryExp(parent.children.get(1));
                }
                case "!" -> {
                    ExpValue value1 = CodeUnaryExp(parent.children.get(1));
                    outStr.append("\t%x").append(regIndex).append(" = icmp eq i32 ").append(value1.out()).append(", 0\n");
                    regIndex++;
                    outStr.append("\t%x").append(regIndex).append(" = zext i1 %x").append(regIndex - 1).append(" to i32\n");
                    regIndex++;
                    return new ExpValue(regIndex-1, "i32");
                }
            }
        }
        else if (parent.children.get(1).value.equals("(")) {
            if (isDefConst) { ErrorSolu.error(); }

            String identName = parent.children.get(0).value;
            Ident ident = IdentMapList.getIdentInAllMap(identName);

            if (ident == null) ErrorSolu.error();

            ArrayList<String> params = new ArrayList<>();
            ArrayList<String> paramsType = ident.funcParams;

            if (parent.children.get(2).type.equals("<FuncRParams>")) {
                params = FuncRParams(parent.children.get(2), paramsType);
            }

            String out = "";
            String outDecl = "";

            for (int i=0; i<params.size(); i++) {
                if (i == 0) {
                    out += paramsType.get(i) + " " + params.get(i);
                    outDecl += paramsType.get(i);
                }
                else {
                    out += ", " + paramsType.get(i) + " " + params.get(i);
                    outDecl += "," + paramsType.get(i);
                }
            }

            if (ident.valueType == ValueType.INT) {

                int regNew = regIndex++;
                // 函数声明
                if(!ident.isDeclared){
                    outStr.insert (0, "declare i32 @" + ident.name + "(" + outDecl + ")\n");
                    ident.isDeclared = true;
                }
                // 函数调用
                outStr.append("\t%x").append(regNew).append(" = call i32 @").append(ident.name).append("(").append(out).append(")\n");
                return new ExpValue(regNew, "i32");
            }
            else if (ident.valueType == ValueType.VOID) {
                if (!ident.isDeclared) {
                    outStr.insert(0, "declare void @" + ident.name + "(" + outDecl + ")\n");
                    ident.isDeclared = true;
                }
                outStr.append("\tcall void @").append(ident.name).append("(").append(out).append(")\n");
            }
        }
        return null;
    }

//    static ArrayList<String> FuncRParams(AstNode parent) {
//        ArrayList<String> params = new ArrayList<>();
//
//        for (AstNode child: parent.children) {
//            if (child.type.equals("<Exp>")) {
//                isFuncParam = true;
//                params.add(CodeExp(child).out());
//                isFuncParam = false;
//
//            }
//        }
//        return params;
//    }

    public static ArrayList<String> FuncRParams(AstNode node, ArrayList<String>paramType){
        ArrayList<String> params = new ArrayList<>();
        int i = 0;
        for(AstNode child: node.children){
            if(child.type.equals("<Exp>")) {
                if(!paramType.get(i++).equals("i32"))
                    isFuncParamArray = true;
                params.add("%x" + CodeExp(child).register);
                if(isFuncParamArray)
                    isFuncParamArray = false;
            }
        }
        return params;
    }

    static String CodeUnaryOp(AstNode parent) {
        return parent.children.get(0).value;
    }

    static ExpValue CodePrimaryExp(AstNode parent) {
        AstNode firstChild = parent.children.get(0);

        if (firstChild.type.equals("NUMBER")) {
            return CodeNumber(parent.children.get(0));
        }
        else if (firstChild.type.equals("<LVal>")) {

            ExpValue expValue = CodeLVal(parent.children.get(0));
            String regBefore;
            if(expValue.type.equals("ident"))
                regBefore = expValue.registerString;
            else
                regBefore = "%x" + expValue.register;
            String ident = parent.children.get(0).children.get(0).value;

            int regNew = expValue.register;
            if(!isDefGlobal && !(expValue.valueType != null && expValue.valueType.equals("ptr")) && !isFuncParamArray) {
                regNew = regIndex++;
                outStr.append("\t%x").append(regNew).append(" = load i32, i32* ").append(regBefore).append("\n");
            }
            return new ExpValue(regNew, "i32", Objects.requireNonNull(IdentMapList.getIdentInAllMap(ident)).value);


        }
        else if (firstChild.value.equals("(")) {
            return CodeExp(parent.children.get(1));
        }

        return null;
    }



    public static ExpValue CodeLVal(AstNode parent){
        if(parent.children.size() == 1){
            String identName = parent.children.get(0).value;
            Ident ident = IdentMapList.getIdentInAllMap(identName);
            ExpValue expValue;
            assert ident != null;
            if(isArray(ident)){
                int regNew = regIndex++;
                String arrayType = ident.arrayType;
                if(ident.identType == IdentType.FUNC_ARRAY){
                    outStr.append("\t%x").append(regNew).append(" = load i32*, i32* * %x").append(ident.regIndex).append("\n");
                    expValue = new ExpValue(regNew, "ptr");
                    expValue.type = "array";
                }
                else {
                    if (ident.identType == IdentType.GLOBAL_ARRAY_CONST || ident.identType == IdentType.GLOBAL_ARRAY_VAR){
                        String regString = "@" + ident.name;
                        outStr.append("\t%x").append(regNew).append(" = getelementptr ").append(arrayType).append(", ").append(arrayType).append("*").append(regString).append(", i32 0, i32 0\n");
                    }
                    else {
                        outStr.append("\t%x").append(regNew).append(" = getelementptr ").append(arrayType).append(", ").append(arrayType).append("* %x").append(ident.regIndex).append(", i32 0, i32 0\n");
                    }
                    expValue = new ExpValue(regNew, "ptr");
                    expValue.type = "array";

                }

                return expValue;
            }
            else{
                if(isConst(identName)) isDefConst = true;
                if(isDefConst && !isConst(identName)){
                    ErrorSolu.error();
                }
                return new ExpValue(getSymReg(identName));
            }
        }
        else {
            String ident = parent.children.get(0).value;
            ArrayList <Integer> arrayParam = new ArrayList<>();
            for(AstNode child : parent.children){
                if(child.type.equals("<Exp>")){
                    arrayParam.add(CodeExp(child).register);
                }
            }
            Ident arraySym = IdentMapList.getIdentInAllMap(ident);

            assert arraySym != null;
            if(arraySym.identType == IdentType.FUNC_ARRAY){

                if(!arraySym.isTransformed) {
                    arraySym.dim++;
                    String arrayType = arraySym.arrayType;
                    int regArray = regIndex++;
                    outStr.append("\t%x").append(regArray).append(" = alloca ").append(arrayType).append("*\n");
                    outStr.append("\tstore ").append(arrayType).append("* %x").append(arraySym.regIndex).append(", ")
                            .append(arrayType).append("* * %").append(regArray).append("\n");
                    arraySym.regIndex = regArray;
                    arraySym.isTransformed = true;
                }


                int regVal = arraySym.regIndex;
                int regNew = regIndex++;
                String arrayType = arraySym.arrayType;
                outStr.append("\t%x").append(regNew).append(" = load ").append(arrayType).append("*, ").append(arrayType).append("* * %x").append(regVal).append("\n");
                regVal = regNew;
                regNew = regIndex++;
                outStr.append("\t%x").append(regNew).append(" = getelementptr ").append(arrayType).append(", ").append(arrayType).append("* %x").append(regVal);
                for(int i = 0; i < arraySym.dim; i++){
                    outStr.append(", i32 %x").append(arrayParam.get(i));
                }
                outStr.append("\n");
                return new ExpValue(regNew);


            }

            if(isFuncParamArray){
                String registerString;
                if(arraySym.identType == IdentType.GLOBAL_ARRAY_CONST || arraySym.identType == IdentType.GLOBAL_ARRAY_VAR)
                    registerString = "@" + arraySym.name;
                else registerString = "%x"+ arraySym.regIndex;

                int registerNew = regIndex++;
                outStr.append("\t%x").append(registerNew).append(" = getelementptr ").append(arraySym.arrayType).append(", ").append(arraySym.arrayType).append("* ").append(registerString).append(", i32 0");
                for (Integer integer : arrayParam) {
                    outStr.append(", i32 %x").append(integer);
                }
                outStr.append(", i32 0\n");
                return new ExpValue(registerNew);
            }

            if(arraySym.dim != arrayParam.size()) ErrorSolu.error();
            String registerString;
            if(arraySym.identType == IdentType.GLOBAL_ARRAY_CONST || arraySym.identType == IdentType.GLOBAL_ARRAY_VAR)
                registerString = "@" + arraySym.name;
            else registerString = "%x"+arraySym.regIndex;

            int registerNew = regIndex++;
            outStr.append("\t%x").append(registerNew).append(" = getelementptr ").append(arraySym.arrayType).append(", ").append(arraySym.arrayType).append("* ").append(registerString).append(", i32 0");
            for(int i = 0; i < arraySym.dim; i++){
                outStr.append(", i32 %x").append(arrayParam.get(i));
            }
            outStr.append("\n");
            return new ExpValue(registerNew);
        }
    }

    static ExpValue CodeNumber(AstNode parent) {
        int value = Integer.parseInt(parent.value);

        if (!isDefGlobal) {
            outStr.append("\t%x" + regIndex + " = add i32 0, " + value + "\n");
            regIndex++;
        }
        return new ExpValue(regIndex-1, "i32", value);
    }

    private static boolean isConst(String identName){
        Ident ident = IdentMapList.getIdentInAllMap(identName);
        if(ident != null){
            return ident.identType == IdentType.CONST || ident.identType == IdentType.GLOBAL_CONST || ident.identType == IdentType.ARRAY_CONST || ident.identType ==IdentType.GLOBAL_ARRAY_CONST;
        }
        else {
            if (IdentMapList.getIdentInAllMap(identName)!=null) {
                return  ident.identType == IdentType.CONST || ident.identType == IdentType.GLOBAL_CONST || ident.identType == IdentType.ARRAY_CONST || ident.identType ==IdentType.GLOBAL_ARRAY_CONST;
            }
        }
        throw new java.lang.Error("symbol used before declaration");
    }


    public static String getSymReg(String identName){
        Ident ident = IdentMapList.getIdentInAllMap(identName);
        String reg = "";
        if(ident != null){
            if(ident.identType == IdentType.GLOBAL_CONST || ident.identType == IdentType.GLOBAL_VAR || ident.identType == IdentType.GLOBAL_ARRAY_CONST || ident.identType == IdentType.GLOBAL_ARRAY_VAR)
                reg =  "@" + ident.name;
            else if(ident.identType == IdentType.FUNC_VAR){
                int newReg = regIndex++;
                outStr.append("\t%x").append(newReg).append(" = alloca i32\n");
                outStr.append("\tstore i32 %x").append(ident.regIndex).append(", i32* %x").append(newReg).append("\n");
                ident.regIndex = newReg;
                ident.identType = IdentType.VAR;
                reg = "%x" + ident.regIndex;
            }
            else reg = "%x" + ident.regIndex;
        }


        if(!reg.equals(""))return reg;
        else throw new java.lang.Error("symbol used before declaration");
    }


    private static boolean isArray(Ident sym) {
        return sym.identType == IdentType.ARRAY_CONST || sym.identType == IdentType.ARRAY_VAR
                || sym.identType == IdentType.FUNC_ARRAY || sym.identType == IdentType.GLOBAL_ARRAY_CONST
                || sym.identType == IdentType.GLOBAL_ARRAY_VAR;
    }

}
