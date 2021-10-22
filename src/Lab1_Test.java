import java.io.*;

public class Lab1_Test {

    public static PushbackReader in;
    public static String outputStr = "";

    public static void main(String[] args) throws IOException {
        String fileLoc = args[0];
//        String fileLoc = "/Users/guanhuaimin/本地磁盘/学习/本地_大三上/编译原理/labs/compilePreLab/src/a.txt";
        in = new PushbackReader(new FileReader(fileLoc));
        Lab1_LexicalAnalysisForGA.getTokenInit(in);

        Lab1_GrammarAnalysis.compUnitAnal();

        String outputFileLoc = args[1];
        FileWriter fileWriter = new FileWriter(outputFileLoc, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(outputStr);
        printWriter.flush();
    }
}
