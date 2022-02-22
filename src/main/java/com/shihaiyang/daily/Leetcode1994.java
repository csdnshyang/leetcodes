package com.shihaiyang.daily;

// 1994. 好子集的数目[动态规划].???

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给你一个整数数组 nums 。如果 nums 的一个子集中，所有元素的乘积可以表示为一个或多个 互不相同的质数 的乘积，那么我们称它为 好子集 。
 *
 * 比方说，如果 nums = [1, 2, 3, 4] ：
 * [2, 3] ，[1, 2, 3] 和 [1, 3] 是 好 子集，乘积分别为 6 = 2*3 ，6 = 2*3 和 3 = 3 。
 * [1, 4] 和 [4] 不是 好 子集，因为乘积分别为 4 = 2*2 和 4 = 2*2 。
 * 请你返回 nums 中不同的 好 子集的数目对 109 + 7 取余 的结果。
 *
 * nums 中的 子集 是通过删除 nums 中一些（可能一个都不删除，也可能全部都删除）元素后剩余元素组成的数组。如果两个子集删除的下标不同，那么它们被视为不同的子集。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：6
 * 解释：好子集为：
 * - [1,2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [1,2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
 * - [1,3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * - [2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
 * - [3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * 示例 2：
 *
 * 输入：nums = [4,2,3,15]
 * 输出：5
 * 解释：好子集为：
 * - [2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [2,3]：乘积为 6 ，可以表示为互不相同质数 2 和 3 的乘积。
 * - [2,15]：乘积为 30 ，可以表示为互不相同质数 2，3 和 5 的乘积。
 * - [3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * - [15]：乘积为 15 ，可以表示为互不相同质数 3 和 5 的乘积。
 */
public class Leetcode1994 {
    @Test
    public void case1() {
        Solution1994 solution1994 = new Solution1994();
        int subsets = solution1994.numberOfGoodSubsets(new int[]{1, 2, 3, 4});
        Assertions.assertEquals(subsets, 6);
    }
}

class Solution1994 {
    private final int mod=1000000007;
    public int numberOfGoodSubsets(int[] nums) {
        long res = 0;
        int[] prime = {2,3,5,7,11,13,17,19,23,29};
        // long 防止溢出
        long[] dp = new long[1024];
        // dp数组初始化
        dp[0] = 1;
        int[] count = new int[31];
        for(int num : nums) count[num]++;
        // 遍历nums中的每一个数，除了1
        for(int num = 2; num <= 30; ++num){
            // 当前数不存在，当前数带有平方数 跳过
            if(count[num] == 0 || num % 4 == 0||num % 9 == 0 || num % 25 == 0) continue;

            // 对10个质数做处理，如果当前数能被质数整除，则记录进maskForNum
            int maskForNum = 0;
            for(int i = 0; i < 10; ++i){
                if(num % prime[i] == 0) maskForNum |= ( 1 << i);
            }

            // 遍历每一种状态
            for(int state = 0; state < 1024; ++state){
                // maskForNum中已经存在了其中一个质数，跳过
                if((maskForNum & state) > 0) continue;
                //这里可能会溢出，所以dp数组类型为long
                // 更新当前状态的的好子集个数
                dp[maskForNum|state] = (dp[maskForNum|state] + count[num] * dp[state])%mod;
                 System.out.println( num + " --- " + state + " --- " + dp[state]);
            }
        }
        // dp[0]不算进去
        for(int i = 1; i < 1024; ++i) res = (res + dp[i]) % mod;
        // 有多少个1，最后的结果就乘以2的多少次方
        for(int i = 0; i < count[1]; ++i) res = (res * 2) % mod;
        return (int)res;
    }
}