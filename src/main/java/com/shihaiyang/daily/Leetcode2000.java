package com.shihaiyang.daily;

// 2000. 反转单词前缀.[字符数组+遍历 0ms].
public class Leetcode2000 {
    public static void main(String[] args) {
        Solution2000 solution2000 = new Solution2000();
        String reversePrefix = solution2000.reversePrefix("abcdefd", 'g');
        System.out.println(reversePrefix);
    }
}

/**
 * 给你一个下标从 0 开始的字符串 word 和一个字符 ch 。
 * 找出 ch 第一次出现的下标 i ，反转 word 中从下标 0 开始、直到下标 i 结束（含下标 i ）的那段字符。
 * 如果 word 中不存在字符 ch ，则无需进行任何操作。
 *
 * 例如，如果 word = "abcdefd" 且 ch = "d" ，那么你应该 反转 从下标 0 开始、直到下标 3 结束（含下标 3 ）。结果字符串将会是 "dcbaefd" 。
 * 返回 结果字符串 。
 * 输入：word = "abcdefd", ch = "d"
 * 输出："dcbaefd"
 * 解释："d" 第一次出现在下标 3 。
 * 反转从下标 0 到下标 3（含下标 3）的这段字符，结果字符串是 "dcbaefd" 。
 */
class Solution2000 {
    public String reversePrefix(String word, char ch) {
        char[] chars = word.toCharArray();
        int i=0;
        for (; i < chars.length; i++) {
            if (chars[i] == ch) {
                break;
            }
        }
        if (i == chars.length) {
            return word;
        }
        for (int j = 0; j <= i / 2; j++) {
            char tmp = chars[j];
            chars[j] = chars[i - j];
            chars[i - j] = tmp;
        }
        return String.valueOf(chars);
    }
}
