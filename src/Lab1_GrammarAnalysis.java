public class Lab1_GrammarAnalysis {

    // 当前的token
    static String currentSym = "";

    /**
     * 处理CompUnit文法
     */
    public static void compUnitAnal() {
        funcDefAnal();
    }

    /**
     * 处理FuncDef文法
     */
    private static void funcDefAnal() {
        funcTypeAnal();
        identAnal();

        currentSym = Lab1_LexicalAnalysisForGA.nextSym().trim();
        // 出现错误，退出
        if (!currentSym.equals("(")) {
            System.exit(1);
        }

        currentSym = Lab1_LexicalAnalysisForGA.nextSym().trim();
        if (!currentSym.equals(")")) {
            System.exit(1);
        }

        blockAnal();
        // 为了下一个可以直接读取
        currentSym = Lab1_LexicalAnalysisForGA.nextSym().trim();
    }

    /**
     * 处理Funcype文法
     */
    private static void funcTypeAnal() {
        if (!currentSym.equals("int")) {
            System.exit(2);
        }
        currentSym = Lab1_LexicalAnalysisForGA.nextSym().trim();
    }

    /**
     * 处理Ident文法
     */
    private static void identAnal() {
        if (!currentSym.equals("main")) {
            System.exit(3);
        }
    }

    /**
     * 处理Block文法
     */
    private static void blockAnal() {
        if (!currentSym.equals("{")) {
            System.exit(1);
        }

        currentSym = Lab1_LexicalAnalysisForGA.nextSym().trim();

        stmtAnal();

        if (!currentSym.equals("}")) {
            System.exit(1);
        }
    }

    /**
     * 处理stmt文法
     */
    private static void stmtAnal() {
        if (!currentSym.equals("return")) {
            System.exit(4);
        }

        currentSym = Lab1_LexicalAnalysisForGA.nextSym().trim();

        if (!Lab1_LexicalAnalysisForGA.isNumber(currentSym)) {
            System.exit(5);
        }

        currentSym = Lab1_LexicalAnalysisForGA.nextSym().trim();

        if (!currentSym.equals(";")) {
            System.exit(6);
        }
    }
}

//错误对照表
//1   括号有问题
//2   返回类型有错
//3   main函数错误
//4   return有误
//5   并不是number
//6   分号有问题
