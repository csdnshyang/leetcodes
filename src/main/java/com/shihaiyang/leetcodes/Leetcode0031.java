package com.shihaiyang.leetcodes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// 0031. 下一个排列.[双指针0ms].
/*
整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
给你一个整数数组 nums ，找出 nums 的下一个排列。
必须 原地 修改，只允许使用额外常数空间。
示例 1：
输入：nums = [1,2,3]
输出：[1,3,2]
示例 2：

输入：nums = [3,2,1]
输出：[1,2,3]
示例 3：

输入：nums = [1,1,5]
输出：[1,5,1]
[4,10,8,6,2,1]
[6,1,2,4,8,10]
1 <= nums.length <= 100
 */
public class Leetcode0031 {
    Solution0031 solution0031 = new Solution0031();

    @Test
    public void case1() {
        int[] ints = {4, 10, 8, 6, 2, 1};
        solution0031.nextPermutation(ints);
        Assertions.assertArrayEquals(ints, new int[]{6,1,2,4,8,10});
    }
    @Test
    public void case2() {
        int[] ints = {1,2,3};
        solution0031.nextPermutation(ints);
        Assertions.assertArrayEquals(ints, new int[]{1,3,2});
    }
    @Test
    public void case3() {
        int[] ints = {3,2,1};
        solution0031.nextPermutation(ints);
        Assertions.assertArrayEquals(ints, new int[]{1,2,3});
    }
    @Test
    public void case4() {
        int[] ints = {1};
        solution0031.nextPermutation(ints);
        Assertions.assertArrayEquals(ints, new int[]{1});
    }
    @Test
    public void case5() {
        int[] ints = {1,5,1};
        solution0031.nextPermutation(ints);
        Assertions.assertArrayEquals(ints, new int[]{5,1,1});
    }
}
/*
双指针
从后往前遍历，找到一个i满足nums[i]<nums[i+1]。     [4,10,8,6,2,1]中的 4,10
那么i就是需要反转的下标  ,然后找到能与i交换的位置，一定是刚好大于i元素的值  是6，然后交换 [6,10,8,4,2,1]
只做这一步还不够。
还需要把后面的所有元素反转成 nums[i]<nums[i+1]的样子。  [6,1,2,4,8,10]
这就可以使用双指针内缩  从i+1到len-1
 */
class Solution0031 {
    public void nextPermutation(int[] nums) {
        int swap;
        for (swap = nums.length - 2; swap >= 0; swap--) {
            if (nums[swap] < nums[swap + 1]) {
                break;
            }
        }
        if (swap >= 0) {
            int bigger = nums.length - 1;
            while (nums[bigger] <= nums[swap]) {
                bigger--;
            }
            int temp = nums[bigger];
            nums[bigger] = nums[swap];
            nums[swap] = temp;
        }
        int left = swap + 1, right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left++] = nums[right];
            nums[right--] = temp;
        }
    }
}