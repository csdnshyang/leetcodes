package com.shihaiyang.offer;

// 剑指 Offer II 016. 不含重复字符的最长子字符串
// Offer016. 不含重复字符的最长子字符串.[滑动窗口+字符数组2ms].
public class Offer016 {
    public static void main(String[] args) {
        SolutionOffer016 solutionOffer016 = new SolutionOffer016();
        int substring = solutionOffer016.lengthOfLongestSubstring("abcabcbb");
        System.out.println(substring);
    }
}

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长连续子字符串 的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子字符串是 "abc"，所以其长度为 3。
 */

/**
 * 滑动窗口.
 * start,end两个指针代表窗口边界.
 * end先动，右移；没有遇到重复就一直右移，遇到重复就停止。 // 如果arr[end]++之后，arr[end]==2 说明重复了。 end停止，start开始
 * 当遇到重复时记录一次长度。start开始右移，直到消除重复。 // 如果arr[start]--之后，arr[start]==1  说明重复消除了。start停止，end开始。
 * 交替执行
 * end到结尾之后，记录一次max，start不需要再动，因为会越来越短.
 */
class SolutionOffer016 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        char arr[] = new char[128];
        int max = 1;
        int start = 0, end = 0;
        while (end < s.length()) {
            arr[s.charAt(end)]++;
            if (arr[s.charAt(end)] > 1){
                max = Math.max(end - start, max);
                while (start < end) {
                    arr[s.charAt(start++)]--;
                    if (arr[s.charAt(start-1)] == 1) {
                        break;
                    }
                }
            }
            end++;
        }
        return Math.max(end-start, max);
    }
}
