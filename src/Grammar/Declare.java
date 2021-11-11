package Grammar;


import java.io.IOException;

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
        String ident = IdentID();
        if (!GrammarAnal.currentSym.type.equals("IDENT")) { GrammarAnal.error(); }
        GrammarAnal.getNextSym();
        if(!GrammarAnal.currentSym.value.equals("=")){ GrammarAnal.error(); }
        GrammarAnal.getNextSym();
        GrammarAnal.currentSym.isConst = true;

        isConst = true;
        ExpValue expValue = ConstInitVal();
        isConst = false;

        if(!addConstAndVar(ident, expValue, true, true)) GrammarAnal.error();

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
        String ident = IdentID();
        if (!GrammarAnal.currentSym.type.equals("IDENT")) { GrammarAnal.error(); }
        GrammarAnal.getNextSym();
        if(GrammarAnal.currentSym.value.equals("=")){
            GrammarAnal.getNextSym();
            ExpValue expValue = InitVal();
            if(!addConstAndVar(ident, expValue, false, true)) error();
        }
        else {
            ExpValue expValue = new ExpValue(0, false);
            if(!addConstAndVar(ident, expValue, false, false))error();
        }
    }
}
