package Grammar;

import Token.ExpValue;
import static Grammar.GrammarAnal.*;

public class Tools {

    static ExpValue addOperation(ExpValue a, ExpValue b) {
        ExpValue expValue;
        String outA = a.out();
        String outB = b.out();

        outStr += addModifyStr(regIndex, outA, outB);
        expValue = new ExpValue(regIndex++, true);
        return expValue;
    }


    private static String addModifyStr(int regNum, String outA, String outB) {
        return "\t%" + regNum + " = add i32 " +outA + ", " + outB + "\n";
    }


    static ExpValue subOperation(ExpValue a, ExpValue b) {
        ExpValue expValue;
        String outA = a.out();
        String outB = b.out();

        outStr += subModifyStr(regIndex, outA, outB);
        expValue = new ExpValue(regIndex++, true);
        return expValue;
    }


    private static String subModifyStr(int regNum, String outA, String outB) {
        return "\t%" + regNum + " = sub i32 " +outA + ", " + outB + "\n";
    }


    static ExpValue mulOperation(ExpValue a, ExpValue b) {
        ExpValue expValue;
        String outA = a.out(), outB = b.out();

        outStr += mulModifyStr(regIndex, outA, outB);
        expValue = new ExpValue(regIndex++, true);
        return expValue;
    }


    private static String mulModifyStr(int regNum, String outA, String outB){
         return "\t%" + regNum + " = mul i32 " +outA + ", " + outB + "\n";
    }
}
