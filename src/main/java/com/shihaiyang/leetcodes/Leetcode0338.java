package com.shihaiyang.leetcodes;

import java.util.Arrays;

// 0338. 比特位计数.[动态规划+位运算+与运算1ms].
public class Leetcode0338 {
    public static void main(String[] args) {
        Solution0338 solution0338 = new Solution0338();
        int[] countBits = solution0338.countBits(5);
        System.out.println(Arrays.toString(countBits));
    }
}
/**
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 * 0=0
 * 1=1
 * 2=10
 * 3=11
 * 4=100
 */

/**
 * 动态规划1ms > 99.9%
 * 1. 确认状态
 *      最终结果：  n的二进制中含有1的个数  f(n)返回n中1的个数
 *      化成子问题： 1100中1的个数与110中1的个数相同 ，而1100=10 110=5     1101比110 多一个1
 *      当n为偶数时，最低位为0所以去掉加上对1的个数无影响。所以f(1100)=f(110)  即十进制f(10)=f(5)
 *      当n为奇数时，最低位为1所以去掉最低位会少一个1。而少的就是最低位的1.最低位的1可以通过是否为奇数来判断，即n%2==1即为奇数
 * 2. 状态转移方程
 *      f[n]=f[n/2] + (n%2)    再优化成右移位和与运算   f[n]=f[n>>1]+(n&1)     ^是异或运算  0&1=0  1&1=1
 * 3. 初始状态
 *      f[0]=0;
 * 4. 计算顺序
 *      f[1],f[2]...f[n]
 *      原则：计算f[n]时，f[n/2]已经计算出
 */
class Solution0338 {
    public int[] countBits(int n) {
        int dp[] = new int[n+1];
        dp[0]=0;
        for (int i = 1; i <= n; i++) {
            dp[i]=dp[i>>1] + (i&1);
        }
        return dp;
    }
}
