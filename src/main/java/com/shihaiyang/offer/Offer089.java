package com.shihaiyang.offer;

// Offer II 089. 房屋偷盗[动态规划 0ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响小偷偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 示例 1：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * 输入：nums = [2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
public class Offer089 {
    SolutionOffer089 solutionOffer089 = new SolutionOffer089();

    @Test
    public void case1() {
        int rob = solutionOffer089.rob(new int[]{1, 2, 3, 1});
        Assertions.assertEquals(rob, 4);
    }
    @Test
    public void case2() {
        int rob = solutionOffer089.rob(new int[]{2,7,9,3,1});
        Assertions.assertEquals(rob, 12);
    }
}

/**
 * 动态规划最值类
 * 1. 确认状态  dp[i] 表示i的最大金额
 *      最终结果     要i能取得最大金额，可以是偷i和i-2的位置的最大金额，或者是i-1的最大金额。在这两个里去最大。
 *      化成子问题   计算i就是计算i-2 i-1两个的最大金额
 * 2. 状态转移方程
 *      i的最值就是  dp[i] = max(dp[i]+dp[i-2], dp[i-1])
 * 3. 初始状态   dp[0] = nums[0]  dp[1] = nums[1]
 * 4. 计算顺序  [0,1...n]
 *      原则：计算n时，n-2，n-1都已经算出。
 *      1 <= nums.length <= 100
 */
class SolutionOffer089 {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];

        // 初始化状态
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }
}
