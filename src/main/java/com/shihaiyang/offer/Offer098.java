package com.shihaiyang.offer;

// Offer II 098. 路径的数目
public class Offer098 {
}

/**
 *
 */
class SolutionOffer098 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= 1) {
                    dp[i][j] += dp[i - 1][j];
                }
                if (j >= 1) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}