package com.shihaiyang.leetcodes;
// 198. 打家劫舍.[奇偶数+动态规划].
public class Leetcode0198 {
    public static void main(String[] args) {
        Solution0198 solution0198 = new Solution0198();
        int rob = solution0198.rob(new int[]{1,2});
        System.out.println(rob);
    }
}

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * [2,1,1,2]  2 2
 * [2,7,9,3,1]  2 9 1
 */

/**
 * 最值类动态规划
 * 1.确定状态
 *  最终结果: 能取得最高金额 n个房间取得最高金额  dp[n]为最大 那么dp[n-1]也是最大  dp[n] = dp[n-2]+nums[i]
 *  化成子问题:  n-2个房间 取得最高金额   约束:不能有相邻的  f(n) = f(n-2) + nums[n]
 * 2.状态转移方程 f(n) = f(n-2) + nums[n]
 * 3.初始状态  f(0) f(1) f(2)
 * 4.计算顺序
 *  f[1],f[2]...f[n]
 *  计算f[n]要先计算f[n-2]
 */
class Solution0198 {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int dp[] = new int[nums.length];

        //初始化 dp[0]=nums[0]  dp[1]就应该比较0 1两个取大值
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // 计算顺序
        // f[2],f[3],f[4]...f[n]
        // 状态转移方程
        // f[i]=f[i-2] + nums[i]
        // f[i]=max(dp[i-1], nums[i]+dp[i-2])
        for(int i=2;i< nums.length;i++){
            dp[i] = Math.max(dp[i-1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length-1];
    }
}