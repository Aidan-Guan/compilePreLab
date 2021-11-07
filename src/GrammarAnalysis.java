import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class GrammarAnalysis {
    private int regNum = 1;
    private final ArrayList<Ident> identList = new ArrayList<>();
    private final ArrayList<String> storage = new ArrayList<>();
    private final ArrayDeque<Token> preSym = new ArrayDeque<>();
    private final ArrayList<String> funcList = new ArrayList<>();
    Token currentSym = new Token();
    PushbackReader in;
    String out = "";
    private boolean isConst = false;

    private boolean addConstAndVar(String ident, ExpValue expValue, boolean isConst, boolean isGiven){
        if(expValue == null){
            error();
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
            if(e.identName.equals(ident)) {
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
        if(currentSym.value.equals("const")){
            ConstDecl();
        }
        else{
            VarDecl();
        }
    }

    private void ConstDecl() throws IOException {
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

    private void BType() throws IOException {
        if(!currentSym.value.equals("int")){
            error();
        }
        getNextSym();
    }

    private void ConstDef() throws IOException {
        String ident = IdentID();
        if(!currentSym.value.equals("=")){
            error();
        }
        getNextSym();
        isConst = true;
        ExpValue expValue = ConstInitVal();
        isConst = false;

        if(!addConstAndVar(ident, expValue, true, true)) error();

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
        while(currentSym.value.equals(",")){
            getNextSym();
            VarDef();
        }
        if(!currentSym.value.equals(";")){
            error();
        }
        getNextSym();
    }

    private void VarDef() throws IOException {
        String ident = IdentID();
        if(currentSym.value.equals("=")){
            getNextSym();
            ExpValue expValue = InitVal();
            if(!addConstAndVar(ident, expValue, false, true)) error();
        }
        else {
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

    private void FuncType() throws IOException {
        if(!currentSym.value.equals("int")) {
            error();
        }
        out += "define dso_local i32 ";
        getNextSym();
    }

    private void Ident() throws IOException {
        if(!currentSym.value.equals("main")){
            error();
        }
        out += "@main";
        getNextSym();
    }

    private void Block() throws IOException {
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

    private void BlockItem() throws IOException {
        if(currentSym.value.equals("int") || currentSym.value.equals("const")){
            Decl();
        }
        else {
            Stmt();
        }
    }

    private void Stmt() throws IOException {
        if (currentSym.type.equals("IDENT")) {
            Token preToken = currentSym;
            getNextSym();
            if (currentSym.value.equals("=")) {
                unGetSym(currentSym);
                currentSym = preToken;

                String ident = LVal();
                if (!isInIdentList(ident)) error();
                if (!currentSym.value.equals("=")) error();
                getNextSym();
                ExpValue value = Exp();
                if (value == null) error();
                if (!currentSym.value.equals(";")) error();
                if (!updateConstAndVar(ident, value)) error();
                getNextSym();
            }
            else {
                Exp();
                if (!currentSym.value.equals(";")) error();
                getNextSym();
            }
        }
        else if (currentSym.value.equals("{")) {
            Block();
        }
        else if (currentSym.value.equals("if")) {
            getNextSym();
            if (!currentSym.value.equals("(")) error();
            getNextSym();
            Cond();
            if (!currentSym.value.equals(")")) error();
            getNextSym();
            Stmt();

            if (currentSym.value.equals("else")) Stmt();

        }
        else if (currentSym.value.equals("return")) {
            getNextSym();
            ExpValue value = Exp();
            if (value == null) error();
            ret(value);
            if (!currentSym.value.equals(";")) error();
            getNextSym();
        }
        else if (currentSym.value.equals(";")) {
            getNextSym();
        }

//
//        boolean flag = false;
//        if(currentSym.type.equals("IDENT")){
//            Token pre = currentSym;
//            getSym();
//            if(currentSym.value.equals("(")){
//                unGetSym(currentSym);
//                currentSym = pre;
//                flag = true;
//            }
//            else {
//                unGetSym(currentSym);
//                currentSym = pre;
//            }
//        }
//
//        if(flag){
//            if (!currentSym.value.equals(";")) {
//                Exp();
//                if(!currentSym.value.equals(";")){
//                    error();
//                }
//                getSym();
//            }
//            else {
//                getSym();
//            }
//        }
//        else if (currentSym.value.equals("return")){
//            getSym();
//            ExpValue expValue = Exp();
//            if(expValue == null) System.exit(-1);
//            ret(expValue);
//            if(!currentSym.value.equals(";")) {
//                error();
//            }
//            getSym();
//        }
//        // 处理 ;
//        else if(currentSym.value.equals(";")){
//            getSym();
//        }
//        // 处理LVal = Exp ;
//        else {
//            String ident = LVal();
//            if(!isInIdentList(ident)) error();
//            if(!currentSym.value.equals("=")){
//                error();
//            }
//            getSym();
//            ExpValue value = Exp();
//            if(value == null) error();
//            if(!currentSym.value.equals(";")){
//                error();
//            }
//            if(!updateConstAndVar(ident, value)) error();
//            getSym();
//        }
    }


    private void Cond() throws IOException {
        LOrExp();
    }

    private void LOrExp() throws IOException {
        LAndExp();
        while (currentSym.value.equals("||")) {
            getNextSym();
            LAndExp();
        }
    }

    private void LAndExp() throws IOException {
        EqExp();
        while (currentSym.value.equals("&&")) {
            getNextSym();
            EqExp();
        }
    }

    private void EqExp() throws IOException {
        RelExp();
        while (currentSym.value.equals("==") || currentSym.value.equals("!=")) {
            getNextSym();
            RelExp();
        }
    }

    private void RelExp() throws IOException {
        ExpValue expValue = null;
        ExpValue expValue1 = AddExp();
        ExpValue expValue2 = null;
        String op = null;
        while (currentSym.value.equals("<")||currentSym.value.equals(">")||currentSym.value.equals("<=")||currentSym.value.equals(">=")) {
            op = currentSym.value;
            getNextSym();
            expValue2 = AddExp();
        }

        if (expValue2 != null) {
            expValue = condSituation(op, expValue1, expValue2);
            if (expValue != null) {

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

    private ExpValue MulExp() throws IOException {
        ExpValue expValue = UnaryExp();
        while(currentSym.value.equals("*") || currentSym.value.equals("/") || currentSym.value.equals("%")){
            if(currentSym.value.equals("*")){
                getNextSym();
                expValue = multiple(expValue, UnaryExp());
            }
            else if(currentSym.value.equals("/")){
                getNextSym();
                expValue = division(expValue, UnaryExp());
            }
            else {
                getNextSym();
                expValue = mod(expValue, UnaryExp());
            }
        }
        return expValue;
    }

    private ExpValue UnaryExp() throws IOException {
        boolean flag = false;
        if(!isInIdentList(currentSym.value) && currentSym.type.equals("IDENT")){
            error();
        }
        if(currentSym.type.equals("IDENT")){
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
            String ident = IdentID();
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
                }
                getNextSym();
                return expValue;
            } else {
                ExpValue param = FuncRParams();
                ExpValue expValue = resolveFunc(ident, param);
                getNextSym();
                return expValue;
            }
        }
        else if((currentSym.value.equals("(") || currentSym.type.equals("NUMBER") || currentSym.type.equals("IDENT"))) {
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

    private ExpValue FuncRParams() throws IOException {
        ExpValue expValue = Exp();
        while(currentSym.value.equals(",")){
            getNextSym();
            Exp();
        }
        return expValue;
    }

    private ExpValue PrimaryExp() throws IOException {
        if(currentSym.value.equals("(")){
            getNextSym();
            ExpValue expValue = Exp();
            if(!currentSym.value.equals(")")){
                error();
            }
            getNextSym();
            return expValue;
        }
        else if(currentSym.type.equals("NUMBER")){
            ExpValue expValue = new ExpValue(Integer.parseInt(currentSym.value), false);
            getNextSym();
            return expValue;
        }
        else {
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

    private String IdentID() throws IOException {
        String ident = currentSym.value;
        if(!currentSym.type.equals("IDENT")){
            error();
        }
        getNextSym();

        return ident;
    }

    private void error() {
        System.exit(-1);
    }

    void getNextSym() throws IOException {
        if(preSym.isEmpty()){
            currentSym = LexAnal.getNextToken();
        }
        else {
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

    private ExpValue condSituation (String op, ExpValue a, ExpValue b) {
        ExpValue expValue = null;
        String outA = a.value+"", outB = b.value+"";
        if (a.isRegister) outA = a.register;
        if (b.isRegister) outB = b.register;

        out = a.icmp(out, regNum, op, outA, outB);
        expValue = new ExpValue(true, "%"+regNum);
        regNum++;
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
