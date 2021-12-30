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
                        outStr.append("\t%x" + regNew + " = sub i32 0, %x" + regBefore + "\n");
                    return new ExpValue(regNew, "i32", 0-value);
                }
                case "+" -> {
                    return CodeUnaryExp(parent.children.get(1));
                }
                case "!" -> {
                    ExpValue value1 = CodeUnaryExp(parent.children.get(1));
                    outStr.append("\t%x" + regIndex + " = icmp eq i32 " + value1.out() + ", 0\n");
                    regIndex++;
                    outStr.append("\t%x" + regIndex + " = zext i1 %x" + (regIndex-1) + " to i32\n");
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
                params = FuncRParams(parent.children.get(2));
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
                outStr.append("\t%x" + regNew + " = call i32 @" + ident.name + "(" + out + ")\n");
                return new ExpValue(regNew, "i32");
            }
            else if (ident.valueType == ValueType.VOID) {
                if (!ident.isDeclared) {
                    outStr.insert(0, "declare void @" + ident.name + "(" + outDecl + ")\n");
                    ident.isDeclared = true;
                }
                outStr.append("\tcall void @" + ident.name + "(" + out + ")\n");
            }
        }
        return null;
    }

    static ArrayList<String> FuncRParams(AstNode parent) {
        ArrayList<String> params = new ArrayList<>();

        for (AstNode child: parent.children) {
            if (child.type.equals("<Exp>")) {
                isFuncParam = true;
                params.add(CodeExp(child).out());
                isFuncParam = false;

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
            if(expValue.valueType.equals("ident"))
                regBefore = expValue.registerString;
            else
                regBefore = "%x" + expValue.register;
            String ident = parent.children.get(0).children.get(0).value;
//                isDefiningConst = false;
            int regNew = regIndex++;
            if(!isDefGlobal)
                outStr.append("\t%x" + regNew + " = load i32, i32* " + regBefore + "\n");
            return new ExpValue(regNew, "i32", Objects.requireNonNull(IdentMapList.getIdentInAllMap(ident)).value);


//            String reg = CodeLVal(parent.children.get(0));
//            String identName = parent.children.get(0).children.get(0).value;
//            isDefConst = false;
//
//            if (!isDefGlobal) {
//                outStr.append("\t%x" + regIndex + " = load i32, i32* " + reg + "\n");
//                regIndex++;
//            }
//            return new ExpValue(regIndex-1, "i32", getIdentValue(identName));

        }
        else if (firstChild.value.equals("(")) {
            return CodeExp(parent.children.get(1));
        }

        return null;
    }

//    public static String CodeLVal(AstNode parent) {
//        String identName = parent.children.get(0).value;
//        if (CodeIsConst(identName)) { isDefConst = true; }
//
//        if (isDefConst && !CodeIsConst(identName)) {
//            ErrorSolu.error();
//        }
//
//        return CodeGetReg(identName);
//    }

//    public static ExpValue CodeLVal(AstNode parent){
//        if(parent.children.size() == 1){
//            String Ident = parent.children.get(0).value;
//            if(isConst(Ident)) isDefConst = true;
//            if(isDefConst){
//                if(!isConst(Ident))
//                    throw new java.lang.Error("can not define const by var");
//            }
//            return new ExpValue(getSymReg(Ident));
//        }
//        else {
//            String ident = parent.children.get(0).value;
//            ArrayList <Integer> arrayParam = new ArrayList<>();
//            for(AstNode child : parent.children){
//                if(child.type.equals("<Exp>")){
//
//                    arrayParam.add(CodeExp(child).register);
//
//                }
//            }
//            int registerNew = regIndex++;
//            Ident arraySym = IdentMapList.getIdentInAllMap(ident);
//
//            if(arraySym.dim != arrayParam.size()) throw new java.lang.Error("dim is not correspond");
//            String registerString;
//            if(arraySym.identType == IdentType.GLOBAL_ARRAY_CONST || arraySym.identType == IdentType.GLOBAL_ARRAY_VAR)
//                registerString = "@" + arraySym.name;
//            else registerString = arraySym.out();
//
//            outStr.append("\t%x" +registerNew + " = getelementptr " + arraySym.arrayType + ", " + arraySym.arrayType + "* " + registerString + ", i32 0");
//            for(int i = 0; i < arraySym.dim; i++){
//                outStr.append(", i32 %x" + arrayParam.get(i));
//            }
//            outStr.append("\n");
//            return new ExpValue(registerNew);
//        }
//    }

    public static ExpValue CodeLVal(AstNode node){
        if(node.children.size() == 1){
            String Ident = node.children.get(0).value;
            // differ array and var
            Ident sym = IdentMapList.getIdentInAllMap(Ident);
            ExpValue expValue;
            if(isArray(sym)){
                int regNew = regIndex++;
                String arrayType = sym.arrayType;
                if(sym.identType == IdentType.FUNC_ARRAY){
                    outStr.append("\t%x").append(regNew).append(" = load i32*, i32* * %x").append(sym.regIndex).append("\n");
                    expValue = new ExpValue(regNew, "ptr");
                    expValue.valueType = "array";
                }
                else {
                    if(sym.identType == IdentType.GLOBAL_ARRAY_CONST || sym.identType == IdentType.GLOBAL_ARRAY_VAR){
                        String regString = "@" + sym.name;
                        outStr.append("\t%x").append(regNew).append(" = getelementptr ").append(arrayType).append(", ").append(arrayType).append("*").append(regString).append(", i32 0, i32 0\n");
                        expValue = new ExpValue(regNew, "ptr");
                        expValue.valueType = "array";
                    }
                    else {
                        outStr.append("\t%x").append(regNew).append(" = getelementptr ").append(arrayType).append(", ").append(arrayType).append("* %x").append(sym.regIndex).append(", i32 0, i32 0\n");
                        expValue = new ExpValue(regNew, "ptr");
                        expValue.valueType = "array";
                    }

                }

                return expValue;
            }
            else{
                if(isConst(Ident)) isDefConst = true;
                if(isDefConst){
                    if(!isConst(Ident))
                        throw new java.lang.Error("can not define const by var");
                }
                return new ExpValue(getSymReg(Ident));
            }
        }
        else {
            String ident = node.children.get(0).value;
            ArrayList <Integer> arrayParam = new ArrayList<>();
            for(AstNode child : node.children){
                if(child.type.equals("<Exp>")){
                    arrayParam.add(CodeExp(child).register);
                }
            }
            Ident arraySym = IdentMapList.getIdentInAllMap(ident);

            // deal with func array
            assert arraySym != null;
            if(arraySym.identType == IdentType.FUNC_ARRAY){

                // transform func array to real array
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

            // deal with normal array
            if(arraySym.dim != arrayParam.size()) throw new java.lang.Error("dim is not correspond");
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

    static String CodeGetReg(String identName) {
        Ident ident = IdentMapList.getIdentInAllMap(identName);
        String reg = "";

        if (ident != null) {
            if(ident.identType == IdentType.GLOBAL_CONST || ident.identType == IdentType.GLOBAL_VAR)
                reg =  "@" + ident.name;
            else reg = ident.out();
        }

        if (!reg.equals(""))
            return reg;

        ErrorSolu.error();
        return null;
    }

    static boolean CodeIsConst (String identName) {
        Ident ident = IdentMapList.getIdentInAllMap(identName);

        if (ident != null) {
            return ident.identType == IdentType.CONST || ident.identType == IdentType.GLOBAL_CONST;
        }

        ErrorSolu.error();
        return false;
    }

    static int getIdentValue (String identName) {
        Ident ident = IdentMapList.getGlobalMap().get(identName);
        if (ident != null) {
            return ident.value;
        }
        return -1;
    }

    private static boolean isConst(String Ident){
        Ident sym = IdentMapList.getIdentInAllMap(Ident);
        if(sym != null){
            return sym.identType == IdentType.CONST || sym.identType == IdentType.GLOBAL_CONST || sym.identType == IdentType.ARRAY_CONST || sym.identType ==IdentType.GLOBAL_ARRAY_CONST;
        }
        else {
            if (IdentMapList.getIdentInAllMap(Ident)!=null) {
                return  sym.identType == IdentType.CONST || sym.identType == IdentType.GLOBAL_CONST || sym.identType == IdentType.ARRAY_CONST || sym.identType ==IdentType.GLOBAL_ARRAY_CONST;
            }
        }
        throw new java.lang.Error("symbol used before declaration");
    }

    private static String getSymReg(String Ident){
        Ident sym = IdentMapList.getIdentInAllMap(Ident);
        String reg = "";
        if(sym != null){
            if(sym.identType == IdentType.GLOBAL_CONST || sym.identType == IdentType.GLOBAL_VAR || sym.identType == IdentType.GLOBAL_ARRAY_CONST || sym.identType == IdentType.GLOBAL_ARRAY_VAR)
                reg =  "@" + sym.name;
            else reg = "%x" + sym.regIndex;
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
