public class Ident {
    public boolean isConst = false;
    public int value = 0;
    public String ident;
    public int register;

    public Ident(boolean isConst, int value, String ident, int register) {
        this.isConst = isConst;
        this.value = value;
        this.ident = ident;
        this.register = register;
    }
}
