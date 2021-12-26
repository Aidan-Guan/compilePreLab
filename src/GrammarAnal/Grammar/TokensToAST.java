package GrammarAnal.Grammar;

import AST.AstNode;
import ErrorSolution.ErrorSolu;
import LexicalAnalysis.Token;

import java.util.ArrayList;
import java.util.Stack;

import static GrammarAnal.Expression.CalculateExpressions.*;
import static GrammarAnal.Grammar.Declare.*;
import static GrammarAnal.Grammar.Statement.*;

public class TokensToAST {
    public static ArrayList<Token> tokens;
    public static Token currentSym;
    public static AstNode root;
    public static int tokenIndex = 0;

    public static ArrayList<String> grammarList = new ArrayList<>();



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

        while ((currentSym.value.equals("int") || currentSym.value.equals("void")) && (showFutureSym(2).value.equals("("))) {
            FuncDef(compUnitNode);
        }

        if (MainFuncDef(compUnitNode)) {
            addGrammar("<CompUnit>");
        }
    }


    public static void FuncDef (AstNode parent) {
        AstNode funcDefNode = new AstNode("<FuncDef>");

        if (!(currentSym.value.equals("int") || currentSym.value.equals("void"))) ErrorSolu.error();
        addChild(currentSym, funcDefNode);
        getNextSym();

        if (!currentSym.type.equals("IDENT")) ErrorSolu.error();
        addChild(currentSym, funcDefNode);
        getNextSym();

        if (!currentSym.value.equals("(")) ErrorSolu.error();
        addChild(currentSym, funcDefNode);
        getNextSym();

        if (!funcFParams(funcDefNode)) ErrorSolu.error();


        if (!currentSym.value.equals(")")) ErrorSolu.error();
        addChild(currentSym, funcDefNode);
        getNextSym();

        Block(funcDefNode);

        addGrammar("<FuncDef>");


        addChild(funcDefNode, parent);
    }


    private static boolean funcFParams (AstNode parent) {
        AstNode NodeFuncFParams = new AstNode("<FuncFParams>");

        if (!funcFParam) {
            return false;
        }

        while (currentSym.value.equals(",")) {
            funcFParam(NodeFuncFParams);
        }

        addChild(NodeFuncFParams, parent);
        addGrammar("<FuncFParams>");
        return true;
    }

    private static  boolean funcFParam (AstNode parent) {
        AstNode NodeFuncFParam = new AstNode("<FuncFParam>");

        BType(NodeFuncFParam);

        if (!currentSym.type.equals("IDENT")) ErrorSolu.error();
        addChild(currentSym, NodeFuncFParam);
        getNextSym();

        if (!currentSym.value.equals("[")) {
            addGrammar("<FuncFParam>");
            addChild(NodeFuncFParam, parent);
            return true;
        }

        addChild(currentSym, NodeFuncFParam);
        getNextSym();

        if (!currentSym.value.equals("]")) ErrorSolu.error();
        addChild(currentSym, NodeFuncFParam);
        getNextSym();

        while (currentSym.value.equals("[")) {
            addChild(currentSym, NodeFuncFParam);
            getNextSym();

            constExp(NodeFuncFParam);

            if (!currentSym.value.equals("]")) ErrorSolu.error();
            addChild(currentSym, NodeFuncFParam);
            getNextSym();
        }

        addGrammar("<FuncFParam>");
        addChild(NodeFuncFParam, parent);
        return true;
    }


    public static boolean MainFuncDef(AstNode parent) {
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

        addGrammar("<MainFuncDef>");

        addChild(NodeFuncDef, parent);
        return true;
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
            currentSym.index = tokenIndex;
            tokenIndex++;
        }
        catch (Exception e ) {
            currentSym = null;
        }
    }

    public static Token showFutureSym(int bias) {
        return tokens.get(tokenIndex-1+bias);
    }


    public static Token getIsEq () {
        Token tmp = showFutureSym(1);
        if (!tmp.value.equals("[")) {
            return tmp;
        }

        int index = tokenIndex;
        Stack<String> braceStack = new Stack<>();

        while (true) {
            if (tokens.get(index).value.equals("[")) {
                braceStack.push("[");
            }

            if (tokens.get(index).value.equals("]")) {
                braceStack.pop();
            }

            index++;
            if (braceStack.isEmpty() && !tokens.get(index).value.equals("[")) {
                break;
            }
        }

        return tokens.get(index);
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

    public static void  addGrammar(String string) {
        grammarList.add(string);

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
