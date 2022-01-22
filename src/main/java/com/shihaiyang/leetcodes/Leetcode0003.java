package com.shihaiyang.leetcodes;

// 0003. 无重复字符的最长子串.[滑动窗口 2ms].
public class Leetcode0003 {
    public static void main(String[] args) {
        Solution0003 solution0003 = new Solution0003();
        int longestSubstring = solution0003.lengthOfLongestSubstring("abcabcbb");
        System.out.println(longestSubstring);
    }
}

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */

/**
 * 滑动窗口
 */
class Solution0003 {
    public int lengthOfLongestSubstring(String s) {
        int start = 0, end = 0;
        int arr[] = new int[128];
        int max = 0;
        while (end < s.length()) {
            arr[s.charAt(end)]++;
            while (start < end && arr[s.charAt(end)] == 2) {
                if (arr[s.charAt(start)] == 2) {
                    arr[s.charAt(start++)]--;
                    break;
                }
                arr[s.charAt(start++)]--;
            }
            max = Math.max(max, end - start+1);
            end++;
        }
        return Math.max(max, end - start);
    }
}
