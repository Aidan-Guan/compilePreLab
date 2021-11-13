package Grammar;

import Token.ExpValue;
import Token.Token;

import java.util.ArrayList;

import static Grammar.GrammarAnal.*;

public class Tools {

    static void checkConstDef () {
        ArrayList<Token> constDefExp = GrammarAnal.getConstDefInitExp();
        for (Token item : constDefExp) {
            if (item.type.equals("IDENT")) {
                if (!identMap.get(item.value).isConst) error();
            }
        }
    }

    static ExpValue addOperation (ExpValue a, ExpValue b) {
        ExpValue expValue;
        String outA = a.out();
        String outB = b.out();

        outStr += addModifyStr(regIndex, outA, outB);
        expValue = new ExpValue(regIndex++, true);
        return expValue;
    }


    static ExpValue subOperation (ExpValue a, ExpValue b) {
        ExpValue expValue;
        String outA = a.out();
        String outB = b.out();

        outStr += subModifyStr(regIndex, outA, outB);
        expValue = new ExpValue(regIndex++, true);
        return expValue;
    }


    static ExpValue mulOperation (ExpValue a, ExpValue b) {
        ExpValue expValue;
        String outA = a.out(), outB = b.out();

        outStr += mulModifyStr(regIndex, outA, outB);
        expValue = new ExpValue(regIndex++, true);
        return expValue;
    }


    static ExpValue divOperation (ExpValue a, ExpValue b) {
        ExpValue expValue;
        String outA = a.out();
        String outB = b.out();

        outStr += divModifyStr(regIndex, outA, outB);
        expValue = new ExpValue(regIndex++, true);
        return expValue;
    }


    static ExpValue modOperation (ExpValue a, ExpValue b) {
        ExpValue expValue;
        String outA = a.out();
        String outB = b.out();

        outStr += modModifyStr(regIndex, outA, outB);
        expValue = new ExpValue(regIndex++, true);
        return expValue;
    }


    private static String addModifyStr(int regNum, String outA, String outB) {
        return "\t%x" + regNum + " = add i32 " +outA + ", " + outB + "\n";
    }


    private static String subModifyStr(int regNum, String outA, String outB) {
        return "\t%x" + regNum + " = sub i32 " +outA + ", " + outB + "\n";
    }


    private static String mulModifyStr(int regNum, String outA, String outB){
         return "\t%x" + regNum + " = mul i32 " +outA + ", " + outB + "\n";
    }


    private static String divModifyStr(int regNum, String outA, String outB) {
        return "\t%x" + regNum + " = sdiv i32 " +outA + ", " + outB + "\n";
    }


    private static String modModifyStr(int regNum, String outA, String outB) {
        return "\t%x" + regNum + " = srem i32 " +outA + ", " + outB + "\n";
    }


    static String load(int regNum, String outReg) {
        return "\t%x" + regNum + " = load i32, i32* " + outReg +"\n";
    }


    static String store(int regNum, String outReg) {
        return "\tstore i32 " + outReg + ", i32* %x" + regNum + "\n";
    }


    static String returnOperation (ExpValue a) {
        String outA = a.out();
        return "\tret i32 " + outA + "\n";
    }
}
