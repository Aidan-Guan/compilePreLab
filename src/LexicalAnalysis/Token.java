package LexicalAnalysis;

public class Token {
    public String value;
    public String type;
    public int index;

    public Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value+"\t\t"+this.type;
    }
}
