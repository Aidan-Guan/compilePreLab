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
        getNextSym(); // 第一个token
        tokens = tokenArrayList;

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

        //TODO: blockitem
        while (!currentSym.value.equals("}") && !currentSym.type.equals("ERR")) {
            BlockItem(NodeBlock);
        }


        if (!currentSym.value.equals("}")) ErrorSolu.error();
        addChild(currentSym, NodeBlock);
        getNextSym();
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
        parent.children.add(kid);
    }

    public static void addChild(AstNode child, AstNode parent) {
        parent.children.add(child);
    }
}
