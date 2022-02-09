package com.shihaiyang.daily;

//  2006. 差的绝对值为 K 的数对数目.[数组+计数 0ms].
public class Leetcode2006 {
    public static void main(String[] args) {
        Solution2006 solution2006 = new Solution2006();
//        int countKDifference = solution2006.countKDifference(new int[]{1,2,2,1}, 1);
//        int countKDifference = solution2006.countKDifference(new int[]{3,1}, 1);
        int countKDifference = solution2006.countKDifference(new int[]{3,2,1,5,4}, 2);
        System.out.println(countKDifference);
    }
}
/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回数对 (i, j) 的数目，满足 i < j 且 |nums[i] - nums[j]| == k 。
 *
 * |x| 的值定义为：
 *
 * 如果 x >= 0 ，那么值为 x 。
 * 如果 x < 0 ，那么值为 -x 。
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,2,1], k = 1
 * 输出：4
 * 解释：差的绝对值为 1 的数对为：
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * 示例 2：
 *
 * 输入：nums = [1,3], k = 3
 * 输出：0
 * 解释：没有任何数对差的绝对值为 3 。
 * 示例 3：
 *
 * 输入：nums = [3,2,1,5,4], k = 2
 * 输出：3
 * 解释：差的绝对值为 2 的数对为：
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 */
/**
 * 数组计数+遍历
 */
class Solution2006 {
    public int countKDifference(int[] nums, int k) {
        int[] num = new int[101];
        for (int key : nums) {
            num[key]++;
        }
        int ans = 0;
        for (int i = 1; i < 101 - k; i++) {
            ans += num[i] * num[i + k];
        }
        return ans;
    }
}