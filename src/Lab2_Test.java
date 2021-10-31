import java.io.*;

public class Lab2_Test {

    public static PushbackReader in;
    public static String outputStr = "";

    public static void main(String[] args) throws IOException {
//        String fileLoc = args[0];
        String fileLoc = "/Users/guanhuaimin/本地磁盘/学习/本地_大三上/编译原理/labs/compilePreLab/src/a.txt";

        in = new PushbackReader(new FileReader(fileLoc));
        Lab2_LexicalAnalysisForGA.getTokenInit(in);

        Lab2_GrammarAnalysis.compUnitAnal();
        Lab2_SemanticAnalysis.dealPolishNotation();

//
//        String outputFileLoc = args[1];
//        FileWriter fileWriter = new FileWriter(outputFileLoc, true);
//        fileWriter.write(outputStr.trim());
//        fileWriter.flush();
//        fileWriter.close();

        System.out.print(outputStr.trim());
    }
}
