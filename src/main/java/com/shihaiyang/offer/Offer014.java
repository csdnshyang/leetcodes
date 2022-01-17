package com.shihaiyang.offer;

import java.util.Arrays;

// 剑指 Offer II 014. 字符串中的变位词
// Offer014. 字符串中的变位词.[滑动窗口+2个字母数组 6ms].
// https://leetcode-cn.com/problems/MPnaiL/solution/offer014-zi-fu-chuan-zhong-de-bian-wei-c-323k/
public class Offer014 {
    public static void main(String[] args) {
        SolutionOffer014 solutionOffer014 = new SolutionOffer014();
        boolean checkInclusion = solutionOffer014.checkInclusion("hello", "ooolleoooleh");
        System.out.println(checkInclusion);
    }
}
/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词。
 * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
 * 示例 1：
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * "hello"
 * "ooolleoooleh"
 * false
 */

/**
 * 空间换时间
 * s1放入map，遍历s2。
 * O(n*m)  速度很慢》
 * 优化。滑动窗口。这个也可以滑动窗口...
 * end右移的时候扩大范围val-1,
 * start右移时候缩小范围val+1,
 * 窗口就是s1长度那个大...map的count和为0。
 */
class SolutionOffer014 {
    public boolean checkInclusion(String s1, String s2) {
        char arr1[] = new char[26];
        char arr2[] = new char[26];
        if (s2.length() < s1.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            arr1[s1.charAt(i) - 'a']++;
            arr2[s2.charAt(i) - 'a']++;
        }
        for (int i = s1.length(); i < s2.length(); i++) {
            if (Arrays.equals(arr1, arr2)) {
                return true;
            }
            arr2[s2.charAt(i) - 'a']++;
            arr2[s2.charAt(i - s1.length()) - 'a']--;
        }
        return Arrays.equals(arr1, arr2);
    }
}