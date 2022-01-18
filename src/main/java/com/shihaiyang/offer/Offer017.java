package com.shihaiyang.offer;

// 剑指 Offer II 017. 含有所有字符的最短字符串
// Offer017. 含有所有字符的最短字符串.[滑动窗口+自定义窗口条件 8ms].

import java.util.Arrays;

public class Offer017 {
    public static void main(String[] args) {
        SolutionOffer017 solutionOffer017 = new SolutionOffer017();
//        String minWindow = solutionOffer017.minWindow("abc", "bc");
        String minWindow = solutionOffer017.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(minWindow);
    }
}
/**
 * 给定两个字符串 s 和 t 。返回 s 中包含 t 的所有字符的最短子字符串。如果 s 中不存在符合条件的子字符串，则返回空字符串 "" 。
 * 如果 s 中存在多个符合条件的子字符串，返回任意一个。
 * 注意： 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最短子字符串 "BANC" 包含了字符串 t 的所有字符 'A'、'B'、'C'
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 */

/**
 * 滑动窗口+自定义窗口满足条件
 * 压缩成一个[58]位数组存储字符出现次数。
 *
 * t遍历一次，把出现字符-'A' 对应索引+1
 * s遍历的时候，出现字符-'A' 对应的索引-1
 *
 * 滑动窗口自定义判断条件  所有索引的值都<=0.出现>0则说明窗口不满足条件
 */
class SolutionOffer017 {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        int arr[] = new int[58];
        for (int i = 0; i < t.length(); i++) {
            arr[t.charAt(i) - 'A']++;
        }
        int min = Integer.MAX_VALUE;
        int minLeft = 0, minRight = 0;
        int start = 0, end = 0;
        while (end < s.length()) {
            arr[s.charAt(end++) - 'A']--;
            while (start < end && isMatch(arr)) {
                if (min > end - start) {
                    min = end - start;
                    minLeft = start;
                    minRight = end;
                }
                arr[s.charAt(start++) - 'A']++;
            }
        }
        return s.substring(minLeft, minRight);
    }

    private boolean isMatch(int[] arr) {
        for (int value:arr) {
            if (value>0) return false;
        }
        return true;
    }
}