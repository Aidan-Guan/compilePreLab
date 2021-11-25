package GrammarAnal.Grammar;

import AST.AstNode;
import ErrorSolution.ErrorSolu;
import LexicalAnalysis.Token;

import java.util.ArrayList;

import static GrammarAnal.Expression.CalculateExpressions.*;
import static GrammarAnal.Expression.CondExpressions.*;
import static GrammarAnal.Grammar.TokensToAST.*;

public class Statement {
    public static void Stmt (AstNode parent) {
        AstNode NodeStmt = new AstNode("<Stmt>");

        if (currentSym.value.equals("return")) {
            addChild(currentSym, NodeStmt);
            getNextSym();
            Exp(NodeStmt);
            if (!currentSym.value.equals(";")) ErrorSolu.error();
            addChild(currentSym, NodeStmt);
            getNextSym();
        }
        else if (currentSym.value.equals("if")) {
            addChild(currentSym, NodeStmt);
            getNextSym();

            if (!currentSym.value.equals("(")) ErrorSolu.error();
            addChild(currentSym, NodeStmt);
            getNextSym();

            Cond(NodeStmt);

            if (!currentSym.value.equals(")")) ErrorSolu.error();
            addChild(currentSym, NodeStmt);
            getNextSym();

            Stmt(NodeStmt);

            if (currentSym.value.equals("else")) {
                addChild(currentSym, NodeStmt);
                getNextSym();

                Stmt(NodeStmt);
            }
        }
        else if (currentSym.value.equals("{")) {
            Block(NodeStmt);
        }
        else if (currentSym.type.equals("IDENT")) {
            Token tmp = showFutureSym(1);
            if (tmp.value.equals("=")) {
                addChild(currentSym, NodeStmt);
                getNextSym();
                addChild(currentSym, NodeStmt);
                getNextSym();
                Exp(NodeStmt);
                if (!currentSym.value.equals(";")) ErrorSolu.error();
                addChild(currentSym, NodeStmt);
                getNextSym();
            }
            else {
                Exp(NodeStmt);
                if (!currentSym.value.equals(";")) ErrorSolu.error();
                addChild(currentSym, NodeStmt);
                getNextSym();
            }
        }
        else {
            ErrorSolu.error();
        }

        addChild(NodeStmt, parent);
    }


    public static void Cond (AstNode parent) {
        AstNode NodeCond = new AstNode("<Cond>");
        lOrExp(NodeCond);

        addChild(NodeCond, parent);
    }
}
