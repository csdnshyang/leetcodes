package com.shihaiyang.leetcodes;

import java.util.ArrayList;
import java.util.List;

// 0524. 通过删除字母匹配到字典里最长单词.[双指针模拟 8ms].
public class Leetcode0524 {
    public static void main(String[] args) {
        Solution0524 solution0524 = new Solution0524();
//        String longestWord = solution0524.findLongestWord("abpcplea", new ArrayList<>(List.of("ale", "apple", "monkey", "plea")));
        String longestWord = solution0524.findLongestWord("aewfafwafjlwajflwajflwafj",
                new ArrayList<>(List.of("apple","ewaf","awefawfwaf","awef","awefe","ewafeffewafewf")));
        System.out.println(longestWord);
    }
}

/**
 * 给你一个字符串 s 和一个字符串数组 dictionary ，找出并返回 dictionary 中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 * 如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。
 * 输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * 输出："apple"
 * "aewfafwafjlwajflwajflwafj"
 * ["apple","ewaf","awefawfwaf","awef","awefe","ewafeffewafewf"]
 * "ewaf"
 */

/**
 * 理解错了这个题，以为只要是字符包含就是答案，其实是有顺序要求的。那只能每个字符遍历了。
 * 这个方法叫模拟。
 * 判断顺序：
 * 长度。如果长度小于maxLen跳过
 * 长度相同，比较字符串子母序
 * 两个指针有一个到头都结束遍历。
 * 如果dict指针直到头，说明扫描完了dict。子母满足条件
 */
class Solution0524 {
    public String findLongestWord(String s, List<String> dictionary) {
        int maxLen = 0;
        String ret = "";
        char[] src = s.toCharArray();
        for (int i = 0; i < dictionary.size(); i++) {
            if (maxLen > dictionary.get(i).length()) {
                continue;
            }
            // 长度相同，字母序大的不取，跳过
            if (maxLen == dictionary.get(i).length() && ret.compareTo(dictionary.get(i)) > 0) {
                continue;
            }
            char[] dict = dictionary.get(i).toCharArray();
            int k=0, m=0;
            while (k < dict.length && m < src.length) {
                if (src[m] == dict[k]) {
                    k++;
                }
                m++;
            }
            if (k == dict.length) {
                maxLen = dictionary.get(i).length();
                ret = dictionary.get(i);
            }
        }
        return ret;
    }
}