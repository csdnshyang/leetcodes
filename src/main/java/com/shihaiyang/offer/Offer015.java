package com.shihaiyang.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 剑指 Offer II 015. 字符串中的所有变位词
// Offer015. 字符串中的所有变位词.[滑动窗口+双数组 10ms].
public class Offer015 {
    public static void main(String[] args) {
        SolutionOffer015 solutionOffer015 = new SolutionOffer015();
//        List<Integer> integers = solutionOffer015.findAnagrams("baa", "aa");
        List<Integer> integers = solutionOffer015.findAnagrams("cbaebabacd", "abc");
        integers.stream().forEach(integer -> System.out.println(integer+","));
        System.out.println();
    }
}

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 变位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 变位词 指字母相同，但排列不同的字符串。
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的变位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的变位词。
 */

/**
 * 滑动窗口+2个字符数组+计数
 */
class SolutionOffer015 {
    public List<Integer> findAnagrams(String s, String p) {
        char arr1[] = new char[26];
        char arr2[] = new char[26];
        if (s.length() < p.length()) {
            return new ArrayList<>();
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < p.length(); i++) {
            arr1[p.charAt(i) - 'a']++;
            arr2[s.charAt(i) - 'a']++;
        }
        for (int i = p.length(); i < s.length(); i++) {
            if (Arrays.equals(arr1, arr2)) {
                ret.add(i-p.length());
            }
            arr2[s.charAt(i) - 'a']++;
            arr2[s.charAt(i-p.length()) - 'a']--;
        }
        if (Arrays.equals(arr1, arr2)) {
            ret.add(s.length() - p.length());
        }
        return ret;
    }
}