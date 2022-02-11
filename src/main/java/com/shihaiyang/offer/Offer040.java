package com.shihaiyang.offer;

// 剑指 Offer II 040. 矩阵中最大的矩形
// Offer040. 矩阵中最大的矩形.[单调栈 4ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给定一个由 0 和 1 组成的矩阵 matrix ，找出只包含 1 的最大矩形，并返回其面积。
 * 注意：此题 matrix 输入格式为一维 01 字符串数组。
 * 示例 1：
 * 输入：matrix = ["10100","10111","11111","10010"]
 * 输出：6
 * 解释：最大矩形如上图所示。
 */
public class Offer040 {
    SolutionOffer040 solutionOffer040 = new SolutionOffer040();

    @Test
    public void case1() {
        int maxRec = solutionOffer040.maximalRectangle(new String[]{"10100", "10111", "11111", "10010"});
        Assertions.assertEquals(maxRec, 6);
    }
    @Test
    public void case2() {
        int maxRec = solutionOffer040.maximalRectangle(new String[]{});
        Assertions.assertEquals(maxRec, 0);
    }
    @Test
    public void case3() {
        int maxRec = solutionOffer040.maximalRectangle(new String[]{"1"});
        Assertions.assertEquals(maxRec, 1);
    }
    @Test
    public void case4() {
        int maxRec = solutionOffer040.maximalRectangle(new String[]{"00"});
        Assertions.assertEquals(maxRec, 0);
    }
}

/**
 * 单调栈。
 * 思路就是如果有最大的矩阵，说明某一行肯定有一条底边。遍历每一行，看下他的高度。
 * 如果本层是0，就得0。
 * 如果本层=1，那就是1+上层
 * 通过单调栈，计算每一层最大面积。
 * 具体计算过程同039题
 */
class SolutionOffer040 {
    public int maximalRectangle(String[] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int ans = 0;
        int[] arr = new int[matrix[0].length()];
        for (int i = 0; i < matrix.length; i++) {
            char[] chars = matrix[i].toCharArray();
            for (int j = 0; j < matrix[i].length(); j++) {
                if (chars[j] == '0') {
                    arr[j] = 0;
                } else {
                    arr[j] = arr[j] + 1;
                }
            }
            ans = Math.max(ans, maxRec(arr));
        }
        return ans;
    }

    public int maxRec(int[] arr) {
        int ans = 0;
        int top = -1;
        int[] stack = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (top != -1 && arr[stack[top]] > arr[i]) {
                int height = arr[stack[top--]];
                int pre = top == -1 ? -1 : stack[top];
                ans = Math.max(ans, height * (i - pre - 1));
            }
            stack[++top] = i;
        }
        while (top != -1) {
            int height = arr[stack[top--]];
            int pre = top == -1 ? -1 : stack[top];
            ans = Math.max(ans, height * (arr.length - pre - 1));
        }
        return ans;
    }
}
