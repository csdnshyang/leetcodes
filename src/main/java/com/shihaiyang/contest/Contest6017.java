package com.shihaiyang.contest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

// 6017. 向数组中追加 K 个整数
public class Contest6017 {
    @Test
    public void testcase1() {
        Solution6017 solution6017 = new Solution6017();
        long minimalKSum = solution6017.minimalKSum(new int[]{1,4,25,10,25}, 2);
        Assertions.assertEquals(minimalKSum, 5);
    }
}
class Solution6017 {
    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 1;
        int index = 0;
        long ret = 0;
        while (k > 0) {
            if (nums[index] > ans) {
                ret += (long)ans;
                ans++;
                k--;
            } else if (nums[index] == ans) {
                index++;
            }
        }
        return ret;
    }
}