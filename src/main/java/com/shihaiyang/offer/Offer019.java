package com.shihaiyang.offer;

// 剑指 Offer II 019. 最多删除一个字符得到回文
// Offer019. 最多删除一个字符得到回文.[双指针+递归一下 4ms 100%].
public class Offer019 {
    public static void main(String[] args) {
        SolutionOffer019 solutionOffer019 = new SolutionOffer019();
        boolean palindrome = solutionOffer019.validPalindrome("abacabda");
        System.out.println(palindrome);
    }
}
/**
 * 给定一个非空字符串 s，请判断如果 最多 从字符串中删除一个字符能否得到一个回文字符串。
 * 示例 1:
 * 输入: s = "aba"
 * 输出: true
 * 示例 2:
 * 输入: s = "abca"
 * 输出: true
 * 解释: 可以删除 "c" 字符 或者 "b" 字符
 * 1 <= s.length <= 10^5
 */

/**
 * 就是遇到一次不匹配的时候，跳过一下，左边跳过或者右边跳过。
 * 左边跳过 || 右边跳过
 * 有一个满足回文就是回文
 */
class SolutionOffer019 {
    public boolean validPalindrome(String s) {
        return checkPalindrome(s, 0, s.length() - 1, false);
    }

    public boolean checkPalindrome(String s, int left, int right, boolean flag) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                if (flag) {
                    return false;
                } else {
                    return checkPalindrome(s, left, right - 1, !flag) || checkPalindrome(s, left + 1, right, !flag);
                }
            }
            left++;
            right--;
        }
        return true;
    }
}
