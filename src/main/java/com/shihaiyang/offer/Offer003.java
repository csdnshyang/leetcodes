package com.shihaiyang.offer;

import java.util.Arrays;

// 剑指 Offer II 003. 前 n 个数字二进制中 1 的个数
// Offer003. 前 n 个数字二进制中 1 的个数.[动态规划+位运算+逻辑与1ms]. 最终
// https://leetcode-cn.com/problems/w3tCBm/solution/jian-zhi-offer-ii-003-qian-n-ge-shu-zi-e-wkqx/
public class Offer003 {
    public static void main(String[] args) {
        SolutionOffer003 solutionOffer003 = new SolutionOffer003();
        int[] countBits = solutionOffer003.countBits(8);
        System.out.println(Arrays.toString(countBits));;
    }
}

/**
 * 通过位运算加动态规划
 * 1. 确认状态
 *      最后一步：f[n] 中的1的个数
 *      化成子问题  f[n>>2]中的个数
 *      5=110 10=1100  当目标数值为偶数时，n中1的个数跟n/2的1的个数相同  1100/2 = 110 低位都是0，没有1，所以个数也相同
 *      5=110 10=1100  11=1101   如果是奇数，n的个数等于比n/2中1的个数多一个  1101/2=110. 低位是一个1，所以比n/2多一个低位的1
 * 2. 状态转义方程
 *      f[n] = f[n/2]+ n%2    n%2奇数时==1，偶数时==0
 *      f[n] = f[n>>1] + n&1  用右移加与运算来替换除法和取模
 * 3. 初始状态  f[0]=0
 * 4. 计算顺序  f[1],f[2]...f[n]
 *  原则：当计算f[n]是f[n/2]已经算出
 */
class SolutionOffer003 {
    public int[] countBits(int n) {
        int dp[] = new int[n+1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }
}
