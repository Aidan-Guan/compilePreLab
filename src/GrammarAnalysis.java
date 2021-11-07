import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class GrammarAnalysis {
    private int regNum = 1;
    private ArrayList<Ident> identList = new ArrayList<>();
    private ArrayList<String> storage = new ArrayList<>();
    private ArrayDeque<Token> preSym = new ArrayDeque<>();
    private ArrayList<String> funcList = new ArrayList<>();
    Token currentSym = new Token();
    TokenAnalysis lex = new TokenAnalysis();
    PushbackReader in;
    String out = "";
    private boolean isConst = false;

    private boolean addConstAndVar(String ident, ExpValue expValue, boolean isConst, boolean isGiven){
        if(expValue == null){
            error();
            return false;
        }
        for(Ident e: identList){
            if(e.identName.equals(ident)){
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

    private boolean updateConstAndVar(String ident, ExpValue value){
        for(Ident e: identList){
            if(e.identName.equals(ident)){
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

    private boolean isInIdentList(String ident){
        for(Ident e: identList){
            if(e.identName.equals(ident)) return true;
        }
        return false;
    }
    private int getValue(String ident){
        int value = 0;
        for(Ident e: identList){
            if (e.identName.equals(ident)){
                value = e.value;
            }
        }
        return value;
    }

    private int getRegister(String ident){
        int register = 0;
        for(Ident e: identList){
            if (e.identName.equals(ident)){
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

    private void Decl() throws IOException {
        if(currentSym.word.equals("const")){
            ConstDecl();
        } else{
            VarDecl();
        }
    }

    private void ConstDecl() throws IOException {
        if(!currentSym.word.equals("const")){
            error();
        } else{
            getSym();
            BType();
            ConstDef();
            while(currentSym.word.equals(",")){
                getSym();
                ConstDef();
            }
            if(!currentSym.word.equals(";")){
                error();
            }
            getSym();
        }
    }

    private void BType() throws IOException {
        if(!currentSym.word.equals("int")){
            error();
        } else{
            getSym();
        }
    }

    private void ConstDef() throws IOException {
        String ident = IdentID();
        if(!currentSym.word.equals("=")){
            error();
        }else{
            getSym();
            isConst = true;
            ExpValue expValue = ConstInitVal();
            isConst = false;

            if(!addConstAndVar(ident, expValue, true, true)) error();
        }
    }

    private ExpValue ConstInitVal() throws IOException {
        return ConstExp();
    }

    private ExpValue ConstExp() throws IOException {
        return AddExp();
    }

    private void VarDecl() throws IOException {
        BType();
        VarDef();
        while(currentSym.word.equals(",")){
            getSym();
            VarDef();
        }
        if(!currentSym.word.equals(";")){
            error();
        } else {
            getSym();
        }
    }

    private void VarDef() throws IOException {
        String ident = IdentID();
        if(currentSym.word.equals("=")){
            getSym();
            ExpValue expValue = InitVal();
            if(!addConstAndVar(ident, expValue, false, true)) error();
        } else {
            ExpValue expValue = new ExpValue(0, false);
            if(!addConstAndVar(ident, expValue, false, false))error();
        }
    }

    private ExpValue InitVal() throws IOException {
        return Exp();
    }

    private void FuncDef() throws IOException {
        FuncType();
        Ident();
        if(!currentSym.word.equals("(")) {
            error();
        } else {
            out += "(";
            getSym();
            if(!currentSym.word.equals(")")) {
                error();
            } else {
                out += ")";
                getSym();
                Block();
            }
        }
    }

    private void FuncType() throws IOException {
        if(!currentSym.word.equals("int")) {
            error();
        } else {
            out += "define dso_local i32 ";
            getSym();
        }
    }

    private void Ident() throws IOException {
        if(!currentSym.word.equals("main")){
            error();
        } else {
            out += "@main";
            getSym();
        }
    }

    private void Block() throws IOException {
        if(!currentSym.word.equals("{")){
            error();
        } else {
            out += "{\n";
            getSym();
            while(!currentSym.word.equals("}") && !currentSym.type.equals("ERR"))
                BlockItem();
            out += "}";
            getSym();
        }
    }

    private void BlockItem() throws IOException {
        if(currentSym.word.equals("int") || currentSym.word.equals("const")){
            Decl();
        }else {
            Stmt();
        }
    }

    private void Stmt() throws IOException {
        boolean flag = false;
        if(currentSym.type.equals("ID")){
            Token pre = currentSym;
            getSym();
            if(currentSym.word.equals("(")){
                unGetSym(currentSym);
                currentSym = pre;
                flag = true;
            } else {
                unGetSym(currentSym);
                currentSym = pre;
            }
        }
        if(flag){
            if (!currentSym.word.equals(";")) {
                Exp();
                if(!currentSym.word.equals(";")){
                    error();
                } else{
                    getSym();
                }
            } else {
                getSym();
            }
        } else if (currentSym.word.equals("return")){
            getSym();
            ExpValue expValue = Exp();
            if(expValue == null) System.exit(-1);
            ret(expValue);
            if(!currentSym.word.equals(";")) {
                error();
            } else {
                getSym();
            }
        } else if(currentSym.word.equals(";")){
            getSym();
        } else {
            String ident = LVal();
            if(!isInIdentList(ident))error();
            if(!currentSym.word.equals("=")){
                error();
            }else{
                getSym();
                ExpValue value = Exp();
                if(value == null) error();
                if(!currentSym.word.equals(";")){
                    error();
                }else {
                    if(!updateConstAndVar(ident, value)) error();
                    getSym();
                }
            }
        }
    }

    private String LVal() throws IOException {
        return IdentID();
    }

    private ExpValue Exp() throws IOException {
        return AddExp();
    }

    private ExpValue AddExp() throws IOException {
        ExpValue expValue = MulExp();
        while(currentSym.word.equals("+") || currentSym.word.equals("-")){
            if(currentSym.word.equals("+")){
                getSym();
                expValue = addition(expValue, MulExp());
            } else {
                getSym();
                expValue = subtraction(expValue, MulExp());
            }

        }
        return expValue;
    }

    private ExpValue MulExp() throws IOException {
        ExpValue expValue = UnaryExp();
        while(currentSym.word.equals("*") || currentSym.word.equals("/") || currentSym.word.equals("%")){
            if(currentSym.word.equals("*")){
                getSym();
                expValue = multiple(expValue, UnaryExp());
            } else if(currentSym.word.equals("/")){
                getSym();
                expValue = division(expValue, UnaryExp());
            } else {
                getSym();
                expValue = mod(expValue, UnaryExp());
            }
        }
        return expValue;
    }

    private ExpValue UnaryExp() throws IOException {
        boolean flag = false;
        if(!isInIdentList(currentSym.word) && currentSym.type.equals("ID")){
            error();
        }
        if(currentSym.type.equals("ID")){
            Token pre = currentSym;
            getSym();
            if(!currentSym.word.equals("(")){
                unGetSym(currentSym);
                currentSym = pre;

            } else {
                unGetSym(currentSym);
                currentSym = pre;
                flag = true;
            }
        }
        if(flag) {
            String ident = IdentID();
            declareFunc(ident);
            if(!currentSym.word.equals("(")){
                error();
            } else {
                getSym();
                if(!currentSym.word.equals(")")){
                    ExpValue param = FuncRParams();
                    ExpValue expValue = resolveFunc(ident, param);
                    if(!currentSym.word.equals(")")){
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
        else if((currentSym.word.equals("(") || currentSym.type.equals("NUMBER") || currentSym.type.equals("ID"))) {
            return PrimaryExp();
        } else if(currentSym.word.equals("+") || currentSym.word.equals("-")){
            String sign = UnaryOp();
            ExpValue expValue = UnaryExp();
            if(sign.equals("-"))
                return subtraction(new ExpValue(0, false), expValue);
            else return expValue;
        }
        return null;
    }

    private ExpValue FuncRParams() throws IOException {
        ExpValue expValue = Exp();  //todo 只支持一个参数
        while(currentSym.word.equals(",")){
            getSym();
            Exp();
        }
        return expValue;
    }

    private ExpValue PrimaryExp() throws IOException {
        if(currentSym.word.equals("(")){
            getSym();
            ExpValue expValue = Exp();
            if(!currentSym.word.equals(")")){
                error();
            } else{
                getSym();
            }
            return expValue;
        } else if(currentSym.type.equals("NUMBER")){
            ExpValue expValue = new ExpValue(currentSym.number, false);
            getSym();
            return expValue;
        } else {
            String ident = LVal();
            for(Ident e: identList){
                if(e.identName.equals(ident) && !e.isConst && isConst) error();
            }
            ExpValue expValue = new ExpValue(getValue(ident), true, getRegister(ident));
            out = expValue.load(out, regNum++, expValue.register);
            return new ExpValue(true, "%" + (regNum-1));
        }
    }

    private String UnaryOp() throws IOException {
        if(currentSym.word.equals("-") || currentSym.word.equals("+")){
            String sign = currentSym.word;
            getSym();
            return sign;
        } else{
            error();
        }
        return "";
    }

    private String IdentID() throws IOException {
        String ident = currentSym.word;
        if(!currentSym.type.equals("ID")){
            error();
            System.exit(-1);
        }else{
            getSym();
        }
        return ident;
    }

    private void error() {
        System.exit(-1);
    }

    void getSym() throws IOException {
        if(preSym.isEmpty()){
            currentSym = lex.getToken(in);
        } else {
            currentSym = preSym.removeFirst();
        }

    }

    private void unGetSym(Token token){
        preSym.addLast(token);
    }

    public GrammarAnalysis(PushbackReader in) {
        this.in = in;
    }



    private ExpValue addition(ExpValue a, ExpValue b){
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
    private ExpValue subtraction(ExpValue a, ExpValue b){
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

    private ExpValue multiple(ExpValue a, ExpValue b) {
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

    private ExpValue division(ExpValue a, ExpValue b){
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

    private ExpValue mod(ExpValue a, ExpValue b){
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

    private void ret(ExpValue a) {

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

    private void declareFunc(String ident){
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

    private ExpValue resolveFunc(String ident, ExpValue param){


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
