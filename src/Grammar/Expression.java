package Grammar;

import Token.ExpValue;
import Token.Token;

import static Grammar.GrammarAnal.*;

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
                expValue = Tools.mulOperation(expValue, UnaryExp());
            }
            else if(GrammarAnal.currentSym.value.equals("/")){
                GrammarAnal.getNextSym();
                expValue = Tools.divOperation(expValue, UnaryExp());
            }
            else {
                GrammarAnal.getNextSym();
                expValue = Tools.modOperation(expValue, UnaryExp());
            }
        }
        return expValue;
    }

    static ExpValue UnaryExp() throws IOException {
        if (currentSym.value.equals("+") || currentSym.value.equals("-")) {
            String sign = currentSym.value;
            ExpValue expValue = UnaryExp();
            if (sign.equals("-")) {
                return Tools.subOperation(new ExpValue(0, false), expValue);
            }
            else {
                return expValue;
            }
        }
        else if (currentSym.value.equals("(") || currentSym.value.equals("NUMBER")) {
            return PrimaryExp();
        }
        else if (currentSym.type.equals("IDENT")) {
            Token nextSym = showNextSym();
            if (nextSym.value.equals("(")) {
                getNextSym();
                getNextSym();

                if (!currentSym.value.equals(")")) {
                    ExpValue param = FuncAnal.FuncRParams();
                    ExpValue expValue = FuncAnal.resolveFunc(currentSym, param);
                    if (!currentSym.value.equals(")")) {error();}
                    getNextSym();
                    return expValue;
                }
                else {
                    ExpValue param = FuncAnal.FuncRParams();
                    ExpValue expValue = FuncAnal.resolveFunc(currentSym, param);
                    getNextSym();
                    return expValue;
                }
            }
            else {
                return PrimaryExp();
            }
        }
        return null;
    }


    private static ExpValue PrimaryExp() throws IOException {
        if (currentSym.value.equals("(")) {
            getNextSym();
            ExpValue expValue = Exp();
            if (!currentSym.value.equals(")")) {error();}
            getNextSym();
            return expValue;
        }
        else if (currentSym.type.equals("NUMBER")) {
            ExpValue expValue = new ExpValue(Integer.parseInt(currentSym.value),false);
            getNextSym();
            return expValue;
        }
        else {
            if (!currentSym.type.equals("IDENT")) {error();}
            Integer varReg = varMap.getOrDefault(currentSym.value, -1);
            if (varReg==-1 || currentSym.isConst) {error();}

            ExpValue expValue = new ExpValue(varReg,true);
            outStr += Tools.load(regIndex++, expValue.out());
            return new ExpValue(regIndex-1, true);
        }
    }



}
