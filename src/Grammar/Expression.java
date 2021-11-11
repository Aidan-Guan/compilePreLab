package Grammar;

import Token.ExpValue;
import static Grammar.Tools.mulOperation;

import java.io.IOException;

public class Expression {
    static ExpValue Exp() throws IOException {
        return AddExp();
    }

    static ExpValue AddExp() throws IOException {
        ExpValue expValue = MulExp();
        while(GrammarAnal.currentSym.value.equals("+") || GrammarAnal.currentSym.value.equals("-")){
            if(GrammarAnal.currentSym.value.equals("+")){
                GrammarAnal.getNextSym();
                expValue = Tools.addOperation(expValue, MulExp());
            }
            else {
                GrammarAnal.getNextSym();
                expValue = Tools.subOperation(expValue, MulExp());
            }
        }
        return expValue;
    }

    static ExpValue MulExp() throws IOException {
        ExpValue expValue = UnaryExp();
        while(GrammarAnal.currentSym.value.equals("*") || GrammarAnal.currentSym.value.equals("/") || currentSym.value.equals("%")){
            if(GrammarAnal.currentSym.value.equals("*")){
                GrammarAnal.getNextSym();
                expValue = mulOperation(expValue, UnaryExp());
            }
            else if(GrammarAnal.currentSym.value.equals("/")){
                GrammarAnal.getNextSym();
                expValue = division(expValue, UnaryExp());
            }
            else {
                GrammarAnal.getNextSym();
                expValue = mod(expValue, UnaryExp());
            }
        }
        return expValue;
    }

    static ExpValue UnaryExp() throws IOException {

    }
}
