package com.shihaiyang.offer;
// 剑指 Offer II 036. 后缀表达式
// Offer036. 后缀表达式.[题干直接给答案的典范:栈 5ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 说明：
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * 示例 2：
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * 示例 3：
 * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * 输出：22
 * 解释：
 * 该算式转化为常见的中缀算术表达式为：
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * 提示：
 * 1 <= tokens.length <= 104
 * tokens[i] 要么是一个算符（"+"、"-"、"*" 或 "/"），要么是一个在范围 [-200, 200] 内的整数
 * 逆波兰表达式（后缀表达式）主要有以下两个优点：
 * 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
 * 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
 */
public class Offer036 {
    SolutionOffer036 solutionOffer036 = new SolutionOffer036();
    @Test
    @DisplayName("((2 + 1) * 3) = 9")
    public void case1() {
        int evalRPN = solutionOffer036.evalRPN(new String[]{"2", "1", "+", "3", "*"});
        Assertions.assertEquals(evalRPN, 9);
    }
    @Test
    @DisplayName("(4 + (13 / 5)) = 6")
    public void case2() {
        int evalRPN = solutionOffer036.evalRPN(new String[]{"4","13","5","/","+"});
        Assertions.assertEquals(evalRPN, 6);
    }
    @Test
    @DisplayName("((10 * (6 / ((9 + 3) * -11))) + 17) + 5 = 22")
    public void case3() {
        int evalRPN = solutionOffer036.evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"});
        Assertions.assertEquals(evalRPN, 22);
    }
}

/**
 * 题干给了思路...
 */
class SolutionOffer036 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    int first = stack.pop();
                    int second = stack.pop();
                    stack.push(second + first);
                    break;
                case "-":
                    first = stack.pop();
                    second = stack.pop();
                    stack.push(second - first);
                    break;
                case "*":
                    first = stack.pop();
                    second = stack.pop();
                    stack.push(second * first);
                    break;
                case "/":
                    first = stack.pop();
                    second = stack.pop();
                    stack.push(second / first);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.peek();
    }
}
