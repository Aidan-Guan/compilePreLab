package GrammarAnal.Grammar;

import AST.AstNode;

import java.util.ArrayList;

import static GrammarAnal.Expression.Expressions.*;
import static GrammarAnal.Grammar.GrammarAnalysis.*;
import static GrammarAnal.Grammar.IfAnal.*;

public class Statement {

    static boolean stmt(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        boolean flag = false;
        AstNode node_stmt = new AstNode("<Stmt>");
        if (block(node_stmt)) {
            flag = true;
        } else if (symbol("IFTK", node_stmt)
                && symbol("LPARENT", node_stmt)
                && cond(node_stmt) && symbol("RPARENT", node_stmt) && stmt(node_stmt)) {
            while (symbol("ELSETK", node_stmt) && stmt(node_stmt));
            flag = true;
        } else if (symbol("RETURNTK", node_stmt)) {
            if (exp(node_stmt));
            if (symbol("SEMICN", node_stmt)) {
                flag = true;
            }
        } else if (lVal(node_stmt) && symbol("ASSIGN", node_stmt)) {
            if (exp(node_stmt) && symbol("SEMICN", node_stmt)) {
                flag = true;
            }
        }
        if (!flag){
            falseSolution(now, nowPrint);
            if(node_stmt.children.size() > 0 && node_stmt.children.get(node_stmt.children.size()-1).type.equals("<LVal>")){
                node_stmt.children = new ArrayList<>();
            }
            if(exp(node_stmt));
            if(symbol("SEMICN", node_stmt)) {
                flag = true;
            }
        }
        if (flag) {
            addGrammar("<Stmt>");
            parent.children.add(node_stmt);
            return true;
        }
        return falseSolution(now, nowPrint);
    }

    public static boolean lVal(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_lVal = new AstNode("<LVal>");
        if (symbol("IDENFR", node_lVal)) {
            parent.children.add(node_lVal);
            addGrammar("<LVal>");
            return true;
        }
        return falseSolution(now, nowPrint);
    }

    public static boolean funcRParams(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_funcRParams = new AstNode("<FuncRParams>");
        if (exp(node_funcRParams)) {
            while (symbol("COMMA", node_funcRParams) && exp(node_funcRParams));
            parent.children.add(node_funcRParams);
            addGrammar("<FuncRParams>");
            return true;
        }
        return falseSolution(now, nowPrint);
    }

}
