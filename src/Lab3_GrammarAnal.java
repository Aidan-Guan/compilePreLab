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
            Lab3_Test.allTokens.add(currentSym);
            currentSym = Lab3_LexicalAnal.getNextToken();
            ConstDecl();
        }
        else if (currentSym.value.equals("int")) {
            Lab3_Test.allTokens.add(currentSym);
            currentSym = Lab3_LexicalAnal.getNextToken();
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

    public static void ConstDef() {

    }

    public static void ConstExp() {

    }

    public static void VarDecl() {

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

    public static void Block() {

    }

    public static void BlockItem() {

    }

    public static void Stmt() {

    }

    public static void Exp() {

    }

    public static void LVal() {

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

    public static void Ident() {

    }
}


/**
 * 1    FuncType错误
 * 2    括号错误
 * 3    数据类型错误
 * 4    Decl错误
 * 5    分号错误
 */