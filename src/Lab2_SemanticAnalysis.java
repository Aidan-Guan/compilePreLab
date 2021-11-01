import java.util.*;

public class Lab2_SemanticAnalysis {

    /**
     * 处理前缀表达式
     */
    public static void dealPolishNotation() {
        /* 符号字符串、符号列表 */
        Character[] ops = new Character[]{'+', '-', '*', '/', '%'};
        List<Character> opList = Arrays.asList(ops);

        /* 获取前缀表达式的反向后的字符串 */
        System.out.println(Lab2_Test.outputStr);
        String expStr = getRevExp();
        System.out.println(expStr);

        System.out.println("转化为前缀表达式");
        Vector<String> polish = generatePolish(expStr);
        Vector<String> revPolish = revertStringSeq(polish);

        /* 数字栈 */
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

//        for (int index = 0; index<expStr.toCharArray().length; index++) {
//            if (Character.isDigit(expStr.charAt(index))) {
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append(expStr.charAt(index));
//                for (index++ ; index<expStr.toCharArray().length && Character.isDigit(expStr.charAt(index)) ; index++) {
//                    stringBuilder.append(expStr.charAt(index));
//                }
//                index --;
//                numStack.push(stringBuilder.reverse().toString());
//            }
//            else if (opList.contains(expStr.charAt(index))) {
//                int first = 0;
//                int second = 0;
//                first = Integer.parseInt(numStack.pop());
//                if (!numStack.empty()) {
//                    second = Integer.parseInt(numStack.pop());
//                }
//                else {
//                    switch (expStr.charAt(index)) {
//                        case '+' -> { numStack.push(String.valueOf(first)); }
//                        case '-' -> { numStack.push(String.valueOf(first * -1)); }
//                    }
//                }
//            }
//
//        }

        /* 栈中最后一个值就是答案 */
        int ans = (int) Double.parseDouble(numStack.pop());
        System.out.println(ans);
        /* 如果此时栈还是有东西说明有问题*/
        if (!numStack.empty()) {
            System.out.println("stack error");
            System.exit(21);
        }

        /* 改变输出字符串 */
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
        StringBuilder stringBuilder = new StringBuilder();
        String polish = stringBuilder.append(revPolish).reverse().toString();
        Lab2_Test.outputStr = Lab2_Test.outputStr.replace(polish, String.valueOf(ans));
    }

//
//
//    static String operator = "+-*/";    // 用于判断是否是操作符
//    // 用于判断优先级
//    static HashMap<Character, Integer> priority = new HashMap<>();
//    static {
//        priority.put('+', 1);
//        priority.put('-', 1);
//        priority.put('*', 2);
//        priority.put('%', 2);
//        priority.put('/', 2);
//        priority.put(')', 0);
//    }
//
//    public static String toPolish(String str) {
//        ArrayDeque<Character> s1 = new ArrayDeque<>();  // 用于储存运算符
//        ArrayDeque<Double> s2 = new ArrayDeque<>();     // 用于储存数字
//
//        Stack<Object> s3 = new Stack<>();
//        char ch, temp;
//
//        int frontDouble = -1;
//        int len = str.length(); // 用于储存字符串的字符串的长度
//
//        for (int i = len - 1; i >= 0; i--) {
//            ch = str.charAt(i); // charAt()方法可以返回字符串索引处的字符
//
//            if (Character.isDigit(ch)) {    // Character的isDigit()可以用来判断数字是否是字符
//                frontDouble = readFrontDouble(str, i);  // 该方法用于算该数字从索引处到往前遍历找到第一个非数字的索引
//                if (frontDouble == -1) {
//                    return "出错";
//                }
//                double d = Double.parseDouble(str.substring(frontDouble, i + 1));
//                if ((int)d == d)
//                    s3.add(String.valueOf((int)d));
//                else
//                    s3.add(String.valueOf(d));
//                s2.push(d);
//                i = frontDouble;
//            }
//            else if (operator.indexOf(ch) != -1) {
//                while (!s1.isEmpty() && s1.peek() != ')'
//                        && priority.get(ch) < priority.get(s1.peek())) {
//                    double d1 = s2.pop();
//                    double d2 = s2.pop();
//                    s3.push(s1.peek().toString());
//                    s2.push(cal(d1, d2, s1.pop()));
//                }
//                s1.push(ch);
//            }
//            // 如遇到右括号，直接压入栈
//            else if (ch == ')')
//                s1.push(ch);
//            else if (ch == '(') {
//                while (s1.peek() != ')') {
//                    double d1 = s2.pop();
//                    double d2 = s2.pop();
//                    s3.push(s1.peek().toString());
//                    s2.push(cal(d1, d2, s1.pop()));
//                    // 如果没遇到左括号，但s1栈已经是空的了，那么肯定出错了
//                    if (s1.isEmpty()) {
//                        return "出错";
//                    }
//                }
//                s1.pop();
//            }
//            // 忽略掉空格
//            else if (ch == ' ') {
//                continue;
//            }
//            // 有其他字符肯定出错
//            else {
//                return "出错";
//            }
//        }
//
//        while (!s1.isEmpty()) {
//            double d1 = s2.pop();
//            double d2 = s2.pop();
//            s3.push(s1.peek().toString());
//            double d3 = cal(d1, d2, s1.pop());
//            s2.push(d3);
//        }
//        System.out.print("前缀是：");
//        while(!s3.isEmpty()) {
//            System.out.print(s3.pop() + " ");
//        }
//        System.out.println();
//        // 若最后栈s2中还有超过一个元素，则证明出错了
//        double result = s2.pop();
//        if (!s2.isEmpty())
//            return "出错";
//        if ((int) result == result)
//            return String.valueOf((int)result);
//        else{
//            return String.valueOf(result);
//        }
//
//    }
//
//
//    private static double cal(double d1, double d2, char op) throws ArithmeticException{
//        switch (op) {
//            case '+':
//                return d1 + d2;
//            case '-':
//                return d1 - d2;
//            case '*':
//                return d1 * d2;
//            case '/':
//                if (d1 == 0) {
//                    return 1;
//                }
//                return d1 / d2;
//            case '%':
//                return (int)d1 % (int)d2;
//        }
//
//        return 1;
//    }
//
//    /**
//     * 这是一个读取数字位置的方法
//     * 该方法可以从右向左的读取一个数字，然后返回该数字在字符串中开始的下标
//     */
//    private static int readFrontDouble(String str, int start) {
//        int flag = -1;    // 用于记录小数点
//        char ch;          // 用于记录每次遍历的字符
//        for (int i = start; i >= 0; i--) {
//            ch = str.charAt(i);
//            if (ch == '.') {      //如果第一次出现小数点，则记录小数点位置，如果不是那么肯定出错
//                if (flag != -1) {
//                    return -1;
//                }
//                else {
//                    flag = i;
//                }
//                // 如果该字符是减号，若该字符是第一位（i == 0），则该减号是负号，或者如果该字符的前一个字符不是数字，证明也是负号
//            } else if (ch == '-' && ((i > 0
//                    && !Character.isDigit((str.charAt(i-1))))
//                    || i == 0)) {
//                return i;
//                // 如果是非数字的肯定该数字已经找到了
//            }else if (!Character.isDigit(ch))
//                return i + 1;
//            else if (i == 0) {
//                return 0;
//            }
//        }
//        return -1;
//    }
}
