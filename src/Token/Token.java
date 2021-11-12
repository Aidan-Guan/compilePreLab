package Token;

public class Token {
    public String value = "";
    public String type = "";
    public String belongBlock = "";
    public boolean isConst;

    public Token( String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "value: \t" + this.value + "\ttype: \t" + this.type;
    }
}
