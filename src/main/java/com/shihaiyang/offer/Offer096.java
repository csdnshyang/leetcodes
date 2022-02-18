package com.shihaiyang.offer;
// Offer II 096. 字符串交织.[动态规划2ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给定三个字符串 s1、s2、s3，请判断 s3 能不能由 s1 和 s2 交织（交错） 组成。
 * 两个字符串 s 和 t 交织 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交织 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 * <p>
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 */
public class Offer096 {
    SolutionOffer096 solutionOffer096 = new SolutionOffer096();

    @Test
    public void case1() {
        Assertions.assertTrue(solutionOffer096.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }
    @Test
    public void case2() {
        Assertions.assertTrue(!solutionOffer096.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }
    @Test
    public void case3() {
        Assertions.assertTrue(solutionOffer096.isInterleave("", "", ""));
    }
    @Test
    public void case4() {
        Assertions.assertTrue(!solutionOffer096.isInterleave("a", "", "c"));
    }
    @Test
    public void case5() {
        Assertions.assertTrue(solutionOffer096.isInterleave("ab", "aa", "abaa"));
    }
}

/**
 * 双指针是不好判断的。
 * 动态规划存在类  二维
 * 1. 确认状态
 *      最终结果  dp[i][j]代表 s1 i个字符和s2 j个字符是能拼成s3 i+j个字符的  dp[i][j]=true则成立
 *      化成子问题  i+j-1=true; 成立的条件有两个情况
 *          s3的最后一个字符等于s1最后一个字符并且 dp[i-1][j]是true
 *          s3的最后一个字符等于s2最后一个字符并且 dp[i][j-1]是true
 * 2. 状态转移方程
 *      如果s3成立，则dp[i][j]= (s3[i+j-1]==s1[i] && dp[i-1][j])  || (s3[i+j-1]==s2[j] && dp[i][j-1])
 * 3. 初始状态
 *      s3.len=s1.len+s2.len
 *      dp[i][0] =0
 * 4. 计算顺序
 *      从0开始计算。
 */
class SolutionOffer096 {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        // 初始化状态
        // 状态转移
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = s3.charAt(i + j - 1) == s2.charAt(j - 1) && dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = s3.charAt(i + j - 1) == s1.charAt(i - 1) && dp[i - 1][j];
                } else {
                    dp[i][j] = (s3.charAt(i + j - 1) == s1.charAt(i - 1) && dp[i - 1][j]) ||
                            (s3.charAt(i + j - 1) == s2.charAt(j - 1) && dp[i][j - 1]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}