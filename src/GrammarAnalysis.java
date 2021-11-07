import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;

public class GrammarAnalysis {
    public int regNum = 1;
    public ArrayList<Ident> identList = new ArrayList<>();
    public ArrayList<String> storage = new ArrayList<>();
    public ArrayDeque<Token> preSym = new ArrayDeque<>();
    public ArrayList<String> funcList = new ArrayList<>();
    Token sym = new Token();
    TokenAnalysis lex = new TokenAnalysis();
    PushbackReader in;
    String out = "";
    boolean isError = false;
    boolean isConst = false;

    public boolean addConstAndVar(String ident, ExpValue expValue, boolean isConst, boolean isGiven){
        if(expValue == null){
            error();
            return false;
        }
        for(Ident e: identList){
            if(e.ident.equals(ident)){
                return false;
            }
        }
        identList.add(new Ident(isConst, expValue.value, ident, regNum++));
        out += "\t%"+ (regNum-1) +" = alloca i32\n";
        storage.add("%"+(regNum-1));
        if(isGiven){
            if(expValue.isRegister)
                out = expValue.store(out, regNum-1, expValue.register);

            else
                out = expValue.store(out, regNum-1, expValue.value+ "");
        }
        return true;
    }

    public boolean updateConstAndVar(String ident, ExpValue value){
        for(Ident e: identList){
            if(e.ident.equals(ident)){
                if(e.isConst) return false;
                else {
                    e.value = value.value;
                    int register = getRegister(ident);
                    if(value.isRegister)
                        out = value.store(out, register, value.register);

                    else
                        out = value.store(out, register, value.value+ "");
                    return true;
                }
            }
        }
        return true;
    }

    public boolean isInIdentList(String ident){
        for(Ident e: identList){
            if(e.ident.equals(ident)) return true;
        }
        return false;
    }
    public int getValue(String ident){
        int value = 0;
        for(Ident e: identList){
            if (e.ident.equals(ident)){
                value = e.value;
            }
        }
        return value;
    }

    public int getRegister(String ident){
        int register = 0;
        for(Ident e: identList){
            if (e.ident.equals(ident)){
                register = e.register;
            }
        }
        return register;
    }

    public void CompUnit() throws IOException {
        identList.add(new Ident(false, 0, "putint", -1));
        identList.add(new Ident(false, 0, "getint", -1));
        identList.add(new Ident(false, 0, "putch", -1));
        identList.add(new Ident(false, 0, "getch", -1));
        FuncDef();
    }

    public void Decl() throws IOException {
        if(sym.word.equals("const")){
            ConstDecl();
        } else{
            VarDecl();
        }
    }

    public void ConstDecl() throws IOException {
        if(!sym.word.equals("const")){
            error();
        } else{
            getSym();
            BType();
            ConstDef();
            while(sym.word.equals(",")){
                getSym();
                ConstDef();
            }
            if(!sym.word.equals(";")){
                error();
            }
            getSym();
        }
    }

    public void BType() throws IOException {
        if(!sym.word.equals("int")){
            error();
        } else{
            getSym();
        }
    }

    public void ConstDef() throws IOException {
        String ident = IdentID();
        if(!sym.word.equals("=")){
            error();
        }else{
            getSym();
            isConst = true;
            ExpValue expValue = ConstInitVal();
            isConst = false;

            if(!addConstAndVar(ident, expValue, true, true)) error();
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
        while(sym.word.equals(",")){
            getSym();
            VarDef();
        }
        if(!sym.word.equals(";")){
            error();
        } else {
            getSym();
        }
    }

    public void VarDef() throws IOException {
        String ident = IdentID();
        if(sym.word.equals("=")){
            getSym();
            ExpValue expValue = InitVal();
            if(!addConstAndVar(ident, expValue, false, true)) error();
        } else {
            ExpValue expValue = new ExpValue(0, false);
            if(!addConstAndVar(ident, expValue, false, false))error();
        }
    }

    public ExpValue InitVal() throws IOException {
        return Exp();
    }

    public void FuncDef() throws IOException {
        FuncType();
        Ident();
        if(!sym.word.equals("(")) {
            error();
        } else {
            out += "(";
            getSym();
            if(!sym.word.equals(")")) {
                error();
            } else {
                out += ")";
                getSym();
                Block();
            }
        }
    }

    public void FuncType() throws IOException {
        if(!sym.word.equals("int")) {
            error();
        } else {
            out += "define dso_local i32 ";
            getSym();
        }
    }

    public void Ident() throws IOException {
        if(!sym.word.equals("main")){
            error();
        } else {
            out += "@main";
            getSym();
        }
    }

    public void Block() throws IOException {
        if(!sym.word.equals("{")){
            error();
        } else {
            out += "{\n";
            getSym();
            while(!sym.word.equals("}") && !sym.type.equals("ERR"))
                BlockItem();
            out += "}";
            getSym();
        }
    }

    public void BlockItem() throws IOException {
        if(sym.word.equals("int") || sym.word.equals("const")){
            Decl();
        }else {
            Stmt();
        }
    }

    public void Stmt() throws IOException {
        boolean flag = false;
        if(sym.type.equals("ID")){
            Token pre = sym;
            getSym();
            if(sym.word.equals("(")){
                unGetSym(sym);
                sym = pre;
                flag = true;
            } else {
                unGetSym(sym);
                sym = pre;
            }
        }
        if(flag){
            if (!sym.word.equals(";")) {
                Exp();
                if(!sym.word.equals(";")){
                    error();
                } else{
                    getSym();
                }
            } else {
                getSym();
            }
        } else if (sym.word.equals("return")){
            getSym();
            ExpValue expValue = Exp();
            if(expValue == null) System.exit(-1);
            ret(expValue);
            if(!sym.word.equals(";")) {
                error();
            } else {
                getSym();
            }
        } else if(sym.word.equals(";")){
            getSym();
        } else {
            String ident = LVal();
            if(!isInIdentList(ident))error();
            if(!sym.word.equals("=")){
                error();
            }else{
                getSym();
                ExpValue value = Exp();
                if(value == null) error();
                if(!sym.word.equals(";")){
                    error();
                }else {
                    if(!updateConstAndVar(ident, value)) error();
                    getSym();
                }
            }
        }
    }

    public String LVal() throws IOException {
        return IdentID();
    }

    public ExpValue Exp() throws IOException {
        return AddExp();
    }

    public ExpValue AddExp() throws IOException {
        ExpValue expValue = MulExp();
        while(sym.word.equals("+") || sym.word.equals("-")){
            if(sym.word.equals("+")){
                getSym();
                expValue = addition(expValue, MulExp());
            } else {
                getSym();
                expValue = subtraction(expValue, MulExp());
            }

        }
        return expValue;
    }

    public ExpValue MulExp() throws IOException {
        ExpValue expValue = UnaryExp();
        while(sym.word.equals("*") || sym.word.equals("/") || sym.word.equals("%")){
            if(sym.word.equals("*")){
                getSym();
                expValue = multiple(expValue, UnaryExp());
            } else if(sym.word.equals("/")){
                getSym();
                expValue = division(expValue, UnaryExp());
            } else {
                getSym();
                expValue = mod(expValue, UnaryExp());
            }
        }
        return expValue;
    }

    public ExpValue UnaryExp() throws IOException {
        boolean flag = false;
        if(!isInIdentList(sym.word) && sym.type.equals("ID")){
            error();
        }
        if(sym.type.equals("ID")){
            Token pre = sym;
            getSym();
            if(!sym.word.equals("(")){
                unGetSym(sym);
                sym = pre;

            } else {
                unGetSym(sym);
                sym = pre;
                flag = true;
            }
        }
        if(flag) {
            String ident = IdentID();
            declareFunc(ident);
            if(!sym.word.equals("(")){
                error();
            } else {
                getSym();
                if(!sym.word.equals(")")){
                    ExpValue param = FuncRParams();
                    ExpValue expValue = resolveFunc(ident, param);
                    if(!sym.word.equals(")")){
                        error();
                    } else {
                        getSym();
                    }
                    return expValue;
                } else {
                    ExpValue param = FuncRParams();
                    ExpValue expValue = resolveFunc(ident, param);
                    getSym();
                    return expValue;
                }
            }
        }
        else if((sym.word.equals("(") || sym.type.equals("NUMBER") || sym.type.equals("ID"))) {
            return PrimaryExp();
        } else if(sym.word.equals("+") || sym.word.equals("-")){
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
        while(sym.word.equals(",")){
            getSym();
            Exp();
        }
        return expValue;
    }

    public ExpValue PrimaryExp() throws IOException {
        if(sym.word.equals("(")){
            getSym();
            ExpValue expValue = Exp();
            if(!sym.word.equals(")")){
                error();
            } else{
                getSym();
            }
            return expValue;
        } else if(sym.type.equals("NUMBER")){
            ExpValue expValue = new ExpValue(sym.number, false);
            getSym();
            return expValue;
        } else {
            String ident = LVal();
            for(Ident e: identList){
                if(e.ident.equals(ident) && !e.isConst && isConst) error();
            }
            ExpValue expValue = new ExpValue(getValue(ident), true, getRegister(ident));
            out = expValue.load(out, regNum++, expValue.register);
            return new ExpValue(true, "%" + (regNum-1));
        }
    }

    public String UnaryOp() throws IOException {
        if(sym.word.equals("-") || sym.word.equals("+")){
            String sign = sym.word;
            getSym();
            return sign;
        } else{
            error();
        }
        return "";
    }

    /**
     * temp
     */
    public String IdentID() throws IOException {
        String ident = sym.word;
        if(!sym.type.equals("ID")){
            error();
            System.exit(-1);
        }else{
            getSym();
        }
        return ident;
    }


    public void error() {
        this.isError = true;
    }

    public void getSym() throws IOException {
        if(preSym.isEmpty()){
            sym = lex.getToken(in);
        } else {
            sym = preSym.removeFirst();
        }

    }

    public void unGetSym(Token token){
        preSym.addLast(token);
    }

    public GrammarAnalysis(PushbackReader in) {
        this.in = in;
    }




    public ExpValue addition(ExpValue a, ExpValue b){
        ExpValue expValue;
        String outA = a.value + "", outB = b.value + "";
        if(a.isRegister){
            outA = a.register;
        }
        if(b.isRegister){
            outB = b.register;
        }
        out = a.addition(out, regNum, outA, outB);
        expValue = new ExpValue(a.value + b.value, true, regNum++);

        return expValue;
    }
    public ExpValue subtraction(ExpValue a, ExpValue b){
        ExpValue expValue;
        String outA = a.value + "", outB = b.value + "";
        if(a.isRegister){
            outA = a.register;
        }
        if(b.isRegister){
            outB = b.register;
        }
        out = a.subtraction(out, regNum, outA, outB);
        expValue = new ExpValue(a.value - b.value, true, regNum++);

        return expValue;
    }

    public ExpValue multiple(ExpValue a, ExpValue b) {
        ExpValue expValue;
        String outA = a.value + "", outB = b.value + "";
        if (a.isRegister) {
            outA = a.register;
        }
        if (b.isRegister) {
            outB = b.register;
        }
        out = a.multiple(out, regNum, outA, outB);
        expValue = new ExpValue(a.value * b.value, true, regNum++);
        return expValue;
    }

    public ExpValue division(ExpValue a, ExpValue b){
        ExpValue expValue;
        String outA = a.value + "", outB = b.value + "";
        if (a.isRegister) {
            outA = a.register;
        }
        if (b.isRegister) {
            outB = b.register;
        }
        out = a.division(out, regNum, outA, outB);
        expValue = new ExpValue(0, true, regNum++);
        return expValue;
    }

    public ExpValue mod(ExpValue a, ExpValue b){
        ExpValue expValue;
        String outA = a.value + "", outB = b.value + "";
        if (a.isRegister) {
            outA = a.register;
        }
        if (b.isRegister) {
            outB = b.register;
        }
        out = a.mod(out, regNum, outA, outB);
        expValue = new ExpValue(a.value % b.value, true, regNum++);
        return expValue;
    }

    public void ret(ExpValue a) {

        String outA = a.value + "";
        if(a.isRegister){
            outA = a.register;
            if(storage.contains(outA)){
                out = a.load(out, regNum, a.register);
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
                if (param.isRegister) {
                    output = param.register;
                }
                out += "\tcall void @putint(i32 " + output + ")\n";
            }
            case "putch"-> {
                if(param == null) {
                    error();
                    return new ExpValue(0, true, regNum++);
                }
                String output = param.value + "";
                if (param.isRegister) {
                    output = param.register;
                }
                out += "\tcall void @putch(i32 " + output + ")\n";
            }
        }
        return expValue;
    }
}
