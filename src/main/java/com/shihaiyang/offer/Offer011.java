package com.shihaiyang.offer;

import java.util.HashMap;

// 剑指 Offer II 011. 0 和 1 个数相同的子数组
// Offer011. 0 和 1 个数相同的子数组.[前缀和+Hash23ms].
public class Offer011 {
    public static void main(String[] args) {
        SolutionOffer011 solutionOffer011 = new SolutionOffer011();
        int maxLength = solutionOffer011.findMaxLength(new int[]{0, 1, 0,1,1,0,1,0,1,0,1,0});
        System.out.println(maxLength);
    }
}
/**
 *给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * 示例 1:
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
 * 示例 2:
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量 0 和 1 的最长连续子数组。
 */

/**
 * 前缀和+HashMap
 * 因为只有0和1。如果两个长度和=1那么就可以判断一个0一个1。=> [更优方案是把0换成-1。只有1和-1的情况。和为0的最长子数组]
 * 如果出现过一次和为3，因为只有-1和+1,相同个-1和+1加完之后，和应该还是3..
 * 基于这个理论，每次遇到preSum时，与preSum在第一次出现的距离就是相同的-1+1的长度。
 * 从头遍历到尾,maxLen
 */
class SolutionOffer011 {
    public int findMaxLength(int[] nums) {
        int preSums[] = new int[nums.length+1];
        HashMap<Integer, Integer> map = new HashMap<>();
        preSums[0]=0;
        map.put(0, -1);
        int maxLen=0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                preSums[i + 1] = preSums[i] + 1;
            } else {
                preSums[i + 1] = preSums[i] - 1;
            }
            if (map.containsKey(preSums[i + 1])) {
                maxLen = Math.max(maxLen, i - map.get(preSums[i + 1]));
            } else {
                map.put(preSums[i + 1], i);
            }
        }
        return maxLen;
    }
}
