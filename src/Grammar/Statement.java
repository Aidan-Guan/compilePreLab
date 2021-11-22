package Grammar;

import Blocks.Block;
import Token.*;

import static Grammar.GrammarAnal.*;
import java.io.IOException;

public class Statement {
    static void Stmt(Block currBlock) throws IOException {
        if (currentSym.value.equals("{")) {
            GrammarAnal.Block(currBlock);
        }
        else if (currentSym.value.equals(";")) {
            getNextSym();
        }
        else if (currentSym.value.equals("return")) {
            getNextSym();
            ExpValue expValue = Expression.Exp(currBlock);
            if (expValue == null) error();
            currBlock.blockStr += Tools.returnOperation(expValue);
            if (!currentSym.value.equals(";")) error();
            getNextSym();
        }
        else if (currentSym.value.equals("if")) {
            if (currBlock.compBlock == null) currBlock.compBlock = new Block();
//            Block resBlock = new Block();
            IfAnal(currBlock, currBlock.compBlock);
            while (!currentSym.value.equals("}") && !currentSym.type.equals("ERR")) {
                BlockItem(currBlock.compBlock);
            }
        }
        else if (currentSym.type.equals("IDENT")) {
            Token nextToken = showNextSym();
            if (nextToken.value.equals("=")) {
                String ident = currentSym.value;
                getNextSym();
                if (!currentSym.value.equals("=")) error();
                getNextSym();
                ExpValue expValue = Expression.Exp(currBlock);
                if (expValue == null) error();
                getNextSym();
                if (currentSym.value.equals(";")) error();

                if (!updateConstAndVar(currBlock, ident, expValue)) error();
            }
            else {
                Expression.Exp(currBlock);
                if (!currentSym.value.equals(";")) error();
                getNextSym();
            }
        }
        else {error();}
    }


    static boolean updateConstAndVar(Block currBlock, String ident, ExpValue value) {
        Ident tarIdent = IdentMapList.getCurrMap().get(ident);
        if (tarIdent == null) return false;

        if (tarIdent.isConst) return false;
        int reg = tarIdent.regNum;

        currBlock.blockStr += Tools.store(reg, value.out());
        return true;
    }


    private static void IfAnal(Block currBlock, Block resBlock) throws IOException {
        currBlock.compBlock = resBlock;
        Block tBlock = new Block(currBlock.compBlock);

        if (!currentSym.value.equals("if")) error();
        getNextSym();
        if (!currentSym.value.equals("(")) error();
        getNextSym();
        Block fBlock = Cond(currBlock, tBlock);

        if (!currentSym.value.equals(")")) error();
        getNextSym();
//        tBlock.compBlock = resBlock;
        Stmt(tBlock);
        tBlock.blockStr += "\tbr label "+resBlock.out()+"\n";

        if (!currentSym.value.equals("else")) return;
        getNextSym();
        Stmt(fBlock);
        if (!fBlock.blockStr.contains("br label"))
            fBlock.blockStr += "\tbr label "+resBlock.out()+"\n";


    }

    private static Block Cond(Block currBlock, Block tBlock) throws IOException {
        Blocks.Block currentBlock = new Block(currBlock.compBlock);
        currBlock.blockStr += "\tbr label " + currentBlock.out()+"\n";

        Block fBlock = Expression.LOrExp(currentBlock, tBlock);

        return fBlock;
    }


}
