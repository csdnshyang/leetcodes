package com.shihaiyang.daily;

import java.util.ArrayList;
import java.util.List;

// 1447. 最简分数.[数学:欧几里得求最大公约数+暴力 18ms].
public class Leetcode1447 {
    public static void main(String[] args) {
        Solution1447 solution1447 = new Solution1447();
        int gcd = solution1447.gcd(175, 65);
        System.out.println(gcd);
        List<String> strings = solution1447.simplifiedFractions(4);
        strings.stream().forEach(s -> System.out.println(s));
    }
}

/**
 * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
 * 示例 1：
 * 输入：n = 2
 * 输出：["1/2"]
 * 解释："1/2" 是唯一一个分母小于等于 2 的最简分数。
 * 示例 2：
 * 输入：n = 3
 * 输出：["1/2","1/3","2/3"]
 * 示例 3：
 * 输入：n = 4
 * 输出：["1/2","1/3","1/4","2/3","3/4"]
 * 解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
 * 示例 4：
 * 输入：n = 1
 * 输出：[]
 * 提示：
 * 1 <= n <= 100
 */

/**
 * 欧几里得定理：求两个数的最大公约数。
 * 如果两个数的最大公约数为1，说明是最简分数。
 * 如果两个数的最大公约数!=1,说明能约分。
 * 比如:gcd(175, 65)
 * 175-65-65 = 45
 * 65-45=20
 * 45-20-20=5
 * 20-5-5-5-5=0
 * 证明最大公约数是5.
 */
class Solution1447 {
    public List<String> simplifiedFractions(int n) {
        List<String> ret = new ArrayList<>();
        // 1..n
        for (int i = 1; i <= n; i++) {
            // 2..m
            for (int j = i + 1; j <= n; j++) {
                if (gcd(j, i) == 1) {
                    ret.add(i + "/" + j);
                }
            }
        }
        return ret;
    }

    /**
     * 如果最大公约数为1，说明是最简分数
     */
    public int gcd(int a, int b) {
        int c = b;
        b = a % b;
        if (b == 0) {
            return c;
        }
        return gcd(c, b);
    }
}
