package com.shihaiyang.leetcodes;
// 0322. 零钱兑换.[典型动态规划,最值类].
public class Leetcode0322 {
    public static void main(String[] args) {
        Solution0322 solution0322 = new Solution0322();
        int coinChange = solution0322.coinChange(new int[]{2}, 4);
        System.out.println(coinChange);
    }
}
/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 */

/**
 * 动态规划
 * 1. 确定状态(找最后一步和化成子问题)
 *  最后一步就是假定是有最优的一个解,即存在最少的k个硬币使得面值为amount.
 *  化成子问题就是递推到1,都有最优解.即存在最少的k-1个硬币使得面值为amount-一个coins(1或2或5)
 *
 * 2. 转移方程(确定下方程式)
 *  加入有1,2,5三个硬币, 需要11面值
 *  f(11)=min(f(11-1)+1, f(11-2)+1, f(11-5)+1)
 *  f(11-5)+1 指最后一枚硬币用5,那么11-5是 k-1枚硬币的最优子解.
 *
 * 3. 初始边界条件. f[0]=0;
 *  f[-1]都设置为不合法的标记值,比如MaxValue或者amount+1  都是不会出现的
 *
 * 4. 计算顺序.从f[1]开始算起。
 *  原则是当算f[n]时,f[n-1],f[n-2],f[n-5]都已经算出来了..所以此题从f[1]开始算,当算f[2]需要f[1]时，f[1]已知.
 */
class Solution0322 {
    public int coinChange(int[] coins, int amount) {
        // 初始化 f[0],f[1],f[2]...f[amount]  amount+1个
        int f[]=new int[amount+1];

        // 初始条件
        f[0]=0;

        // 计算f[1],f[2]...f[amount]
        for(int i=1;i<amount+1;i++){
            // 先假定拼不出来,拼不出来用最大值标识. 从而帮助我们取到能拼出的min
            // 比如 f(4)拼不出,f[4]=MaxValue;f[3]能拼出=1, 我们再拼f[5]时，可以f[3]+1  f[4]+1 我们就知道 min一定是f[4]+1
            f[i] = Integer.MAX_VALUE;

            // f[i] = min(f[i-1]+1, f[i-2]+1, f[i-5]+1)
            for (int j=0;j<coins.length;j++){
                // 防止溢出. f[-1]=MaxValue
                if(i-coins[j] >= 0 && f[i-coins[j]] != Integer.MAX_VALUE){
                    f[i]=Math.min(f[i], f[i-coins[j]]+1);
                }
            }
        }
        if (f[amount] == Integer.MAX_VALUE) {
            return -1;
        }
        return f[amount];
    }
}
class Solution0322Second {
    // 最值类动态规划
    // f[n] = min(f[n-1]+1, f[n-2]+1, f[n-5]+1);
    // 计算f[n]时，f[n-1]已经算出
    public int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount+1];
        int minCoins = amount+1;
        dp[0]=0;
        for(int i=1;i<amount+1;i++){
            dp[i] = amount+1;
            for(int j=0;j<coins.length;j++){
                // 不可能的值占位
                int count = amount+1;
                if(i-coins[j]>=0){
                    count=dp[i-coins[j]]+1;
                }
                dp[i]=Math.min(count, dp[i]);
            }
        }
        return dp[amount]>amount?-1:dp[amount];
    }
}
