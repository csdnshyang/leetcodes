package com.shihaiyang.contest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// 5996. 统计数组中相等且可以被整除的数对
public class Contest5996 {
    @Test
    public void case1() {
        Solution5996 solution = new Solution5996();
        Assertions.assertEquals(solution.countPairs(new int[]{3, 1, 2, 2, 2, 1, 3}, 2), 4);
    }
}
class Solution5996 {
    public int countPairs(int[] nums, int k) {
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    if ((i * j) % k == 0) {
                        ret++;
                    }
                }
            }
        }
        return ret;
    }
}