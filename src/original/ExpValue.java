//public class ExpValue {
//    public int value;
//    public boolean isRegister;
//    public String register;
//    public boolean isFunc;
//    public boolean isVoid;
//
//    public ExpValue(int value, boolean isRegister) {
//        this.value = value;
//        this.isRegister = isRegister;
//    }
//
//    public ExpValue(int value, boolean isRegister, int register) {
//        this.value = value;
//        this.isRegister = isRegister;
//        this.register = "%"+register;
//    }
//
//    public ExpValue(boolean isRegister, String register) {
//        this.isRegister = isRegister;
//        this.register = register;
//    }
//
//    public String load(String out, int regNum, String outReg){
//        out += "\t%" + regNum + " = load i32, i32* " + outReg +"\n";
//        return out;
//    }
//
//    public String store(String out, int regNum, String outReg){
//        out += "\tstore i32 " + outReg + ", i32* %" + regNum + "\n";
//        return out;
//    }
//
//    public String subtraction(String out, int regNum, String outA, String outB){
//        out += "\t%" + regNum + " = sub i32 " +outA + ", " + outB + "\n";
//        return out;
//    }
//    public String addition(String out, int regNum, String outA, String outB){
//        out += "\t%" + regNum + " = add i32 " +outA + ", " + outB + "\n";
//        return out;
//    }
//
//    public String multiple(String out, int regNum, String outA, String outB){
//        out += "\t%" + regNum + " = mul i32 " +outA + ", " + outB + "\n";
//        return out;
//    }
//
//    public String division(String out, int regNum, String outA, String outB){
//        out += "\t%" + regNum + " = sdiv i32 " +outA + ", " + outB + "\n";
//        return out;
//    }
//
//    public String mod(String out, int regNum, String outA, String outB){
//        out += "\t%" + regNum + " = srem i32 " +outA + ", " + outB + "\n";
//        return out;
//    }
//
//    public String ret(String out, String outA){
//        out += "\tret i32 " + outA + "\n";
//        return out;
//    }
//}
