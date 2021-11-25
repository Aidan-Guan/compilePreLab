package GenerateCode.ExpressionCode;

import AST.AstNode;
import ErrorSolution.ErrorSolu;
import GrammarAnal.Expression.*;
import TokenUtils.Ident;
import TokenUtils.IdentMapList;
import TokenUtils.IdentType;
import TokenUtils.ValueType;

import java.util.ArrayList;

import static GenerateCode.GrammarCode.ASTToCode.*;

public class CalculateExpCode {
    public static ExpValue CodeExp(AstNode parent) {
        return CodeAddExp(parent.children.get(0));
    }

    public static ExpValue CodeAddExp(AstNode parent) {
        int childIndex = 0;
        ExpValue value1 = CodeMulExp(parent.children.get(childIndex));
        childIndex++;

        while (parent.children.get(childIndex).value.equals("+") || parent.children.get(childIndex).value.equals("-")) {
            String op = parent.children.get(childIndex).value;
            childIndex++;
            ExpValue value2 = CodeMulExp(parent.children.get(childIndex));
            if (op.equals("+")) {
                outStr.append("\t%" + regIndex + " = add i32 " + value2.out() + ", " + value1.out() + "\n");
            }
            else if (op.equals("-")) {
                outStr.append("\t%" + regIndex + " = sub i32 %" + value1.out() + ", %" + value2.out() + "\n");
            }
            regIndex++;
        }

        return new ExpValue(regIndex-1, "i32" );
    }

    public static ExpValue CodeMulExp(AstNode parent) {
        //TODO: here
        int childIndex = 0;
        ExpValue value1 = CodeUnaryExp(parent.children.get(childIndex));
        childIndex++;

        String op = parent.children.get(childIndex).value;
        while (op.equals("*") || op.equals("/") || op.equals("%")) {
            op = parent.children.get(childIndex).value;
            childIndex++;
            ExpValue value2 = CodeUnaryExp(parent.children.get(childIndex));
            switch (op) {
                case "*" -> {
                    outStr.append("\t%" + regIndex + " = mul i32 " + value1.out() + ", " + value2.out() + "\n");
                }
                case "/" -> {
                    outStr.append("\t%" + regIndex + " = sdiv i32 %" + value1.out() + ", %" + value2.out() + "\n");
                }
                case "%" -> {
                    outStr.append("\t%" + regIndex + " = srem i32 %" + value1.out() + ", %" + value2.out() + "\n");
                }
            }
            regIndex++;
        }
        return new ExpValue(regIndex-1, "i32");
    }

    static ExpValue CodeUnaryExp (AstNode parent) {
        String type = parent.children.get(0).type;

        if (type.equals("<PrimaryExp>")) {
            return CodePrimaryExp(parent.children.get(0));
        }
        else if (type.equals("UnaryOp")) {
            String op = CodeUnaryOp(parent.children.get(0));
            switch (op) {
                case "-" -> {
                    ExpValue value1 = CodeUnaryExp(parent.children.get(1));
                    outStr.append("\t%" + regIndex + " = sub i32 0, " + value1.out() + "\n");
                    regIndex++;
                    return new ExpValue(regIndex-1, "i32");
                }
                case "+" -> {
                    return CodeUnaryExp(parent.children.get(1));
                }
                case "!" -> {
                    ExpValue value1 = CodeUnaryExp(parent.children.get(1));
                    outStr.append("\t%" + regIndex + " = icmp eq i32 " + value1.out() + ", 0\n");
                    regIndex++;
                    outStr.append("\t%" + regIndex + " = zext i1 %" + (regIndex-1) + " to i32\n");
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

            if (parent.children.get(2).value.equals("<FuncRParams>")) {
                params = FuncRParams(parent.children.get(2));
            }

            String out = "";
            String outDecl = "";

            for (int i=0; i<=params.size(); i++) {
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
                if (!ident.isDeclared) {
                    outStr.insert(0, "declare i32 @" + ident.name + "(" + outDecl + ")\n");
                    ident.isDeclared = true;
                }
                outStr.append("\t%" + regIndex + " = call i32 @" + ident.name + "(" + out + ")\n");
                regIndex++;
                return new ExpValue(regIndex-1, "i32");
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
            params.add(CodeExp(child).out());
        }
        return params;
    }

    static String CodeUnaryOp(AstNode parent) {
        return parent.children.get(0).value;
    }

    static ExpValue CodePrimaryExp(AstNode parent) {
        AstNode firstChild = parent.children.get(0);

        if (firstChild.type.equals("<Number>")) {
            return CodeNumber(parent.children.get(0));
        }
        else if (firstChild.type.equals("LVal")) {
            String reg = CodeLVal(parent.children.get(0));
            String identName = parent.children.get(0).children.get(0).value;
            isDefConst = false;

            if (!isDefGlobal) {
                outStr.append("\t%" + regIndex + " = load i32, i32* " + reg + "\n");
                regIndex++;
            }
            return new ExpValue(regIndex-1, "i32");

        }
        else if (firstChild.value.equals("(")) {
            return CodeExp(parent.children.get(1));
        }

        return null;
    }

    static String CodeLVal(AstNode parent) {
        String identName = parent.children.get(0).value;
        if (CodeIsConst(identName)) { isDefConst = true; }

        if (isDefConst && !CodeIsConst(identName)) {
            ErrorSolu.error();
        }

        return CodeGetReg(identName);
    }

    static ExpValue CodeNumber(AstNode parent) {
        int value = Integer.parseInt(parent.children.get(0).value);

        if (!isDefGlobal) {
            outStr.append("\t%" + regIndex + " = add i32 0, " + value + "\n");
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
}
