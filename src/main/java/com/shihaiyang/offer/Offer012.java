package com.shihaiyang.offer;

// 剑指 Offer II 012. 左右两边子数组的和相等
// Offer012. 左右两边子数组的和相等.[前缀和1ms 100%].
// https://leetcode-cn.com/problems/tvdfij/solution/offer012-zuo-you-liang-bian-zi-shu-zu-de-6x0t/
public class Offer012 {
    public static void main(String[] args) {
        SolutionOffer012 solutionOffer012 = new SolutionOffer012();
        int pivotIndex = solutionOffer012.pivotIndex(new int[]{-1,-1,-1,1,1,1});
        System.out.println(pivotIndex);
    }
}

/**
 * 给你一个整数数组 nums ，请计算数组的 中心下标 。
 * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
 * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
 * 示例 1：
 * 输入：nums = [1,7,3,6,5,6]
 * 输出：3
 * 解释：
 * 中心下标是 3 。
 * 左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
 * 右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
 * [-1,-1,-1,1,1,1]
 * -1
 */

/**
 * Offer012. 左右两边子数组的和相等.[前缀和1ms 100%].
 * 计算一次前缀和
 * 在遍历一次前缀和。左右相等.
 */
class SolutionOffer012 {
    public int pivotIndex(int[] nums) {
        int preSums[] = new int[nums.length + 1];
        preSums[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            preSums[i + 1] = preSums[i] + nums[i];
        }
        for (int i = 1; i <= preSums.length-1; i++) {
            if (preSums[i-1] == preSums[preSums.length - 1] - preSums[i]) {
                return i-1;
            }
        }
        return -1;
    }
}
