package com.shihaiyang.offer;

import java.util.Arrays;

// 剑指 Offer II 006. 排序数组中两个数字之和
// Offer II 006. 排序数组中两个数字之和.[双指针夹逼0ms].
public class Offer006 {
    public static void main(String[] args) {
        SolutionOffer006 solutionOffer006 = new SolutionOffer006();
        int[] twoSum = solutionOffer006.twoSum(new int[]{1, 2, 4, 6, 10}, 8);
        System.out.println(Arrays.toString(twoSum));
    }
}

/**
 * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 0 开始计数 ，所以答案数组应当满足 0 <= answer[0] < answer[1] < numbers.length 。
 * 假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。
 *
 * 输入：numbers = [1,2,4,6,10], target = 8
 * 输出：[1,3]
 * 解释：2 与 6 之和等于目标数 8 。因此 index1 = 1, index2 = 3 。
 */

/**
 * 双指针夹逼
 */
class SolutionOffer006 {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length-1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                return new int[]{i, j};
            }
        }
        return new int[]{};
    }
}

