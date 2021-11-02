import java.io.IOException;

public class Lab2_GrammarAnalysis {


    // 当前的token
    static Lab2_Token currentSym;


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

        currentSym = Lab2_LexicalAnalysisForGA.getNextToken();

        stmtAnal();

        if (currentSym == null || !currentSym.value.equals("}")) {
            System.exit(1);
        }
        Lab2_Test.outputStr += "}";

        currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
        if (currentSym != null) {
            System.exit(2);
        }
    }

    /**
     * 处理stmt文法
     */
    private static void stmtAnal() throws IOException {
        /* 处理return */
        if (currentSym == null || !currentSym.value.equals("return")) { System.exit(4); }
        Lab2_Test.outputStr += "ret i32";

        currentSym = Lab2_LexicalAnalysisForGA.getNextToken();

        /* 处理Exp */
        //TODO: 注意在exp处理结束之后需要再读取一个字符
        Lab2_SemanticAnalysis.expResult = expAnal();
        System.out.println("结果为："+String.valueOf(Lab2_SemanticAnalysis.expResult));

        if (!currentSym.value.equals(";")) { System.exit(6); }

        currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
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
//        mulExpAnal();
//        //TODO: 确保有新的读入
//        addExp2Anal();
    }


//    private static void addExp2Anal() throws IOException {
//        if (currentSym == null) {return;}
//
//        if (currentSym.value.equals("+") || currentSym.value.equals("-")) {
//            Lab2_Test.outputStr += currentSym.value;
//            currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
//
//            mulExpAnal();
//            addExp2Anal();
//        }
//    }


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
