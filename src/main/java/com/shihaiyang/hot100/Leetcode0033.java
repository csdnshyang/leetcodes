package com.shihaiyang.hot100;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// 0033. 搜索旋转排序数组.[二分查找 0ms].
/*
整数数组 nums 按升序排列，数组中的值 互不相同 。
在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
示例 1：
输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4
示例 2：
输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1
示例 3：
输入：nums = [1], target = 0
输出：-1
 */
public class Leetcode0033 {
    @Test
    public void testCase1() {
        Solution0033 solution0033 = new Solution0033();
        Assertions.assertEquals(4, solution0033.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    }
    @Test
    public void testCase2() {
        Solution0033 solution0033 = new Solution0033();
        Assertions.assertEquals(-1, solution0033.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    }
    @Test
    public void testCase3() {
        Solution0033 solution0033 = new Solution0033();
        Assertions.assertEquals(-1, solution0033.search(new int[]{1}, 0));
    }
    @Test
    public void testCase4() {
        Solution0033 solution0033 = new Solution0033();
        Assertions.assertEquals(-1, solution0033.search(new int[]{2, 1}, 0));
    }
    @Test
    public void testCase5() {
        Solution0033 solution0033 = new Solution0033();
        Assertions.assertEquals(1, solution0033.search(new int[]{2, 0}, 0));
    }
    @Test
    public void testCase6() {
        Solution0033 solution0033 = new Solution0033();
        Assertions.assertEquals(0, solution0033.search(new int[]{2, 0}, 2));
    }
    @Test
    public void testCase7() {
        Solution0033 solution0033 = new Solution0033();
        Assertions.assertEquals(-1, solution0033.search(new int[]{2}, 0));
    }
    @Test
    public void testCase8() {
        Solution0033 solution0033 = new Solution0033();
        Assertions.assertEquals(-1, solution0033.search(new int[]{1}, 2));
    }
    @Test
    public void testCase9() {
        Solution0033 solution0033 = new Solution0033();
        Assertions.assertEquals(1, solution0033.search(new int[]{1,3}, 3));
    }
    @Test
    public void testCase10() {
        Solution0033 solution0033 = new Solution0033();
        Assertions.assertEquals(0, solution0033.search(new int[]{1,3,5}, 1));
    }
}

/*
目标值应该处于左边或者右边。
进入左边或者右边之后，再二分查找即可。
中间的区分位置，有两种情况，前半段丢一个，或者后半段丢一个。
就判断i=len-nums[0], j=len-nums[0]+1
如果i>j 3>4,就是后半段少一个，前半段就是0,j;后半段j+1,len-1
4,5,6,7,0,1,2
len=7, nums[0]=4  len-num=3,4
否则i<j,就是前半段少一个，前半段就是0,i;后半段j,len-1

======== 题意了解有误
不一定是缺少几个。
应该就用二分查找去查。
根据二分查找的左右元素的大小比较决定应该左走还是右走。
一个集合肯定会被二分为一个完全有序和部分有序的两部分。
怎么判断那一侧有序？我感觉可以通过nums[mid]与nums[0]比较。
如果nums[mid]>=nums[0],说明是在大值那部分，左侧有序，右侧不有序
如果nums[mid]<nums[0],说明在小值那部分，左侧无序，右侧有序。

判断一个值应该往做查找还是右查找时，先看他应该属于那一边，
    nums[mid]>=nums[0]// 左侧递增
        target>nums[mid] 右侧
        target<nums[mid]
            target >= nums[0] 左侧
            target < nums[0] 右侧
    nums[mid]<nums[0] //右侧递增
        target<nums[mid]  左侧
        target>nums[mid]
            target<=nums[len-1] 右移
            target>nums[len-1] 左移


1 <= nums.length <= 5000
 */
class Solution0033 {
    public int search(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left <= right && left >= 0 && left < nums.length && right >= 0 && right < nums.length) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return mid;
            }
            // 考虑[2,0]的情况
            if (nums[mid] >= nums[0]) { // 左侧有序
                if (target > nums[mid]) {
                    left = mid + 1;
                } else if (target < nums[mid] && target >= nums[0]) {
                    right = mid - 1;
                } else if (target < nums[mid] && target < nums[0]) {
                    left = mid + 1;
                }
            }else{ // 右侧有序
                if (target < nums[mid]) {
                    right = mid - 1;
                } else if (target >= nums[mid] && target <= nums[len - 1]) {
                    left = mid + 1;
                } else if (target >= nums[mid] && target > nums[len - 1]) {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}