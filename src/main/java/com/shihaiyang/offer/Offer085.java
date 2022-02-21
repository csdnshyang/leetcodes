package com.shihaiyang.offer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

// Offer II 085. 生成匹配的括号.[栈或者回溯1ms].

/**
 * 正整数 n 代表生成括号的对数，请设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 */
public class Offer085 {
    SolutionOffer085 solutionOffer085 = new SolutionOffer085();

    @Test
    public void case1() {
        List<String> list = solutionOffer085.generateParenthesis(3);
        Assertions.assertTrue(list.equals(List.of("((()))","(()())","(())()","()(())","()()()")));
    }
    @Test
    public void case2() {
        List<String> list = solutionOffer085.generateParenthesis(1);
        Assertions.assertTrue(list.equals(List.of("()")));
    }
}

/**
 * 回溯。
 * 统计left个数，right个数
 * () 的运行情况.
 * 加入'(',backTrace加入'(',回退'(',加入')' 最终形成()
 * (()())的运行情况
 * 加入'(',backTrace加入'(',backTrace加入'(',回退')',加入')',
 * backTrace加入'(',backTrace加入'(',回退'(',加入')',加入')'
 */
class SolutionOffer085 {
    List<String> ret = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        backTrace(new String(), n, n);
        return ret;
    }

    private void backTrace(String string, int left, int right) {
        // 如果没有括号余额，直接返回
        // 如果右括号多了 ())的情况不合法，直接返回
        if (left < 0 || right < 0 || right < left) {
            return;
        }
        // 括号正好用完，加入
        if (left == 0 && right == 0) {
            ret.add(string);
            return;
        }
        // 先加入左括号 回退是一个逻辑，因为string本身没变。left本身没变。
        backTrace(string + "(", left - 1, right);
        // 加入右括号，加入右括号时，其实已经是回退了
        backTrace(string + ")", left, right - 1);
    }
}
