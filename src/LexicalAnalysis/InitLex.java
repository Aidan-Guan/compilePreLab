package LexicalAnalysis;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InitLex {
    static ArrayList<String[]> lexical = new ArrayList<>();
    static HashMap<String, String> reserveMap = new HashMap<>();

    private static void initHashMap() {
        reserveMap.put("main", "MAINTK");
        reserveMap.put("const", "CONSTTK");
        reserveMap.put("int", "INTTK");
        reserveMap.put("break", "BREAKTK");
        reserveMap.put("continue", "CONTINUETK");
        reserveMap.put("if", "IFTK");
        reserveMap.put("else", "ELSETK");
        reserveMap.put("!", "NOT");
        reserveMap.put("&&", "AND");
        reserveMap.put("||", "OR");
        reserveMap.put("while", "WHILETK");
        reserveMap.put("return", "RETURNTK");
        reserveMap.put("+", "PLUS");
        reserveMap.put("-", "MINU");
        reserveMap.put("void", "VOIDTK");
        reserveMap.put("*", "MULT");
        reserveMap.put("/", "DIV");
        reserveMap.put("%", "MOD");
        reserveMap.put("<", "LSS");
        reserveMap.put("<=", "LEQ");
        reserveMap.put(">", "GRE");
        reserveMap.put(">=", "GEQ");
        reserveMap.put("==", "EQL");
        reserveMap.put("!=", "NEQ");
        reserveMap.put("=", "ASSIGN");
        reserveMap.put(";", "SEMICN");
        reserveMap.put(",", "COMMA");
        reserveMap.put("(", "LPARENT");
        reserveMap.put(")", "RPARENT");
        reserveMap.put("[", "LBRACK");
        reserveMap.put("]", "RBRACK");
        reserveMap.put("{", "LBRACE");
        reserveMap.put("}", "RBRACE");
    }

    public static ArrayList<String[]> initLex(ArrayList<Token> tokens) {
        initHashMap();

        for (Token token:tokens) {
            String str = token.value;
            String[] temp = new String[2];

            if (str.charAt(0)=='"') {
                temp[0] = "STRCON";
                temp[1] = str;
            }
            else if (Character.isDigit(str.charAt(0))) {
                temp[0] = "INTCON";
                if(str.charAt(0) == '0'){
                    if(str.length() == 1)
                        temp[1] = "" + 0;
                    else if(str.charAt(1) == 'x' || str.charAt(1) == 'X')
                        temp[1] = Integer.parseInt(str.substring(2), 16) + "";
                    else temp[1] = Integer.parseInt(str, 8) + "";
                }
                else {
                    temp[1] = str;
                }
            }
            else if (reserveMap.containsKey(str)) {
                temp[0] = reserveMap.get(str);
                temp[1] = str;
            }
            else {
                temp[0] = "IDENFR";
                temp[1] = str;
            }
            lexical.add(temp);
        }
        return lexical;
    }
}
