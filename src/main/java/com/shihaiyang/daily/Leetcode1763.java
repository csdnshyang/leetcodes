package com.shihaiyang.daily;

// 1763. 最长的美好子字符串.[方法1：双循环暴力+位运算4ms].
public class Leetcode1763 {
    public static void main(String[] args) {
        Solution1763 solution1763 = new Solution1763();
//        String niceSubstring = solution1763.longestNiceSubstring("YazaAay");
        String niceSubstring = solution1763.longestNiceSubstring("dDzeE");
        System.out.println(niceSubstring);
    }
}

/**
 * 当一个字符串 s 包含的每一种字母的大写和小写形式 同时 出现在 s 中，就称这个字符串 s 是 美好 字符串。
 * 比方说，"abABB" 是美好字符串，因为 'A' 和 'a' 同时出现了，且 'B' 和 'b' 也同时出现了。
 * 然而，"abA" 不是美好字符串因为 'b' 出现了，而 'B' 没有出现。
 * 给你一个字符串 s ，请你返回 s 最长的 美好子字符串 。如果有多个答案，请你返回 最早 出现的一个。如果不存在美好子字符串，请你返回一个空字符串。
 * 输入：s = "YazaAay"
 * 输出："aAa"
 * 解释："aAa" 是一个美好字符串，因为这个子串中仅含一种字母，其小写形式 'a' 和大写形式 'A' 也同时出现了。
 * "aAa" 是最长的美好子字符串。
 */

/**
 * 方法1：双循环暴力+位运算
 */
class Solution1763 {
    public String longestNiceSubstring(String s) {
        int maxLen = 0;
        String ret = "";
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            int big = 0, small = 0;
            for (int j = i; j < s.length(); j++) {
                if (checkBig(chars[j])) {
                    int index = chars[j] - 'A';
                    big |= (1 << index);
                } else {
                    int index = chars[j] - 'a';
                    small |= (1 << index);
                }
                if (big == small && maxLen < j - i) {
                    ret = s.substring(i, j + 1);
                    maxLen = j - i + 1;
                }
            }
        }
        return ret;
    }

    private boolean checkBig(char c) {
        if (c >= 'a' && c <= 'z') {
            return false;
        }
        return true;
    }
}
