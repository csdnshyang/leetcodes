package com.shihaiyang.offer;
// Offer II 103. 最少的硬币数目.[动态规划 13ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的。
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 输入：coins = [1], amount = 1
 * 输出：1
 * 输入：coins = [1], amount = 2
 * 输出：2
 * 提示：
 * 1 <= coins.length <= 12
 * 0 <= amount <= 104
 */
public class Offer103 {
    SolutionOffer103 solutionOffer103 = new SolutionOffer103();

    @Test
    public void case1() {
        Assertions.assertEquals(3, solutionOffer103.coinChange(new int[]{1, 2, 5}, 11));
        Assertions.assertEquals(6, solutionOffer103.coinChange(new int[]{1, 2, 5}, 26));
    }
    @Test
    public void case2() {
        Assertions.assertEquals(-1, solutionOffer103.coinChange(new int[]{2}, 11));
    }
}

/**
 * 动态规划
 * 计算amount的时候，就计算amount-1的个数。
 * Math.min(dp[amount-coins[i]])
 * 从0开始算。
 * -----
 * 可能出现拼不出来的情况
 * 如果对应的dp[amount-coins[i]] = MaxValue，就跳过。
 */
class SolutionOffer103 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0 && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }
            }
        }
        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[amount];
    }
}