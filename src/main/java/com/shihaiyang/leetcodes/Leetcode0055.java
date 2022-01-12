package com.shihaiyang.leetcodes;
// 0055. 跳跃游戏.[存在类动态规划].
// https://leetcode-cn.com/problems/jump-game/solution/0055-tiao-yue-you-xi-cun-zai-lei-dong-ta-am8r/
public class Leetcode0055 {
    public static void main(String[] args) {
        Solution0055 solution0055 = new Solution0055();
        boolean canJump = solution0055.canJump(new int[]{3,2,1,0,4});
        System.out.println(canJump);
    }
}

/**
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标
 *
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 */

/**
 * 存在类动态规划
 * 1. 确定状态
 *      最终结果：能从i跳到n;  每一步记录能跳到的最大距离.
 *      化成子方程: 能从i跳到n-1。能跳到n一定能跳到n-1。  换句话说,判断能否到f[i]时，即判断f[i-1]>=i是否成立，成立则可跳到.
 * 2. 状态转移方程  f[n] = if(f[n-1] >= n) f[n]= max(i+nums[i], f[n-1])
 * 3. 最初状态  f[0]=nums[0];  // nums[0]=2，f[0]能往后跳2步，最远f[2].
 * 4. 计算顺序
 *      f[1],f[2],f[3]...f[n]
 *      原则:计算f[n]时，f[n-1]已经算出
 */

/**
 * 优化了下空间复杂度。只需要一个最远距离即可.
 */
class Solution0055 {
    public boolean canJump(int[] nums) {
        // O(n)应该可以优化为O(1)
//        int dp[] = new int[nums.length];
        // 第一步初始能跳最远处
//        dp[0] = nums[0];
        // 空间复杂度优化为 O(1)
        int max = nums[0];

        // 计算顺序  f[1],f[2]...f[n]
        // 状态转换方程  f(i) 返回从第i步能跳到的最远处. 并且满足 f(i-1) >= i, 即f(i-1)应该能跳到i.
        // f[n] = min(f[n-1], nums[i]+i)
        for (int i = 1; i < nums.length; i++) {
            if (max >= i) {
                max = Math.max(max, nums[i] + i);
            } else {
                return false;
            }
        }
        return true;
    }
}
