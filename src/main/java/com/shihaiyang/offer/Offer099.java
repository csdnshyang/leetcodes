package com.shihaiyang.offer;

// Offer II 099. 最小路径之和

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：一个机器人每次只能向下或者向右移动一步。
 * 示例 1：
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 */
//
public class Offer099 {
    SolutionOffer099 solutionOffer099 = new SolutionOffer099();

    @Test
    public void case1() {
        int pathSum = solutionOffer099.minPathSum(new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1},
        });
        Assertions.assertEquals(pathSum, 7);
    }
}

/**
 *
 */
class SolutionOffer099 {
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
                if (i >= 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + grid[i][j]);
                }
                if (j >= 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + grid[i][j]);
                }
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }
}