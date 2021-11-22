package Grammar;

import Blocks.Block;
import Blocks.BlockList;
import Token.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class GrammarAnal {
    public static Block mainBlock = new Block(true);
    static ArrayList<Token> tokens;
    static int currentTokenIndex = -1;
    static String currentBlockName = "";
    static Token currentSym;

    public static int regIndex = 0;

    public static String getOutputString(ArrayList<Token> lexAnalResult) throws IOException {
        tokens = lexAnalResult;
        currentSym = getNextSym();
        Comp();
        return mainBlock.blockStr;
    }


    static void Comp() throws IOException {
        // 此处是全局变量
        IdentMapList.mapGenerate();
        FuncDef();
    }

    static void FuncDef() throws IOException {
        FuncType();
        Ident();
        if (!currentSym.value.equals("(")) { error(); }
        mainBlock.blockStr += "(";

        getNextSym();
        if (!currentSym.value.equals(")")) { error(); }
        mainBlock.blockStr += ")";
        getNextSym();

        Block(mainBlock);

    }

    static void FuncType() throws IOException {
        if (!currentSym.value.equals("int")) { error(); }
        mainBlock.blockStr += "define dso_local i32 ";
        getNextSym();
    }

    static void Ident() throws IOException {
        if(!currentSym.value.equals("main")){ error(); }
        mainBlock.blockStr += "@main";
        currentBlockName = "main";
        getNextSym();
    }

    static void Block(Block currentBlock) throws IOException {

        IdentMapList.mapGenerate();

        if(!currentSym.value.equals("{")){ error(); }
        if (currentBlock.regNum == -1) currentBlock.blockStr += "{\n";

        getNextSym();
        while (!currentSym.value.equals("}") && !currentSym.type.equals("ERR")) {
            BlockItem(currentBlock);
        }

        if (currentBlock.regNum == -1) {
            concatAllBlocks();
        }

        if (currentBlock.regNum == -1)
            currentBlock.blockStr+="}\n";
        getNextSym();

        IdentMapList.removeTopMap();
    }

    private static void concatAllBlocks() {
        Block mainBlock = BlockList.getMainBlock();
        mainBlock.blockStr += "\n";
        for (Block item : BlockList.blockArrayList) {
            if (item.regNum == -1) continue;
            else if (item.blockStr.equals("")) continue;
            mainBlock.blockStr += item.outputBlockStr()+"\n";
        }
    }

    static void BlockItem(Block currBlock) throws IOException {
        if(currentSym.value.equals("int") || currentSym.value.equals("const")){
            Declare.Decl(currBlock);
        }
        else {
            Statement.Stmt(currBlock);
        }
    }

    static Token getNextSym() {

        currentTokenIndex ++;
        try {
            currentSym = tokens.get(currentTokenIndex);
            if (currentSym.value.equals("9")) {
                System.out.println("here");
            }
            return currentSym;
        }
        catch (Exception e) {
            return null;
        }
    }

    static Token showNextSym() {
        try {
            return tokens.get(currentTokenIndex+1);
        }
        catch (Exception e) {
            System.exit(-1);
        }
        return null;
    }


    static Token getLastSym() {
        currentTokenIndex --;
        try {
            currentSym = tokens.get(currentTokenIndex);
            return currentSym;
        }
        catch (Exception e) {
            System.exit(-1);
        }
        return null;
    }

    static boolean isHaveElse() {
        Stack<String> braceStack = new Stack<>();
        for (int i=0; currentTokenIndex+i<tokens.size(); i++) {
            Token tempToken = tokens.get(currentTokenIndex + i);

            if (braceStack.isEmpty() && tempToken.value.equals("else")) {
                return true;
            }

            if (braceStack.isEmpty() && tempToken.value.equals("if")) {
                return false;
            }

            if (tempToken.value.equals("{")) {
                braceStack.push("{");
            }
            else if (tempToken.value.equals("}")) {
                if (!braceStack.isEmpty()) {
                    braceStack.pop();
                }
                else {
                    return false;
                }
            }
        }

        return false;
    }

    static boolean isHaveOr() {
        for (int i=0; currentTokenIndex+i<tokens.size(); i++) {
            Token tmpToken = tokens.get(currentTokenIndex + i);
            if (tmpToken.value.equals("if")) {
                return false;
            }
            else if (tmpToken.value.equals("||")) {
                return true;
            }
        }
        return false;
    }

    static boolean isContainEq() {
        int leftIndex, rightIndex;
        leftIndex = 0;
        for (int i=0; ;i--) {
            Token tmpToken = tokens.get(currentTokenIndex + i);
            Token lastToken = tokens.get(currentTokenIndex+i-1);
            if ((tmpToken.value.equals("(")&&lastToken.value.equals("if")) || tmpToken.value.equals("&&") || tmpToken.value.equals("||")) {
                leftIndex = currentTokenIndex+i;
                break;
            }
        }
        for (int i=0; ;i++) {
            Token tmpToken = tokens.get(currentTokenIndex + i);
            Token nxtToken = tokens.get(currentTokenIndex + i+1);
            if ((tmpToken.value.equals(")") &&( nxtToken.value.equals("{")||nxtToken.type.equals("IDENT"))) || tmpToken.value.equals("&&") || tmpToken.value.equals("||")) {
                rightIndex = currentTokenIndex+i;
                break;
            }
        }

        for (int i=0; i+leftIndex<=rightIndex; i++) {
            Token tmpToken = tokens.get(i+leftIndex);
            if (tmpToken.value.equals("==") || tmpToken.value.equals("!=") || tmpToken.value.equals("<=") || tmpToken.value.equals(">=") || tmpToken.value.equals("<") || tmpToken.value.equals(">")) return true;
        }
        return false;
    }

    static boolean isContainNot() {
        int lIndex, rIndex;
        for (int i=0; ;i--) {
            Token tmp = tokens.get(currentTokenIndex+i);
            if (tmp.value.equals("if")) {
                lIndex = currentTokenIndex+i;
                break;
            }
        }
        for (int i=0; ; i++) {
            Token tmp = tokens.get(currentTokenIndex+i);
            if (tmp.value.equals("{")) {
                rIndex = currentTokenIndex+i;
                break;
            }
        }
        for (int i=0; i+lIndex<=rIndex; i++) {
            Token tmp1 = tokens.get(lIndex+i);
            Token tmp2 = tokens.get(lIndex+i+1);
            Token tmp3 = tokens.get(lIndex+i+2);

            if(tmp1.value.equals("!")&&tmp2.value.equals("!")&&tmp3.value.equals("!")) {
                return true;
            }
        }
        return false;
    }


    static ArrayList<Token> getConstDefInitExp() {
        ArrayList<Token> defInitTokens = new ArrayList<Token>();
        for (int i=0; !tokens.get(currentTokenIndex+i).value.equals(";"); i++ ) {
            defInitTokens.add(tokens.get(currentTokenIndex+i));
        }
        return defInitTokens;
    }


    static void error() {
        System.exit(-1);
    }
}
