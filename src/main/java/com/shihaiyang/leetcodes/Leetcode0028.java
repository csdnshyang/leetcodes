package com.shihaiyang.leetcodes;

// 0028. 实现 strStr().[朴素字符数组效率高].
public class Leetcode0028 {
    public static void main(String[] args) {
        Solution0028 solution0028 = new Solution0028();
        int strStr = solution0028.strStr("abc", "c");
        System.out.println(strStr);
    }
}
class Solution0028 {
    public int strStr(String haystack, String needle) {
        if (needle.equals(haystack)) {
            return 0;
        }
        char[] ss = haystack.toCharArray();
        char[] pp = needle.toCharArray();
        for (int i = 0; i <= ss.length - pp.length; i++) {
            int found = 1;
            int p, q;
            for (p = 0, q = i; p < pp.length; p++, q++) {
                if (ss[q] != pp[p]) {
                    found = 0;
                    break;
                }
            }
            if (found == 1 && p == pp.length) {
                return i;
            }
        }
        return -1;
    }
}
