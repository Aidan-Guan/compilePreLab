package GrammarAnal.Grammar;

import AST.AstNode;

import java.util.ArrayList;

import static GrammarAnal.Expression.Expressions.*;
import static GrammarAnal.Grammar.GrammarAnalysis.*;

public class IfAnal {
    public static boolean cond(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_cond = new AstNode("<Cond>");
        if (lOrExp(node_cond)) {
            parent.children.add(node_cond);
            addGrammar("<Cond>");
            return true;
        }
        return falseSolution(now, nowPrint);
    }

    public static boolean relExp(AstNode parent) {
        int now = pointer;
        int nowprint = printPointer;
        AstNode node_relExp = new AstNode("<RelExp>");
        if(addExp(node_relExp)) {
            parent.children.add(node_relExp);
            addGrammar("<RelExp>");
            ArrayList<String> temp = new ArrayList<>();
            temp.add("LSS");
            temp.add("LEQ");
            temp.add("GRE");
            temp.add("GEQ");
            AstNode node_relExp_temp;
            while ((symbol(temp, node_relExp)) && addExp(node_relExp)) {
//                node_relExp_temp = new AstNode("<RelExp>");
//                node_relExp.children.add(node_relExp_temp);
//                node_relExp = node_relExp_temp;
//                addgrammar("<RelExp>");
            }
            return true;
        }
        return falseSolution(now, nowprint);
    }

    private static boolean eqExp(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_eqExp = new AstNode("<EqExp>");
        if(relExp(node_eqExp)) {
            parent.children.add(node_eqExp);
            addGrammar("<EqExp>");
            ArrayList<String> temp = new ArrayList<>();
            temp.add("EQL");
            temp.add("NEQ");
            AstNode node_eqExp_temp;
            while ((symbol(temp, node_eqExp)) && relExp(node_eqExp)) {
//                node_eqExp_temp = new AstNode("<EqExp>");
//                node_eqExp.children.add(node_eqExp_temp);
//                node_eqExp = node_eqExp_temp;
//                addgrammar("<EqExp>");
            }
            return true;
        }
        return falseSolution(now, nowPrint);
    }

    private static boolean lAndExp(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_lAndExp = new AstNode("<LAndExp>");
        if(eqExp(node_lAndExp)) {
            parent.children.add(node_lAndExp);
            addGrammar("<LAndExp>");
            AstNode node_lAngExp_temp;
            while ((symbol("AND", node_lAndExp)) && eqExp(node_lAndExp)) {
//                node_lAngExp_temp = new AstNode("<LAngExp>");
//                node_lAndExp.children.add(node_lAngExp_temp);
//                node_lAndExp = node_lAngExp_temp;
//                addgrammar("<LAndExp>");
            }
            return true;
        }
        return falseSolution(now, nowPrint);
    }


    private static boolean lOrExp(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_lOrExp = new AstNode("<LOrExp>");
        if(lAndExp(node_lOrExp)) {
            parent.children.add(node_lOrExp);
            addGrammar("<LOrExp>");
            AstNode node_lOrExp_temp;
            while ((symbol("OR", node_lOrExp)) && lAndExp(node_lOrExp)) {
//                node_lOrExp_temp = new AstNode("<LOrExp>");
//                node_lOrExp.children.add(node_lOrExp_temp);
//                node_lOrExp = node_lOrExp_temp;
//                addgrammar("<LOrExp>");
            }
            return true;
        }
        return falseSolution(now, nowPrint);
    }
}
