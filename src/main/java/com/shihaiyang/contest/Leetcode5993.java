package com.shihaiyang.contest;

import java.util.HashMap;
import java.util.Map;

// 5993. 将找到的值乘以 2[位运算+map 4ms]
public class Leetcode5993 {
    public static void main(String[] args) {
        Solution5993 solution5993 = new Solution5993();
        int finalValue = solution5993.findFinalValue(new int[]{5, 3, 6, 1, 12}, 3);
        System.out.println(finalValue);
    }
}

/**
 *输入：nums = [5,3,6,1,12], original = 3
 * 输出：24
 * 解释：
 * - 3 能在 nums 中找到。3 * 2 = 6 。
 * - 6 能在 nums 中找到。6 * 2 = 12 。
 * - 12 能在 nums 中找到。12 * 2 = 24 。
 * - 24 不能在 nums 中找到。因此，返回 24
 */
class Solution5993 {
    public int findFinalValue(int[] nums, int original) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], 1);
        }
        while (map.containsKey(original)) {
            original = original << 1;
        }
        return original;
    }
}
