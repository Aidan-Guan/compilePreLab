package Grammar;

import Blocks.Block;
import Blocks.BlockList;
import Token.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GrammarAnal {
    public static Block mainBlock = new Block(true);
    static ArrayList<Token> tokens;
    static int currentTokenIndex = -1;
    static String currentBlockName = "";
    static Token currentSym;
//    static String outStr = "";
    public static int regIndex = 0;

//    static HashMap<String, Integer> varMap = new HashMap<>();
    static HashMap<String, Ident> identMap = new HashMap<>();

    public static String getOutputString(ArrayList<Token> lexAnalResult) throws IOException {
        tokens = lexAnalResult;
        currentSym = getNextSym();
        Comp();
        return mainBlock.blockStr;
    }


    static void Comp() throws IOException {
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
        if(!currentSym.value.equals("{")){ error(); }
        currentBlock.blockStr += "{\n";
        getNextSym();
        while (!currentSym.value.equals("}") && !currentSym.type.equals("ERR")) {
            BlockItem(currentBlock);
        }

        if (currentBlock.regNum == -1) {
            concatAllBlocks();
        }

        currentBlock.blockStr+="}";
        getNextSym();
    }

    private static void concatAllBlocks() {
        Block mainBlock = BlockList.getMainBlock();
        mainBlock.blockStr += "\n";
        for (Block item : BlockList.blockArrayList) {
            if (item.regNum == -1) continue;
            mainBlock.blockStr += item.outputBlockStr();
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
