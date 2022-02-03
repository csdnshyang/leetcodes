package com.shihaiyang.daily;

import java.util.ArrayList;
import java.util.List;

// 1414. 和为 K 的最少斐波那契数字数目.[动态规划+贪心 2ms].
public class Leetcode1414 {
    public static void main(String[] args) {
        Solution1414 solution1414 = new Solution1414();
        int minFibonacciNumbers = solution1414.findMinFibonacciNumbers(8852252);
        System.out.println(minFibonacciNumbers);
    }
}

/**
 * 给你数字 k ，请你返回和为 k 的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。
 * 斐波那契数字定义为：
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 ， 其中 n > 2 。
 * 数据保证对于给定的 k ，一定能找到可行解。
 * 输入：k = 7
 * 输出：2
 * 解释：斐波那契数字为：1，1，2，3，5，8，13，……
 * 对于 k = 7 ，我们可以得到 2 + 5 = 7 。
 */

/**
 * 动态规划
 * 一个数组先存储起来斐波那契数字
 * 再来一个数组来存每个数字可用的最少k
 * 计数类动态规划
 * 跟硬币是一个题
 * 比如 1 1 2 3 5
 * minFin = min(5+minFin[i-5], 3+minFin[i-3])
 * 动态规划能算出来，就是有点慢，可选择的硬币有点多。
 *
 * 贪心吧
 * 贪心也可以用
 */
class Solution1414 {
    public int findMinFibonacciNumbers(int k) {
        // 初始化斐波那契数组
        if (k == 1) {
            return 1;
        }
        List<Integer> fib = new ArrayList<>();
        fib.add(0);
        fib.add(1);
        fib.add(1);
        int i = 2;
        while (fib.get(i) < k) {
            i++;
            fib.add(fib.get(i - 1) + fib.get(i - 2));
        }
        // 贪心、动态规划都可
        int ans = 0;
        int index = fib.size() - 1;
        while (k > 0) {
            if (k >= fib.get(index)) {
                k -= fib.get(index);
                ans++;
            } else {
                index--;
            }
        }
        return ans;
    }
}
