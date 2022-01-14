package com.shihaiyang.leetcodes;

import java.util.ArrayList;
import java.util.List;
// 0022. 括号生成.[递归算法+两个int标记左右括号存量.0ms].
/**
 * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗：38.6 MB, 在所有 Java 提交中击败了32.34%的用户
 */

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 */
public class Leetcode0022 {
    public static void main(String[] args) {
        Solution0022 solution0020 = new Solution0022();
        solution0020.generateParenthesis(3);
    }
}
// 回溯算法本质是递归. 沿着递归的思路想想...刚才快排刚递归过.

/**
 * 借助一个栈.递归判断。
 * 其实可以使用两个int值。标记是否还有存量
 */
class Solution0022 {
    public List<String> generateParenthesis(int n) {
        List<String> parent = new ArrayList<>();
        char[] chars = new char[2 * n];
        chars[0]='(';
        int left=n-1, right=n;
        generateAll(chars, 1, left, right, parent);
        return parent;
    }

    public void generateAll(char[] chars, int pos, int left, int right, List<String> lists) {
        // 充满. 跳出。
        if (chars.length == pos) {
            lists.add(new String(chars));
            return;
        }
        // 左括号还有余量, 左括号加入.递归
        if (left > 0) {
            chars[pos] = '(';
            generateAll(chars, pos + 1, left-1, right, lists);
        }
        // 右括号有余粮,且左括号数量小于右括号。保持合法化.递归
        if (right>0 && left<right) {
            chars[pos] = ')';
            generateAll(chars, pos + 1, left, right-1, lists);
        }
    }

}