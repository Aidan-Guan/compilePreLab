package Grammar;

import Token.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GrammarAnal {
    static ArrayList<Token> tokens;
    static int currentTokenIndex = -1;
    static String currentBlockName = "";
    static Token currentSym;
    static String outStr = "";
    static int regIndex = 0;

    static HashMap<String, Integer> varMap = new HashMap<>();
    static HashMap<String, Ident> identMap = new HashMap<>();

    public static String getOutputString(ArrayList<Token> lexAnalResult) {
        tokens = lexAnalResult;
        currentSym = getNextSym();

        return "";
    }


    static void Comp() throws IOException {
        FuncDef();
    }

    static void FuncDef() throws IOException {
        FuncType();
        Ident();
        if (!currentSym.value.equals("(")) { error(); }
        outStr += "(";

        getNextSym();
        if (!currentSym.value.equals(")")) { error(); }
        outStr += ")";
        getNextSym();

        Block();
    }

    static void FuncType() throws IOException {
        if (!currentSym.value.equals("int")) { error(); }
        outStr += "define dso_local i32 ";
        getNextSym();
    }

    static void Ident() throws IOException {
        if(!currentSym.value.equals("main")){ error(); }
        outStr += "@main";
        currentBlockName = "main";
        getNextSym();
    }

    static void Block() throws IOException {
        if(!currentSym.value.equals("{")){ error(); }
        outStr += "{\n";
        getNextSym();
        while (!currentSym.value.equals("}") && !currentSym.type.equals("ERR")) {
            BlockItem();
        }
        outStr+="}";
        getNextSym();
    }

    static void BlockItem() throws IOException {
        if(currentSym.value.equals("int") || currentSym.value.equals("const")){
            Declare.Decl();
        }
        else {
            Stmt();
        }
    }

    static Token getNextSym() {
        currentTokenIndex ++;
        try {
            return tokens.get(currentTokenIndex);
        }
        catch (Exception e) {
            System.exit(-1);
        }
        return null;
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
            return tokens.get(currentTokenIndex);
        }
        catch (Exception e) {
            System.exit(-1);
        }
        return null;
    }


    static void error() {
        System.exit(-1);
    }
}
