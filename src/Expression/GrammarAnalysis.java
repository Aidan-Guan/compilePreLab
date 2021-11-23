package Expression;

import AST.AstNode;
import LexicalAnalysis.Token;

import java.util.AbstractList;
import java.util.ArrayList;

public class GrammarAnalysis {
    private static ArrayList<Token> tokens;
    private static ArrayList<String> grammar = new ArrayList<>();
    private static int tokensIndex = 0;
    public static AstNode root;

    private static int pointer = 0;
    private static int printPointer = 0;

    public static void grammarAnal(ArrayList<Token> tokenList) {
        tokens = tokenList;
        compUnit();
    }

    private static boolean compUnit() {
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

    private static boolean decl (AstNode node) {

    }

    private static boolean MainFuncDef (AstNode node) {

    }


    private static void addGrammar (String string) {
        if (printPointer == grammar.size()) {
            grammar.add(string);
            printPointer++;
        }
        else {
            grammar.set(printPointer++, string);
        }
    }

    private static boolean falseSolution (int now, int nowPrint) {
        pointer = now;
        printPointer = nowPrint;
        return false;
    }

}
