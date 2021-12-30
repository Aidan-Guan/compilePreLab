package GenerateCode.GrammarCode;

import AST.AstNode;
import ErrorSolution.ErrorSolu;
import GrammarAnal.Expression.*;

import java.util.ArrayList;

import static AST.AstNode.*;
import static GenerateCode.ExpressionCode.CalculateExpCode.*;
import static GenerateCode.ExpressionCode.CondExpCode.*;
import static GenerateCode.GrammarCode.ASTToCode.*;

public class StatementCode {

    public static ArrayList<Integer> labels = new ArrayList<>();

    static void CodeStmt(AstNode parent) {
        String type = parent.children.get(0).type;
        String value = parent.children.get(0).value;

        if (type.equals("<LVal>")) {
            ExpValue expValue = CodeLVal(parent.children.get(0));
            String regIdent;
            if(expValue.type.equals("ident"))
                regIdent= expValue.registerString;
            else
                regIdent = "%x" + expValue.register;

            if(isDefConst)
                throw new java.lang.Error("const can not be assigned again");
            int regExp = CodeExp(parent.children.get(2)).register;
            outStr.append("\tstore i32 %x" + regExp + ", i32* " + regIdent + "\n");

        }
        else if (type.equals("<Block>")) {
            CodeBlock(parent.children.get(0));
        }
        else if (type.equals("<Exp>")) {
            CodeExp(parent.children.get(0));
        }

        else if (value.equals("if")) {
            labels = new ArrayList<>();

            int tLabel = blockIndex++;
            int fLabel = blockIndex++;
            boolean flag = false;

            CodeCond(parent.children.get(2), tLabel, fLabel);

            if (parent.children.size() == 5) {
                outStr.append("\nblock" + tLabel + ":\n");

                CodeStmt(parent.children.get(4));
                if (!isReturn && !hasContinueOrBreak(parent.children.get(4)))
                    outStr.append("\tbr label %block" + fLabel + "\n");
                else
                    isReturn = false;

                outStr.append("\nblock" + fLabel + ":\n");
            }
            else if (parent.children.size() == 7) {
                int returnNum = 0;
                int nextLabel;
                if(parent.nextLabel == -1){
                    nextLabel = blockIndex++;
                    parent.nextLabel = nextLabel;
                }
                else {
                    nextLabel = parent.nextLabel;
                    flag = true;
                }
                parent.copyNextLabel(parent);
                outStr.append("\nblock" + tLabel + ":\n");

                CodeStmt(parent.children.get(4));
                if (isReturn) returnNum++;

                if (!isReturn && !hasContinueOrBreak(parent.children.get(4)))
                    outStr.append("\tbr label %block" + nextLabel + "\n");
                else
                    isReturn = false;

                outStr.append("\nblock" + fLabel + ":\n");
                CodeStmt(parent.children.get(6));
                if (isReturn) returnNum++;

//                if (!isReturn && !hasLoopControl(parent.children.get(6)) && !labels.contains(nextLabel)){
//                    outStr.append("\tbr label %block" + nextLabel + "\n");
//                    labels.add(nextLabel);
//                }
                if (!isReturn && !hasContinueOrBreak(parent.children.get(6))){
                    if(!labels.contains(nextLabel)) {
                        outStr.append("\tbr label %block").append(nextLabel).append("\n");
                        labels.add(nextLabel);
                    }
                }
                else {
                    isReturn = false;
                }

                if (!flag && returnNum < 2) outStr.append("\nblock" + nextLabel + ":\n");
            }
        }
        else if (value.equals("while")) {

            int condLabel = blockIndex++;
            int tLabel = blockIndex++;
            int fLabel = blockIndex++;

            ArrayList<Integer> loopLabels = new ArrayList<>();

            loopLabels.add(condLabel);
            loopLabels.add(tLabel);
            loopLabels.add(fLabel);

            parent.loopLabel = loopLabels;

            copyWhile(parent);
            outStr.append("\tbr label %block" + condLabel + "\n");
            outStr.append("\nblock" + condLabel + ":\n");

            CodeCond(parent.children.get(2), tLabel, fLabel);

            outStr.append("\nblock" + tLabel + ":\n");

            CodeStmt(parent.children.get(4));

            if (!isReturn)
                outStr.append("\tbr label %block" + condLabel + "\n");
            else
                isReturn = false;

            outStr.append("\nblock" + fLabel + ":\n");
        }
        else if (value.equals("break")) {
            int breakBlock = parent.loopLabel.get(2);
            outStr.append("\tbr label %block" + breakBlock + "\n");
        }
        else if (value.equals("continue")) {
            int continueBlock = parent.getContinueBlock();
            outStr.append("\tbr label %block" + continueBlock + "\n");
        }
        else if (value.equals("return")) {
            isReturn = true;
            if(parent.children.size() > 2) {
                int returnReg = CodeExp(parent.children.get(1)).register;
                outStr.append("\tret i32 %x").append(returnReg).append("\n");
            }else{
                outStr.append("\tret void\n");
            }
        }



    }

    static void CodeCond(AstNode parent, int tLabel, int fLabel) {
        CodeLOrExp(parent.children.get(0), tLabel, fLabel);
    }

    static boolean hasContinueOrBreak(AstNode parent){
        boolean flag = false;
        for(AstNode child : parent.children){
            if (child.value!=null && (child.value.equals("continue") || child.value.equals("break"))){
                return true;
            }
            else{
                if( child.value!=null && (child.value.equals("if") || child.value.equals("while")))
                    break;
                if(hasContinueOrBreak(child))
                    flag = true;
            }
        }
        return flag;
    }
}
