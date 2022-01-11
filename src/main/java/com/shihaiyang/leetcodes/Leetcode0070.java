package com.shihaiyang.leetcodes;
// 70. 爬楼梯.[计数类动态规划]
public class Leetcode0070 {
    public static void main(String[] args) {
        Solution0070 solution0070 = new Solution0070();
        int climbStairs = solution0070.climbStairs(4);
        System.out.println(climbStairs);
    }
}
/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 */

/**
 * 计数类动态规划
 * 1.确认状态
 *  最终结果   有k种方法到达n个台阶  f(n)=k
 *  化成子问题   k-1 种方法能到达  n-1台阶   n-1台阶 1  n-2台阶  1
 *  f(n-1)  f(n-2)
 * 2.状态转移方程  f[n] = f[n-2] + f[n-1]
 * 3.初始状态  f[1]=1,f[2]=2;
 * 4.计算顺序  从小开始，计算f[n]时，f[n-1] f[n-2]已经计算出。
 *  f[1],f[2],f[3]...f[n]
 */
class Solution0070 {

    public int climbStairs(int n) {
        // 存放状态
        int f[] = new int[n+1];
        // 初始化状态
        f[1]=1;
        f[2]=2;
        // 计算顺序
        // f[2],f[3],f[4]...f[n]
        // 状态转换方程
        // f[n] = f[n-2] + f[n-1]
        for (int i=3;i<n+1;i++){
            f[i]=f[i-1]+f[i-2];
        }
        return f[n];
    }
}

