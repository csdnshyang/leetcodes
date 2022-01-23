package com.shihaiyang.contest;

// 5989. 元素计数.[排序+最大最小]
public class Leetcode5989 {
    public static void main(String[] args) {
        Solution5989 solution5989 = new Solution5989();
        int elements = solution5989.countElements(new int[]{11, 7, 2, 15});
        System.out.println(elements);
    }
}

/**
 * 给你一个整数数组 nums ，统计并返回在 nums 中同时具有一个严格较小元素和一个严格较大元素的元素数目。
 * 输入：nums = [11,7,2,15]
 * 输出：2
 * 解释：
 * 元素 7 ：严格较小元素是元素 2 ，严格较大元素是元素 11 。
 * 元素 11 ：严格较小元素是元素 7 ，严格较大元素是元素 15 。
 * 总计有 2 个元素都满足在 nums 中同时存在一个严格较小元素和一个严格较大元素。
 * 输入：nums = [-3,3,3,90]
 * 输出：2
 * 解释：元素 3 ：严格较小元素是元素 -3 ，严格较大元素是元素 90 。
 * 由于有两个元素的值为 3 ，总计有 2 个元素都满足在 nums 中同时存在一个严格较小元素和一个严格较大元素。
 *
 */
class Solution5989 {
    public int countElements(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > min && nums[i] < max) {
                count++;
            }
        }
        return count;
    }
}
