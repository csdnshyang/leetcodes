package com.shihaiyang.offer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//Offer II 102. 加减的目标值[回溯566ms 动态规划].???
public class Offer102 {
    SolutionOffer102 solutionOffer102 = new SolutionOffer102();
    @Test
    public void case1() {
        int targetSumWays = solutionOffer102.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
        Assertions.assertEquals(targetSumWays, 5);
    }
}

/**
 * 回溯。 566ms
 */
class SolutionOffer102 {
    int ret = 0;
    public int findTargetSumWays(int[] nums, int target) {
        backTrace(nums, target, 0);
        return ret;
    }

    private void backTrace(int[] nums, int target, int index) {
        if (index == nums.length) {
            if (target == 0) {
                ret++;
            }
            return;
        }
        backTrace(nums, target + nums[index], index + 1);
        backTrace(nums, target - nums[index], index + 1);
    }
}