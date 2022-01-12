package com.shihaiyang.leetcodes;

import java.util.Stack;

// 0032. 最长有效括号.[栈应用+记录长度O(n)].
// https://leetcode-cn.com/problems/longest-valid-parentheses/solution/0032-zui-chang-you-xiao-gua-hao-zhan-yin-bncx/
public class Leetcode0032 {
    public static void main(String[] args) {
        SolutionStack solutionStack = new SolutionStack();
        int parentheses = solutionStack.longestValidParentheses("()(())");
        System.out.println(parentheses);
    }
}

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 */

/**
 * 动态规划 最值类
 * 1. 确认状态
 *      最后一步：最长的合法字符子串    比较下之前最长的子串是多少，
 *      化成子问题：n有k个，n-i有几个呢？   left > right 的情况下。是有效的
 * 2. 状态转换方程
 *
 * 3. 初始状态
 *      f[0]=0;
 * 4. 计算顺序
 */
class SolutionDynamicProgramming {
    public int longestValidParentheses(String s) {
        return 0;
    }
}
/**
 * 利用栈来做。时间复杂度 n  空间复杂度 n
 * 依次遍历每个字符.
 * 如果是左括号，下标leftIndex存入stack.
 * 如果是右括号，判断是否栈空，
 *      如果为空说明无效.
 *      如果不为空，出栈左括号下标；计算长度=i-leftIndex+1
 * 时间复杂度 O(n)  空间复杂度O(n)
 * "()(())"
 * "(()()"
 * "(()(()"
 */
class SolutionStack {
    public int longestValidParentheses(String s) {
        if (s.equals("")) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int max=0;
        int f[] = new int[s.length()];
        for(int i=0;i<s.length();i++){
            // 左括号，入栈下标. f[i]=0
            if (s.charAt(i) == '('){
                stack.push(i);
                f[i]=0;
            }
            // 右括号，出栈一个左括号下标，  i-leftIndex+1 = len
            if (s.charAt(i) == ')') {
                // 栈空的时候 说明是多余的右括号, f[i]=0
                if (stack.isEmpty()) {
                    f[i]=0;
                } else {
                    Integer leftIndex = stack.pop();
                    int len = i - leftIndex+1;
                    // 当左括号的左边是一个完整有效的字符串时，长度拼接
                    if (leftIndex - 1 >= 0) {
                        len += f[leftIndex - 1];
                    }
                    f[i]=len;
                    max = Math.max(max, len);
                }
            }
        }
        return max;
    }
}
