package com.shihaiyang.leetcodes;

import java.util.Arrays;

// 0283. 移动零.[双指针2ms].
public class Leetcode0283 {
    public static void main(String[] args) {
        Solution0283 solution0283 = new Solution0283();
        int[] ints = {0,1,0,3,12};
        solution0283.moveZeroes(ints);
        System.out.println(Arrays.toString(ints));
    }
}

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 示例:
 *   输入: [0,1,0,3,12]
 *   输出: [1,3,12,0,0]
 */

/**
 * 双指针。一个指针i从头遍历到尾。
 * 另一个指针j记录最左边的0的位置。
 * 每次如果第一个指针i遍历到不为0的值，就放在j处，j++
 */
class Solution0283 {
    public void moveZeroes(int[] nums) {
        // 存储可插入的位置
        int j=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0 && nums[j] != 0) {
                j++;
            }else if (nums[i] != 0 && nums[j] == 0) {
                nums[j++] = nums[i];
                nums[i] = 0;
            }
        }
    }
}
