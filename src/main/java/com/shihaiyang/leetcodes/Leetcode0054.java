package com.shihaiyang.leetcodes;
// 0054. 螺旋矩阵[伪dfs 0ms 100%].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * -100 <= matrix[i][j] <= 100
 */
public class Leetcode0054 {
    Solution0054 solution0054 = new Solution0054();

    @Test
    public void case1() {
        int[][] ints = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };
        List<Integer> integers = solution0054.spiralOrder(ints);
        Assertions.assertTrue(integers.equals(List.of(1, 2, 3, 6, 9, 8, 7, 4, 5)));
    }

    @Test
    public void case2() {
        int[][] ints = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
        };
        List<Integer> integers = solution0054.spiralOrder(ints);
        Assertions.assertTrue(integers.equals(List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7)));
    }
}

/**
 * 右下左上右
 */
class Solution0054 {
    int[] xops = new int[]{0,1,0,-1};
    int[] yops = new int[]{1,0,-1,0};
    int m,n;
    int[][] grid;
    public List<Integer> spiralOrder(int[][] matrix) {
        grid = matrix;
        m=matrix.length;
        n = matrix[0].length;
        List<Integer> ret = new ArrayList<>(m * n);
        dfs(0, -1, 0, ret);
        return ret;
    }
    // 先计算，如果计算通过，就加入列表，并设置已扫描；
    // 如果计算不通过，更换方向，再计算，再加入列表；
    public void dfs(int i, int j, int index, List<Integer> ret) {
        int x = i + xops[index % 4];
        int y = j + yops[index % 4];
        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != 101) {
            ret.add(grid[x][y]);
            grid[x][y] = 101;
            dfs(x, y, index, ret);
        } else {
            if (ret.size() == m * n) {
                return;
            }
            dfs(i, j, index + 1, ret);
        }
    }
}