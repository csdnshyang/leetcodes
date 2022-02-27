package com.shihaiyang.hot100;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// 0034. 在排序数组中查找元素的第一个和最后一个位置.[二分查找 0ms].
/*
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
如果数组中不存在目标值 target，返回 [-1, -1]。
进阶：
你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
示例 1：

输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
示例 2：

输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
示例 3：

输入：nums = [], target = 0
输出：[-1,-1]
 */
public class Leetcode0034 {
    Solution0034 solution0034 = new Solution0034();

    @Test
    public void testcase1() {
        int[] range = solution0034.searchRange(new int[]{5,7,7,8,8,10}, 8);
        Assertions.assertArrayEquals(range, new int[]{3,4});
    }
    @Test
    public void testcase2() {
        int[] range = solution0034.searchRange(new int[]{5,7,7,8,8,10}, 6);
        Assertions.assertArrayEquals(range, new int[]{-1,-1});
    }
    @Test
    public void testcase3() {
        int[] range = solution0034.searchRange(new int[]{}, 0);
        Assertions.assertArrayEquals(range, new int[]{-1,-1});
    }
    @Test
    public void testcase4() {
        int[] range = solution0034.searchRange(new int[]{1}, 1);
        Assertions.assertArrayEquals(range, new int[]{0,0});
    }
}
/*
二分查找模板。
先找到target的一个位置
然后左右延伸一下。
 */
class Solution0034 {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                int i = mid, j=mid;
                while (i >= 0 && nums[i] == target) {
                    i--;
                }
                while (j<nums.length && nums[j] == target){
                    j++;
                }
                return new int[]{i+1, j-1};
            }
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return new int[]{-1, -1};
    }
}
