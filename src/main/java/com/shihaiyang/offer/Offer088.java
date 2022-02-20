package com.shihaiyang.offer;
// Offer II 088. 爬楼梯的最少成本.[动态规划0ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
 * 每当爬上一个阶梯都要花费对应的体力值，一旦支付了相应的体力值，就可以选择向上爬一个阶梯或者爬两个阶梯。
 * 请找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
 * 示例 1：
 * 输入：cost = [10, 15, 20]
 * 输出：15
 * 解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
 *  示例 2：
 * 输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出：6
 * 解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
 */
public class Offer088 {
    SolutionOffer088 solutionOffer088 = new SolutionOffer088();

    @Test
    public void case1() {
        int climbingStairs = solutionOffer088.minCostClimbingStairs(new int[]{10, 15, 20});
        Assertions.assertEquals(climbingStairs, 15);
    }
    @Test
    public void case2() {
        int climbingStairs = solutionOffer088.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1});
        Assertions.assertEquals(climbingStairs, 6);
    }
}

/**
 * 1. 确认状态   dp[i] 记录调到i时的最小话费
 *      最终结果   跳到i的花费是 Min(前面两个)  min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2])
 *      化成子问题  i的最少花费就是求跳到i-1和i-2里最少花费的问题
 * 2. 状态转移方程  dp[i]=min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2])
 * 3. 初始状态  dp[0] = 0, dp[1] = 0  可以从第0或者1作为初始。
 * 4. 计算顺序  dp[0,1..n]
 */
class SolutionOffer088 {
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        // 可以从0或者1起跳
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length];
    }
}
