package com.shihaiyang.offer;

// 剑指 Offer II 013. 二维子矩阵的和
// Offer013. 二维子矩阵的和.[前缀和与计算57ms].
public class Offer013 {
    public static void main(String[] args) {
        int [][] matrix = new int[][]{
                {3,0,1,4,2},
                {5,6,3,2,1},
                {1,2,0,1,5},
                {4,1,0,1,7},
                {1,0,3,0,5}
        };
        NumMatrix obj = new NumMatrix(matrix);
        int param_1 = obj.sumRegion(2,1,4,3);
        System.out.println(param_1);
    }
}
/**
 * 给定一个二维矩阵 matrix，以下类型的多个请求：
 *
 * 计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 * 实现 NumMatrix 类：
 *
 * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
 * int sumRegion(int row1, int col1, int row2, int col2) 返回左上角 (row1, col1) 、右下角 (row2, col2) 的子矩阵的元素总和。
 */

/**
 * 第一版直接一个双层循环，念念有词。这也是值中等难度?!
 * 嗯...超时马上教做人
 * 利用前缀和预结算
 */
class NumMatrix {
    int [][] preSum;
    public NumMatrix(int[][] matrix) {
        if (matrix.length > 0) {
            preSum = new int[matrix.length][matrix[0].length];
        }
        for (int i = 0; i < matrix.length; i++) {
            preSum[i][0] = matrix[i][0];
            for (int j = 1; j < matrix[i].length; j++) {
                preSum[i][j] = preSum[i][j-1] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            if (col1 == 0) {
                sum += preSum[i][col2];
            } else {
                sum += preSum[i][col2] - preSum[i][col1-1];
            }
        }
        return sum;
    }
}