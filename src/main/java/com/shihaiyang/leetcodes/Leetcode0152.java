package com.shihaiyang.leetcodes;

// 152. 乘积最大子数组.[最值类动态规划+正负号判断].
public class Leetcode0152 {
}

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 */

/**
 * 最值类动态规划
 * 1. 确定状态
 * 最终结果: f(n) 具有最大乘积
 * 化成子问题： f(n) 一定是f(n-1)*nums[n] 和 nums[n]中的极大值.  即前n-1个如果是<0的, 第n个>0, 那么应该选第n个数才是大的。
 * *** 有一个问题就是乘法有正负号区分. 当出现符号时，最大变成最小值. 所以记录一个最大序列，一个最小序列，当出现负数时，最小序列为最大。最大序列变成最小。
 * 2. 状态转移方程
 * f(n)=max(f(n-1)*nums[n], nums[n]);
 * 3. 初始状态
 * f(0)=nums[0]
 * 4. 计算顺序
 * f[1],f[2],f[3]...f[n]
 * 原则：当计算f(n)时，f(n-1)已经计算出.
 */
class Solution0152 {
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int min[] = new int[nums.length];
        int max[] = new int[nums.length];
        min[0] = nums[0];
        max[0] = nums[0];
        int maxVal= nums[0];

        // 正负号处理
        // 维护两个数组，最大和最小。当遇到负号，最大变最小，最小变最大.
        for (int i = 1; i < nums.length; i++) {
            // 当出现负数, 最大变最小，最小变最大 交换最大最小值
            if (nums[i] < 0) {
                int tmp = min[i - 1];
                min[i - 1] = max[i - 1];
                max[i - 1] = tmp;
            }
            //
            min[i] = Math.min(nums[i], min[i - 1] * nums[i]);
            max[i] = Math.max(nums[i], max[i - 1] * nums[i]);
            maxVal = Math.max(maxVal, max[i]);
        }
        return maxVal;
    }
}
