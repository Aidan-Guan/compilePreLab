import java.io.IOException;

public class Lab1_GrammarAnalysis {


    // 当前的token
    static Lab1_Token currentSym;

    static {
        try {
            currentSym = Lab1_LexicalAnalysisForGA.getNextToken();
        }
        catch (IOException e) {
            e.printStackTrace();
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

        currentSym = Lab1_LexicalAnalysisForGA.getNextToken();
        // 出现错误，退出
        if (currentSym == null || !currentSym.value.equals("(")) {
            System.exit(-1);
        }
        // 处理(的输出
        Lab1_Test.outputStr += "(";


        currentSym = Lab1_LexicalAnalysisForGA.getNextToken();
        if (currentSym == null || !currentSym.value.equals(")")) {
            System.exit(-1);
        }
        Lab1_Test.outputStr += ")";

        currentSym = Lab1_LexicalAnalysisForGA.getNextToken();

        blockAnal();
        // 为了下一个可以直接读取
        currentSym = Lab1_LexicalAnalysisForGA.getNextToken();
    }

    /**
     * 处理Funcype文法
     */
    private static void funcTypeAnal() throws IOException {
        if (currentSym == null || !currentSym.value.equals("int")) {
            System.exit(-1);
        }
        Lab1_Test.outputStr += "define dso_local i32";

        currentSym = Lab1_LexicalAnalysisForGA.getNextToken();

    }

    /**
     * 处理Ident文法
     */
    private static void identAnal() {
        if (currentSym == null || !currentSym.value.equals("main")) {
            System.exit(-1);
        }

        //对于main的输出
        Lab1_Test.outputStr += "@main";
    }

    /**
     * 处理Block文法
     */
    private static void blockAnal() throws IOException {
        if (currentSym == null || !currentSym.value.equals("{")) {
            System.exit(-1);
        }
        Lab1_Test.outputStr += "{";

        currentSym = Lab1_LexicalAnalysisForGA.getNextToken();

        stmtAnal();

        currentSym = Lab1_LexicalAnalysisForGA.getNextToken();

        if (currentSym == null || !currentSym.value.equals("}")) {
            System.exit(-1);
        }
        Lab1_Test.outputStr += "}";
    }

    /**
     * 处理stmt文法
     */
    private static void stmtAnal() throws IOException {
        if (currentSym == null || !currentSym.value.equals("return")) {
            System.exit(-1);
        }
        Lab1_Test.outputStr += "ret i32";

        currentSym = Lab1_LexicalAnalysisForGA.getNextToken();
        if (currentSym == null || !currentSym.type.equals("NUMBER")) {
            System.exit(-1);
        }
        Lab1_Test.outputStr += currentSym.value;

        currentSym = Lab1_LexicalAnalysisForGA.getNextToken();

        if (currentSym == null || !currentSym.value.equals(";")) {
            System.exit(-1);
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
