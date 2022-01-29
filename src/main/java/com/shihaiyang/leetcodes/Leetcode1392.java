package com.shihaiyang.leetcodes;

// 1392. 最长快乐前缀.[KMP 8ms].
public class Leetcode1392 {
    public static void main(String[] args) {
        Solution1392 solution1392 = new Solution1392();
        String longestPrefix = solution1392.longestPrefix("ababab");
        System.out.println(longestPrefix);
    }
}

/**
 * 「快乐前缀」是在原字符串中既是 非空 前缀也是后缀（不包括原字符串自身）的字符串。
 * 给你一个字符串 s，请你返回它的 最长快乐前缀。
 * 如果不存在满足题意的前缀，则返回一个空字符串。
 * 输入：s = "level"
 * 输出："l"
 * 解释：不包括 s 自己，一共有 4 个前缀（"l", "le", "lev", "leve"）和 4 个后缀（"l", "el", "vel", "evel"）。最长的既是前缀也是后缀的字符串是 "l" 。
 * 输入：s = "ababab"
 * 输出："abab"
 * 解释："abab" 是最长的既是前缀也是后缀的字符串。题目允许前后缀在原字符串中重叠。
 */

/**
 * 感觉这个就是一个kmp的最大前缀数组就可以了...
 */
class Solution1392 {
    public String longestPrefix(String s) {
        if (s.equals("")) {
            return "";
        }
        int[] next = new int[s.length()];
        int left = 0, right = 0;
        char[] chars = s.toCharArray();
        // 双指针
        while (++right < s.length()) {
            // 预判，不相等先回退。
            while (left > 0 && chars[left] != chars[right]) {
                left = next[left - 1];
            }
            // 如果相等，左指针右移
            if (chars[left] == chars[right]) {
                left++;
                next[right] = left;
            }
        }
        return s.substring(0, next[s.length() - 1]);
    }
}


