import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

public class Operations {

    /**
     *
     * @param line
     * 处理每一个token的输出
     */
    public static void readLine(String line) {
        Vector<String> tokens = splitLine(line);
        String[] speStrs = new String[] {"if", "else", "while", "break", "continue", "return"};
        List<String> speList = Arrays.asList(speStrs);

        for (String token : tokens) {

            // 判断是否为无符号整数
            if (isDigit(token)) { System.out.println("Number("+token+")"); continue; }
            // 判断是否为标识符
            if (isIdentifier(token) && !speList.contains(token)) { System.out.println("Ident("+token+")"); continue; }

            // 判断是否为其他的东西
            switch (token) {
                case "if" -> { System.out.println("If");  }
                case "else" -> { System.out.println("Else");  }
                case "while" -> { System.out.println("While");  }
                case "break" -> { System.out.println("Break");  }
                case "continue" -> { System.out.println("Continue"); }
                case "return" -> { System.out.println("Return"); }
                case "=" -> { System.out.println("Assign"); }
                case ";" -> { System.out.println("Semicolon"); }
                case "(" -> { System.out.println("LPar"); }
                case ")" -> { System.out.println("RPar"); }
                case "{" -> { System.out.println("LBrace"); }
                case "}" -> { System.out.println("RBrace"); }
                case "+" -> { System.out.println("Plus"); }
                case "*" -> { System.out.println("Mult"); }
                case "/" -> { System.out.println("Div"); }
                case "<" -> { System.out.println("Lt"); }
                case ">" -> { System.out.println("Gt"); }
                case "==" -> { System.out.println("Eq"); }
//                default -> {
//                    System.out.println("Err");
//                    System.exit(0);
//                }
            }
        }
    }

    /**
     *
     * @param line
     * @return tokens
     * 对于一整行代码进行token的分开
     */
    private static Vector<String> splitLine (String line) {
        // 获得字符列表
        Character[] opStrs = new Character[] {';', '(', ')', '{', '}', '+', '*', '/', '<', '>'};
        List<Character> opList = Arrays.asList(opStrs);

        // 先按照空格分，可能区分了一部分但没有完全区分的token
        String[] priTokens = line.trim().split("\\s");
        Vector<String> tokens = new Vector<>();

        for (String str : priTokens) {
            str = str.trim();
            // 如果长度为1，不可能再分
            if (str.length() == 1) { tokens.add(str); continue; }

            // 长度大于1的情况
            int index = 0;
            String tmp = "";
            for (; index < str.length() ; index += 1) {
                // 发现当前为一个等号
                if (str.charAt(index) == '=') {
                    // 保存之前的所有tmp 如果tmp非空
                    if (!tmp.isEmpty()) {
                        tokens.add(tmp.trim());
                        tmp = "";
                    }
                    // 判断是否为两个等号
                    if (index+1 < str.length() && str.charAt(index+1) == '=') {
                        index += 1;
                        tokens.add("==");
                    }
                    else { // 仅有一个等号
                        tokens.add("=");
                    }
                }
                // 出现了字符但是不是等号
                else if (opList.contains(str.charAt(index))) {
                    if (!tmp.isEmpty()) {
                        tokens.add(tmp.trim());
                        tmp = "";
                    }
                    // 向token中添加这个特殊字符
                    tokens.add(String.valueOf(str.charAt(index)).trim());
                }
                else {
                    tmp = tmp + str.charAt(index);
                }
            }

            if (!tmp.isEmpty()) {
                tokens.add(tmp.trim());
                tmp = "";
            }
        }

        return tokens;
    }

    /**
     *
     * @param token
     * @return boolean
     * 判断token是否为无符号整数
     */
    private static boolean isDigit (String token) {
        for (char c : token.trim().toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param token
     * @return boolean
     * 判断是token是否是标识符
     */
    private static boolean isIdentifier(String token) {
        char firstLetter = token.trim().charAt(0);

        // 判断第一个字母
        if (!(firstLetter == '_' || Character.isLetter(firstLetter))) {
            return false;
        }

        // 第一个字母符合文法
        // 长度如果是1则已经满足
        if (token.length() == 1) {
            return true;
        }

        // 在长度大于1时从第二个字符开始判断
        for (char c : token.trim().substring(1).toCharArray()) {
            if (!(c == '_' || Character.isLetterOrDigit(c))) {
                return false;
            }
        }
        return true;
    }

}
