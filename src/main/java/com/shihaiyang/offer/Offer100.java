package com.shihaiyang.offer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

// Offer II 100. 三角形中最小路径之和[动态规划2ms]
public class Offer100 {
    SolutionOffer100 solutionOffer100 = new SolutionOffer100();

    @Test
    public void case1() {
        int total = solutionOffer100.minimumTotal(List.of(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3)
        ));
        Assertions.assertEquals(total, 11);
    }
}

/**
 * dp[i]用来代表这一行的第i个位置的最小值
 * 每一个行的第i个的值可能是 tri[i]+dp[i-1] 或者 tri[i]+ dp[i]两种中的min值
 * dp[i] = tri[i]+min(dp[i], dp[i-1]);
 * 每一行的数据只与上一行有关，压缩成一行的数据
 */
class SolutionOffer100 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];

        for (int i = 0; i < triangle.size(); i++) {
            for (int j = triangle.get(i).size() - 1; j >= 0 ; j--) {
                if (j == 0) {
                    dp[0] = dp[0] + triangle.get(i).get(0);
                } else if (j == i) {
                    dp[j] = triangle.get(i).get(j) + dp[j - 1];
                } else {
                    dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j - 1]);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < dp.length; i++) {
            min = Math.min(min, dp[i]);
        }
        return min;
    }
}