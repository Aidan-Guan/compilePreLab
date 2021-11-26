package GrammarAnal.Grammar;

import AST.AstNode;
import ErrorSolution.ErrorSolu;
import LexicalAnalysis.Token;

import java.util.ArrayList;

import static GrammarAnal.Grammar.Declare.*;
import static GrammarAnal.Grammar.Statement.*;

public class TokensToAST {
    public static ArrayList<Token> tokens;
    public static Token currentSym;
    public static AstNode root;
    public static int tokenIndex = 0;

    public static AstNode generateAST(ArrayList<Token> tokenArrayList) {
        tokens = tokenArrayList;
        getNextSym();

        CompUnit();
        return root;
    }

    public static void CompUnit() {
        AstNode compUnitNode = new AstNode("<CompUnit>");
        root = compUnitNode;

        while (currentSym.value.equals("int") || currentSym.value.equals("const")) {
            if (showFutureSym(2).value.equals("("))
                break;

            Decl(root);
        }

        FuncDef(compUnitNode);
    }

    public static void FuncDef(AstNode parent) {
        AstNode NodeFuncDef = new AstNode("<FuncDef>");

        if (!currentSym.value.equals("int")) ErrorSolu.error();
        addChild(currentSym, NodeFuncDef);
        getNextSym();

        if (!currentSym.value.equals("main")) ErrorSolu.error();
        addChild(currentSym, NodeFuncDef);
        getNextSym();

        if (!currentSym.value.equals("(")) ErrorSolu.error();
        addChild(currentSym, NodeFuncDef);
        getNextSym();

        if (!currentSym.value.equals(")")) ErrorSolu.error();
        addChild(currentSym, NodeFuncDef);
        getNextSym();

        Block(NodeFuncDef);

        addChild(NodeFuncDef, parent);
    }

    public static void Block(AstNode parent) {
        AstNode NodeBlock = new AstNode("<Block>");

        if (!currentSym.value.equals("{")) ErrorSolu.error();
        addChild(currentSym, NodeBlock);
        getNextSym();

        while (!currentSym.value.equals("}") && !currentSym.type.equals("ERR")) {
            BlockItem(NodeBlock);
        }

        if (!currentSym.value.equals("}")) ErrorSolu.error();
        addChild(currentSym, NodeBlock);
        getNextSym();

        addChild(NodeBlock, parent);
    }

    public static void BlockItem(AstNode parent) {
        AstNode NodeBlockItem = new AstNode("<BlockItem>");
        String startStr = currentSym.value;
        String type = currentSym.type;
        if (currentSym.value.equals("int") || currentSym.value.equals("const")) {
            Decl(NodeBlockItem);
        }
        else if (type.equals("IDENT") || startStr.equals(";") || startStr.equals("{")) {
            Stmt(NodeBlockItem);
        }
        addChild(NodeBlockItem, parent);
    }


    public static void getNextSym() {
        try {
            currentSym = tokens.get(tokenIndex);
            tokenIndex++;
        }
        catch (Exception e ) {
            currentSym = null;
        }
    }

    public static Token showFutureSym(int bias) {
        return tokens.get(tokenIndex-1+bias);
    }

    public static void addChild(Token child, AstNode parent) {
        AstNode kid = new AstNode(child.type, child.value);
        kid.parent = parent;
        parent.children.add(kid);
    }

    public static void addChild(AstNode child, AstNode parent) {
        child.parent = parent;
        parent.children.add(child);
    }

    public static AstNode findBlockParent (AstNode childNode) {
        AstNode child = childNode;
        if (child.type.equals("<Block>")) {
            return child;
        }

        while (true) {
            AstNode parent = child.parent;
            if (parent.type.equals("<Block>")) {
                return parent;
            }
            child = parent;
        }
    }

    public static void setChildBlockInfo (AstNode parent) {
        for (AstNode child: parent.children) {
            if (parent.breakBlock != -1) child.breakBlock = parent.breakBlock;

            if (parent.continueBlock != -1) child.continueBlock = parent.continueBlock;

            setChildBlockInfo(child);
        }
    }
}
