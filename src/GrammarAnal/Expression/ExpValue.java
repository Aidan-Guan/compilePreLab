package GrammarAnal.Expression;

public class ExpValue {
    public int register;
    public int value;
    public String valueType;
    public String type;
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
        this.type = "ident";
        this.registerString = registerString;
    }

    public ExpValue(int register) {
        this.register = register;
        this.type = "array";
    }

    public String out() {
        return "%x"+register;
    }
}
