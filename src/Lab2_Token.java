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

    public String output() {
        if (this.type.equals("NUMBER"))
            return this.value;
        else if (this.type.equals("REG"))
            return "%"+this.value;
        return null;
    }

    @Override
    public String toString() {
        return "Token{" + "\ttype:" + type + "\tvalue:" + value + "}";
    }
}
