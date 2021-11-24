package GrammarAnal.Grammar;

import AST.AstNode;
import ErrorSolution.ErrorSolu;

import static GrammarAnal.Expression.CalculateExpressions.*;
import static GrammarAnal.Grammar.TokensToAST.*;


public class Declare {
    public static void Decl(AstNode parent) {
        if (currentSym.value.equals("const")) {
            ConstDecl(parent);
        }
        else if (currentSym.value.equals("int")) {
            VarDecl(parent);
        }
        else {
            ErrorSolu.error();
        }
    }

    public static void ConstDecl(AstNode parent) {
        AstNode NodeConstDecl = new AstNode("<ConstDecl>");

        if (!currentSym.value.equals("const")) ErrorSolu.error();
        getNextSym();

        BType(NodeConstDecl);

        ConstDef(NodeConstDecl);

        while (currentSym.value.equals(",")) {
            addChild(currentSym, NodeConstDecl);
            getNextSym();
            ConstDef(NodeConstDecl);
        }

        if (!currentSym.value.equals(";")) { ErrorSolu.error(); }
        addChild(currentSym, NodeConstDecl);
        getNextSym();

        addChild(NodeConstDecl, parent);
    }

    public static void BType(AstNode parent) {
        if (!currentSym.value.equals("int")) ErrorSolu.error();
        addChild(currentSym, parent);
        getNextSym();
    }

    public static void ConstDef(AstNode parent) {
        AstNode NodeConstDef = new AstNode("<ConstDef>");

        if (!currentSym.type.equals("IDENT")) ErrorSolu.error();
        addChild(currentSym, NodeConstDef);
        getNextSym();

        if (!currentSym.value.equals("=")) ErrorSolu.error();
        addChild(currentSym, NodeConstDef);
        getNextSym();

        ConstInitVal(NodeConstDef);

        addChild(NodeConstDef, parent);
    }

    public static void ConstInitVal(AstNode parent) {
        AstNode NodeConstInitVal = new AstNode("<ConstInitVal>");

        constExp(NodeConstInitVal);
        addChild(NodeConstInitVal, parent);
    }

    public static void VarDecl (AstNode parent) {
        AstNode NodeVarDecl = new AstNode("<VarDecl>");
        BType(NodeVarDecl);

        VarDef(NodeVarDecl);

        while (currentSym.value.equals(",")) {
            addChild(currentSym, NodeVarDecl);
            getNextSym();
            VarDef(NodeVarDecl);
        }

        if (!currentSym.value.equals(";")) ErrorSolu.error();
        addChild(currentSym,NodeVarDecl);
        getNextSym();

        addChild(NodeVarDecl, parent);

    }

    public static void VarDef(AstNode parent) {
        AstNode NodeVarDef = new AstNode("<VarDef>");

        if (!currentSym.type.equals("IDENT")) ErrorSolu.error();
        addChild(currentSym, NodeVarDef);
        getNextSym();

        if (!currentSym.value.equals("=")) {
            addChild(NodeVarDef, parent);
            return;
        }

        addChild(currentSym, NodeVarDef);
        getNextSym();
        InitVal(NodeVarDef);

        addChild(NodeVarDef, parent);
    }

    public static void InitVal(AstNode parent) {
        AstNode NodeInitVal = new AstNode("<InitVal>");
        Exp(NodeInitVal);
        addChild(NodeInitVal, parent);
    }


}
