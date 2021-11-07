public class Ident {
    public boolean isConst = false;
    public int value = 0;
    public String identName;
    public int register;

    public Ident(boolean isConst, int value, String identName, int register) {
        this.isConst = isConst;
        this.value = value;
        this.identName = identName;
        this.register = register;
    }
}
