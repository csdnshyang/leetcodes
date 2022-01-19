package com.shihaiyang.daily;

import java.util.HashMap;
import java.util.Map;

// 0219. 存在重复元素 II.[滑动窗口17ms map18ms].
public class Leetcode0219 {
    public static void main(String[] args) {
        Solution0219SlidingWindow solution0219 = new Solution0219SlidingWindow();
        boolean nearbyDuplicate1 = solution0219.containsNearbyDuplicate(new int[]{1,2,3,1}, 3);
        boolean nearbyDuplicate2 = solution0219.containsNearbyDuplicate(new int[]{99,99}, 2);
        boolean nearbyDuplicate3 = solution0219.containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2);
        System.out.println(nearbyDuplicate1+","+nearbyDuplicate2+","+nearbyDuplicate3);
    }
}
/**
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，
 * 满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：nums = [1,2,3,1], k = 3     j-i<=3,i=0,j=321
 * 输出：true
 * 输入：nums = [1,2,3,1,2,3], k = 2
 * 输出：false
 * 0 <= k <= 105
 */

/**
 * 双层循环 借助map
 */
class Solution0219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])
                && i - map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
/**
 * 滑动窗口 判断滑动窗口内是否有重复值
 */
class Solution0219SlidingWindow {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Boolean> map = new HashMap<>();
        int start =0, end=0;
        while (end < nums.length) {
            // k=3, end=3,start=0 end=4
            while (end - start > k) {
                map.remove(nums[start++]);
            }
            if (map.containsKey(nums[end])) {
                return true;
            }
            map.put(nums[end++], true);
        }
        return false;
    }
}
