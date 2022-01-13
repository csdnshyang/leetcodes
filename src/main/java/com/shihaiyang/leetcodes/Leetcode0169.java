package com.shihaiyang.leetcodes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 0169. 多数元素.[MooreVote 1ms+Quick Sort 2ms+Map 16ms].
// https://leetcode-cn.com/problems/majority-element/solution/0169-duo-shu-yuan-su-moorevote-1msquick-ixosm/
public class Leetcode0169 {
}

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 输入：[3,2,3]
 * 输出：3
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 */

/**
 * 方法1：遍历一遍，存储。。空间复杂度就高了.O(n)
 * 优化成空间复杂度O(1).
 */
class Solution0169 {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (map.get(nums[i]) > (nums.length / 2)) {
                return nums[i];
            }
        }
        return 0;
    }
}

/**
 * 方法2：排序。找最长的。
 * 无论是1 1 1 2 3，0 1 1 1 2还是-1 0 1 1 1，数组中间的元素总是“多数元素”，毕竟它长度> ⌊ n/2 ⌋。
 */
class Solution0169Sort {
    public int majorityElement(int[] nums) {
        if(nums.length==1){
            return nums[0];
        }
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }
}

/**
 * 方法3：摩尔排序，这个也是从没想过的思路。
 * 摩尔排序根本是"大多数"比其他"所有的少数和" 更多。
 * 遍历数组，设置一个候选人，票数=1，
 * 每次遇到相同值，投票数+1，
 * 当遇到不同值，投票数-1.
 * 直到投票数=0时，换成值为新的候选人，重新票数=1.
 * 遍历完数组，最终的候选人就是最多的大多数.
 */
class Solution0169Moore {
    public int majorityElement(int[] nums) {
        int candidate = nums[0], vote = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == candidate) {
                vote += 1;
            } else {
                vote -= 1;
                if (vote == 0) {
                    candidate = nums[i];
                    vote = 1;
                }
            }
        }
        return candidate;
    }
}

