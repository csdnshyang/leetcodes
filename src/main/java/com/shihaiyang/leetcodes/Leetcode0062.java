package com.shihaiyang.leetcodes;

// 0062. 不同路径.[计数类动态规划].
public class Leetcode0062 {
    public static void main(String[] args) {
        Solution0062 solution0062 = new Solution0062();
        int uniquePaths = solution0062.uniquePaths(3, 2);
        System.out.println(uniquePaths);
    }
}
/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 */

/**
 * 计数类动态规划
 * 1. 确认状态
 * 最终结果 走到右下角 假设长m高n的矩阵  到达 [m-1, n-1] 的位置
 * 化成子问题   走到最终位置的前一个  即左侧或者上方  [m-1-1, n-1] 或者[m-1, n-1-1]
 * 2. 状态转义方程
 * f(m,n) = f(m-1, n) + f(m, n-1)
 * 3. 初始状态
 * f(0,0)=1
 * 4. 计算顺序
 * f(0,0) f(0,1) f(1,0) ...
 * 原则：计算f(m,n)时,f(m-1, n) f(m, n-1)已经算出
 */
class Solution0062 {
    public int uniquePaths(int m, int n) {
        // 存储状态
        int dp[][] = new int[m][n];

        // 状态转移方程
        // dp[i][j] = dp[i-1][j] + dp[i][j-1]
        // 计算顺序
        // dp[0,0], dp[0,1] dp[1,1]
        // dp[m][n]计算时,dp[m-1][n]  dp[m][n-1]都已算出
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 初始状态
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                }
                if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                }
                if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
