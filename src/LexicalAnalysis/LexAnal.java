package LexicalAnalysis;

import TokenUtils.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LexAnal {

    private static ArrayList<Token> tokens = new ArrayList<Token>();

    private static char tokenChar;
    private static String tokenStr;
    private static PushbackReader in;
    public static Map<String, String> tokenTable = new HashMap<>();


    public static ArrayList<Token> getAllTokens (PushbackReader reader) throws IOException {
        in = reader;
        getTokenInit();

        Token tmpToken = getNextToken();
        while (tmpToken != null) {
            if (tmpToken.type.equals("ERR")) { System.exit(-1); }
            tokens.add(tmpToken);
            tmpToken = getNextToken();
        }
        return tokens;
    }


    private static void getTokenInit (){
        tokenTable.put("int", "int");
        tokenTable.put("main", "main");
        tokenTable.put("return", "return");
        tokenTable.put("const", "const");
        tokenTable.put("if", "if");
        tokenTable.put("else", "else");
    }


    private static Token getNextToken () throws IOException {
        tokenChar = (char) in.read();
        tokenStr = "";

        // 读取空字符
        while (Character.isWhitespace(tokenChar)) {
            tokenChar = (char) in.read();
        }


        // 首字符是字母
        if (Character.isLetter(tokenChar) || tokenChar=='_') {
            // 识别标识符
            while (Character.isLetterOrDigit(tokenChar) || tokenChar=='_') {
                tokenStr += tokenChar;
                tokenChar = (char) in.read();
            }
            in.unread(tokenChar);

            if (tokenTable.containsKey(tokenStr))
                return new Token("IDENT", tokenTable.get(tokenStr));
            else
                return new Token("IDENT", tokenStr);
        }
        else if (Character.isDigit(tokenChar)) {
            // 一定是十进制 非 0
            if (tokenChar != '0') {
                while (Character.isDigit(tokenChar)) {
                    tokenStr += tokenChar;
                    tokenChar = (char) in.read();
                }
                in.unread(tokenChar);
                return new Token("NUMBER", tokenStr);
            }
            else {
                tokenChar = (char) in.read();

                // 单纯为0的情况
                if (!Character.isDigit(tokenChar) && tokenChar!='x' && tokenChar!='X') {
                    in.unread(tokenChar);
                    return new Token("NUMBER", "0");
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
                        return new Token("NUMBER", String.valueOf(Integer.parseInt(tokenStr, 16)));
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
                    return new Token("NUMBER", String.valueOf(Integer.parseInt(tokenStr,8)));
                }
            }
        }
        else {

            switch (tokenChar) {
                case ';' -> { return new Token("SIGN", ";"); }
                case '(' -> { return new Token("SIGN", "("); }
                case ')' -> { return new Token("SIGN", ")"); }
                case '{' -> { return new Token("SIGN", "{"); }
                case '}' -> { return new Token("SIGN", "}"); }
                case '+' -> { return new Token("SIGN", "+"); }
                case '-' -> { return new Token("SIGN", "-"); }
                case '*' -> { return new Token("SIGN", "*"); }
                case '%' -> { return new Token("SIGN", "%"); }
                case '[' -> { return new Token("SIGN", "["); }
                case ']' -> { return new Token("SIGN", "]"); }
                case '=' -> { return eqAnal(); }
                case ',' -> { return new Token("SIGN", ","); }
                case '<' -> { return lOrLeAnal(); }
                case '>' -> { return gOrGeAnal(); }
                case '!' -> { return excAnal(); }
                case '&' -> { return andAnal(); }
                case '|' -> { return orAnal(); }
                case '/' -> {
                    if ( commentAnal()) { return getNextToken(); }
                    else {
                        in.unread(tokenChar);
                        return new Token("SIGN", "/"); }
                }
                case '\uFFFF' -> {return null;}
            }
        }
        return new Token("ERR", "");
    }

    private static Token eqAnal() throws IOException {
        tokenChar = (char) in.read();
        // 读了一个之后发现不是等号，一定是小于号
        if (tokenChar != '=') {
            in.unread(tokenChar);
            return new Token("SIGN", "=");
        }
        else {
            return new Token("SIGN", "==");
        }
    }

    // 区分小于 小于等于
    private static Token lOrLeAnal() throws IOException {
        tokenChar = (char) in.read();
        // 读了一个之后发现不是等号，一定是小于号
        if (tokenChar != '=') {
            in.unread(tokenChar);
            return new Token("SIGN", "<");
        }
        else {
            return new Token("SIGN", "<=");
        }
    }

    // 区分大于 大于等于
    private static Token gOrGeAnal() throws IOException {
        tokenChar = (char) in.read();
        // 读了一个之后发现不是等号，一定是小于号
        if (tokenChar != '=') {
            in.unread(tokenChar);
            return new Token("SIGN", ">");
        }
        else {
            return new Token("SIGN", ">=");
        }
    }

    private static Token excAnal() throws IOException {
        tokenChar = (char) in.read();
        // 读了一个之后发现不是等号，一定是小于号
        if (tokenChar != '=') {
            in.unread(tokenChar);
            return new Token("SIGN", "!");
        }
        else {
            return new Token("SIGN", "!=");
        }
    }

    private static Token andAnal() throws IOException {
        tokenChar = (char) in.read();
        if (tokenChar != '&') {
            System.exit(-1);
        }
        return new Token("SIGN", "&&");
    }

    private static Token orAnal() throws IOException {
        tokenChar = (char) in.read();
        if (tokenChar != '|') {
            System.exit(-1);
        }
        return new Token("SIGN", "||");
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

    public static void myLex(String fileLoc) throws IOException {
        String lex="";
        Scanner in = new Scanner(new FileReader(fileLoc));
        while (in.hasNextLine()) {
            lex += in.nextLine()+"\n";
        }
        System.out.println(lex);
    }
}
