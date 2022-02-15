package com.shihaiyang.daily;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

// 1380. 矩阵中的幸运数[模拟 1ms].

/**
 * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。
 * 请你按 任意 顺序返回矩阵中的所有幸运数。
 * <p>
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 * 在同一行的所有元素中最小
 * 在同一列的所有元素中最大
 * <p>
 * 示例 1：
 * 输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
 * 输出：[15]
 * 解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * 示例 2：
 * 输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 * 输出：[12]
 * 解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * 示例 3：
 * 输入：matrix = [[7,8],[1,2]]
 * 输出：[7]
 * 1 <= n, m <= 50
 * 1 <= matrix[i][j] <= 10^5
 */
public class Leetcode1380 {
    Solution1380 solution1380 = new Solution1380();

    @Test
    public void case1() {
        int[][] matrix = new int[][]{
                {3,7,8},
                {9,11,13},
                {15,16,17},
        };
        Assertions.assertTrue(solution1380.luckyNumbers(matrix).equals(List.of(15)));
    }
    @Test
    public void case2() {
        int[][] matrix = new int[][]{
                {1,10,4,2},
                {9,3,8,7},
                {15,16,17,12},
        };
        Assertions.assertTrue(solution1380.luckyNumbers(matrix).equals(List.of(12)));
    }
    @Test
    public void case3() {
        int[][] matrix = new int[][]{
                {7,8},
                {1,2},
        };
        Assertions.assertTrue(solution1380.luckyNumbers(matrix).equals(List.of(7)));
    }
    @Test
    public void case4() {
        int[][] matrix = new int[][]{
                {7},
        };
        Assertions.assertTrue(solution1380.luckyNumbers(matrix).equals(List.of(7)));
    }
}

class Solution1380 {
    public List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> ret = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        int[] rowSmall = new int[n];
        for (int i = 0; i < m; i++) {
            int min = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < matrix[i][min]) {
                    min = j;
                }
            }
            // 把每一行最小值的对应列找出。如果有冲突的列，就选大的列。
            // 如果第一行第二列最小，第二行第二列最小  那么肯定有一行不是幸运数。列取大值，最小值更小的就不是幸运数
            if (matrix[i][min] > rowSmall[min]) {
                rowSmall[min] = matrix[i][min];
            }
        }
        // 列中设置了值的话，才有可能是幸运数
        for (int i = 0; i < rowSmall.length; i++) {
            if (rowSmall[i] != 0) {
                boolean flag = true;
                for (int j = 0; j < m; j++) {
                    if (matrix[j][i] > rowSmall[i]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    ret.add(rowSmall[i]);
                }
            }
        }
        return ret;
    }
}