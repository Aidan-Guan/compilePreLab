package Token;

public class Ident {
    public boolean isConst = false;
    public String name;
    public int regNum;

    public Ident(boolean isConst, String name, int regNum) {
        this.isConst = isConst;
        this.name = name;
        this.regNum = regNum;
    }


}
