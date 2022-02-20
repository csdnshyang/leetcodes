package com.shihaiyang.contest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// 6012. 统计各位数字之和为偶数的整数个数[动态规划]
public class Contest6012 {
    @Test
    public void case1() {
        Solution6012 solution6012 = new Solution6012();
        Assertions.assertEquals(14, solution6012.countEven(30));
    }

}
class Solution6012 {
    public int countEven(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i <= num; i++) {
            int sum = 0;
            int k = i;
            while (k > 0) {
                sum += (k % 10);
                k /= 10;
            }
            if ((sum & 1) == 0) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[num];
    }
}
