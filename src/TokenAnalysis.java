import java.io.IOException;
import java.io.PushbackReader;
import java.util.HashMap;
import java.util.Map;

public class TokenAnalysis {
    /**
     * To store current char
     */
    public char CHAR = 0;
    /**
     * To store current string
     */
    public String TOKEN = "";

    /**
     * reserve map
     */
    public Map<String, String> reserveTable = new HashMap<>();

    /**
     * sign map
     */
    public Map<Character, String> signTable = new HashMap<>();

    /**
     * init reserveTable
     */
    public void initReserveTable(){
        reserveTable.put("int", "int");
        reserveTable.put("main", "main");
        reserveTable.put("return", "return");
        reserveTable.put("const", "const");
    }

    public void initSignTable(){
        signTable.put(';', ";");
        signTable.put('(', "(");
        signTable.put(')', ")");
        signTable.put('{', "{");
        signTable.put('}', "}");
        signTable.put('+', "+");
        signTable.put('-', "-");
        signTable.put('*', "*");
        signTable.put('/', "/");
        signTable.put('%', "%");
        signTable.put('=', "=");
        signTable.put(',', ",");
    }

    /**
     *
     * @param in reader
     */
    public void getNBC(PushbackReader in) throws IOException {
        CHAR = (char) in.read();
        while(Character.isWhitespace(CHAR)){
            CHAR = (char)in.read();
        }
        in.unread(CHAR);
    }

    /**
     * a method for getting new char which deals with comments
     * @param in reader
     * @return the char newly coming
     * @throws IOException deal with exception
     */
    public char getChar(PushbackReader in) throws IOException {

        char newChar = (char) in.read();
        if(newChar == '/'){
            char newChar2 = (char) in.read();
            if(newChar2 == '/'){
                while(newChar != '\n' && newChar != (char)-1){
                    newChar = (char) in.read();
                }
                getNBC(in);
                newChar = getChar(in);
            } else if (newChar2 == '*'){
                boolean isComment = true;
                while(isComment && newChar !=(char) -1){
                    newChar = (char) in.read();
                    if(newChar == '*'){
                        newChar2 =(char) in.read();
                        if(newChar2 == '/') isComment = false;
                        else {
                            in.unread(newChar2);
                        }
                    }
                }
                getNBC(in);
                newChar = getChar(in);
            } else {
                in.unread(newChar2);
            }
        }
        return newChar;
    }
    /**
     *
     * @param in reader
     * @return provide token for grammar
     */
    public Token getToken (PushbackReader in) throws IOException {
        TOKEN = "";
        initReserveTable();
        initSignTable();
        getNBC(in);
        CHAR = getChar(in);
        if(Character.isDigit(CHAR)){
            if(CHAR == '0'){
                CHAR = getChar(in);
                if(!Character.isDigit(CHAR) && CHAR != 'x' && CHAR != 'X'){
                    in.unread(CHAR);
                    return new Token("NUMBER", 0);
                }else if(CHAR == 'x' || CHAR == 'X'){
                    TOKEN = "";
                    CHAR = getChar(in);
                    while(Character.isLetterOrDigit(CHAR)){
                        TOKEN += CHAR;
                        CHAR = getChar(in);
                    }
                    in.unread(CHAR);
                    try {
                        return new Token("NUMBER", Integer.parseInt(TOKEN, 16));
                    }
                    catch (Exception e){
                        System.exit(1);
                    }
                }else if(Character.isDigit(CHAR)){
                    while(Character.isDigit(CHAR)){
                        TOKEN += CHAR;
                        CHAR = getChar(in);
                    }
                    in.unread(CHAR);
                    return new Token("NUMBER", Integer.parseInt(TOKEN, 8));
                }
            } else {
                while(Character.isDigit(CHAR)){
                    TOKEN += CHAR;
                    CHAR = getChar(in);
                }
                in.unread(CHAR);
                return new Token("NUMBER", Integer.parseInt(TOKEN));
            }
        }else if(Character.isLetter(CHAR) || CHAR == '_'){
            while(Character.isLetterOrDigit(CHAR) || CHAR == '_'){
                TOKEN += CHAR;
                CHAR = getChar(in);
            }
            in.unread(CHAR);
            if(reserve(TOKEN)){
                return new Token("RESERVE", reserveTable.get(TOKEN));
            }else {
                return new Token("ID", TOKEN);
            }
        }{
            if(isSign(CHAR)) {
                return new Token("SIGN", signTable.get(CHAR));
            }
            else {
                return new Token("ERR", " ");
            }

        }
    }

    /**
     *
     * @param word str to judge
     * @return whether it is a reserve word
     */
    public boolean reserve(String word){
        return reserveTable.containsKey(word);
    }

    public boolean isSign(char CHAR){
        return signTable.containsKey(CHAR);
    }
}
