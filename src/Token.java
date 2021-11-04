public class Token {
    String type;
    String value;

    public Token() {
        this.type = "null";
        this.value = "";
    }

    public Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String output() {
        if (this.type.equals("NUMBER"))
            return this.value.trim();
        else if (this.type.equals("REG"))
            return "%"+this.value.trim();
        else if (this.type.equals("FUNC")) {
            switch (this.value) {
                case "getint" -> {return "i32 @getint()";}
                case "getch" -> {return "i32 @getch()";}
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Token{" + "\ttype:" + type + "\tvalue:" + value + "}";
    }
}
