import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class GrammarAnalysis {
    public int regNum = 1;
    public ArrayList<Ident> identArrayList = new ArrayList<>();
    public ArrayList<String> store = new ArrayList<>();
    public ArrayDeque<Token> lastSyms = new ArrayDeque<>();
    public ArrayList<String> funcList = new ArrayList<>();
    static Token currentSym;
    static {
        try {
            currentSym = LexAnal.getNextToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    PushbackReader in;
    String out = "";
    boolean isConst = false;

    public boolean addVar(String ident, ExpValue expValue, boolean isConst, boolean isGiven){
        if(expValue == null){
            error();
            return false;
        }
        for(Ident e: identArrayList){
            if(e.ident.equals(ident)){
                return false;
            }
        }
        identArrayList.add(new Ident(isConst, expValue.value, ident, regNum++));
        out += "\t%"+ (regNum-1) +" = alloca i32\n";
        store.add("%"+(regNum-1));
        if(isGiven){
            if(expValue.isReg)
                out = expValue.store(out, regNum-1, expValue.reg);

            else
                out = expValue.store(out, regNum-1, expValue.value+ "");
        }
        return true;
    }

    public boolean updateConstAndVar(String ident, ExpValue value){
        for(Ident e: identArrayList){
            if(e.ident.equals(ident)){
                if(e.isConst) return false;
                else {
                    e.value = value.value;
                    int register = getRegister(ident);
                    if(value.isReg)
                        out = value.store(out, register, value.reg);

                    else
                        out = value.store(out, register, value.value+ "");
                    return true;
                }
            }
        }
        return true;
    }

    public boolean isInIdentList(String ident){
        for(Ident e: identArrayList){
            if(e.ident.equals(ident)) return true;
        }
        return false;
    }
    public int getValue(String ident){
        int value = 0;
        for(Ident e: identArrayList){
            if (e.ident.equals(ident)){
                value = e.value;
            }
        }
        return value;
    }

    public int getRegister(String ident){
        int register = 0;
        for(Ident e: identArrayList){
            if (e.ident.equals(ident)){
                register = e.register;
            }
        }
        return register;
    }

    public void CompUnit() throws IOException {
        identArrayList.add(new Ident(false, 0, "putint", -1));
        identArrayList.add(new Ident(false, 0, "getint", -1));
        identArrayList.add(new Ident(false, 0, "putch", -1));
        identArrayList.add(new Ident(false, 0, "getch", -1));
        FuncDef();
    }

    public void Decl() throws IOException {
        if(currentSym.value.equals("const")){
            ConstDecl();
        }
        else{
            VarDecl();
        }
    }

    public void ConstDecl() throws IOException {
        if(!currentSym.value.equals("const")){
            error();
        }
        getNextSym();
        BType();
        ConstDef();
        while(currentSym.value.equals(",")){
            getNextSym();
            ConstDef();
        }
        if(!currentSym.value.equals(";")){
            error();
        }
        getNextSym();
    }

    public void BType() throws IOException {
        if(!currentSym.value.equals("int")){
            error();
        }
        getNextSym();
    }

    public void ConstDef() throws IOException {
        String ident = IdentName();
        if(!currentSym.value.equals("=")){
            error();
        }else{
            getNextSym();
            isConst = true;
            ExpValue expValue = ConstInitVal();
            isConst = false;

            if(!addVar(ident, expValue, true, true)) error();
        }
    }

    public ExpValue ConstInitVal() throws IOException {
        return ConstExp();
    }

    public ExpValue ConstExp() throws IOException {
        return AddExp();
    }

    public void VarDecl() throws IOException {
        BType();
        VarDef();
        while(currentSym.value.equals(",")){
            getNextSym();
            VarDef();
        }
        if(!currentSym.value.equals(";")){
            error();
        }
        getNextSym();
    }

    public void VarDef() throws IOException {
        String ident = IdentName();
        if(currentSym.value.equals("=")){
            getNextSym();
            ExpValue expValue = InitVal();
            if(!addVar(ident, expValue, false, true)) error();
        } else {
            ExpValue expValue = new ExpValue(0, false);
            if(!addVar(ident, expValue, false, false))error();
        }
    }

    public ExpValue InitVal() throws IOException {
        return Exp();
    }

    public void FuncDef() throws IOException {
        FuncType();
        Ident();
        if(!currentSym.value.equals("(")) {
            error();
        }
        out += "(";
        getNextSym();
        if(!currentSym.value.equals(")")) {
            error();
        }
        out += ")";
        getNextSym();
        Block();
    }

    public void FuncType() throws IOException {
        if(!currentSym.value.equals("int")) {
            error();
        }
        out += "define dso_local i32 ";
        getNextSym();
    }

    public void Ident() throws IOException {
        if(!currentSym.value.equals("main")){
            error();
        }
        out += "@main";
        getNextSym();
    }

    public void Block() throws IOException {
        if(!currentSym.value.equals("{")){
            error();
        }
        out += "{\n";
        getNextSym();
        while(!currentSym.value.equals("}") && !currentSym.type.equals("ERR"))
            BlockItem();
        out += "}";
        getNextSym();
    }

    public void BlockItem() throws IOException {
        if(currentSym.value.equals("int") || currentSym.value.equals("const")){
            Decl();
        }
        else {
            Stmt();
        }
    }

    public void Stmt() throws IOException {
        boolean flag = false;
        if(currentSym.type.equals("ID")){
            Token pre = currentSym;
            getNextSym();
            if(currentSym.value.equals("(")){
                unGetSym(currentSym);
                currentSym = pre;
                flag = true;
            }
            else {
                unGetSym(currentSym);
                currentSym = pre;
            }
        }

        if(flag){
            if (!currentSym.value.equals(";")) {
                Exp();
                if(!currentSym.value.equals(";")){
                    error();
                }
            }
            getNextSym();
        }
        else if (currentSym.value.equals("return")){
            getNextSym();
            ExpValue expValue = Exp();
            if(expValue == null) error();

            ret(expValue);
            if(!currentSym.value.equals(";")) {
                error();
            }
            getNextSym();
        }
        else if(currentSym.value.equals(";")){
            getNextSym();
        }
        else {
            String ident = LVal();
            if(!isInIdentList(ident)) error();
            if(!currentSym.value.equals("=")){
                error();
            }
            else{
                getNextSym();
                ExpValue value = Exp();
                if(value == null) error();
                if(!currentSym.value.equals(";")){
                    error();
                }
                if(!updateConstAndVar(ident, value)) error();
                getNextSym();
            }
        }
    }

    public String LVal() throws IOException {
        return IdentName();
    }

    public ExpValue Exp() throws IOException {
        return AddExp();
    }

    public ExpValue AddExp() throws IOException {
        ExpValue expValue = MulExp();
        while(currentSym.value.equals("+") || currentSym.value.equals("-")){
            if(currentSym.value.equals("+")){
                getNextSym();
                expValue = addition(expValue, MulExp());
            }
            else {
                getNextSym();
                expValue = subtraction(expValue, MulExp());
            }

        }
        return expValue;
    }

    public ExpValue MulExp() throws IOException {
        ExpValue expValue = UnaryExp();
        while(currentSym.value.equals("*") || currentSym.value.equals("/") || currentSym.value.equals("%")){
            if(currentSym.value.equals("*")){
                getNextSym();
                expValue = multiple(expValue, UnaryExp());
            } else if(currentSym.value.equals("/")){
                getNextSym();
                expValue = division(expValue, UnaryExp());
            } else {
                getNextSym();
                expValue = mod(expValue, UnaryExp());
            }
        }
        return expValue;
    }

    public ExpValue UnaryExp() throws IOException {
        boolean flag = false;
        if(!isInIdentList(currentSym.value) && currentSym.type.equals("ID")){
            error();
        }
        if(currentSym.type.equals("ID")){
            Token pre = currentSym;
            getNextSym();
            if(!currentSym.value.equals("(")){
                unGetSym(currentSym);
                currentSym = pre;
            }
            else {
                unGetSym(currentSym);
                currentSym = pre;
                flag = true;
            }
        }
        if(flag) {
            String ident = IdentName();
            declareFunc(ident);
            if(!currentSym.value.equals("(")){
                error();
            }
            getNextSym();
            if(!currentSym.value.equals(")")){
                ExpValue param = FuncRParams();
                ExpValue expValue = resolveFunc(ident, param);
                if(!currentSym.value.equals(")")){
                    error();
                } else {
                    getNextSym();
                }
                return expValue;
            }
            else {
                ExpValue param = FuncRParams();
                ExpValue expValue = resolveFunc(ident, param);
                getNextSym();
                return expValue;
            }

        }
        else if((currentSym.value.equals("(") || currentSym.type.equals("NUMBER") || currentSym.type.equals("ID"))) {
            return PrimaryExp();
        }
        else if(currentSym.value.equals("+") || currentSym.value.equals("-")){
            String sign = UnaryOp();
            ExpValue expValue = UnaryExp();
            if(sign.equals("-"))
                return subtraction(new ExpValue(0, false), expValue);
            else return expValue;
        }
        return null;
    }

    public ExpValue FuncRParams() throws IOException {
        ExpValue expValue = Exp();  //todo 只支持一个参数
        while(currentSym.value.equals(",")){
            getNextSym();
            Exp();
        }
        return expValue;
    }

    public ExpValue PrimaryExp() throws IOException {
        if(currentSym.value.equals("(")){
            getNextSym();
            ExpValue expValue = Exp();
            if(!currentSym.value.equals(")")){
                error();
            } else{
                getNextSym();
            }
            return expValue;
        }
        else if(currentSym.type.equals("NUMBER")){
            ExpValue expValue = new ExpValue(Integer.parseInt(currentSym.value), false);
            getNextSym();
            return expValue;
        }
        else {
            String ident = LVal();
            for(Ident e: identArrayList){
                if(e.ident.equals(ident) && !e.isConst && isConst) error();
            }
            ExpValue expValue = new ExpValue(getValue(ident), true, getRegister(ident));
            out = expValue.load(out, regNum++, expValue.reg);
            return new ExpValue(true, "%" + (regNum-1));
        }
    }

    public String UnaryOp() throws IOException {
        if(currentSym.value.equals("-") || currentSym.value.equals("+")){
            String sign = currentSym.value;
            getNextSym();
            return sign;
        }
        else{
            error();
        }
        return "";
    }


    public String IdentName() throws IOException {
        String ident = currentSym.value;
        if(!currentSym.type.equals("ID")){
            error();
        }
        getNextSym();
        return ident;
    }


    private void error() {
        System.exit(-1);
    }

    public void getNextSym() throws IOException {
        if(lastSyms.isEmpty()){
            currentSym = LexAnal.getNextToken();
        } else {
            currentSym = lastSyms.removeFirst();
        }
    }

    public void unGetSym(Token token){
        lastSyms.addLast(token);
    }

    public GrammarAnalysis(PushbackReader in) {
        this.in = in;
    }




    public ExpValue addition(ExpValue a, ExpValue b){
        ExpValue expValue;
        String outA = a.value + "", outB = b.value + "";
        if(a.isReg){
            outA = a.reg;
        }
        if(b.isReg){
            outB = b.reg;
        }
        out = a.addition(out, regNum, outA, outB);
        expValue = new ExpValue(a.value + b.value, true, regNum++);

        return expValue;
    }
    public ExpValue subtraction(ExpValue a, ExpValue b){
        ExpValue expValue;
        String outA = a.value + "", outB = b.value + "";
        if(a.isReg){
            outA = a.reg;
        }
        if(b.isReg){
            outB = b.reg;
        }
        out = a.subtraction(out, regNum, outA, outB);
        expValue = new ExpValue(a.value - b.value, true, regNum++);

        return expValue;
    }

    public ExpValue multiple(ExpValue a, ExpValue b) {
        ExpValue expValue;
        String outA = a.value + "", outB = b.value + "";
        if (a.isReg) {
            outA = a.reg;
        }
        if (b.isReg) {
            outB = b.reg;
        }
        out = a.multiple(out, regNum, outA, outB);
        expValue = new ExpValue(a.value * b.value, true, regNum++);
        return expValue;
    }

    public ExpValue division(ExpValue a, ExpValue b){
        ExpValue expValue;
        String outA = a.value + "", outB = b.value + "";
        if (a.isReg) {
            outA = a.reg;
        }
        if (b.isReg) {
            outB = b.reg;
        }
        out = a.division(out, regNum, outA, outB);
        expValue = new ExpValue(0, true, regNum++);
        return expValue;
    }

    public ExpValue mod(ExpValue a, ExpValue b){
        ExpValue expValue;
        String outA = a.value + "", outB = b.value + "";
        if (a.isReg) {
            outA = a.reg;
        }
        if (b.isReg) {
            outB = b.reg;
        }
        out = a.mod(out, regNum, outA, outB);
        expValue = new ExpValue(a.value % b.value, true, regNum++);
        return expValue;
    }

    public void ret(ExpValue a) {

        String outA = a.value + "";
        if(a.isReg){
            outA = a.reg;
            if(store.contains(outA)){
                out = a.load(out, regNum, a.reg);
                outA = "%" + regNum;
            }

        }

        out = a.ret(out, outA);
    }



    public void declareFunc(String ident){
        if(funcList.contains(ident)){
            return;
        }
        funcList.add(ident);
        switch(ident){
            case "getint"-> out = "declare i32 @getint()\n" + out;
            case "getch"-> out = "declare i32 @getch()\n" + out;
            case "putint"-> out = "declare void @putint(i32)\n" + out;
            case "putch"-> out = "declare void @putch(i32)\n" + out;
        }
    }

    public ExpValue resolveFunc(String ident, ExpValue param){


        ExpValue expValue = null;
        switch(ident){
            case "getint"->{
                if(param != null){
                    error();
                    return new ExpValue(0, true, regNum++);
                }
                expValue = new ExpValue(0, true, regNum++);
                out += "\t%" + (regNum-1) +" = call i32 @getint()\n";
            }
            case "getch"->{
                if(param != null){
                    error();
                    return new ExpValue(0, true, regNum++);
                }
                expValue = new ExpValue(0, true, regNum++);
                out += "\t%" + (regNum-1) +" = call i32 @getch()\n";
            }
            case "putint"-> {
                if(param == null) {
                    error();
                    return new ExpValue(0, true, regNum++);
                }
                String output = param.value + "";
                if (param.isReg) {
                    output = param.reg;
                }
                out += "\tcall void @putint(i32 " + output + ")\n";
            }
            case "putch"-> {
                if(param == null) {
                    error();
                    return new ExpValue(0, true, regNum++);
                }
                String output = param.value + "";
                if (param.isReg) {
                    output = param.reg;
                }
                out += "\tcall void @putch(i32 " + output + ")\n";
            }
        }
        return expValue;
    }
}
