package com.shihaiyang.offer;
// 剑指 Offer II 018. 有效的回文
// Offer018. 有效的回文.[双指针 2ms].
public class Offer018 {
    public static void main(String[] args) {
        SolutionOffer018 solutionOffer018 = new SolutionOffer018();
        boolean palindrome = solutionOffer018.isPalindrome("0P");
        System.out.println(palindrome);
    }
}

/**
 * 给定一个字符串 s ，验证 s 是否是 回文串 ，只考虑字母和数字字符，可以忽略字母的大小写。
 * 本题中，将空字符串定义为有效的 回文串 。
 * 示例 1:
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * 示例 2:
 * 输入: s = "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 */

/**
 * 左右双指针
 * 到中间之后说明回文了
 * 只考虑字母数字，忽略字母大小写
 */
class SolutionOffer018 {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (isValid(s.charAt(left)) && isValid(s.charAt(right))) {
                if (!isMatch(s.charAt(left), s.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            } else if (!isValid(s.charAt(left))) {
                left++;
            } else if (!isValid(s.charAt(right))) {
                right--;
            }
        }
        return true;
    }

    public boolean isMatch(char c1, char c2) {
        if (c1 == c2) {
            return true;
        }
        if (c1 >= 'A' && c1 <= 'Z' &&  c2 >= 'a' && c2 <= 'z' && c1 - 'A' == c2 - 'a') {
            return true;
        }
        if (c1 >= 'a' && c1 <= 'z' &&  c2 >= 'A' && c2 <= 'Z' && c1 - 'a' == c2 - 'A') {
            return true;
        }
        return false;
    }

    public boolean isValid(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        return false;
    }
}
