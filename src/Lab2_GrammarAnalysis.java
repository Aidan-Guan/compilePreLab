import java.io.IOException;

public class Lab2_GrammarAnalysis {


    // 当前的token
    static Lab2_Token lastSym;
    static Lab2_Token currentSym;
    static Lab2_Token futureSym;
    String process = "";
    int regIndex = 0;


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


        currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
        if (currentSym == null || !currentSym.value.equals(")")) {
            System.exit(-1);
        }
        Lab2_Test.outputStr += ")";

        currentSym = Lab2_LexicalAnalysisForGA.getNextToken();

        blockAnal();
        // 为了下一个可以直接读取
//        currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
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
            initValAnal();
        }
    }

    private static void initValAnal() throws IOException {
        expAnal();
    }

    private static void constDeclAnal() throws IOException {
        if (!currentSym.value.equals("const")) { System.exit(-1); }
        currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
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
        getNextSym();
        if (!currentSym.value.equals("=")) {
            System.exit(-1);
        }
        getNextSym();
        constInitValAnal();
    }

    private static void constInitValAnal() throws IOException {
        constExpAnal();
    }

    private static void constExpAnal() throws IOException {
        addExpAnal();
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

    private static int expAnal() throws IOException {
        int expResult = addExpAnal();
        return expResult;
    }

    private static int addExpAnal() throws IOException {
        int addResult = 0;
        addResult = mulExpAnal();

        while (true) {
            if (currentSym.value.equals("+")) {
                Lab2_Test.outputStr += "+";
                currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
                addResult += mulExpAnal();

            }
            else if (currentSym.value.equals("-")) {
                Lab2_Test.outputStr += "-";
                currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
                addResult -= mulExpAnal();
            }
            else {
                break;
            }
        }
        return addResult;
    }

    private static int mulExpAnal() throws IOException {
        int mulResult = unaryExpAnal();
        while (true) {
            if (currentSym.value.equals("*")) {
                Lab2_Test.outputStr += "*";
                currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
                mulResult *= unaryExpAnal();
            }
            else if (currentSym.value.equals("/")) {
                Lab2_Test.outputStr += "/";
                currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
                mulResult /= unaryExpAnal();
            }
            else if (currentSym.value.equals("%")) {
                Lab2_Test.outputStr += "%";
                currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
                mulResult %= unaryExpAnal();
            }
            else { break; }
        }
        return mulResult;
    }

    private static int unaryExpAnal() throws IOException {
        int unaryResult = 1;
        if (currentSym.value.equals("+") || currentSym.value.equals("-")) {
            unaryResult *= unaryOpAnal();
            unaryResult *= unaryExpAnal();
        }
        else { unaryResult *= primaryExpAnal(); }

        return unaryResult;
    }

    private static int primaryExpAnal() throws IOException {
        int priResult = 0;
        if (currentSym.value.equals("(")) {
            Lab2_Test.outputStr += "(";
            currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
            priResult = expAnal();
            if (currentSym.value.equals(")")) {
                Lab2_Test.outputStr += ")";
                currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
            }
            else { System.exit(1); }
        }
        else {
            priResult = numberAnal();
        }

        return priResult;
    }

    private static int unaryOpAnal() throws IOException {
        if (currentSym.value.equals("-")) {
            Lab2_Test.outputStr += "-";
            currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
            return -1;
        }
        else if (currentSym.value.equals("+")) {
            Lab2_Test.outputStr += "+";
            currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
            return 1;
        }
        else {
            System.exit(21);
            return -1;
        }
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
