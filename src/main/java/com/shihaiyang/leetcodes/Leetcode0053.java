package com.shihaiyang.leetcodes;
// 53. 最大子数组和.[最值类动态规划].
public class Leetcode0053 {
}

/**
 * 这道题理解题意还挺重要的...
 * 要求连续的子数组的最大和。必须要连续的
 * 最值类动态规划
 * 1.确定状态
 *  最终结果  最大的连续子串和   f(i) = f(i-1)+nums[i]  如果求i的时候，前面的子串和<0，其实可以舍去.
 *  化成子问题  求f(i-1)的最大和
 * 2.状态转换方程    f(i) = f(i-1)+nums[i]   max=max(f(i-1)+nums[i], nums[i]);
 * 3.初始状态  f[0]=nums[0];
 * 4.计算顺序   从左到右.
 *  f[1],f[2],f[3]...f[n]
 *  原则：计算f[i]，保证f[i-1]已经计算出来
 */
class Solution0053 {
    public int maxSubArray(int[] nums) {
        int f[] = new int[nums.length];
        // 初始状态
        f[0]=nums[0];
        int max= f[0];

        // 计算顺序
        // f[0],f[1],f[2]...f[i]
        // 转换方程
        // f[i]=max(f[i-1]+nums[i], nums[i]);
        for(int i=1;i<nums.length;i++){
            f[i]=Math.max(f[i-1]+nums[i], nums[i]);
            max = Math.max(f[i], max);
        }
        return max;
    }
}
