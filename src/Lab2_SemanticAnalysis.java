import java.util.*;

public class Lab2_SemanticAnalysis {

    /**
     * 处理前缀表达式
     */
    public static void dealPolishNotation() {
        /* 符号字符串、符号列表 */
        Character[] ops = new Character[]{'+', '-'};
        List<Character> opList = Arrays.asList(ops);

        /* 获取前缀表达式的反向后的字符串 */
        System.out.println(Lab2_Test.outputStr);
        String expStr = getRevExp();
        System.out.println(expStr);

        System.out.println("转化为前缀表达式");
        Vector<String> polish = new Vector<>();
        polish = generateRevPolish(expStr);
        for (String str : polish) {
            System.out.print(str + " ");
        }
        System.out.println("finished");

        /* 数字栈 */
        Stack<String> numStack = new Stack<>();
        for (int index = 0; index<expStr.toCharArray().length; index++) {
            if (Character.isDigit(expStr.charAt(index))) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(expStr.charAt(index));
                for (index++ ; index<expStr.toCharArray().length && Character.isDigit(expStr.charAt(index)) ; index++) {
                    stringBuilder.append(expStr.charAt(index));
                }
                index --;
                numStack.push(stringBuilder.reverse().toString());
            }
            else if (opList.contains(expStr.charAt(index))) {
                int first = 0;
                int second = 0;
                first = Integer.parseInt(numStack.pop());
                if (!numStack.empty()) {
                    second = Integer.parseInt(numStack.pop());
                }
                else {
                    switch (expStr.charAt(index)) {
                        case '+' -> { numStack.push(String.valueOf(first)); }
                        case '-' -> { numStack.push(String.valueOf(first * -1)); }
                    }
                }
            }

        }


        /* 栈中最后一个值就是答案 */
        int ans = Integer.parseInt(numStack.pop());
        /* 如果此时栈还是有东西说明有问题*/
        if (!numStack.empty()) {
            System.out.println("stack error");
            System.exit(21);
        }

        /* 改变输出字符串 */
        modifyOutputStr(expStr, ans);
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
    private static Vector<String> generateRevPolish(String originStr) {
        Character[] ops = new Character[]{'+', '-', '*', '/', '%'};
        List<Character> opList = Arrays.asList(ops);

        Stack<String> opStack = new Stack<>();
        Stack<String> prStack = new Stack<>();
        String revOriginStr = new StringBuilder().append(originStr).reverse().toString();

        for (int index = 0; index < revOriginStr.length(); index++) {
            // 遇到操作数
            if (Character.isDigit(revOriginStr.charAt(index))) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(revOriginStr.charAt(index));

                for (index++; index<revOriginStr.length() && Character.isDigit(revOriginStr.charAt(index)); index++) {
                    stringBuilder.append(revOriginStr.charAt(index));
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
}
