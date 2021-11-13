package Grammar;

import Blocks.Block;
import Blocks.BlockList;
import Token.ExpValue;
import Token.Token;

import java.io.IOException;

import static Grammar.GrammarAnal.*;

public class FuncAnal {

    static ExpValue FuncRParams(Block currBlock) throws IOException {
        ExpValue expValue =Expression.Exp(currBlock);
        while (currentSym.value.equals(",")) {
            getNextSym();
            Expression.Exp(currBlock);
        }
        return expValue;
    }

    static ExpValue resolveFunc(Block currBlock, Token ident, ExpValue param) throws IOException {
        ExpValue expValue = null;

        switch (ident.value) {
            case "getint" -> {
                if (param != null) { error(); }
                expValue = new ExpValue(regIndex++, true);
                currBlock.blockStr += "\t%x" + (regIndex-1) +" = call i32 @getint()\n";
                declareFunc("declare i32 @getint()");
            }
            case "getch" -> {
                if (param != null) { error(); }
                expValue = new ExpValue(regIndex++, true);
                currBlock.blockStr += "\t%x" + (regIndex-1) +" = call i32 @getch()\n";
                declareFunc("declare i32 @getch()");
            }
            case "putint" -> {
                if (param == null) { error(); }
                String output = param.value + "";
                if (param.isRegister) { output = param.out(); }
                currBlock.blockStr += "\tcall void @putint(i32 " + output + ")\n";
                declareFunc("declare void @putint(i32)");
            }
            case "putch" -> {
                if (param == null) { error(); }
                String output = param.value+"";
                if (param.isRegister) {
                    output = param.out();
                }
                currBlock.blockStr += "\tcall void @putch(i32 " + output + ")\n";
                declareFunc("declare void @putch(i32)");
            }
        }
        return expValue;
    }

    private static void declareFunc(String func) {
        Block mainBlock = BlockList.getMainBlock();

        int loc = mainBlock.blockStr.indexOf(func);
        if (loc == -1) {
            mainBlock.blockStr = func + "\n" + mainBlock.blockStr;
        }
    }


}
