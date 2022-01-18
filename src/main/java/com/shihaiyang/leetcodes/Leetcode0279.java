package com.shihaiyang.leetcodes;

// 0279. 完全平方数.[最值类动态规划 39ms].
public class Leetcode0279 {
    public static void main(String[] args) {
        Solution0279 solution0279 = new Solution0279();
        int numSquares = solution0279.numSquares(12);
        System.out.println(numSquares);
    }
}
/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 */

/**
 * 方法1：最值类动态规划
 * 都要借助于开平方函数
 * 类似于硬币问题
 * 1. 确认状态
 *      最终结果：计算n能通过几个平方数获取。dp[n]代表n的最小平方数个数  那么之前的数都已经是最小的开平方数了
 *      化成子问题：计算n的时候，对n开平方的值k  dp[n] = 1+dp[n-k*k];
 * 2. 状态转移方程
 *      dp[n] =min(1+dp[n-k*k], 1+dp[n-(k-1)*(k-1)], ....,1+dp[n-2*2]);
 * 3. 初始状态
 *      dp[1]=1
 * 4. 计算顺序
 *      dp[1] dp[2]...  dp[k]..dp[n]
 *      原则计算dp[n]的时候，dp[k]已经算出
 */
class Solution0279 {
    public int numSquares(int n) {
        int dp[] = new int[n + 1];
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            // 初始一下
            dp[i] = dp[i - 1] + 1;
            int sqrt = (int)Math.sqrt(i);
            if (i - sqrt * sqrt == 0) {
                dp[i] = 1;
            } else {
                double sqrt1 = Math.sqrt(sqrt);
                for (; sqrt > sqrt1; sqrt--) {
                    dp[i] = Math.min(1 + dp[i - sqrt * sqrt], dp[i]);
                }
            }
        }
        return dp[n];
    }
}

