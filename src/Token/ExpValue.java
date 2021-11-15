package Token;

public class ExpValue {
    public boolean isRegister;
    public int value;
    public boolean isI1;

    public ExpValue(int value, boolean isRegister) {
        this.value = value;
        this.isRegister = isRegister;
    }

    public String out() {


        if (isRegister) {
            return "%x"+this.value;
        }
        else {
            return String.valueOf(value);
        }
    }
}
