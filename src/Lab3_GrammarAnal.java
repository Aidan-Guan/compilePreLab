import java.io.IOException;

public class Lab3_GrammarAnal {

    static Lab3_Token currentSym;
    static {
        try {
            currentSym = Lab3_LexicalAnal.getNextToken();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void CompUnit() throws IOException {
        FuncDef();
    }

    public static void Decl() throws IOException {
        if (currentSym.value.equals("const")) {
            getNextToken();
            ConstDecl();
        }
        else if (currentSym.value.equals("int")) {
            getNextToken();
            VarDecl();
        }
        else {System.exit(4);}
    }

    public static void ConstDecl() throws IOException {
        // 在Decl中已经省略对const的判断
        Btype();
        ConstDef();
        while (true) {
            if (!currentSym.value.equals(",")) {break;}
            Lab3_Test.allTokens.add(currentSym);
            currentSym = Lab3_LexicalAnal.getNextToken();
            ConstDef();
        }

        if (!currentSym.value.equals(";")) {
            System.exit(5);
        }
        Lab3_Test.allTokens.add(currentSym);
        currentSym = Lab3_LexicalAnal.getNextToken();
    }

    /**
     *
     * @throws IOException
     */
    public static void Btype() throws IOException {
        if (!currentSym.value.equals("int")) {
            System.exit(3);
        }
        Lab3_Test.allTokens.add(currentSym);
        currentSym = Lab3_LexicalAnal.getNextToken();
    }

    // FINISHED
    public static void ConstDef() throws IOException {
        Ident();
        if (!currentSym.equals("=")) {
            System.exit(6);
        }
        Lab3_Test.allTokens.add(currentSym);
        currentSym = Lab3_LexicalAnal.getNextToken();

        ConstInitVal();
    }

    // FINISHED
    public static void ConstInitVal() {
        ConstExp();
    }

    // FINISHED
    public static void ConstExp() {
        AddExp();
    }

    // FINISHED
    public static void VarDecl() throws IOException {
        Btype();
        VarDef();

        while (true) {
            if (!currentSym.value.equals(",")) { break; }
            Lab3_Test.allTokens.add(currentSym);
            currentSym = Lab3_LexicalAnal.getNextToken();
            VarDef();
        }

        if (!currentSym.value.equals(";")) {System.exit(5);}
        Lab3_Test.allTokens.add(currentSym);
        currentSym = Lab3_LexicalAnal.getNextToken();
    }

    /**
     *
     * @throws IOException
     */
    public static void VarDef() throws IOException {
        Ident();
        if (currentSym.value.equals("=")) {
            Lab3_Test.allTokens.add(currentSym);
            currentSym = Lab3_LexicalAnal.getNextToken();
            InitVal();
        }
    }

    /**
     *
     */
    public static void InitVal() {
        Exp();
    }

    /**
     *
     * @throws IOException
     */
    public static void FuncDef() throws IOException {
        FuncType();
        Ident(); //TODO: 完成此处Ident
        // 判断左括号
        if (!currentSym.value.equals("(")) { System.exit(2); }
        Lab3_Test.allTokens.add(currentSym);
        currentSym = Lab3_LexicalAnal.getNextToken();
        // 判断右括号
        if (!currentSym.value.equals(")")) { System.exit(2); }
        Lab3_Test.allTokens.add(currentSym);
        currentSym = Lab3_LexicalAnal.getNextToken();
        // Block文法
        Block();
    }

    /**
     *
     * @throws IOException
     */
    public static void FuncType() throws IOException {
        if (!currentSym.value.equals("int")) {
            System.exit(1);
        }
        Lab3_Test.allTokens.add(currentSym);
        currentSym = Lab3_LexicalAnal.getNextToken();
    }

    // FINISHED
    public static void Block() throws IOException {
        if (!currentSym.value.equals("{")) {System.exit(2);}
        getNextToken();
        while (true) {
            if (!(currentSym.value.equals("const") || currentSym.value.equals("int") || currentSym.type.equals("IDENT"))) {
                break;
            }
            BlockItem();
        }
        if (!currentSym.value.equals("}")) {System.exit(2);}
        getNextToken();
    }

    public static void BlockItem() throws IOException {
        if (currentSym.type.equals("IDENT")) {
            Stmt();
        }
        else if (currentSym.value.equals("const") || currentSym.value.equals("int")) {
            Decl();
        }
        else {
            System.exit(3);
        }
    }

    //TODO: 确定中括号是啥意思
    public static void Stmt() throws IOException {
        LVal();
        if (!currentSym.value.equals("=")) {System.exit(6);}
        getNextToken();
        Exp();
        if (!currentSym.value.equals(";")) {System.exit(6);}
        getNextToken();
    }

    // FINISHED
    public static void Exp() {
        AddExp();
    }

    // FINISHED
    public static void LVal() throws IOException {
        Ident();
    }

    public static void PrimaryExp() {

    }

    public static void AddExp() {

    }

    public static void MulExp() {

    }

    public static void UnaryExp() {

    }

    public static void FuncRParams() {

    }

    public static void UnaryOp() {

    }

    public static void Ident() throws IOException {
        if (!currentSym.type.equals("IDENT")) {System.exit(3);}
        getNextToken();
    }

    private static void getNextToken() throws IOException {
        Lab3_Test.allTokens.add(currentSym);
        currentSym = Lab3_LexicalAnal.getNextToken();
    }
}


/**
 * 1    FuncType错误
 * 2    括号错误
 * 3    数据类型错误
 * 4    Decl错误
 * 5    分号错误
 * 6    符号错误
 */