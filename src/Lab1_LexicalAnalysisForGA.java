import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.*;

public class Lab1_LexicalAnalysisForGA {
    static char tokenChar;
    static String tokenStr;

    public static Map<String, String> tokenTable = new HashMap<>();
    static PushbackReader in;

    public static void getTokenInit (PushbackReader reader) {
        in = reader;
        tokenTable.put("int", "int");
        tokenTable.put("main", "main");
        tokenTable.put("return", "return");
    }

    public static Lab1_Token getNextToken () throws IOException {
        tokenChar = (char) in.read();
        tokenStr = "";

        // 读取空字符
        int flag = 0;
        while (Character.isWhitespace(tokenChar)) {
            flag = 1;

            Lab1_Test.outputStr += String.valueOf(tokenChar);
            tokenChar = (char) in.read();
        }

        // 首字符是字母
        if (Character.isLetter(tokenChar)) {
            // 识别标识符
            while (Character.isLetterOrDigit(tokenChar)) {
                tokenStr += tokenChar;
                tokenChar = (char) in.read();
            }
            in.unread(tokenChar);

            if (tokenTable.containsKey(tokenStr))
                return new Lab1_Token("RESERVED", tokenTable.get(tokenStr));
            else
                return new Lab1_Token("IDENT", tokenStr);
        }
        else if (Character.isDigit(tokenChar)) {
            if (tokenChar != '0') { // 一定是十进制 非 0
                while (Character.isDigit(tokenChar)) {
                    tokenStr += tokenChar;
                    tokenChar = (char) in.read();
                }
                in.unread(tokenChar);
                return new Lab1_Token("NUMBER", tokenStr);
            }
            else {
                tokenChar = (char) in.read();
                // 单纯为0的情况
                if (!Character.isDigit(tokenChar) && tokenChar!='x' && tokenChar!='X') {
                    in.unread(tokenChar);
                    return new Lab1_Token("NUMBER", "0");
                }
                // 16进制
                else if (tokenChar == 'x' || tokenChar == 'X') {
                    tokenStr = "";
                    tokenChar = (char)in.read();
                    while (Character.isLetterOrDigit(tokenChar)) {
                        tokenStr += tokenChar;
                        tokenChar = (char) in.read();
                    }
                    in.unread(tokenChar);

                    try {
                        return new Lab1_Token("NUMBER", String.valueOf(Integer.parseInt(tokenStr, 16)));
                    }
                    catch (Exception e) {
                        System.exit(1);
                    }
                }
                // 8进制
                else {
                    while (Character.isDigit(tokenChar)) {
                        tokenStr += tokenChar;
                        tokenChar = (char) in.read();
                    }
                    in.unread(tokenChar);
                    return new Lab1_Token("NUMBER", String.valueOf(Integer.parseInt(tokenStr,8)));
                }
            }
        }
        else {
            switch (tokenChar) {
                case ';' -> { return new Lab1_Token("SIGN", ";"); }
                case '(' -> { return new Lab1_Token("SIGN", "("); }
                case ')' -> { return new Lab1_Token("SIGN", ")"); }
                case '{' -> { return new Lab1_Token("SIGN", "{"); }
                case '}' -> { return new Lab1_Token("SIGN", "}"); }
                case '/' -> {
                    commentAnal();
                    return getNextToken();
                }
                case '\uFFFF' -> {return null;}
            }
        }

        return null;
    }

    private static void commentAnal() throws IOException {
        tokenChar = (char) in.read();

        if (tokenChar == '/') { // 单行注释
            tokenChar = (char) in.read();
            // 跳过所有换行之前的字符 考虑文件结束
            while (tokenChar != '\n' && tokenChar != '\uFFFF') {
                tokenChar = (char) in.read();
            }
            // 回退字符
            in.unread(tokenChar);
        }
        else if (tokenChar == '*') { // 多行注释
            char charBeforeTokenChar = '/';
            tokenChar = (char) in.read();

            while (true) {
                if (tokenChar == '\uFFFF') {
                    System.exit(10);
                }
                if (charBeforeTokenChar == '*' && tokenChar == '/') {
                    break;
                }
                charBeforeTokenChar = tokenChar;
                tokenChar = (char) in.read();
            }
//            in.unread(tokenChar);
        }
        else {
            System.exit(10);
        }

    }
}
