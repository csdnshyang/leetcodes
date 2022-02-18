package com.shihaiyang.offer;
// Offer II 105. 岛屿的最大面积.[多源DFS 2ms].

/**
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 * 1 <= m, n <= 50
 */
public class Offer105 {
}

/**
 * 多源dfs
 */
class SolutionOffer105 {
    int[] xops = new int[]{1, 0, -1, 0};
    int[] yops = new int[]{0, 1, 0, -1};
    int max = Integer.MIN_VALUE;
    int[][] grid;
    int m, n;
    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    max = Math.max(dfs(i, j), max);
                }
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }

    private int dfs(int i, int j) {
        grid[i][j] = 0;
        int cnt = 1;
        for (int k = 0; k < 4; k++) {
            int x = i + xops[k];
            int y = j + yops[k];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != 0) {
                cnt += dfs(x, y);
            }
        }
        return cnt;
    }
}
