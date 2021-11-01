import java.util.*;

public class Lab2_SemanticAnalysis {

    /**
     * 处理前缀表达式
     */
    public static void dealPolishNotation() {
//      符号字符串、符号列表
        Character[] ops = new Character[]{'+', '-', '*', '/', '%'};
        List<Character> opList = Arrays.asList(ops);

//      获取前缀表达式的反向后的字符串
        System.out.println(Lab2_Test.outputStr);
        String expStr = getRevExp();
        System.out.println(expStr);

        System.out.println("转化为前缀表达式");
        Vector<String> polish = generatePolish(expStr);
        Vector<String> revPolish = revertStringSeq(polish);

        // 数字栈
        Stack<String> numStack = new Stack<>();
        for (String str : revPolish) {
            // 如果是数字
            if (Character.isDigit(str.charAt(0)) || (str.charAt(0)=='-' && str.length()>=2 && Character.isDigit(str.charAt(1)))) {
                numStack.push(str);
            }
            else if (opList.contains(str.charAt(0))) {
                double first = Double.parseDouble(numStack.pop());
                double second = 0;

                if (!numStack.empty()) {
                    second = Double.parseDouble(numStack.pop());
                    char op = str.charAt(0);
                    String ans = calculateNum(first, second, op);

                    numStack.push(ans);
                }
                else {
                    switch (str.charAt(0)) {
                        case '+' -> { numStack.push(String.valueOf(first));}
                        case '-' -> { numStack.push(String.valueOf(first * -1));}
                    }
                }
            }
        }


//        栈中最后一个值就是答案
        int ans = (int) Double.parseDouble(numStack.pop());
        System.out.println(ans);
//        如果此时栈还是有东西说明有问题
        if (!numStack.empty()) {
            System.out.println("stack error");
            System.exit(21);
        }

//        改变输出字符串
        modifyOutputStr(expStr, ans);
    }


    private static String calculateNum (double first, double second, char op) {
        String ans = "";
        int a = (int) first;
        int b = (int) second;

        switch (op) {
            case '+' -> { ans = String.valueOf(first+second); }
            case '-' -> { ans = String.valueOf(first-second); }
            case '*' -> { ans = String.valueOf(first*second); }
            case '%' -> { ans = String.valueOf(a % b); }
            case '/' -> { ans = String.valueOf(first/second); }
        }

        return ans;
    }

    /**
     *
     * @return String
     * 在语法分析正确的基础上提取出表达式
     */
    private static String getRevExp() {
        int start = Lab2_Test.outputStr.indexOf("ret") + 7; // 此处的7是固定的
        int end = Lab2_Test.outputStr.indexOf('}', start);

        String polish = Lab2_Test.outputStr.substring(start, end).trim();
        System.out.println("polish is");
        System.out.println(polish);

        return polish;
    }

    /**
     *
     * @param originStr
     * @return Vector 前缀表达式
     * 通过中缀表达式转换为前缀表达式
     */
    private static Vector<String> generatePolish(String originStr) {
        Character[] ops = new Character[]{'+', '-', '*', '/', '%'};
        List<Character> opList = Arrays.asList(ops);

        Stack<String> opStack = new Stack<>();
        Stack<String> prStack = new Stack<>();
        // 去所有空格
        originStr = originStr.replaceAll("\\s", "");
        String revOriginStr = new StringBuilder().append(originStr).reverse().toString();

        for (int index = 0; index < revOriginStr.length(); index++) {
            // 遇到操作数
            if (Character.isDigit(revOriginStr.charAt(index))) {

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(revOriginStr.charAt(index));

                for (index++; index<revOriginStr.length() && Character.isDigit(revOriginStr.charAt(index)); index++) {
                    stringBuilder.append(revOriginStr.charAt(index));
                }

                // 是负数
                if (index<revOriginStr.length() && revOriginStr.charAt(index)=='-' && (index + 1 >= revOriginStr.length() || !Character.isDigit(revOriginStr.charAt(index + 1)))) {
                    stringBuilder.append("-");
                    index++;
                }
                index --;
                prStack.push(stringBuilder.reverse().toString());
            }
            // 遇到右括号
            else if (revOriginStr.charAt(index)==')') {
                opStack.push(")");
            }
            // 遇到左括号
            else if (revOriginStr.charAt(index)=='(') {
                String stackTop = opStack.pop();
                while (!stackTop.equals(")")) {
                    prStack.push(stackTop);
                    if (opStack.empty()) {System.out.println("有问题");}
                    stackTop = opStack.pop();
                }
            }
            // 遇到运算符号
            else if (opList.contains(revOriginStr.charAt(index))) {
                if (index == 20) {
                    System.out.println("fordebug");
                }
                // 1. 空栈，直接加入
                if (opStack.isEmpty()) {
                    opStack.push(String.valueOf(revOriginStr.charAt(index)));
                    continue;
                }

                String stackTop = opStack.pop();
                // 2. 栈顶为）直接加入
                if (stackTop.equals(")")) {
                    opStack.push(stackTop);
                    opStack.push(String.valueOf(revOriginStr.charAt(index)));
                    continue;
                }

                opStack.push(stackTop);
                // 3. 优先级比栈顶符号更高or相等
                if (isHigherOrSameOp(String.valueOf(revOriginStr.charAt(index)), stackTop)) {
                    opStack.push(String.valueOf(revOriginStr.charAt(index)));
                }
                else {
                    prStack.push(opStack.pop());
                    index -- ;
                }
            }
        }

        while (!opStack.isEmpty()) { prStack.push(opStack.pop()); }

        Vector<String> polish = new Vector<>();
        while (!prStack.isEmpty()) { polish.add(prStack.pop()); }
        return polish;
    }


    private static Vector<String> revertStringSeq(Vector<String> polish) {
        Vector<String> revPolish = new Vector<>();
        Stack<String> tmpStack = new Stack<>();
        for (String str : polish) {
            tmpStack.push(str);
        }
        while (!tmpStack.isEmpty()) { revPolish.add(tmpStack.pop()); }

        return revPolish;
    }


    private static boolean isHigherOrSameOp (String newOp, String stackOp) {
//        '+', '-', '*', '/', '%'
        HashMap<String, List<String>> priorityMap = new HashMap<>(); // 比String更高or相等的符号list
        priorityMap.put("+", Arrays.asList("+", "-", "*", "/", "%"));
        priorityMap.put("-", Arrays.asList("+", "-", "*", "/", "%"));

        priorityMap.put("*", Arrays.asList("*", "/", "%"));
        priorityMap.put("/", Arrays.asList("*", "/", "%"));
        priorityMap.put("%", Arrays.asList("*", "/", "%"));

        if (priorityMap.get(stackOp).contains(newOp)) {
            return true;
        }
        return false;
    }


    private static void modifyOutputStr(String revPolish, int ans) {
        Lab2_Test.outputStr = Lab2_Test.outputStr.replace(revPolish, String.valueOf(ans));
    }

}
