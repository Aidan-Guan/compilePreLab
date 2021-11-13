package Test;

import Grammar.GrammarAnal;
import LexAnal.LexAnal;
import Token.Token;

import java.io.*;
import java.util.ArrayList;

/**
 * https://github.com/Aidan-Guan/compilePreLab.git
 * Aidan-Guan
 * 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
 */

public class Test {

    public static PushbackReader in;
    public static String outputStr = "";

    public static ArrayList<Token> tokens = new ArrayList<Token>();

    public static void main(String[] args) throws IOException {
        String fileLoc = args[0];
//        String fileLoc = "/Users/guanhuaimin/本地磁盘/学习/本地_大三上/编译原理/labs/compilePreLab/src/a.txt";

        in = new PushbackReader(new FileReader(fileLoc));

        tokens = LexAnal.getAllTokens(in);

        for (Token item : tokens) {
            System.out.println(item.toString());
        }

        outputStr = GrammarAnal.getOutputString(tokens);
        System.out.println(outputStr);

        filePrint(args[1]);
    }

    private static void filePrint(String fileLoc) throws IOException {
        String outputFileLoc = fileLoc;
        FileWriter fileWriter = new FileWriter(outputFileLoc, true);
        fileWriter.write(outputStr.trim());
        fileWriter.flush();
        fileWriter.close();

        System.out.print(outputStr.trim());
    }
}

