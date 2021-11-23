package GrammarAnal;

import AST.AstNode;
import ErrorSolution.Error;
import LexicalAnalysis.Token;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.SplittableRandom;

import static GrammarAnal.Declare.*;
import static GrammarAnal.Statement.stmt;
import static LexicalAnalysis.InitLex.initLex;

public class GrammarAnalysis {
    private static ArrayList<String[]> lexical;
    private static ArrayList<String> grammar = new ArrayList<>();
    public static AstNode root;

    public static int pointer = 0;
    public static int printPointer = 0;

    public static void grammarAnal(ArrayList<Token> tokenList) {
        lexical = initLex(tokenList);
        if (!compUnit()) {
            Error.error();
        }
    }

    static boolean compUnit() {
        int now = pointer;
        int nowPrint = printPointer;

        AstNode nodeCompUnit = new AstNode("<CompUnit>");
        root = nodeCompUnit;

        while (decl(nodeCompUnit));

        if (MainFuncDef(nodeCompUnit)) {
            addGrammar("<CompUnit>");
            return true;
        }
        return falseSolution(now, nowPrint);
    }


    static boolean MainFuncDef(AstNode root) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_mainFuncDef = new AstNode("<MainFuncDef>");
        if (symbol("INTTK", node_mainFuncDef)
                && symbol("MAINTK", node_mainFuncDef)
                && symbol("LPARENT", node_mainFuncDef)
                && symbol("RPARENT", node_mainFuncDef)
                && block(node_mainFuncDef)) {
            addGrammar("<MainFuncDef>");
            root.children.add(node_mainFuncDef);
            return true;
        }
        return falseSolution(now, nowPrint);
    }


    static boolean block(AstNode root) {
        int now = pointer;
        int nowprint = printPointer;
        AstNode node_block = new AstNode("<Block>");
        if (symbol("LBRACE", node_block)) {
            while (blockItem(node_block));
            if (symbol("RBRACE", node_block)) {
                root.children.add(node_block);
                addGrammar("<Block>");
                return true;
            }
        }
        return falseSolution(now, nowprint);
    }


    static boolean blockItem(AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        AstNode node_blockItem = new AstNode("<BlockItem>");
        if (decl(node_blockItem) || stmt(node_blockItem)) {
            parent.children.add(node_blockItem);
            return true;
        }
        return falseSolution(now, nowPrint);
    }



    public static void addGrammar(String string) {
        if (printPointer == grammar.size()) {
            grammar.add(string);
            printPointer++;
        }
        else {
            grammar.set(printPointer++, string);
        }
    }

    public static boolean falseSolution(int now, int nowPrint) {
        pointer = now;
        printPointer = nowPrint;
        return false;
    }


    public static boolean symbol(String str, AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        String[] temp = lexical.get(pointer++);
        if (str.equals(temp[0])) {
            AstNode node_symbol = new AstNode(temp[0],  temp[1]);
            parent.children.add(node_symbol);
            addGrammar(temp[0] + " " + temp[1]);
            return true;
        }
        return falseSolution(now, nowPrint);
    }

    public static boolean symbol(ArrayList<String> str, AstNode parent) {
        int now = pointer;
        int nowPrint = printPointer;
        String[] temp = lexical.get(pointer++);
        if (str.contains(temp[0])) {
            AstNode node_symbol = new AstNode(temp[0], temp[1]);
            parent.children.add(node_symbol);
            addGrammar(temp[0] + " " + temp[1]);
            return true;
        }
        return falseSolution(now, nowPrint);
    }




}
