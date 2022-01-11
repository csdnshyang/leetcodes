package com.shihaiyang.leetcodes;
// 64. 最小路径和.[最值+计数类动态规划].
public class Leetcode0064 {
    public static void main(String[] args) {

    }
}
/**
 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 说明：每次只能向下或者向右移动一步。
 */

/**
 * 最值+计数类动态规划
 * 1. 确认状态
 *  最终结果  dp[m,n]最小
 *  化成子问题   其实是求 dp[m-1, n] 和  dp[m, n-1]的极小值  dp[m, n] = min(dp[m-1, n]+nums[m, n] , dp[m,n-1]+nums[m,n])
 * 2. 状态转义方程
 *  dp[m, n] = min(dp[m-1, n]+nums[m, n] , dp[m,n-1]+nums[m,n])
 * 3. 初始状态
 *  dp[0,0] = nums[0,0];
 * 4. 计算顺序
 *  dp[0,0] dp[0,1] dp[0, 2]
 * 原则：计算dp[m,n]时，dp[m-1, n] 和dp[m, n-1]都已算出
 */
class Solution0064 {
    public int minPathSum(int[][] grid) {
        // 特判
        if (grid.length == 1 && grid[0].length == 1) {
            return grid[0][0];
        }
        int m = grid.length, n = grid[0].length;
        // 存储状态
        int dp[][] = new int[m][n];
        int i,j;
        // 状态转移方程
        // dp[m, n] = min(dp[m-1, n]+nums[m, n] , dp[m,n-1]+nums[m,n])
        // 计算顺序
        // dp[0,0] dp[0,1] dp[0, 2]
        // 原则：计算dp[m,n]时，dp[m-1, n] 和dp[m, n-1]都已算出
        for (i=0;i<m;i++){
            for(j=0;j<n;j++){
                // 初始状态
                if (i == 0 && j == 0) {
                    dp[0][0] = grid[0][0];
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                    continue;
                }
                if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                    continue;
                }
                dp[i][j] = Math.min(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
            }
        }
        return dp[m - 1][n - 1];
    }
}
