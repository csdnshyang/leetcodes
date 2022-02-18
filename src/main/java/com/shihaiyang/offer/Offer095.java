package com.shihaiyang.offer;

// Offer II 095. 最长公共子序列[动态规划11ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 */
public class Offer095 {
    SolutionOffer095 solutionOffer095 = new SolutionOffer095();

    @Test
    public void case1() {
        int subsequence = solutionOffer095.longestCommonSubsequence("abced", "ace");
        Assertions.assertEquals(subsequence, 3);
    }
    @Test
    public void case2() {
        int subsequence = solutionOffer095.longestCommonSubsequence("abc", "abc");
        Assertions.assertEquals(subsequence, 3);
    }
    @Test
    public void case3() {
        int subsequence = solutionOffer095.longestCommonSubsequence("abc", "def");
        Assertions.assertEquals(subsequence, 0);
    }
    @Test
    public void case4() {
        int subsequence = solutionOffer095.longestCommonSubsequence("adcb", "amdcb");
        Assertions.assertEquals(subsequence, 4);
    }
}
/**
 * 频率比较高的面试题
 * 最值类动态规划
 * 二维动态规划。两个字符串的长度分别为一维
 * dp[i][j]的公共长度  如果i==j，那么应该等于 1+dp[i-1][j-1]  否则就等于 dp[i-1][j-1]
 * 1. 确认状态
 *      最终结果： 求长度i，和长度j的两个字符串的公共子序列。
 *      化成子问题：求长度i和长度j-1的公共子序列或者长度i-1和长度j的公共子序列，或者i=j时，长度i-1，好、j-1的公共子序列
 * 2. 状态转移方程
 *      三种方向的最大值
 *          如果字符相等，则直接等于  [i-1][j-1]+1
 *          否则max([i][j-1], [i-1][j])
 * 3. 初始状态
 *      dp[0][j] dp[i][0]  ===0
 * 4. 计算顺序
 *      从小到大
 *      原则，计算[i][j]时，[i-1][j],[i][j-1],[i-1][j-1]都已算出
 */
class SolutionOffer095 {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i <= text2.length(); i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i <= text1.length(); i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}