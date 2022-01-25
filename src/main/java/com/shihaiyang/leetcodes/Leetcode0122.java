package com.shihaiyang.leetcodes;
// 0122. 买卖股票的最佳时机 II.[greedy1ms dp2ms]
public class Leetcode0122 {
    public static void main(String[] args) {
        Solution0122Greedy solution0122 = new Solution0122Greedy();
        int maxProfit = solution0122.maxProfit(new int[]{7,1,5,3,6,4});
        System.out.println(maxProfit);
    }
}

/**
 * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 输入: prices = [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 */

/**
 * 动态规划最值类
 * 1. 确认状态
 *      最终结果   持有股票，不持有股票。如果后一天比前一天低，那就前一天卖，后一天卖。
 *      化成子问题  have持有  noHave不持有
 *      低买高卖 如果明天更低，今天就卖，明天就买。
 *      如果今天买了，明天更低，就按明天的买。
 *      买 have=-price  noHave=0
 *      卖 have=0 noHave=price-price0
 * 2. 状态转移方程
 *      持有状态变更：不持有变持有(noHave[i-1]-prices[i])，持有继续持有(have[i-1]) 两个转移状态取一个大的   1 5
 *      不持有状态变更：持有变不持有(have[i-1]+prices[i])，不持有继续不持有(noHave[i-1])  两个转移状态取一个大的 7 1
 *      have[i]=max(have[i-1], noHave[i-1]-prices[i])
 *      noHave[i]
 * 3. 初始状态
 *      have[0]=-price[0]
 *      noHave[0]=0;
 * 4. 计算状态
 */
class Solution0122 {
    public int maxProfit(int[] prices) {
        int have[] = new int[prices.length];
        int noHave[] = new int[prices.length];

        have[0] = -prices[0];
        noHave[0] = 0;

        for (int i = 1; i < prices.length; i++) {
            have[i] = Math.max(have[i - 1], noHave[i - 1] - prices[i]);
            noHave[i] = Math.max(noHave[i - 1], have[i - 1] + prices[i]);
        }
        return noHave[prices.length - 1];
    }
}

/**
 * 贪心算法
 * 只要今天比昨天高，就吃利润
 * 只要今天比昨天低，就不动。
 */
class Solution0122Greedy {
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0) {
                max += prices[i] - prices[i - 1];
            }
        }
        return max;
    }
}
