package GenerateCode.ExpressionCode;

import AST.AstNode;
import GrammarAnal.Expression.*;

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
                //TODO: 确定是哪个减哪个;
                outStr.append("\t%" + regIndex + " = sub i32 %" + value1.out() + ", %" + value2.out() + "\n");
            }
            regIndex++;
        }

        return new ExpValue(regIndex-1, "i32" );
    }

    public static ExpValue CodeMulExp(AstNode parent) {
        //TODO: here
    }
}
