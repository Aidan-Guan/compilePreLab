package Grammar;

import Token.ExpValue;
import Token.Ident;

import java.io.IOException;

import static Grammar.GrammarAnal.*;

public class Declare {

    static void Decl() throws IOException {
        if (GrammarAnal.currentSym.value.equals("const")) {
            ConstDecl();
        }
        else {
            VarDecl();
        }
    }

    static void ConstDecl() throws IOException {
        if(!GrammarAnal.currentSym.value.equals("const")){ GrammarAnal.error(); }
        GrammarAnal.getNextSym();
        BType();
        ConstDef();
        while(GrammarAnal.currentSym.value.equals(",")){
            GrammarAnal.getNextSym();
            ConstDef();
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

    static void ConstDef() throws IOException {
        if (!GrammarAnal.currentSym.type.equals("IDENT")) { GrammarAnal.error(); }
        GrammarAnal.getNextSym();
        if(!GrammarAnal.currentSym.value.equals("=")){ GrammarAnal.error(); }
        GrammarAnal.getNextSym();

        ExpValue expValue = ConstInitVal();

        GrammarAnal.currentSym.isConst = true;
        if(!addConstAndVar(currentSym.value, expValue, true, true)) GrammarAnal.error();

    }

    static void VarDecl() throws IOException {
        BType();
        VarDef();
        while(GrammarAnal.currentSym.value.equals(",")){
            GrammarAnal.getNextSym();
            VarDef();
        }
        if(!GrammarAnal.currentSym.value.equals(";")){ GrammarAnal.error(); }
        GrammarAnal.getNextSym();
    }

    static void VarDef() throws IOException {
        if (!GrammarAnal.currentSym.type.equals("IDENT")) { error(); }
        GrammarAnal.getNextSym();
        if(GrammarAnal.currentSym.value.equals("=")){
            GrammarAnal.getNextSym();
            ExpValue expValue = InitVal();
            if(!addConstAndVar(currentSym.value, expValue, false, true)) error();
        }
        else {
            ExpValue expValue = new ExpValue(0, false);
            if(!addConstAndVar(currentSym.value, expValue, false, false))error();
        }
    }


    //TODO: 这里还没看懂
    private static boolean addConstAndVar(String ident, ExpValue expValue, boolean isConst, boolean isGiven) throws IOException {
        if (expValue == null) {error();}

        if (identMap.get(ident) != null) { return false; }

        identMap.put(ident, new Ident(isConst, ident, regIndex));
        regIndex++;
        outStr += "\t%"+ (regIndex-1) +" = alloca i32\n";

        if (isGiven) {
            outStr += Tools.store(regIndex-1, expValue.out());
        }
        return true;
    }


    private static ExpValue InitVal() throws IOException {
        return Expression.Exp();
    }


    private static ExpValue ConstInitVal() throws IOException {
        return Expression.AddExp();
    }
}
