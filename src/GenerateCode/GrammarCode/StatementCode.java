package GenerateCode.GrammarCode;

import AST.AstNode;
import ErrorSolution.ErrorSolu;
import GrammarAnal.Expression.*;

import static GenerateCode.ExpressionCode.CalculateExpCode.*;
import static GenerateCode.ExpressionCode.CondExpCode.*;
import static GenerateCode.GrammarCode.ASTToCode.*;
import static GrammarAnal.Grammar.TokensToAST.*;

public class StatementCode {
    static void CodeStmt(AstNode parent) {
        String type = parent.children.get(0).type;
        String value = parent.children.get(0).value;
        AstNode currBlock = parent.children.get(0);

        if (type.equals("<LVal>")) {
            String reg = CodeLVal(parent.children.get(0));

            if (isDefConst) ErrorSolu.error();
            ExpValue value1 = CodeExp(parent.children.get(2));
            outStr.append("\tstore i32 " + value1.out() + ", i32* " + reg + "\n");
        }
        else if (type.equals("<Block>")) {
            CodeBlock(parent.children.get(0));
        }
        else if (type.equals("<Exp>")) {
            CodeExp(parent.children.get(0));
        }
        else if (value.equals("if")) {
            int tLabel = blockIndex++;
            int fLabel = blockIndex++;

            CodeCond(parent.children.get(2), tLabel, fLabel);

            if (parent.children.size() == 5) {
                outStr.append("\nblock" + tLabel + ":\n");
                CodeStmt(parent.children.get(4));

                if (!isReturn)
                    outStr.append("\tbr label %block" + fLabel + "\n");
                else
                    isReturn = false;

                outStr.append("\nblock" + fLabel + ":\n");
            }
            else if (parent.children.size() == 7) {
                int rLabel = blockIndex++;

                outStr.append("\nblock" + tLabel + ":\n");
                CodeStmt(parent.children.get(4));

                if (!isReturn)
                    outStr.append("\tbr label %block" + rLabel + "\n");
                else
                    isReturn = false;

                outStr.append("\nblock" + fLabel + ":\n");
                CodeStmt(parent.children.get(6));

                if (!isReturn)
                    outStr.append("\tbr label %block" + rLabel + "\n");
                else
                    isReturn = false;

                outStr.append("\nblock" + rLabel + ":\n");
            }
        }
        else if (value.equals("while")) {
            int condLabel = blockIndex++;
            int tLabel = blockIndex++;
            int fLabel = blockIndex++;
            getWhileBlock(currBlock).tBlock = tLabel;
            getWhileBlock(currBlock).fBlock = fLabel;

            // 跳转到cond部分
            outStr.append("\tbr label %block" + condLabel + "\n");
            outStr.append("\nblock" + condLabel + ":\n");
            CodeCond(parent.children.get(2), tLabel, fLabel);


            outStr.append("\nblock" + tLabel + ":\n");
            parent.children.get(4).condBlock = condLabel;
            CodeStmt(parent.children.get(4));

            if (isReturn) {
                isReturn = false;
            }
            if (isBreak) {
                isBreak = false;
            }
//            if (!isReturn)
//                outStr.append("\tbr label %block" + fLabel + "\n");
//            else
//                isReturn = false;

            outStr.append("\nblock" + fLabel + ":\n");
        }
        else if (value.equals("break")) {
            int breakBlock = getWhileBlock(parent).fBlock;

            outStr.append("\tbr label %block" + breakBlock + "\n");

            isBreak = true;
            return;
        }
        else if (value.equals("continue")) {

        }
        else if (value.equals("return")) {
            isReturn = true;
            ExpValue value1 = CodeExp(parent.children.get(1));
            outStr.append("\tret i32 " + value1.out() + "\n");
        }

        if (parent.condBlock != -1 && !isBreak) {
            outStr.append("\tbr label %block" + parent.condBlock + "\n");
        }

    }

    static void CodeCond(AstNode parent, int tLabel, int fLabel) {
        CodeLOrExp(parent.children.get(0), tLabel, fLabel);
    }

}
