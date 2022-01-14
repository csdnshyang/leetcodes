package com.shihaiyang.leetcodes;
// 0121. 买卖股票的最佳时机.[最值类动态规划,记录之前买卖的利润].
public class Leetcode0121 {
    public static void main(String[] args) {
        Solution0121 solution0121 = new Solution0121();
        int maxProfit = solution0121.maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        System.out.println(maxProfit);
    }
}

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */

/**
 * 动态规划 最值类.
 * 1. 确认状态
 *  找最后一步  就是股票卖出产生的利润最大化.i天股票卖出产生的利润取决于 (i天股价-i-1持有的股票). 持有股票取决于 (i天股价和i-1天持有的股票)
 *  化成子问题 i天的利润大于i-1天利润
 * 2. 状态转移方程 确定方程
 *      have[i]=max(have[i-1], -prices[i]);
 *      noHave[i]=max(noHave[i-1], have[i-1]+prices[i])
 * 3. 初始边界条件   第0天的持有、不持有利润0
 *      have[0]
 *      noHave[0]=0
 * 4. 执行顺序
 *      从第一天开始计算持有、不持有的利润。直到最后一天
 *      noHave[1],noHave[2],noHave[3]...
 *      原则 noHave[i]的时候 noHave[i-1]已经算出.
 */
class Solution0121 {
    public int maxProfit(int[] prices) {
        // 存储状态
        int days = prices.length;
        int have[] = new int[days];
        int noHave[] = new int[days];

        // 初始化
        have[0]=-prices[0];
        noHave[0]=0;

        // 持有股票因为是放-值，也是取大值，跟放正值取小值一样的. min(5,1)=1 max(-5,-1)=-1
        // have[i]=max(have[i-1], prices[i]);
        // 不持有股票就是卖出产生利润. 比较i-1持有变不持有和i-1本身不持有两种情况的最大值
        // noHave[i]=max(have[i-1]+prices[i], noHave[i-1]);
        // noHave[1], noHave[2]...noHave[i]
        for(int i=1;i<days;i++){
            have[i] = Math.max(-prices[i], have[i - 1]);
            noHave[i] = Math.max(have[i - 1] + prices[i], noHave[i - 1]);
        }
        return noHave[days - 1];
    }
}