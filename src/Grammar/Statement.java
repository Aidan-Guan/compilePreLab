package Grammar;

import Blocks.Block;
import Token.*;

import static Grammar.GrammarAnal.*;
import java.io.IOException;

public class Statement {
    static void Stmt() throws IOException {
        if (currentSym.value.equals("{")) {
            GrammarAnal.Block();
        }
        else if (currentSym.value.equals(";")) {
            getNextSym();
        }
        else if (currentSym.value.equals("return")) {
            getNextSym();
            ExpValue expValue = Expression.Exp();
            if (expValue == null) error();
            outStr += Tools.returnOperation(expValue);
            if (!currentSym.value.equals(";")) error();
            getNextSym();
        }
        else if (currentSym.value.equals("if")) {

        }
        else if (currentSym.type.equals("IDENT")) {
            Token nextToken = showNextSym();
            if (nextToken.value.equals("=")) {
                String ident = currentSym.value;
                getNextSym();
                if (!currentSym.value.equals("=")) error();
                getNextSym();
                ExpValue expValue = Expression.Exp();
                if (expValue == null) error();
                getNextSym();
                if (currentSym.value.equals(";")) error();

                if (!updateConstAndVar(ident, expValue)) error();
            }
            else {
                Expression.Exp();
                if (!currentSym.value.equals(";")) error();
                getNextSym();
            }
        }
        else {error();}
    }


    static boolean updateConstAndVar(String ident, ExpValue value) {
        Ident tarIdent = identMap.get(ident);
        if (tarIdent == null) return true;

        if (tarIdent.isConst) return false;
        int reg = tarIdent.regNum;

        outStr += Tools.store(reg, value.out());
        return true;
    }


    private static void IfAnal() throws IOException {
        Block tBlock = new Block();

        if (!currentSym.value.equals("if")) error();
        getNextSym();
        if (!currentSym.value.equals("(")) error();
        Cond(tBlock);
//TODO 把所有的stmt中对于Str的操作作为返回值return Str
        if (!currentSym.value.equals(")")) error();

        Stmt();

        if (!currentSym.value.equals("else")) return;

        Stmt();
    }

    private static void Cond(Block tBlock) throws IOException {
        Blocks.Block currentBlock = new Block();
        outStr += "\tbr label " + currentBlock.out();

        ExpValue expValue = Expression.LOrExp(currentBlock, tBlock);

    }


}
