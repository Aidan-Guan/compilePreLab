import java.io.*;

/**
 * https://github.com/Aidan-Guan/compilePreLab.git
 * Aidan-Guan
 * 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
 */

public class Test {

    public static PushbackReader in;
    public static String outputStr = "";

    public static void main(String[] args) throws IOException {
//        String fileLoc = args[0];
        String fileLoc = "/Users/guanhuaimin/本地磁盘/学习/本地_大三上/编译原理/labs/compilePreLab/src/a.txt";

        in = new PushbackReader(new FileReader(fileLoc));
        LexAnal.getTokenInit(in);
        LexAnal.myLex(fileLoc);

//        GrammarAnal.compUnitAnal();
        GrammarAnalysis grammarAnalysis = init(in);
        grammarAnalysis.CompUnit();
        outputStr = grammarAnalysis.out;


//        String outputFileLoc = args[1];
//        FileWriter fileWriter = new FileWriter(outputFileLoc, true);
//        fileWriter.write(outputStr.trim());
//        fileWriter.flush();
//        fileWriter.close();

        System.out.print(outputStr.trim());
    }

    private static GrammarAnalysis init(PushbackReader in) {
        GrammarAnalysis grammarAnalysis = new GrammarAnalysis(in);
        return grammarAnalysis;
    }
}


/**
 * bug总结
 * 1. 遇到括号应当如何处理
 * 2. 对于数字超过1位的情况，不能 使用char
 * 3. 对于输入格式的问题，如何能够很好地获取到表达式
 */