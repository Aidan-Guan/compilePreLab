import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class Lab2_GrammarAnalysis {


    // 当前的token
    static Lab2_Token lastSym;
    static Lab2_Token currentSym;
    static Lab2_Token futureSym;
    static String process = "";
    static int regIndex = 1;
    static HashMap<String, Integer> identRegMap = new HashMap<>();
    static HashMap<String, Boolean> isConst = new HashMap<>();


    static {
        try {
            currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
        } catch (IOException e) {
        }
    }

    /**
     * 处理CompUnit文法
     */
    public static void compUnitAnal() throws IOException {
        funcDefAnal();
    }

    /**
     * 处理FuncDef文法
     */
    private static void funcDefAnal() throws IOException {
        funcTypeAnal();
        identAnal();

        // 出现错误，退出
        if (currentSym == null || !currentSym.value.equals("(")) {
            System.exit(-1);
        }
        // 处理(的输出
        Lab2_Test.outputStr += "(";


        getNextSym();
        if (currentSym == null || !currentSym.value.equals(")")) {
            System.exit(-1);
        }
        Lab2_Test.outputStr += ")";

        getNextSym();

        blockAnal();
    }

    /**
     * 处理Funcype文法
     */
    private static void funcTypeAnal() throws IOException {
        if (currentSym == null || !currentSym.value.equals("int")) {
            System.exit(-1);
        }
        Lab2_Test.outputStr += "define dso_local i32";

        getNextSym();
    }

    /**
     * 处理Ident文法
     */
    private static void identAnal() throws IOException {
        if (currentSym == null || !currentSym.value.equals("main")) {
            System.exit(-1);
        }

        //对于main的输出
        Lab2_Test.outputStr += "@main";
        getNextSym();
    }

    /**
     * 处理Block文法
     */
    private static void blockAnal() throws IOException {
        if (currentSym == null || !currentSym.value.equals("{")) {
            System.exit(1);
        }
        Lab2_Test.outputStr += "{";

        getNextSym();
        while (true) {
            if (!(currentSym.value.equals("return")||currentSym.value.equals("int")||currentSym.value.equals("const")||currentSym.type.equals("IDENT"))){break;}
            blockItemAnal();
        }

        if (currentSym == null || !currentSym.value.equals("}")) {
            System.exit(1);
        }
        Lab2_Test.outputStr += "}";

        getNextSym();
        if (currentSym != null) {
            System.exit(2);
        }
    }

    private static void blockItemAnal() throws IOException {
        if (currentSym.value.equals("int") || currentSym.value.equals("const")) {
            declAnal();
        }
        else if (currentSym.value.equals("return") || currentSym.value.equals(";") || currentSym.type.equals("IDENT")) {
            stmtAnal();
        }
    }


    private static void declAnal() throws IOException {
        if (currentSym.value.equals("const")) {
            constDeclAnal();
        }
        else if (currentSym.value.equals("int")) {
            varDeclAnal();
        }
        else { System.exit(-1); }
    }

    private static void varDeclAnal() throws IOException {
        if (!currentSym.value.equals("int")) {
            System.exit(-1);
        }
        getNextSym();
        varDefAnal();

        while (true) {
            if (!currentSym.value.equals(",")) { break;}
            varDefAnal();
        }
//        getNextSym();
        if (!currentSym.value.equals(";")) {
            System.exit(-1);
        }
        getNextSym();
    }

    private static void varDefAnal() throws IOException {
        if (currentSym.value.equals(","))
            getNextSym();
        if (!currentSym.type.equals("IDENT")) {
            System.exit(-1);
        }

        String varName = currentSym.value;
        getNextSym();
        if (currentSym.value.equals("=")) {
            getNextSym();
            Lab2_Token result = initValAnal();
            if (result.type.equals("FUNC")) {
//                getNextSym();
                Lab2_Test.outputStr = Lab2_Test.outputStr.trim();
                Lab2_Test.outputStr += "\n";
                Lab2_Test.outputStr += "\t%"+regIndex+" = call "+result.output()+"\n";
                identRegMap.put(varName, regIndex);
                regIndex++;

                Lab2_Test.outputStr = Lab2_Test.outputStr.trim()+"\n";
                Lab2_Test.outputStr += "\t%"+regIndex+" = alloca i32\n";
                Lab2_Test.outputStr += "\tstore i32 %" + String.valueOf(regIndex-1) + ", i32* %" + String.valueOf(regIndex)+"\n";
                identRegMap.put(varName, regIndex);
                regIndex++;

                return;
            }
            Lab2_Test.outputStr = Lab2_Test.outputStr.trim();
            Lab2_Test.outputStr += "\n";
            Lab2_Test.outputStr += "\t%"+regIndex+" = alloca i32\n";
            Lab2_Test.outputStr += "\tstore i32 " + result.output() + ", i32* %" + String.valueOf(regIndex)+"\n";
            identRegMap.put(varName, regIndex);
            regIndex++;
        }
        else {
            Lab2_Test.outputStr = Lab2_Test.outputStr.trim()+"\n";
            Lab2_Test.outputStr += "\t%"+regIndex+" = alloca i32\n";
            identRegMap.put(varName, regIndex);
            regIndex++;
        }
//        getNextSym();
    }

    private static Lab2_Token initValAnal() throws IOException {
        return exp();
    }

    private static void constDeclAnal() throws IOException {
        if (!currentSym.value.equals("const")) {
            System.exit(-1);
        }
        getNextSym();
        bTypeAnal();
        constDefAnal();

        while (true) {
            if (!currentSym.value.equals(",")) {break;}
            constDefAnal();
        }
//        getNextSym();
        if (!currentSym.value.equals(";")) {
            System.exit(-1);
        }
        getNextSym();
    }

    private static void bTypeAnal() throws IOException {
        if (!currentSym.value.equals("int")) {
            System.exit(-1);
        }
        currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
    }

    private static void constDefAnal() throws IOException{
        if (!currentSym.type.equals("IDENT")) {
            System.exit(-1);
        }
        String identName = currentSym.value;
        getNextSym();
        if (!currentSym.value.equals("=")) {
            System.exit(-1);
        }
        getNextSym();
        Lab2_Token result = constInitValAnal();
        Lab2_Test.outputStr = Lab2_Test.outputStr.trim()+"\n";
        Lab2_Test.outputStr += "\t%" + String.valueOf(regIndex) + " = alloca i32\n";
        int varReg = regIndex;
        Lab2_Test.outputStr += "\tstore i32 " + result.output() + ", i32* %" + String.valueOf(varReg) + "\n";


        identRegMap.put(identName, varReg);
        isConst.put(identName, true);
        regIndex ++;
    }

    private static Lab2_Token exp() throws IOException {
        return addExpAnal();
    }

    private static Lab2_Token constInitValAnal() throws IOException {
        return constExpAnal();
    }

    private static Lab2_Token constExpAnal() throws IOException {
        return addExpAnal();
    }

    private static Lab2_Token addExpAnal() throws IOException {
        Lab2_Token result = mulExpAnal();
//        getNextSym();
        while (true) {
            if (currentSym.value.equals("+")) {
                getNextSym();
                Lab2_Token result2 = mulExpAnal();
                Lab2_Test.outputStr = Lab2_Test.outputStr.trim() + "\n";
                Lab2_Test.outputStr += "\t%"+String.valueOf(regIndex) + " = add i32 "+ result.output() + ", " + result2.output()+"\n";
                result.value = String.valueOf(regIndex);
                regIndex++;
            }
            else if (currentSym.value.equals("-")) {
                getNextSym();
                Lab2_Token result2 = mulExpAnal();
                Lab2_Test.outputStr = Lab2_Test.outputStr.trim()+"\n";
                Lab2_Test.outputStr += "\t%"+String.valueOf(regIndex) + " = sub i32 "+ result.output() + ", "+ result2.output()+"\n";
                result.value = String.valueOf(regIndex);
                result.type = "REG";
                regIndex++;
            }
            else { break; }
        }

        return result;
    }

    private static Lab2_Token mulExpAnal() throws IOException {
        Lab2_Token result = unaryExp();
        getNextSym();
        while (true) {
            if (currentSym.value.equals("*")) {
                getNextSym();
                Lab2_Token result2 = unaryExp();
                Lab2_Test.outputStr = Lab2_Test.outputStr.trim()+"\n";
                if (result.type.equals("REG")) {
                    Lab2_Test.outputStr += "\t%" + String.valueOf(regIndex) + " = mul i32 " + result.output() + ", " + result2.output() + "\n";
                }
                else {
                    Lab2_Test.outputStr += "\t%" + String.valueOf(regIndex) + " = mul i32 " + result.output() + ", " + result2.output() + "\n";
                }
                result.value = String.valueOf(regIndex);
                regIndex++;
                getNextSym();
            }
            else if (currentSym.value.equals("/")) {
                getNextSym();
                Lab2_Token result2 = unaryExp();
                Lab2_Test.outputStr = Lab2_Test.outputStr.trim() + "\n";
                Lab2_Test.outputStr += "\t%"+String.valueOf(regIndex) + " = sdiv i32 "+ result.output() + ", " + result2.output()+"\n";
                result.value = String.valueOf(regIndex);
                regIndex++;
                getNextSym();
            }
            else if (currentSym.value.equals("%")) {
                getNextSym();
                Lab2_Token result2 = unaryExp();

                Lab2_Test.outputStr += "%"+String.valueOf(regIndex) + " = srem i32 "+ result.output() + ", " + result2.output()+"\n";
                result.value = String.valueOf(regIndex);
                regIndex++;
                getNextSym();
            }
            else {break;}
        }
        return result;
    }

    private static Lab2_Token unaryExp() throws IOException {
        if (currentSym.value.equals("-")) {
            getNextSym();
            Lab2_Token result = unaryExp();
            if (result.type.equals("FUNC")) {
                return result;
            }
            Lab2_Test.outputStr += "%" + regIndex + " = sub i32 0, " + result.output()+"\n";

            result =  new Lab2_Token("REG", String.valueOf(regIndex));
            regIndex++;
            return result;
        }
        else if (currentSym.value.equals("+")) {
            getNextSym();
            return unaryExp();
        }
        else if (currentSym.value.equals("(")) {
            getNextSym();
            Lab2_Token result = exp();
            if (!currentSym.value.equals(")")) {
                System.exit(-1);
            }
            return result;
        }
        else if (currentSym.type.equals("NUMBER")) {
            return currentSym;
        }
        else if (currentSym.type.equals("IDENT")) {
            getNextSym();
            if (currentSym.value.equals("(")) { // 库函数调用
                rollbackSym();
                switch (currentSym.value) {
                    case "getint" -> {
                        getNextSym();
                        if (!currentSym.value.equals("(")) {
                            System.exit(-1);
                        }
                        getNextSym();
                        if (!currentSym.value.equals(")")) {
                            System.exit(-1);
                        }
//                        getNextSym();
                        if (!Lab2_Test.outputStr.contains("declare i32 @getint()")) {
                            Lab2_Test.outputStr = "declare i32 @getint()\n" + Lab2_Test.outputStr.trim();
                        }
                        return new Lab2_Token("FUNC", "getint");
                    }
                    case "getch" -> {
                        getNextSym();
                        if (!currentSym.value.equals("(")) {
                            System.exit(-1);
                        }
                        getNextSym();
                        if (!currentSym.value.equals(")")) {
                            System.exit(-1);
                        }
//                        getNextSym();
                        if (!Lab2_Test.outputStr.contains("declare i32 @getch()")) {
                            Lab2_Test.outputStr = "declare i32 @getch()\n" + Lab2_Test.outputStr.trim();
                        }
                        return new Lab2_Token("FUNC", "getch");
                    }
                    case "putint" -> {
                        getNextSym();
                        if (!currentSym.value.equals("(")) {
                            System.exit(-1);
                        }
                        getNextSym();
                        Lab2_Token result = exp();
                        if (!currentSym.value.equals(")")) {
                            System.exit(-1);
                        }
//                        getNextSym();
                        if (!Lab2_Test.outputStr.contains("declare void @putint(i32)")) {
                            Lab2_Test.outputStr = "declare void @putint(i32)\n" + Lab2_Test.outputStr.trim();
                        }

                        Lab2_Test.outputStr = Lab2_Test.outputStr.trim()+"\n";
                        Lab2_Test.outputStr += "\tcall void @putint(i32 "+result.output()+")\n";
                        return new Lab2_Token("FUNC", "putint");

                    }
                    case "putch" -> {
                        getNextSym();
                        if (!currentSym.value.equals("(")) {
                            System.exit(-1);
                        }
                        getNextSym();
                        Lab2_Token result = exp();
                        if (!currentSym.value.equals(")")) {
                            System.exit(-1);
                        }
//                        getNextSym();
                        if (!Lab2_Test.outputStr.contains("declare void @putch(i32)")) {
                            Lab2_Test.outputStr = "declare void @putch(i32)\n" + Lab2_Test.outputStr.trim();
                        }

                        Lab2_Test.outputStr = Lab2_Test.outputStr.trim()+"\n";
                        Lab2_Test.outputStr += "\tcall void @putch(i32 "+result.output()+")\n";
                        return new Lab2_Token("FUNC", "putch");
                    }
                }
            }
            else {
                rollbackSym();
                Integer identIndex = identRegMap.get(currentSym.value);
//                if (isConst.get(currentSym.value)!=null) {
//                    System.exit(-1);
//                }
                if (identIndex == null) {
                    System.exit(-1);
                }
                Lab2_Test.outputStr = Lab2_Test.outputStr.trim() + "\n";
                Lab2_Test.outputStr += "\t%"+String.valueOf(regIndex)+" = load i32, i32* %"+String.valueOf(identIndex)+"\n";
                regIndex++;
                return new Lab2_Token("REG", String.valueOf(regIndex-1));
            }
        }
        else {
           System.exit(-1);
        }
        return null;
    }

    private static void stmtAnal() throws IOException {
        if (currentSym.value.equals("return")) {
            getNextSym();
            Lab2_Token result = exp();
            if (!currentSym.value.equals(";")) {
                System.exit(-1);
            }
            Lab2_Test.outputStr = Lab2_Test.outputStr.trim() + "\n";
            Lab2_Test.outputStr += "\tret i32 "+result.output();
            getNextSym();
        }
        else if (currentSym.value.equals(";")){}
        else {
            String varName = currentSym.value;
            getNextSym();
            if (currentSym.value.equals("=")) {
                rollbackSym();
                lValAnal();
                getNextSym();
                Lab2_Token result = exp();
                if (!result.type.equals("FUNC")) {
                    Integer reg = identRegMap.get(varName);
                    if (isConst.get(varName)!=null) {
                        System.exit(-1);
                    }
                    Lab2_Test.outputStr = Lab2_Test.outputStr.trim() + "\n";
                    Lab2_Test.outputStr += "\tstore i32 " + result.output() + ", i32* %" + String.valueOf(reg) + "\n";
                }
                else {
                    Lab2_Test.outputStr = Lab2_Test.outputStr.trim() + "\n";
                    Lab2_Test.outputStr += "\t%"+regIndex+ " = call " + result.output() + "\n";
                    regIndex++;
                    Integer reg = identRegMap.get(varName);
                    Lab2_Test.outputStr = Lab2_Test.outputStr.trim() + "\n";
                    Lab2_Test.outputStr += "\tstore i32 %" + String.valueOf(regIndex-1) + ", i32* %" + String.valueOf(reg) + "\n";
                }

                if (!currentSym.value.equals(";")) {
                    System.exit(-1);
                }
                getNextSym();
            }
            else {
                rollbackSym();
                exp();
                getNextSym();
            }
        }
    }

    private static void lValAnal() throws IOException {
        if (!currentSym.type.equals("IDENT")) {
            System.exit(-1);
        }
        getNextSym();
    }

    private static int numberAnal() throws IOException {
        int numberResult = 0;
        if (currentSym.type.equals("NUMBER")) {
            Lab2_Test.outputStr += currentSym.value;
            numberResult = Integer.parseInt(currentSym.value);
            currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
        }
        else {
            System.exit(20);
        }
        return numberResult;
    }

    private static void getNextSym() throws IOException {
//        System.out.println(currentSym);
        if (futureSym!=null) {
            lastSym = currentSym;
            currentSym = futureSym;
            futureSym = null;
        }
        else {
            lastSym = currentSym;
            currentSym = Lab2_LexicalAnalysisForGA.getNextToken();
        }
    }

    private static void rollbackSym() throws IOException {
        futureSym = currentSym;
        currentSym = lastSym;
    }

}

//错误对照表
//1   括号有问题
//2   返回类型有错
//3   main函数错误
//4   return有误
//5   并不是number
//6   分号有问题
//20  读取有问题
//21  读取运算符错误
