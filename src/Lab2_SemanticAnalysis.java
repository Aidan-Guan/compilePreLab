import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Lab2_SemanticAnalysis {

    /**
     * 处理前缀表达式
     */
    public static void dealPolishNotation() {
        /* 符号字符串、符号列表 */
        Character[] ops = new Character[]{'+', '-'};
        List<Character> opList = Arrays.asList(ops);

        /* 获取前缀表达式的反向后的字符串 */
        String revPolish = getRevPolish();

        /* 数字栈 */
        Stack<String> numStack = new Stack<>();
        for (char c : revPolish.toCharArray()) {
            /* 如果是数字，直接入栈*/
            if (Character.isDigit(c)) { numStack.push(String.valueOf(c)); }
            /* 如果是符号，进行处理 */
            else if (opList.contains(c)){
                int first = 0;
                int second = 0;
                first = Integer.parseInt(numStack.pop());
                if (!numStack.empty()) {
                    second = Integer.parseInt(numStack.pop());
                }
                else {
                    switch (c) {
                        case '+' -> {numStack.push(String.valueOf(first));}
                        case '-' -> {numStack.push(String.valueOf(first*-1));}
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
        modifyOutputStr(revPolish, ans);
    }

    /**
     *
     * @return String
     * 在语法分析正确的基础上提取出波兰表达式并反向
     */
    private static String getRevPolish() {
        int start = Lab2_Test.outputStr.indexOf("ret") + 7; // 此处的7是固定的
        int end = Lab2_Test.outputStr.indexOf('\n', start);

        String polish = Lab2_Test.outputStr.substring(start, end).trim();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(polish);
        stringBuilder.reverse();
        return stringBuilder.toString();
    }


    private static void modifyOutputStr(String revPolish, int ans) {
        StringBuilder stringBuilder = new StringBuilder();
        String polish = stringBuilder.append(revPolish).reverse().toString();
        Lab2_Test.outputStr = Lab2_Test.outputStr.replace(polish, String.valueOf(ans));
    }
}