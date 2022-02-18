package com.shihaiyang.daily;
// 0688. 骑士在棋盘上的概率.[dfs+记忆化搜索 4ms/动态规划7ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。
 * 行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 * 骑士继续移动，直到它走了 k 步或离开了棋盘。
 * 返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
 * 示例 1：
 * 输入: n = 3, k = 2, row = 0, column = 0
 * 输出: 0.0625
 * 解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
 * 在每一个位置上，也有两种移动可以让骑士留在棋盘上。
 * 骑士留在棋盘上的总概率是0.0625。
 * 示例 2：
 * 输入: n = 1, k = 0, row = 0, column = 0
 * 输出: 1.00000
 */
public class Leetcode0688 {
    Solution0688Dp solution0688 = new Solution0688Dp();

    @Test
    public void case1() {
        double probability = solution0688.knightProbability(3, 2, 0, 0);
        Assertions.assertEquals(probability, 0.0625);
    }

    @Test
    public void case2() {
        double probability = solution0688.knightProbability(1, 0, 0, 0);
        Assertions.assertEquals(probability, 1.00000);
    }
    @Test
    public void case3() {
        double probability = solution0688.knightProbability(8, 30, 6, 4);
        Assertions.assertEquals(probability, 0.00019);
    }
}

/**
 * 计数类动态规划
 * 1. 确认状态
 *      最终状态   k步之后落在棋盘
 *      化成子问题   8种 k-1步落在棋盘的概率之和就是k步落在棋盘的概率
 * 2. 状态转移方程  k[i][j][k] = m*(k[i][j][k-1]/8)   8种情况中只有落在棋盘中的情况才有1/8的概率  所以有m个落在棋盘，就是m/8的概率
 * 3. 初始状态  k[i][j][0] = 1  0步落在棋盘的i，j位置，概率为1.
 * 4. 计算顺序  从k[i][j][0]  k[i][j][1] 开始计算。
 *      原则，计算k步时，k-1步已经算出。
 */
class Solution0688Dp {
    int n;
    int[] xops = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
    int[] yops = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};

    public double knightProbability(int n, int k, int row, int column) {
        this.n = n;
        double[][][] memo = new double[n][n][k + 1];
        // 初始化
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j][0] = 1;
            }
        }

        for (int kk = 1; kk <= k; kk++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int l = 0; l < 8; l++) {
                        int x = i + xops[l];
                        int y = j + yops[l];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            memo[i][j][kk] += memo[x][y][kk-1] / 8.0;
                        }
                    }
                }
            }
        }
        return memo[row][column][k];
    }
}

/**
 * 统计走完 k步所有在棋盘里的总数统计  / 8^k次
 * 暴力超时
 * 记忆化搜索：第k步重复走到nums[i][j]的位置的话，就直接返回。
 * ===
 * 能不能算总步数，那样8的k次方很难算。可能有30次方...所以每次跳跃都是 1/8概率
 */
class Solution0688 {
    int n;
    int[] xops = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
    int[] yops = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};

    public double knightProbability(int n, int k, int row, int column) {
        this.n = n;
        double[][][] memo = new double[n][n][k + 1];
        return dfs(k, row, column, memo);
    }

    public double dfs(int k, int i, int j, double[][][] memo) {
        if (k == 0) {
            return 1;
        }
        // 记忆化搜索，如果剩余k步在i，j已经走过，直接返回。
        if (memo[i][j][k] != 0) {
            return memo[i][j][k];
        }

        double cnt = 0;
        for (int l = 0; l < 8; l++) {
            int x = i + xops[l];
            int y = j + yops[l];
            if (x >= 0 && x < n && y >= 0 && y < n) {
                cnt += dfs(k - 1, x, y, memo) / 8.0;
            }
        }
        // 记忆化存储  第k步走到i,j的结果存储
        memo[i][j][k] = cnt;
        return cnt;
    }
}
