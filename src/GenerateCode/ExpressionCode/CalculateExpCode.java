package GenerateCode.ExpressionCode;

import AST.AstNode;
import GrammarAnal.Expression.*;

public class CalculateExpCode {
    public static ExpValue CodeExp(AstNode parent) {
        return CodeAddExp(parent.children.get(0));
    }

    public static ExpValue CodeAddExp(AstNode parent) {
        int childIndex = 0;
        ExpValue value1 = CodeMulExp(parent.children.get(childIndex));
        childIndex++;

        while (parent.children.get(childIndex).value.equals("+") || parent.children.get(childIndex).value.equals("-")) {

        }

    }
}
