package com.shihaiyang.leetcodes;

import java.util.Arrays;
import java.util.List;

// 0119. 杨辉三角 II.[计数类动态规划+空间优化 1ms].
public class Leetcode0119 {
    public static void main(String[] args) {
        Solution0119 solution0119 = new Solution0119();
        List<Integer> row = solution0119.getRow(5);
        row.stream().forEach(integer -> System.out.print(integer + ","));
        System.out.println();
    }
}
/**
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * 输入: rowIndex = 3  // 从0行开始
 * 输出: [1,3,3,1]
 *    1     0
 *   1 1    1
 *  1 2 1   2
 * 1 3 3 1
 */

/**
 * 计数类动态规划。
 * 1. 确认状态
 *      最终结果：某行n的所有元素   是基于n-i行计算得出  // 本题是从0行开始
 *      化成子方程：
 *          每一行的第一个元素1，最后一个元素1，中间元素是上面两个元素之和。n行第i个元素即n-i行的i-1, i两个元素之和
 * 2. 状态转移方程
 *      f[n][0] = 1              //i=0
 *      f[n][f[n-1].length] = 1  //i=n
 *      f[n][1]=f[n-1][0]+f[n-1][1]   // 1<i<n-1
 * 3. 初始状态
 *     f[0]=1;
 * 4. 计算顺序
 *      f[1] f[2] f[3]
 *      原则：f[n]计算时，f[n-1]已经计算出结果
 *  空间优化：因为每一行只需要上一行，所以只需要存储一行元素，每次覆盖上一行即可。
 */
class Solution0119 {
    public List<Integer> getRow(int rowIndex) {
        // 特判
        if (rowIndex == 0) {
            return List.of(1);
        }
        if (rowIndex == 1) {
            return List.of(1,1);
        }
        // 题目从0行开始算
        Integer dp[] = new Integer[rowIndex+1];
        dp[0] = 1;
        // 计算顺序
        // row=1,2,3...
        for (int i = 1; i <= rowIndex; i++) {
            dp[i] = 1;
            // dp[n][i] = dp[n-1][i]+dp[n-1][i-1]
            // 优化空间复杂度后从后开始计算，不影响前值
            // dp[j] = dp[j--]+dp[j];
            int j = i-1;
            while (j > 0) {
                dp[j] = dp[j--]+dp[j];
            }
        }
        return Arrays.asList(dp);
    }
}
