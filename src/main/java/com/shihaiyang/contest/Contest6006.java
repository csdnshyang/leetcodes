package com.shihaiyang.contest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
// 6006. 拿出最少数目的魔法豆.[排序+Long+最大直方图 36ms].
public class Contest6006 {
    Solution6006 solution6006 = new Solution6006();

    @Test
    public void case1() {
        long minimumRemoval = solution6006.minimumRemoval(new int[]{4, 1, 6, 5});
        Assertions.assertEquals(minimumRemoval, 4);
    }
    @Test
    public void case2() {
        long minimumRemoval = solution6006.minimumRemoval(new int[]{2,10,3,2});
        Assertions.assertEquals(minimumRemoval, 7);
    }
}

/**
 * 排序+最大直方图
 */
class Solution6006 {
    public long minimumRemoval(int[] beans) {
        if (beans.length == 1) {
            return 0;
        }
        Arrays.sort(beans);
        long ans = Long.MAX_VALUE;
        long sum = 0;
        for (int bean : beans) {
            sum += bean;
        }
        for (int i = 0; i < beans.length; i++) {
            long out = sum - (long) (beans.length - i) * beans[i];
            ans = Math.min(ans, out);
        }
        return ans;
    }
}
