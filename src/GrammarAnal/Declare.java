package GrammarAnal;

import AST.AstNode;

import static GrammarAnal.GrammarAnalysis.*;


public class Declare {

    static boolean decl (AstNode parent) {
        int now = pointer;
        int nowPoint = printPointer;
        if (constDecl(parent) || varDecl(parent)) {
            return true;
        }
        return falseSolution(now, nowPoint);
    }

    static boolean constDecl (AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_constDecl = new AstNode("<ConstDecl>");
        if (symbol("CONSTTK", node_constDecl) && BType(node_constDecl) && ConstDef(node_constDecl)) {
            while (symbol("COMMA", node_constDecl) && ConstDef(node_constDecl));
            if (symbol("SEMICN", node_constDecl)) {
                parent.children.add(node_constDecl);
                addGrammar("<ConstDecl>");
                return true;
            }
        }
        return falseSolution(now, nowPrint);
    }

    static boolean BType(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        if (symbol("INTTK", parent)) {
            return true;
        }
        return falseSolution(now, nowPrint);
    }

    static boolean constDef(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_constDef = new AstNode("<ConstDef>");
        if (symbol("IDENFR", node_constDef)) {
            if (symbol("ASSIGN", node_constDef) && constInitVal(node_constDef)) {
                parent.children.add(node_constDef);
                addGrammar("<ConstDef>");
                return true;
            }
        }
        return falseSolution(now, nowPrint);
    }

    static boolean constInitVal(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_constInitVal = new AstNode("<ConstInitVal>");
        if (constExp(node_constInitVal)) {
            parent.children.add(node_constInitVal);
            addGrammar("<ConstInitVal>");
            return true;
        }
        return falseSolution(now, nowPrint);
    }

    static boolean varDecl(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_varDecl = new AstNode("<VarDecl>");
        if(BType(node_varDecl) && varDef(node_varDecl)) {
            while(symbol("COMMA", node_varDecl) && varDef(node_varDecl));
            if (symbol("SEMICN", node_varDecl)) {
                parent.children.add(node_varDecl);
                addGrammar("<VarDecl>");
                return true;
            }
        }
        return falseSolution(now, nowPrint);
    }

    static boolean varDef(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_varDef = new AstNode("<VarDef>");
        if (symbol("IDENFR", node_varDef)) {
            if (symbol("ASSIGN", node_varDef) && initVal(node_varDef));
            parent.children.add(node_varDef);
            addGrammar("<VarDef>");
            return true;
        }
        return falseSolution(now, nowPrint);
    }

    private static boolean initVal(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_initVal = new AstNode("<InitVal>");
        if (exp(node_initVal)) {
            addGrammar("<InitVal>");
            parent.children.add(node_initVal);
            return true;
        }
        return falseSolution(now, nowPrint);
    }
}
