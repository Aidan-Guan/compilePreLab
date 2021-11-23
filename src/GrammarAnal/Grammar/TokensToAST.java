package GrammarAnal.Grammar;

import AST.AstNode;
import LexicalAnalysis.Token;

import java.util.ArrayList;

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
            Decl(root);
        }

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

    public static Token showNextSym() {
        return tokens.get(tokenIndex+1);
    }

    public static void addChild(Token child, AstNode parent) {
        AstNode kid = new AstNode(child.type, child.value);
        parent.children.add(kid);
    }

    public static void addChild(AstNode child, AstNode parent) {
        parent.children.add(child);
    }
}
