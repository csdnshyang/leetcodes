package com.shihaiyang.leetcodes;

// 0028. 实现 strStr().[kmp 2ms].
public class Leetcode0028 {
    public static void main(String[] args) {
        Solution0028KMP solution0028 = new Solution0028KMP();
        int strStr = solution0028.strStr("a", "");
        System.out.println(strStr);
    }
}

/**
 * KMP
 * 前缀数组记录每个字符能匹配到多少的目标字符串
 *
 */
class Solution0028KMP {
    public int strStr(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }
        char[] ss = haystack.toCharArray();
        char[] pp = needle.toCharArray();

        int[] next = new int[needle.length()];
        int right = 0, left = 0;
        // 初始化前缀表next数组
        while (++right < needle.length()) {
            // left 的回退位置,回退到前一位匹配到的位数，比较是否相等。
            // 预判一下，不匹配先回退。直到left=0说明没有相同前缀
            while (left>0 &&pp[right] != pp[left]) {
                left = next[left - 1];
            }
            // left 和right匹配，设置前缀表
            if (pp[left] == pp[right]) {
                // left移动
                left++;
                next[right] = left;
            }
        }
        // 开始匹配
        // 匹配逻辑跟生成next数组类似，只是长度变成了原字符串
        int i = -1, j = 0;
        while (++i < haystack.length()) {
            // 先预判是否相同，不相同就先回退，直到回退到0，就相当于没有相同前缀，从头开始匹配
            while (j > 0 && ss[i] != pp[j]) {
                j = next[j - 1];
            }
            if (ss[i] == pp[j]) {
                j++;
                if (j == needle.length()) {
                    return i - j + 1;
                }
            }
        }
        return -1;
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
