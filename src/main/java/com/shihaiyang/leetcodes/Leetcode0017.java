package com.shihaiyang.leetcodes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 0017. 电话号码的字母组合.[回溯、DFS、BFS 5ms].
/*
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
示例 1：
输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
示例 2：
输入：digits = ""
输出：[]
示例 3：
输入：digits = "2"
输出：["a","b","c"]
 */
public class Leetcode0017 {
    Solution0017 solution0017 = new Solution0017();

    @Test
    public void case1() {
        List<String> list = solution0017.letterCombinations("23");
        Assertions.assertTrue(list.equals(List.of("ad","ae","af","bd","be","bf","cd","ce","cf")));
    }
    @Test
    public void case2() {
        List<String> list = solution0017.letterCombinations("2");
        Assertions.assertTrue(list.equals(List.of("a","b","c")));
    }
    @Test
    public void case3() {
        List<String> list = solution0017.letterCombinations("");
        Assertions.assertTrue(list.equals(List.of()));
    }
}
/*
回溯算法

DFS

BFS

 */
class Solution0017 {
    List<String> ret = new ArrayList<>();
    Map<Character, char[]> map = Map.of(
            '2', new char[]{'a', 'b', 'c'},
            '3', new char[]{'d', 'e', 'f'},
            '4', new char[]{'g', 'h', 'i'},
            '5', new char[]{'j', 'k', 'l'},
            '6', new char[]{'m', 'n', 'o'},
            '7', new char[]{'p', 'q', 'r', 's'},
            '8', new char[]{'t', 'u', 'v'},
            '9', new char[]{'w', 'x', 'y', 'z'}
    );

    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return ret;
        }
        char[] digit = digits.toCharArray();
        backTrace(digit, new String(), 0);
        return ret;
    }

    private void backTrace(char[] digit, String string, int index) {
        if (string.length() == digit.length) {
            ret.add(string);
            return;
        }

        char[] chars = map.get(digit[index]);
        for (int i = 0; i < chars.length; i++) {
            backTrace(digit, string + chars[i], index + 1);
        }
    }
}
