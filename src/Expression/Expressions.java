package Expression;

import AST.AstNode;

import java.util.ArrayList;

import static GrammarAnal.GrammarAnalysis.*;
import static GrammarAnal.Statement.lVal;

public class Expressions {
    public static boolean exp(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_exp = new AstNode("<Exp>");
        if (addExp(node_exp)) {
            parent.children.add(node_exp);
            addGrammar("<Exp>");
            return true;
        }
        return falseSolution(now, nowPrint);
    }


    public static boolean addExp(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_addExp = new AstNode("<AddExp>");
        if(mulExp(node_addExp)) {
            parent.children.add(node_addExp);
            addGrammar("<AddExp>");
            ArrayList<String> temp = new ArrayList<>();
            temp.add("PLUS");
            temp.add("MINU");
            while ((symbol(temp, node_addExp)) && mulExp(node_addExp)) {
//                AstNode node_addExp_temp = new AstNode("<AddExp>");
//                node_addExp.children.add(node_addExp_temp);
//                node_addExp = node_addExp_temp;
//                addgrammar("<AddExp>");
            }
            return true;
        }
        return falseSolution(now, nowPrint);
    }

    public static boolean mulExp(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_mulExp = new AstNode("<MulExp>");
        if(unaryExp(node_mulExp)) {
            parent.children.add(node_mulExp);
            addGrammar("<MulExp>");
            ArrayList<String> temp = new ArrayList<>();
            temp.add("MULT");
            temp.add("DIV");
            temp.add("MOD");
            AstNode node_mulExp_temp;
            while ((symbol(temp, node_mulExp)) && unaryExp(node_mulExp)) {
//                node_mulExp_temp = new AstNode("<MulExp>");
//                node_mulExp.children.add(node_mulExp_temp);
//                node_mulExp = node_mulExp_temp;
//                addgrammar("<MulExp>");
            }
            return true;
        }
        return falseSolution(now, nowPrint);
    }

    public static boolean primaryExp(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_primaryExp = new AstNode("<PrimaryExp>");
        if (lVal(node_primaryExp) || number(node_primaryExp)
                || (symbol("LPARENT", node_primaryExp)
                && exp(node_primaryExp)
                && symbol("RPARENT", node_primaryExp))) {
            parent.children.add(node_primaryExp);
            addGrammar("<PrimaryExp>");
            return true;
        }
        return falseSolution(now, nowPrint);
    }

    private static boolean number(AstNode parent) {
        int now = pointer;
        int nowprint = printPointer;
        AstNode node_number = new AstNode("<Number>");
        if (symbol("INTCON", node_number)) {
            parent.children.add(node_number);
            addGrammar("<Number>");
            return true;
        }
        return falseSolution(now, nowprint);
    }


    private static boolean unaryExp(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_unaryExp = new AstNode("<UnaryExp>");
        if (symbol("IDENFR", node_unaryExp) && symbol("LPARENT", node_unaryExp)) {
            if(funcRParams(node_unaryExp));
            if (symbol("RPARENT", node_unaryExp)) {
                parent.children.add(node_unaryExp);
                addGrammar("<UnaryExp>");
                return true;
            }
        }
        falseSolution(now, nowPrint);
        // 去掉回溯造成的多余的IDENFR
        if(node_unaryExp.children.size() > 0 && node_unaryExp.children.get(node_unaryExp.children.size()-1).type.equals("IDENFR")){
            node_unaryExp.children.remove(node_unaryExp.children.size()-1);
        }
        if (primaryExp(node_unaryExp)) {
            parent.children.add(node_unaryExp);
            addGrammar("<UnaryExp>");
            return true;
        }
        else if (unaryOp(node_unaryExp) && unaryExp(node_unaryExp)) {
            parent.children.add(node_unaryExp);
            addGrammar("<UnaryExp>");
            return true;
        }
        return falseSolution(now, nowPrint);
    }


    private static boolean unaryOp(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_unaryOp = new AstNode("<UnaryOp>");
        ArrayList<String> temp = new ArrayList<>();
        temp.add("PLUS");
        temp.add("MINU");
        temp.add("NOT");
        if (symbol(temp, node_unaryOp)) {
            parent.children.add(node_unaryOp);
            addGrammar("<UnaryOp>");
            return true;
        }
        return falseSolution(now, nowPrint);
    }


    private static boolean constExp(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_constExp = new AstNode("<ConstExp>");
        if(addExp(node_constExp)) {
            parent.children.add(node_constExp);
            addGrammar("<ConstExp>");
            return true;
        }
        return falseSolution(now, nowPrint);
    }
}
