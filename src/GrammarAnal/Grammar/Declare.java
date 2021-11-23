package GrammarAnal.Grammar;

import AST.AstNode;
import ErrorSolution.ErrorSolu;

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
            getNextSym();
            ConstDef(NodeConstDecl);
        }

        if (!currentSym.value.equals(";")) { ErrorSolu.error(); }
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

        ConstInitVal();

        addChild(NodeConstDef, parent);
    }

    public static void ConstInitVal(AstNode parent) {
        AstNode NodeConstInitVal = new AstNode("<ConstInitVal>");

        ConstExp();
        addChild(NodeConstInitVal, parent);

    }
}
