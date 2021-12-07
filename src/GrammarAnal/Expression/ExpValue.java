package GrammarAnal.Expression;

public class ExpValue {
    public int register;
    public int value;
    public String valueType;
    public String registerString;

    public ExpValue(int register, String valueType) {
        this.register = register;
        this.valueType = valueType;
    }

    public ExpValue(int register, String valueType, int value) {
        this.register = register;
        this.valueType = valueType;
        this.value = value;
    }

    public ExpValue (String registerString) {
        this.valueType = "ident";
        this.registerString = registerString;
    }

    public ExpValue(int register) {
        this.register = register;
        this.valueType = "array";
    }

    public String out() {
        return "%x"+register;
    }
}
