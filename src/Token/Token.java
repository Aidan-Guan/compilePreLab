package Token;

public class Token {
    public String value = "";
    public String type = "";

    public Token(String value, String type) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "value: \t" + this.value + "\ttype: \t" + this.type;
    }
}
