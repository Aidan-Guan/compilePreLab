import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.*;

public class Lab1_LexicalAnalysisForGA {

    public static Map<String, String> tokenTable = new HashMap<>();
    static PushbackReader in;

    public static void getTokenInit (PushbackReader reader) {
        in = reader;
        tokenTable.put("int", "int");
        tokenTable.put("main", "main");
        tokenTable.put("return", "return");
    }

    public static Lab1_Token getNextToken () throws IOException {
        char tokenChar = (char) in.read();
        String tokenStr = "";

        // 读取空字符
        int flag = 0;
        while (Character.isWhitespace(tokenChar)) {
            flag = 1;

            Lab1_Test.outputStr += String.valueOf(tokenChar);
            tokenChar = (char) in.read();
        }
//        if (flag == 1 && Lab1_Test.outputStr.length()>=1)
//            Lab1_Test.outputStr = Lab1_Test.outputStr.substring(0,Lab1_Test.outputStr.length()-1);

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
                    while (Character.isDigit(tokenChar)) {
                        tokenStr += tokenChar;
                        tokenChar = (char) in.read();
                    }
                    in.unread(tokenChar);
                    return new Lab1_Token("NUMBER", String.valueOf(Integer.parseInt(tokenStr, 16)));
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
                case '\uFFFF' -> {return null;}
                default -> { System.exit(1); }
            }
        }

        return null;
    }



//    public static String nextSym() {
//        String nextsym = "";
//
//
//        return nextsym;
//    }


    public static boolean isNumber (String token) {
        return false;
    }


}
