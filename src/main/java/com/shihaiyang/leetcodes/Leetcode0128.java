package com.shihaiyang.leetcodes;

import java.util.HashMap;
import java.util.Map;

// 0128. 最长连续序列.[动态规划29ms].
public class Leetcode0128 {
    public static void main(String[] args) {
        Solution0128 solution0128 = new Solution0128();
        int longestConsecutive = solution0128.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2, 5, 6});
        System.out.println(longestConsecutive);
    }
}
/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */

/**
 * 使用map记录某个节点连续的长度。
 * 思路是每个节点(5)遍历的时候，都去访问上一个(4)=2，下一个(6)=1。如果有，就链接起来，长度变成2+1+1(3456)。
 * 顺便设置回上边界(3=5-2)和下边界(6=5+1)，这样上边界的上一个(2)遍历到的时候，取(3)长度就是4，连起来就是5了。
 */
class Solution0128 {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                continue;
            }
            int left = map.getOrDefault(num - 1, 0);
            int right = map.getOrDefault(num + 1, 0);
            int curMaxLen = 1 + left + right;
            max = Math.max(max, curMaxLen);
            map.put(num, curMaxLen);
            if (left != 0) {
                map.put(num -left, curMaxLen);
            }
            if (right != 0) {
                map.put(num +right, curMaxLen);
            }
        }
        return max;
    }
}

