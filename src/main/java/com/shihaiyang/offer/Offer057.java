package com.shihaiyang.offer;

// Offer II 057. 值和下标之差都在给定的范围内.[有序集合+滑动窗口].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;

/**
 * 给你一个整数数组 nums 和两个整数 k 和 t 。
 * 请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 * 如果存在则返回 true，不存在返回 false。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * 示例 2：
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * 示例 3：
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 */
public class Offer057 {
    SolutionOffer057 solutionOffer057 = new SolutionOffer057();

    @Test
    public void case1() {
        boolean duplicate = solutionOffer057.containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0);
        Assertions.assertTrue(duplicate);
    }
    @Test
    public void case2() {
        boolean duplicate = solutionOffer057.containsNearbyAlmostDuplicate(new int[]{1,0,1,1}, 1, 2);
        Assertions.assertTrue(duplicate);
    }
    @Test
    public void case3() {
        boolean duplicate = solutionOffer057.containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2, 3);
        Assertions.assertTrue(!duplicate);
    }
    @Test
    public void case4() {
        boolean duplicate = solutionOffer057.containsNearbyAlmostDuplicate(new int[]{-2147483648,2147483647}, 1, 1);
        Assertions.assertTrue(!duplicate);
    }
}

class SolutionOffer057 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 基于key排序的
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 判断
            long num = nums[i];
            // 求相减能不能有小于t的值，之前里面肯定么有满足条件的两个数，新加入一个是否能满足，就需与新加的数比较。
            // 所以取新加的数字的最近的左、右节点。
            Long floor = treeSet.floor(num);
            Long ceiling = treeSet.ceiling(num);
            if (floor != null && num - floor <= t) {
                return true;
            }
            if (ceiling != null && ceiling - num <= t) {
                return true;
            }
            treeSet.add(num);
            if (treeSet.size() > k) {
                treeSet.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}