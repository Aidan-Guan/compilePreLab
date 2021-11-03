import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class Lab2_GrammarAnalysis {


    // 当前的token
    static Lab2_Token lastSym;
    static Lab2_Token currentSym;
    static Lab2_Token futureSym;
    static String process = "";
    static int regIndex = 0;
    static HashMap<String, Integer> identRegMap = new HashMap<>();


    static {
        try {
            currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
        } catch (IOException e) {
        }
    }

    /**
     * 处理CompUnit文法
     */
    public static void compUnitAnal() throws IOException {
        funcDefAnal();
    }

    /**
     * 处理FuncDef文法
     */
    private static void funcDefAnal() throws IOException {
        funcTypeAnal();
        identAnal();

        // 出现错误，退出
        if (currentSym == null || !currentSym.value.equals("(")) {
            System.exit(-1);
        }
        // 处理(的输出
        Lab2_Test.outputStr += "(";


        getNextSym();
        if (currentSym == null || !currentSym.value.equals(")")) {
            System.exit(-1);
        }
        Lab2_Test.outputStr += ")";

        getNextSym();

        blockAnal();
    }

    /**
     * 处理Funcype文法
     */
    private static void funcTypeAnal() throws IOException {
        if (currentSym == null || !currentSym.value.equals("int")) {
            System.exit(-1);
        }
        Lab2_Test.outputStr += "define dso_local i32";

        currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
    }

    /**
     * 处理Ident文法
     */
    private static void identAnal() throws IOException {
        if (currentSym == null || !currentSym.value.equals("main")) {
            System.exit(-1);
        }

        //对于main的输出
        Lab2_Test.outputStr += "@main";
        currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
    }

    /**
     * 处理Block文法
     */
    private static void blockAnal() throws IOException {
        if (currentSym == null || !currentSym.value.equals("{")) {
            System.exit(1);
        }
        Lab2_Test.outputStr += "{";

        getNextSym();
        while (true) {
            if (!(currentSym.value.equals("return")||currentSym.value.equals("int")||currentSym.value.equals("const")||currentSym.type.equals("IDENT"))){break;}
            blockItemAnal();
        }

        if (currentSym == null || !currentSym.value.equals("}")) {
            System.exit(1);
        }
        Lab2_Test.outputStr += "}";

        getNextSym();
        if (currentSym != null) {
            System.exit(2);
        }
    }

    private static void blockItemAnal() throws IOException {
        if (currentSym.value.equals("int") || currentSym.value.equals("const")) {
            declAnal();
        }
        else if (currentSym.value.equals("return") || currentSym.value.equals(";") || currentSym.type.equals("IDENT")) {
            stmtAnal();
        }
    }


    private static void declAnal() throws IOException {
        if (currentSym.value.equals("const")) {
            constDeclAnal();
        }
        else if (currentSym.value.equals("int")) {
            varDeclAnal();
        }
        else { System.exit(-1); }
    }

    private static void varDeclAnal() throws IOException {
        if (!currentSym.value.equals("int")) {System.exit(-1);}
        getNextSym();
        varDefAnal();

        while (true) {
            if (!currentSym.value.equals(",")) { break;}
            varDefAnal();
        }
        getNextSym();
        if (!currentSym.value.equals(";")) { System.exit(-1);}
        getNextSym();
    }

    private static void varDefAnal() throws IOException {
        if (!currentSym.type.equals("IDENT")) {System.exit(-1);}

        getNextSym();
        if (currentSym.value.equals("=")) {
            getNextSym();
            Lab2_Token result = initValAnal();
            Lab2_Test.outputStr += "%"+regIndex+" = alloca i32\n";
            Lab2_Test.outputStr += "store i32 %"+result.value+", i32* %"+String.valueOf(regIndex)+"\n";
            regIndex++;
        }
    }

    private static Lab2_Token initValAnal() throws IOException {
        return exp();
    }

    private static void constDeclAnal() throws IOException {
        if (!currentSym.value.equals("const")) { System.exit(-1); }
        getNextSym();
        bTypeAnal();
        constDefAnal();

        while (true) {
            if (!currentSym.value.equals(",")) {break;}
            constDefAnal();
        }
        getNextSym();
        if (!currentSym.value.equals(";")) {System.exit(-1);}
        getNextSym();
    }

    private static void bTypeAnal() throws IOException {
        if (!currentSym.value.equals("int")) { System.exit(-1); }
        currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
    }

    private static void constDefAnal() throws IOException{
        if (!currentSym.type.equals("IDENT")) {
            System.exit(-1);
        }
        String identName = currentSym.value;
        getNextSym();
        if (!currentSym.value.equals("=")) {
            System.exit(-1);
        }
        getNextSym();
        Lab2_Token result = constInitValAnal();

        Lab2_Test.outputStr += "%" + String.valueOf(regIndex) + " = alloca i32\n";
        Lab2_Test.outputStr += "store i32 %" + result.value + ", i32* %" + String.valueOf(regIndex) + "\n";
    }

    private static Lab2_Token exp() throws IOException {
        return addExpAnal();
    }

    private static Lab2_Token constInitValAnal() throws IOException {
        return constExpAnal();
    }

    private static Lab2_Token constExpAnal() throws IOException {
        return addExpAnal();
    }

    private static Lab2_Token addExpAnal() throws IOException {
        Lab2_Token result = mulExpAnal();

        while (true) {
            if (currentSym.value.equals("+")) {
                getNextSym();
                Lab2_Token result2 = mulExpAnal();

                Lab2_Test.outputStr += "%"+String.valueOf(regIndex) + " = add i32 "+ result.output() + ", " + result2.output();
                result.value = String.valueOf(regIndex);
                regIndex++;
            }
            else if (currentSym.value.equals("-")) {
                getNextSym();
                Lab2_Token result2 = mulExpAnal();

                Lab2_Test.outputStr += "%"+String.valueOf(regIndex) + " = sub i32 "+ result.output() + ", "+ result2.output();
                result.value = String.valueOf(regIndex);
                regIndex++;
            }
            else { break; }
        }

        return result;
    }

    private static Lab2_Token mulExpAnal() throws IOException {
        Lab2_Token result = unaryExp();
        getNextSym();
        while (true) {
            if (currentSym.value.equals("*")) {
                getNextSym();
                Lab2_Token result2 = unaryExp();

                Lab2_Test.outputStr += "%"+String.valueOf(regIndex) + " = mul i32 "+ result.output() + ", " + result2.output();
                result.value = String.valueOf(regIndex);
                regIndex++;
            }
            else if (currentSym.value.equals("/")) {
                getNextSym();
                Lab2_Token result2 = unaryExp();

                Lab2_Test.outputStr += "%"+String.valueOf(regIndex) + " = sdiv i32 "+ result.output() + ", " + result2.output();
                result.value = String.valueOf(regIndex);
                regIndex++;
            }
            else if (currentSym.value.equals("%")) {
                getNextSym();
                Lab2_Token result2 = unaryExp();

                Lab2_Test.outputStr += "%"+String.valueOf(regIndex) + " = mod i32 "+ result.output() + ", " + result2.output();
                result.value = String.valueOf(regIndex);
                regIndex++;
            }
            else {break;}
        }
        return result;
    }

    private static Lab2_Token unaryExp() throws IOException {
        if (currentSym.value.equals("-")) {
            getNextSym();
            Lab2_Token result = unaryExp();

            if (result.type.equals("REG")) {
                Lab2_Test.outputStr += "%" + regIndex + " = sub i32 0, %" + result.value+"\n";
            }
            else if (result.type.equals("NUMBER")) {
                Lab2_Test.outputStr += "%" + regIndex + " = sub i32 0, " + result.value+"\n";
            }

            result =  new Lab2_Token("REG", String.valueOf(regIndex));
            regIndex++;
            return result;
        }
        else if (currentSym.value.equals("+")) {
            getNextSym();
            return unaryExp();
        }
        else if (currentSym.value.equals("(")) {
            getNextSym();
            Lab2_Token result = exp();
            if (!currentSym.value.equals(")")) {System.exit(-1);}
        }
        else if (currentSym.type.equals("NUMBER")) {
            return currentSym;
        }
        else if (currentSym.type.equals("IDENT")) {
            Integer identIndex = identRegMap.get(currentSym.value);
            if (identIndex == null) {System.exit(-1);}
            return new Lab2_Token("REG", String.valueOf(identIndex));
        }
        else {
           System.exit(-1);
        }
    }

    private static void stmtAnal() throws IOException {
        if (currentSym.value.equals("return")) {
            getNextSym();
            expAnal();
            if (!currentSym.value.equals(";")) {System.exit(-1);}
            getNextSym();
        }
        else if (currentSym.value.equals(";")){}
        else {
            getNextSym();
            if (currentSym.value.equals("=")) {
                rollbackSym();
                lValAnal();
            }
            else {
                rollbackSym();
                expAnal();
            }
        }
    }

    private static void lValAnal() throws IOException {
        if (!currentSym.type.equals("IDENT")) {System.exit(-1);}
        getNextSym();
    }

    private static int numberAnal() throws IOException {
        int numberResult = 0;
        if (currentSym.type.equals("NUMBER")) {
            Lab2_Test.outputStr += currentSym.value;
            numberResult = Integer.parseInt(currentSym.value);
            currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
        }
        else {
            System.exit(20);
        }
        return numberResult;
    }

    private static void getNextSym() throws IOException {
        if (futureSym!=null) {
            lastSym = currentSym;
            currentSym = futureSym;
            futureSym = null;
        }
        else {
            lastSym = currentSym;
            currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
        }
    }

    private static void rollbackSym() throws IOException {
        futureSym = currentSym;
        currentSym = lastSym;
    }

//    private static void mulExp2Anal() throws IOException {
//        //TODO: 确认为空的时候是null
//        if (currentSym == null) {
//            return;
//        }
//
//
//        if (currentSym.value.equals("*") || currentSym.value.equals("/") || currentSym.value.equals("%")) {
//            Lab2_Test.outputStr += currentSym.value;
//            currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
//
//            unaryExpAnal();
//            mulExp2Anal();
//        }
//
//    }

//    private static void unaryExpAnal() throws IOException {
//        /* 处理null情况 */
//        if (currentSym == null) {
//            System.exit(20);
//        }
//
//        /* unaryExp文法 */
//        if (currentSym.value.equals("+") || currentSym.value.equals("-")) {
//            Lab2_Test.outputStr += currentSym.value;
//            currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
//            unaryExpAnal();
//        }
//        /* primaryExp文法 */
//        else if (currentSym.value.equals("(")) {
//            Lab2_Test.outputStr += currentSym.value;
//            currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
//            expAnal(); //TODO: 确保之后会读入下一个新字符
//            if (currentSym == null || !currentSym.value.equals(")")) {System.exit(1);}
//            Lab2_Test.outputStr += currentSym.value;
//            currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
//        }
//        else if (currentSym.type.equals("NUMBER")) {
//            Lab2_Test.outputStr += currentSym.value;
//            currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
//        }
//        else {
//            System.exit(20);
//        }
//    }
}

//错误对照表
//1   括号有问题
//2   返回类型有错
//3   main函数错误
//4   return有误
//5   并不是number
//6   分号有问题
//20  读取有问题
//21  读取运算符错误
