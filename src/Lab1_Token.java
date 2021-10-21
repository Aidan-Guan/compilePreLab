public class Lab1_Token {
    String type;
    String value;

    public Lab1_Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Token{" + "\ttype:" + type + "\tvalue:" + value + "}";
    }
}
