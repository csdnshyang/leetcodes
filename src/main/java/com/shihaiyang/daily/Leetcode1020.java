package com.shihaiyang.daily;

// 1020. 飞地的数量.[DFS 5ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 * 输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * 输出：3
 * 解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 * 输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * 输出：0
 * 解释：所有 1 都在边界上或可以到达边界。
 * grid[i][j] 的值为 0 或 1
 */
public class Leetcode1020 {
    Solution1020 solution1020 = new Solution1020();

    @Test
    public void case1() {
        int[][] grid = new int[][]{
                {0,0,0,0},
                {1,0,1,0},
                {0,1,1,0},
                {0,0,0,0},
        };
        int numEnclaves = solution1020.numEnclaves(grid);
        Assertions.assertEquals(numEnclaves, 3);
    }
    @Test
    public void case2() {
        int[][] grid = new int[][]{
                {0,1,1,0},
                {0,0,1,0},
                {0,0,1,0},
                {0,0,0,0},
        };
        int numEnclaves = solution1020.numEnclaves(grid);
        Assertions.assertEquals(numEnclaves, 0);
    }
    @Test
    public void case3() {
        int[][] grid = new int[][]{
                {0,1,1,0},
        };
        int numEnclaves = solution1020.numEnclaves(grid);
        Assertions.assertEquals(numEnclaves, 0);
    }
    @Test
    public void case4() {
        int[][] grid = new int[][]{};
        int numEnclaves = solution1020.numEnclaves(grid);
        Assertions.assertEquals(numEnclaves, 0);
    }
}

/**
 * DFS
 * 每个边缘点进行DFS，每个点设置为0.
 * 在遍历一下内部的点，统计非0的个数
 */
class Solution1020 {
    int[] xop = new int[]{-1, 1, 0, 0};
    int[] yop = new int[]{0, 0, -1, 1};

    public int numEnclaves(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if ((i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length - 1) && grid[i][j] == 1) {
                    dfs(grid, i, j);
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[1].length - 1; j++) {
                if (grid[i][j] == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private void dfs(int[][] grid, int i, int j) {
        grid[i][j] = 0;
        for (int k = 0; k < 4; k++) {
            int x = i + xop[k];
            int y = j + yop[k];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                dfs(grid, x, y);
            }
        }
    }
}