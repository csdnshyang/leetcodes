package com.shihaiyang.offer;

// 剑指 Offer II 032. 有效的变位词
// Offer032. 有效的变位词.[数组+遍历 1ms].
public class Offer032 {
    public static void main(String[] args) {
        SolutionOffer032 solutionOffer032 = new SolutionOffer032();
        boolean anagram = solutionOffer032.isAnagram("cat", "rat");
        System.out.println(anagram);
    }
}

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断它们是不是一组变位词（字母异位词）。
 * 注意：若 s 和 t 中每个字符出现的次数都相同且字符顺序不完全相同，则称 s 和 t 互为变位词（字母异位词）。
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 示例 3:
 * 输入: s = "a", t = "a"
 * 输出: false
 * 提示:
 * 1 <= s.length, t.length <= 5 * 104
 * s and t 仅包含小写字母
 */
class SolutionOffer032 {
    public boolean isAnagram(String s, String t) {
        if (s.equals(t) || s.length() != t.length()) {
            return false;
        }
        int sa[] = new int[26];
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            sa[chars1[i] - 'a']++;
        }
        for (int i = 0; i < chars2.length; i++) {
            if (sa[chars2[i] - 'a'] == 0) {
                return false;
            }
            sa[chars2[i] - 'a']--;
        }
        return true;
    }
}