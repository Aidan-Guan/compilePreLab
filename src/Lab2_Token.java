public class Lab2_Token {
    String type;
    String value;

    public Lab2_Token() {
        this.type = "null";
        this.value = "";
    }

    public Lab2_Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Token{" + "\ttype:" + type + "\tvalue:" + value + "}";
    }
}
