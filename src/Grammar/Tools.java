package Grammar;

import Token.ExpValue;
import static Grammar.GrammarAnal.*;

public class Tools {

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
        return "\t%" + regNum + " = add i32 " +outA + ", " + outB + "\n";
    }


    private static String subModifyStr(int regNum, String outA, String outB) {
        return "\t%" + regNum + " = sub i32 " +outA + ", " + outB + "\n";
    }


    private static String mulModifyStr(int regNum, String outA, String outB){
         return "\t%" + regNum + " = mul i32 " +outA + ", " + outB + "\n";
    }


    private static String divModifyStr(int regNum, String outA, String outB) {
        return "\t%" + regNum + " = sdiv i32 " +outA + ", " + outB + "\n";
    }


    private static String modModifyStr(int regNum, String outA, String outB) {
        return "\t%" + regNum + " = srem i32 " +outA + ", " + outB + "\n";
    }
}
