package com.shihaiyang.leetcodes;

import java.util.HashMap;
import java.util.Map;

// 0001. 两数之和.[Map空间换时间1ms].
public class Leetcode0001 {
    public static void main(String[] args) {
        Solution0001 solution0001 = new Solution0001();
        int[] twoSum = solution0001.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(twoSum[0] + "," + twoSum[1]);
    }
}

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 */

/**
 * 空间换时间的方式，可以 O(n) 返回结果
 */
class Solution0001 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }
}

