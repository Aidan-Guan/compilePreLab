package GenerateCode.ExpressionCode;

import AST.AstNode;
import GrammarAnal.Expression.*;

import static GenerateCode.GrammarCode.ASTToCode.*;

public class CondExpCode {

    public static void CodeLOrExp(AstNode parent, int tLabel, int fLabel) {
        int rLabel = -1;

        for (int i=0; i<parent.children.size(); i++) {
            AstNode child = parent.children.get(i);

            if (child.type.equals("<LAndExp>")) {
                if (i == parent.children.size()-1) {
                    if (rLabel != -1) {
                        outStr.append("\nblock" + rLabel + ":\n");
                    }
                    CodeLAndExp(child, tLabel, fLabel);
                }
                else {
                    if (rLabel != -1) {
                        outStr.append("\nblock" + rLabel + ":\n");
                    }
                    rLabel = blockIndex++;
                    CodeLAndExp(child, tLabel, fLabel);
                }
            }
        }
    }

    static void CodeLAndExp (AstNode parent, int tLabel, int fLabel) {
        int rLabel = -1;

        for (int i = 0; i<parent.children.size(); i++) {
            AstNode child = parent.children.get(i);

            if (child.type.equals("<EqExp>")) {
                if(i == (parent.children.size() -1)){
                    if (rLabel != -1) { outStr.append("\nblock" + rLabel + ":\n"); }

                    ExpValue regValue = CondEqExp(child);

                    int reg = i32Toi1(regValue);
                    outStr.append("\tbr i1 %" + reg + ", label %block" + tLabel + ", label %block" + fLabel + "\n");
                }
                else {
                    if (rLabel != -1) { outStr.append("\nblock" + rLabel + ":\n"); }

                    rLabel = blockIndex++;
                    ExpValue regValue = CondEqExp(child);
                    int reg = i32Toi1(regValue);
                    outStr.append("\tbr i1 %" + reg + ", label %block" + rLabel + ", label %block" + fLabel + "\n");
                }
            }
        }
    }

    static void CondEqExp(AstNode parent) {
        ExpValue value;
        int valueReg;
        int preReg = -1;
        int resultReg;
        String op = "";
        String type = "";

        for (int i=0; i<parent.children.size(); i++) {
            AstNode child = parent.children.get(i);

            if (child.type.equals("<RelExp>")) {
                value = CodeRelExp(child);
                valueReg = value.register;
                type = value.valueType;

                if (op.equals("==")) {
                    if (value.valueType.equals("i1")) {
                        int temp = regIndex++;
                        outStr.append("%x" + temp + " = zext i1 " + value.out() + " to i32\n");
                        valueReg = temp;
                        type = "i32";
                    }
                    resultReg = regIndex++;
                    outStr.append("\t%" + resultReg +" = icmp eq i32 %" + preReg + ", %" + valueReg + "\n");
                    preReg = resultReg;
                    type = "i1";

                    if (i != parent.children.size()-1) {
                        preReg = regIndex++;
                        outStr.append("\t%" + preReg + " = zext i1 %" + resultReg + " to i32\n");
                    }
                }
                else if (op.equals("!=")) {
                    if (value.valueType.equals("i1")) {
                        int temp = regIndex++;
                        outStr.append("\t%" + temp + " = zext i1 %" + valueReg + " to i32\n");
                        valueReg = temp;
                    }
                    resultReg = regIndex++;
                    outStr.append("\t%" + resultReg +" = icmp ne i32 %" + preReg + ", %" + valueReg + "\n");
                    preReg = resultReg;
                    type = "i1";

                    if (i != parent.children.size()-1) {
                        preReg = regIndex++;
                        outStr.append("\t%" + preReg + " = zext i1 %" + resultReg + " to i32\n")
                    }
                }
                else {
                    preReg = valueReg;
                }
            }
            else if (child.value.equals("==") || child.value.equals("!=")) {
                op = child.value;
            }
        }
    }

}
