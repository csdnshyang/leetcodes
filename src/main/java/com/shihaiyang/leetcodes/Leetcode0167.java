package com.shihaiyang.leetcodes;
// 0167. 两数之和 II - 输入有序数组.[双指针夹逼0ms].
// https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/solution/0167-liang-shu-zhi-he-ii-shu-ru-you-xu-s-brlj/
public class Leetcode0167 {

}
/**
 * 给定一个已按照 非递减顺序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素
 */
class Solution0167 {
    public int[] twoSum(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum > target) {
                j--;
            } else if (sum < target) {
                i++;
            } else {
                return new int[]{i+1, j+1};
            }
        }
        return null;
    }
}