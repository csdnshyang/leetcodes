package com.shihaiyang.contest;

import java.util.*;

// 5985. 根据给定数字划分数组.[空间换时间 12ms].
public class Contest5985 {
    public static void main(String[] args) {
        Solution5985 solution5985 = new Solution5985();
        int[] pivotArray = solution5985.pivotArray(new int[]{-3,4,3,2}, 2);
        System.out.println(Arrays.toString(pivotArray));
    }
}

/**
 * 示例 1：
 *
 * 输入：nums = [9,12,5,10,14,3,10], pivot = 10
 * 输出：[9,5,3,10,10,12,14]
 * 解释：
 * 元素 9 ，5 和 3 小于 pivot ，所以它们在数组的最左边。
 * 元素 12 和 14 大于 pivot ，所以它们在数组的最右边。
 * 小于 pivot 的元素的相对位置和大于 pivot 的元素的相对位置分别为 [9, 5, 3] 和 [12, 14] ，它们在结果数组中的相对顺序需要保留。
 * 示例 2：
 *
 * 输入：nums = [], pivot = 2
 * 输出：[-3,2,4,3]
 * 解释：
 * 元素 -3 小于 pivot ，所以在数组的最左边。
 * 元素 4 和 3 大于 pivot ，所以它们在数组的最右边。
 * 小于 pivot 的元素的相对位置和大于 pivot 的元素的相对位置分别为 [-3] 和 [4, 3] ，它们在结果数组中的相对顺序需要保留。
 */

/**
 * 类似一个冒泡排序+快排的合并
 * 栈吧...
 */
class Solution5985 {
    public int[] pivotArray(int[] nums, int pivot) {
        List<Integer> big = new ArrayList<>();
        List<Integer> small = new ArrayList<>();
        int equal = 0;
        for (int i = 0; i < nums.length; i++) {
            if (pivot == nums[i]) {
                equal++;
            } else if (nums[i] > pivot) {
                big.add(nums[i]);
            } else {
                small.add(nums[i]);
            }
        }
        int smallIndex = 0;
        int bigIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (smallIndex != small.size()) {
                nums[i] = small.get(smallIndex++);
                continue;
            }
            if (equal != 0) {
                nums[i] = pivot;
                equal--;
                continue;
            }
            nums[i] = big.get(bigIndex++);
        }
        return nums;
    }
}