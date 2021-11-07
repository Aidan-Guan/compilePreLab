/**
 * the class which TokenAnalysis send to GrammarAnalysis
 */
public class Token {
    /**
     * NUMBER, ID
     */
    String type = "";
    int number = 0;
    String word = "";

    public Token(String type, int number) {
        this.type = type;
        this.number = number;
    }

    public Token(String type, String word) {
        this.type = type;
        this.word = word;
    }

    public Token() {
    }

    @Override
    public String toString() {
        return "Token{" +
                "type='" + type + '\'' +
                ", number=" + number +
                ", word='" + word + '\'' +
                '}';
    }
}
