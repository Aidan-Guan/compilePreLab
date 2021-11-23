package LexicalAnalysis;

public class Token {
    String value;
    String type;

    public Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value+"\t\t"+this.type;
    }
}
