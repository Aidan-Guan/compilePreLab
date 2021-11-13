package Grammar;

import Blocks.Block;
import Token.ExpValue;
import Token.Ident;
import Token.Token;

import java.io.IOException;

import static Grammar.GrammarAnal.*;

public class Declare {

    static void Decl(Block currBlock) throws IOException {
        if (GrammarAnal.currentSym.value.equals("const")) {
            ConstDecl(currBlock);
        }
        else {
            VarDecl(currBlock);
        }
    }

    static void ConstDecl(Block currBlock) throws IOException {
        if(!GrammarAnal.currentSym.value.equals("const")){ GrammarAnal.error(); }
        GrammarAnal.getNextSym();
        BType();
        ConstDef(currBlock);
        while(GrammarAnal.currentSym.value.equals(",")){
            GrammarAnal.getNextSym();
            ConstDef(currBlock);
        }
        if(!GrammarAnal.currentSym.value.equals(";")){
            GrammarAnal.error();
        }
        GrammarAnal.getNextSym();
    }

    static void BType() throws IOException {
        if(!GrammarAnal.currentSym.value.equals("int")){ GrammarAnal.error(); }
        GrammarAnal.getNextSym();
    }

    static void ConstDef(Block currBlock) throws IOException {
        if (!GrammarAnal.currentSym.type.equals("IDENT")) { GrammarAnal.error(); }
        String constName = currentSym.value;
        GrammarAnal.getNextSym();
        if(!GrammarAnal.currentSym.value.equals("=")){ GrammarAnal.error(); }
        GrammarAnal.getNextSym();

        ExpValue expValue = ConstInitVal(currBlock);

        GrammarAnal.currentSym.isConst = true;
        if(!addConstAndVar(currBlock, constName, expValue, true, true)) GrammarAnal.error();

    }

    static void VarDecl(Block currBlock) throws IOException {
        BType();
        VarDef(currBlock);
        while(GrammarAnal.currentSym.value.equals(",")){
            GrammarAnal.getNextSym();
            VarDef(currBlock);
        }
        if (currentSym.type.equals("IDENT")) getNextSym();
        if(!GrammarAnal.currentSym.value.equals(";")){ GrammarAnal.error(); }
        GrammarAnal.getNextSym();
    }

    static void VarDef(Block currBlock) throws IOException {
        if (!GrammarAnal.currentSym.type.equals("IDENT")) { error(); }
        Token tmpNextSym = showNextSym();
        if(tmpNextSym.value.equals("=")){
            String identName = currentSym.value;
            getNextSym();
            getNextSym();
            ExpValue expValue = InitVal(currBlock);
            if(!addConstAndVar(currBlock, identName, expValue, false, true)) error();
        }
        else {
            ExpValue expValue = new ExpValue(0, false);
            if(!addConstAndVar(currBlock, currentSym.value, expValue, false, false))error();
            getNextSym();
        }
    }


    //TODO: 这里还没看懂
    private static boolean addConstAndVar(Block currBlock, String ident, ExpValue expValue, boolean isConst, boolean isGiven) throws IOException {
        if (expValue == null) {error();}

        if (identMap.get(ident) != null) { return false; }

        identMap.put(ident, new Ident(isConst, ident, regIndex));
        regIndex++;
        currBlock.blockStr += "\t%x"+ (regIndex-1) +" = alloca i32\n";

        if (isGiven) {
            currBlock.blockStr += Tools.store(regIndex-1, expValue.out());
        }

        return true;
    }


    private static ExpValue InitVal(Block currBlock) throws IOException {
        return Expression.Exp(currBlock);
    }


    private static ExpValue ConstInitVal(Block currBlock) throws IOException {
        Tools.checkConstDef();
        return Expression.AddExp(currBlock);
    }
}
