import java.io.*;
import java.util.Vector;

/**
 * https://github.com/Aidan-Guan/compilePreLab.git
 * Aidan-Guan
 * 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
 */

public class Lab3_Test {
    public static PushbackReader in;
    public static String outputStr = "";
    public static Vector<Lab3_Token> allTokens = new Vector<>();

    public static void main(String[] args) throws FileNotFoundException {
//        String fileLoc = args[0];
        String fileLoc = "/Users/guanhuaimin/本地磁盘/学习/本地_大三上/编译原理/labs/compilePreLab/src/a.txt";

        in = new PushbackReader(new FileReader(fileLoc));


    }
}
