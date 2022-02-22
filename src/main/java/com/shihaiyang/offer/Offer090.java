package com.shihaiyang.offer;

// Offer II 090. 环形房屋偷盗[动态规划+分两次计算 0ms]

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 一个专业的小偷，计划偷窃一个环形街道上沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 */
public class Offer090 {
    SolutionOffer090 solutionOffer090 = new SolutionOffer090();

    @Test
    public void case1() {
        int rob = solutionOffer090.rob(new int[]{2, 3, 2});
        Assertions.assertEquals(rob, 3);
    }
    @Test
    public void case2() {
        int rob = solutionOffer090.rob(new int[]{1,2,3,1});
        Assertions.assertEquals(rob, 4);
    }
    @Test
    public void case3() {
        int rob = solutionOffer090.rob(new int[]{0});
        Assertions.assertEquals(rob, 0);
    }
}

/**
 * 1. 确认状态  dp[i] 盗窃i的最大值
 *      最终结果 盗窃到最后最大的值。
 *      化成子问题  dp[n] = dp[n-2]+nums[n], dp[n-1]
 *      有一个特殊的，最后一间房子是跟第一个挨着。最后一个要考虑的是不取第一个，或者不取最后一个的最大值。
 *      要么有第一个，没最后一个。从0到n-2
 *      要么有最后一个，没第一个。从1到n-1  最后比较n-2,n-1的最大值
 * 2. 状态转移
 *      dp[n] = max(dp[n-2]+nums[n], dp[n-1])
 * 3. 初始状态
 *      dp[0]
 * 4. 计算顺序
 *      0..n
 *      1 <= nums.length <= 100
 */
class SolutionOffer090 {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // 0..n-2
        int[] dpfirst = new int[nums.length - 1];
        // 1..n-1
        int[] dplast = new int[nums.length];
        dpfirst[0] = nums[0];
        dplast[0] = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            if (i == 1) {
                dpfirst[i] = Math.max(dpfirst[i - 1], nums[i]);
            } else {
                dpfirst[i] = Math.max(dpfirst[i - 1], nums[i] + dpfirst[i - 2]);
            }
        }
        for (int i = 1; i < nums.length; i++) {
            if (i == 1) {
                dplast[i] = nums[i];
            } else {
                dplast[i] = Math.max(dplast[i - 1], nums[i] + dplast[i - 2]);
            }
        }
        return Math.max(dpfirst[nums.length - 2], dplast[nums.length - 1]);
    }
}
