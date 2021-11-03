import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.*;

public class Lab2_LexicalAnalysisForGA {
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

    public static Lab2_Token getNextToken () throws IOException {
        tokenChar = (char) in.read();
        tokenStr = "";

        // 读取空字符
        int flag = 0;
        while (Character.isWhitespace(tokenChar)) {
//            System.out.print(tokenChar);
            flag = 1;

            Lab2_Test.outputStr += String.valueOf(tokenChar);
            tokenChar = (char) in.read();
        }

        // 首字符是字母
        if (Character.isLetter(tokenChar) || tokenChar=='_') {
            // 识别标识符
            while (Character.isLetterOrDigit(tokenChar) || tokenChar=='_') {
//                System.out.print(tokenChar);
                tokenStr += tokenChar;
                tokenChar = (char) in.read();
            }
            in.unread(tokenChar);

            if (tokenTable.containsKey(tokenStr))
                return new Lab2_Token("RESERVED", tokenTable.get(tokenStr));
            else
                return new Lab2_Token("IDENT", tokenStr);
        }
        else if (Character.isDigit(tokenChar)) {
            if (tokenChar != '0') { // 一定是十进制 非 0
                while (Character.isDigit(tokenChar)) {
//                    System.out.print(tokenChar);

                    tokenStr += tokenChar;
                    tokenChar = (char) in.read();
                }
                in.unread(tokenChar);
                return new Lab2_Token("NUMBER", tokenStr);
            }
            else {
                tokenChar = (char) in.read();
//                System.out.print(tokenChar);

                // 单纯为0的情况
                if (!Character.isDigit(tokenChar) && tokenChar!='x' && tokenChar!='X') {
                    in.unread(tokenChar);
                    return new Lab2_Token("NUMBER", "0");
                }
                // 16进制
                else if (tokenChar == 'x' || tokenChar == 'X') {
                    tokenStr = "";
                    tokenChar = (char)in.read();
                    while (Character.isLetterOrDigit(tokenChar)) {
//                        System.out.print(tokenChar);

                        tokenStr += tokenChar;
                        tokenChar = (char) in.read();
                    }
                    in.unread(tokenChar);

                    try {
                        return new Lab2_Token("NUMBER", String.valueOf(Integer.parseInt(tokenStr, 16)));
                    }
                    catch (Exception e) {
                        System.exit(1);
                    }
                }
                // 8进制
                else {
                    while (Character.isDigit(tokenChar)) {
//                        System.out.print(tokenChar);

                        tokenStr += tokenChar;
                        tokenChar = (char) in.read();
                    }
                    in.unread(tokenChar);
                    return new Lab2_Token("NUMBER", String.valueOf(Integer.parseInt(tokenStr,8)));
                }
            }
        }
        else {
//            System.out.print(tokenChar);

            switch (tokenChar) {
                case ';' -> { return new Lab2_Token("SIGN", ";"); }
                case '(' -> { return new Lab2_Token("SIGN", "("); }
                case ')' -> { return new Lab2_Token("SIGN", ")"); }
                case '{' -> { return new Lab2_Token("SIGN", "{"); }
                case '}' -> { return new Lab2_Token("SIGN", "}"); }
                case '+' -> { return new Lab2_Token("SIGN", "+"); }
                case '-' -> { return new Lab2_Token("SIGN", "-"); }
                case '*' -> { return new Lab2_Token("SIGN", "*"); }
                case '%' -> { return new Lab2_Token("SIGN", "%"); }
                case '=' -> { return new Lab2_Token("SIGN", "="); }
                case ',' -> { return new Lab2_Token("SIGN", ","); }
                case '/' -> {
                    if ( commentAnal()) { return getNextToken(); }
                    else {
                        in.unread(tokenChar);
                        return new Lab2_Token("SIGN", "/"); }
                }
                case '\uFFFF' -> {return null;}
            }
        }

        return null;
    }

    public static String lex = "";
    public static void myLex(String fileLoc) throws IOException {
        Scanner in = new Scanner(new FileReader(fileLoc));
        while (in.hasNextLine()) {
            lex += in.nextLine();
            System.out.println(lex);
        }
    }

    private static boolean commentAnal() throws IOException {
        tokenChar = (char) in.read();

        if (tokenChar == '/') { // 单行注释
            tokenChar = (char) in.read();
            // 跳过所有换行之前的字符 考虑文件结束
            while (tokenChar != '\n' && tokenChar != '\uFFFF') {
                tokenChar = (char) in.read();
            }
            // 回退字符
            in.unread(tokenChar);
            return true;
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
            return true;
        }
        else {
            return false;
//            System.exit(10);
        }

    }
}
