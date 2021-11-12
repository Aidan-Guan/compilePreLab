package Grammar;

import Token.ExpValue;
import Token.Token;

import java.io.IOException;

import static Grammar.GrammarAnal.*;

public class FuncAnal {

    static ExpValue FuncRParams() throws IOException {
        ExpValue expValue =Expression.Exp();
        while (currentSym.value.equals(",")) {
            getNextSym();
            Expression.Exp();
        }
        return expValue;
    }

    static ExpValue resolveFunc(Token ident, ExpValue param) throws IOException {
        ExpValue expValue = null;

        switch (ident.value) {
            case "getint" -> {
                if (param != null) { error(); }
                expValue = new ExpValue(regIndex++, true);
                outStr += "\t%x" + (regIndex-1) +" = call i32 @getint()\n";
            }
            case "getch" -> {
                if (param != null) { error(); }
                expValue = new ExpValue(regIndex++, true);
                outStr += "\t%x" + (regIndex-1) +" = call i32 @getch()\n";
            }
            case "putint" -> {
                if (param == null) { error(); }
                String output = param.value + "";
                if (param.isRegister) { output = param.out(); }
                outStr += "\tcall void @putint(i32 " + output + ")\n";
            }
            case "putch" -> {
                if (param == null) { error(); }
                String output = param.value+"";
                if (param.isRegister) {
                    output = param.out();
                }
                outStr += "\tcall void @putch(i32 " + output + ")\n";
            }
        }
        return expValue;
    }


}
