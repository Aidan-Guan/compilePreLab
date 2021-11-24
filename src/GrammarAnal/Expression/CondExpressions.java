package GrammarAnal.Expression;

import AST.*;

import static GrammarAnal.Expression.CalculateExpressions.*;
import static GrammarAnal.Grammar.TokensToAST.*;

public class CondExpressions {
    public static void lOrExp(AstNode parent) {
        AstNode NodeLOrExp = new AstNode("<LOrExp>");

        lAndExp(NodeLOrExp);

        while (currentSym.value.equals("||")) {
            addChild(currentSym, NodeLOrExp);
            getNextSym();

            lAndExp(NodeLOrExp);
        }

        addChild(NodeLOrExp, parent);
    }


    public static void lAndExp(AstNode parent) {
        AstNode NodeLAndExp = new AstNode("<LAndExp>");

        eqExp(NodeLAndExp);

        while (currentSym.value.equals("&&")) {
            addChild(currentSym, NodeLAndExp);
            getNextSym();

            eqExp(NodeLAndExp);
        }

        addChild(NodeLAndExp, parent);
    }


    public static void eqExp(AstNode parent) {
        AstNode NodeEqExp = new AstNode("<EqExp>");

        relExp(NodeEqExp);

        while (currentSym.value.equals("==") || currentSym.value.equals("!=")) {
            addChild(currentSym, NodeEqExp);
            getNextSym();

            relExp(NodeEqExp);
        }

        addChild(NodeEqExp, parent);
    }


    public static void relExp(AstNode parent) {
        AstNode NodeRelExp = new AstNode("<RelExp>");

        addExp(NodeRelExp);

        while (currentSym.value.equals("<") || currentSym.value.equals(">") || currentSym.value.equals("<=") || currentSym.value.equals(">=")) {
            addChild(currentSym, NodeRelExp);
            getNextSym();

            addExp(NodeRelExp);
        }

        addChild(NodeRelExp, parent);
    }
}
