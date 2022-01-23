package com.shihaiyang.contest;

import java.util.Arrays;

// 5991. 按符号重排数组.[局部冒泡].
public class Leetcode5991 {
    public static void main(String[] args) {
        Solution5991 solution5991 = new Solution5991();
        int[] rearrangeArray = solution5991.rearrangeArray(new int[]{28, -41, 22, -8, -37, 46, 35, -9, 18, -6, 19, -26, -37, -10, -9, 15, 14, 31});
//        int[] rearrangeArray = solution5991.rearrangeArray(new int[]{3,1,-2,-5,2,-4});
        System.out.println(Arrays.toString(rearrangeArray));
    }
}

/**
 * 给你一个下标从 0 开始的整数数组 nums ，数组长度为 偶数 ，由数目相等的正整数和负整数组成。
 * <p>
 * 你需要 重排 nums 中的元素，使修改后的数组满足下述条件：
 * <p>
 * 任意 连续 的两个整数 符号相反
 * 对于符号相同的所有整数，保留 它们在 nums 中的 顺序 。
 * 重排后数组以正整数开头。
 * 重排元素满足上述条件后，返回修改后的数组。
 * 输入：nums = [3,1,-2,-5,2,-4]
 * 输出：[3,-2,1,-5,2,-4]
 * 解释：
 * nums 中的正整数是 [3,1,2] ，负整数是 [-2,-5,-4] 。
 * 重排的唯一可行方案是 [3,-2,1,-5,2,-4]，能满足所有条件。
 * 像 [1,-2,2,-5,3,-4]、[3,1,2,-2,-5,-4]、[-2,3,-5,1,-4,2] 这样的其他方案是不正确的，因为不满足一个或者多个条件。
 * [28,-41,22,-8,-37,46,35,-9,18,-6,19,-26,-37,-10,-9,15,14,31]
 * [28,-41,22,-8,46,-37,35,-9,18,-6,19,-26,15,-37,14,-10,31,-9]
 * [28, -41, 22, -8, 46, -37, 35, -9, 18, -6, 19, -26, 15, -37, 14, -10, 31, -9]
 * [28, -41, 22, -8, 46, -37, 35, -9, 18, -6, 19, -26, 15, -37, 14, -37, 31, -9]
 */

/**
 */
class Solution5991 {
    public int[] rearrangeArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if ((i&1) == 0) {
                if (nums[i] < 0) {
                    int temp = nums[i];
                    int index = i + 1;
                    while (nums[index] < 0) {
                        int a = temp;
                        temp = nums[index];
                        nums[index] = a;
                        index++;
                    }
                    nums[i] = nums[index];
                    nums[index] = temp;
                }
            } else {
                if (nums[i] > 0) {
                    int temp = nums[i];
                    int index = i + 1;
                    while (nums[index] > 0) {
                        int a = temp;
                        temp = nums[index];
                        nums[index] = a;
                        index++;
                    }
                    nums[i] = nums[index];
                    nums[index] = temp;
                }
            }
        }
        return nums;
    }
}
