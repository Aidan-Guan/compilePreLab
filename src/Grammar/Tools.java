package Grammar;

import Blocks.Block;
import Token.*;

import java.util.ArrayList;

import static Grammar.GrammarAnal.*;

public class Tools {

    public static ArrayList<Integer> i1RegArrayList = new ArrayList<>();

    static void checkConstDef () {
        ArrayList<Token> constDefExp = GrammarAnal.getConstDefInitExp();
        for (Token item : constDefExp) {
            if (item.type.equals("IDENT")) {
                if (!IdentMapList.getCurrMap().get(item.value).isConst) error();
            }
        }
    }

    static ExpValue addOperation (Block currBlock, ExpValue a, ExpValue b) {
        ExpValue expValue;
        String outA = a.out();
        String outB = b.out();

        currBlock.blockStr += addModifyStr(regIndex, outA, outB);
        expValue = new ExpValue(regIndex++, true);
        return expValue;
    }


    static ExpValue subOperation (Block currBlock, ExpValue a, ExpValue b) {
        ExpValue expValue;
        String outA = a.out();
        String outB = b.out();

        currBlock.blockStr += subModifyStr(regIndex, outA, outB);
        expValue = new ExpValue(regIndex++, true);
        return expValue;
    }


    static ExpValue mulOperation (Block currBlock, ExpValue a, ExpValue b) {
        ExpValue expValue;
        String outA = a.out(), outB = b.out();

        currBlock.blockStr += mulModifyStr(regIndex, outA, outB);
        expValue = new ExpValue(regIndex++, true);
        return expValue;
    }


    static ExpValue divOperation (Block currBlock, ExpValue a, ExpValue b) {
        ExpValue expValue;
        String outA = a.out();
        String outB = b.out();

        currBlock.blockStr += divModifyStr(regIndex, outA, outB);
        expValue = new ExpValue(regIndex++, true);
        return expValue;
    }


    static ExpValue modOperation (Block currBlock, ExpValue a, ExpValue b) {
        ExpValue expValue;
        String outA = a.out();
        String outB = b.out();

        currBlock.blockStr += modModifyStr(regIndex, outA, outB);
        expValue = new ExpValue(regIndex++, true);
        return expValue;
    }


    static ExpValue notOperation (Block currBlock, ExpValue a) {
        ExpValue expValue;

        currBlock.blockStr += notModifyStr(regIndex, a);
        expValue = new ExpValue(regIndex, true);
        regIndex++;

//        currBlock.blockStr += zextModifyStr(regIndex, "%x"+String.valueOf(regIndex-1));
//        regIndex++;
        return expValue;
    }


    static ExpValue zextOperation (Block currBlock, ExpValue a) {
        ExpValue expValue;
        currBlock.blockStr += zextModifyStr(regIndex++, a.out());
        expValue = new ExpValue(regIndex-1, true);
        return expValue;
    }


    private static String notModifyStr(int regNum, ExpValue a) {
        if (i1RegArrayList.contains(a.value)) {
            i1RegArrayList.add(regNum);
            return "\t%x" + regNum + " = icmp eq i1 " + a.out() + ", 0\n";
        }
        else {
            i1RegArrayList.add(regNum);
            return "\t%x" + regNum + " = icmp eq i32 " + a.out() + ", 0\n";
        }
    }


    private static String zextModifyStr(int regNum, String outA) {
        return "\t%x" + regNum + " = zext i1 "+outA+" to i32\n";
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
