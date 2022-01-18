package com.shihaiyang.offer;
// 剑指 Offer II 020. 回文子字符串的个数
// Offer020. 回文子字符串的个数.[双指针外扩3ms].
public class Offer020 {
    public static void main(String[] args) {
        SolutionOffer020 solutionOffer020 = new SolutionOffer020();
        int countSubstrings = solutionOffer020.countSubstrings("abc");
        System.out.println(countSubstrings);
    }
}
/**
 * 给定一个字符串 s ，请计算这个字符串中有多少个回文子字符串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 示例 1：
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 */
/**
 * 遍历+双指针外扩
 * 遇到左边、右指针停止
 * 两种情况，一种从中间向两边分散，一种左右两个头结点向两边分散
 */
class SolutionOffer020 {
    public int countSubstrings(String s) {
        int count=0;
        for (int i = 0; i < s.length(); i++) {
            count += countSubstrings(s, i);
            count += countSubstrings(s, i, i+1);
        }
        return count;
    }

    public int countSubstrings(String s, int middle) {
        int count = 0;
        int left = middle, right = middle;
        while (left >= 0 && right <= s.length() - 1) {
            if (s.charAt(left--) != s.charAt(right++)) {
                break;
            }
            count++;
        }
        return count;
    }
    public int countSubstrings(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right <= s.length() - 1) {
            if (s.charAt(left--) != s.charAt(right++)) {
                break;
            }
            count++;
        }
        return count;
    }
}