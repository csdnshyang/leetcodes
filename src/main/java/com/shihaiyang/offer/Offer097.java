package com.shihaiyang.offer;

// Offer II 097. 子序列的数目.[动态规划4ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
 * （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * 题目数据保证答案符合 32 位带符号整数范围。
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * rabbbit
 * rabbbit
 * rabbbit
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 */
public class Offer097 {
    SolutionOffer097 solutionOffer097 = new SolutionOffer097();

    @Test
    public void case1() {
        Assertions.assertEquals(3, solutionOffer097.numDistinct("rabbbit", "rabbit"));
    }
    @Test
    public void case2() {
        Assertions.assertEquals(5, solutionOffer097.numDistinct("babgbag", "bag"));
    }
    @Test
    public void case3() {
        Assertions.assertEquals(2, solutionOffer097.numDistinct("abb", "ab"));
        Assertions.assertEquals(2, solutionOffer097.numDistinct("aab", "ab"));
    }
}

/**
 * 动态规划计数类
 * 1. 确认状态  dp[i][j] 表示i个长度s和j个长度的t能匹配的个数。
 *      最终状态：k个中有几个子序列
 *      化成子问题：k-1个字符串中有几个子序列
 *          dp[i-1][j] 表示i-1长度的s和j长度的t的子序列个数。  dp[i-1][j-1]表示i-1长的的s和j-1长度的t的子序列个数
 *          dp[i][j]有两种情况，如果字符相等的情况下，他可以dp[i-1][j]也可以是dp[i-1][j-1]
 * 2. 状态转移方程
 *      if(s[i]==t[j])  dp[i][j]=dp[i-1][j-1] + dp[i-1][j]
 *      else dp[i][j]=dp[i-1][j]
 * 3. 初始状态
 *      dp[i][0] 出界的情况，认为是能匹配。=1
 * 4. 计算顺序
 *      从0到i。
 *      原则：计算i是 i-1已经算出
 */
class SolutionOffer097 {
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        // 初始化状态
        // 初始认为是1.
        for (int i = 0; i < s.length(); i++) {
            dp[i][0] = 1;
        }
        char[] sch = s.toCharArray();
        char[] tch = t.toCharArray();

        for (int i = 1; i <= sch.length; i++) {
            for (int j = 1; j <= tch.length; j++) {
                // 状态转移方程 默认等于i-1长度的s字符串的匹配个数
                dp[i][j] = dp[i - 1][j];
                // 当字符相同，再加上[i-1]长度的s字符串能匹配到j-1个t字符串的匹配个数
                if (sch[i - 1] == tch[j - 1]) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }

        return dp[s.length()][t.length()];
    }
}
