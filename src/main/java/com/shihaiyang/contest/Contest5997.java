package com.shihaiyang.contest;

import org.junit.jupiter.api.Test;

// 5997. 找到和为给定整数的三个连续整数
public class Contest5997 {
    @Test
    public void case1() {
        Solution5997 solution5997 = new Solution5997();
        long[] longs = solution5997.sumOfThree(33);
    }
}

class Solution5997 {
    public long[] sumOfThree(long num) {
        if (num % 3 != 0) {
            return new long[]{};
        }
        long mid = num / 3;
        return new long[]{mid - 1, mid, mid + 1};
    }
}