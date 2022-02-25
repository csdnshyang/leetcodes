package com.shihaiyang.leetcodes;
// 0647. 回文子串.[中心扩展4ms 动态规划8ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 示例 1：
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * aaaaa
 * a a a a a aa aa aa aa aaa aaa aaaaa aaa aaaa aaaa
 * 寻找的方式
 * a aa
 * a aaa aa aaaa
 * a aaa aaaaa aa aaaa
 * a aaa aa
 * a
 */
public class Leetcode0647 {
    Solution0647Dp solution0647 = new Solution0647Dp();

    @Test
    public void case1() {
        Assertions.assertEquals(3, solution0647.countSubstrings("abc"));
        Assertions.assertEquals(6, solution0647.countSubstrings("aaa"));
        Assertions.assertEquals(15, solution0647.countSubstrings("aaaaa"));
    }
}

/**
 * 动态规划统计类 二维 记得之前还做过一个二维字符串的动态规划
 * 动态规划其实是采用的内缩的思路，跟中心扩展判断方向相反
 * 统计一个字符串是否是回文，分两种情况，
 * 一种是奇数个字符，中心是一个字符
 * 一种是偶数个字符，中心是两个字符
 * 1. 确认状态  dp[i][j] 标识以i为头，以j为尾的字符串是不是回文字符串。 所以dp[i+1][j-1]就标识向内缩了一次，即去掉头尾的子串是否是回文字符串。
 * 最终结果  统计完dp[i][j]中有多少个回文字符串
 * 化成子问题   如果想统计dp[i][j]是不是回文字符串，就看下i,j个字符是不是相等，并且dp[i+1][j-1]是否回文。
 * 即dp[i][j]=s[i]==s[j] && dp[i+1][j-1]
 * 2. 状态转移方程  即dp[i][j]=s[i]==s[j] && dp[i+1][j-1]
 * 3. 初始状态列出所有可能的基础中心点   有两种，
 * 一种是奇数字符的，单个字符作为中心，dp[0][0]  dp[1][1]  ...都是回文
 * 一种是偶数字符的，两个字符作为中心，dp[0][1]=(s[0] == s[1])  dp[1][2] = (s[1]==s[2])
 * 4. 计算顺序
 * 先计算出所有基础的中心点
 * 再递归遍历所有的点
 * 其实也算是记忆化搜索，把相关搜索历史记忆下来。
 * 参考了https://leetcode-cn.com/problems/palindromic-substrings/solution/shou-hua-tu-jie-dong-tai-gui-hua-si-lu-by-hyj8/
 */
class Solution0647Dp {
    public int countSubstrings(String s) {
        char[] sc = s.toCharArray();
        int ans = 0;
        // dp[i][j] 标识以i为头，以j为尾的字符串是不是回文字符串。
        boolean[][] dp = new boolean[s.length()][s.length()];
        // 默认false
        Arrays.stream(dp).forEach(booleans -> Arrays.fill(booleans, false));

        // 初始化状态  奇数类
        for (int i = 0; i < sc.length; i++) {
            dp[i][i] = true;
            ans++;
        }
        // 初始化状态 偶数类
        for (int i = 0; i < sc.length - 1; i++) {
            if (sc[i] == sc[i + 1]) {
                dp[i][i + 1] = true;
                ans++;
            }
        }
        // 初始化状态也可以在双层遍历中跟状态转移一起进行。
        // 这个遍历比较讲究，在计算dp[i][j]时候，要保证dp[i+1][j-1]已经算出  统计dp[0][4]时候dp[1][3]还没统计到，所以不能i<len,j<len
        // 第一层遍历应该是结尾，第二层是开头。这样保证算[0][4] 的时候[1][3]已经算过了
        for (int j = 0; j < sc.length; j++) {
            for (int i = 0; i <= j; i++) {
                if (i + 1 < j) {
                    // 状态转移方程  头尾ij字符相同，且内缩一个字符串是回文
                    dp[i][j] = sc[i] == sc[j] && dp[i + 1][j - 1];
                    if (dp[i][j]) ans++;
                }
            }
        }
        return ans;
    }
}

/**
 * 中心扩展法
 * 作为奇数的扩展，
 * left=right=i为初始，计数+1   left--,right++计数再加1.
 * 作为偶数的扩展，只统计当前节点作为偶数的左边节点，再加右边一个节点，两个节点作为中心节点。
 * left=i,right=i+1, 计数+1，  left--,right++计数再加1.
 */
class Solution0647 {
    public int countSubstrings(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int step = 0;
            while (isSubstring(s, i, step)) {
                ans++;
                step++;
            }
            step = 0;
            while (isSubstring(s, i, i + 1, step)) {
                ans++;
                step++;
            }
        }
        return ans;
    }

    private boolean isSubstring(String s, int mid, int step) {
        if (mid - step >= 0 && mid + step < s.length()) {
            if (s.charAt(mid - step) == s.charAt(mid + step)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean isSubstring(String s, int left, int right, int step) {
        if (left - step >= 0 && right + step < s.length()) {
            if (s.charAt(left - step) == s.charAt(right + step)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}