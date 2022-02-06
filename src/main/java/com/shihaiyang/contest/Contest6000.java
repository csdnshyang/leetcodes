package com.shihaiyang.contest;

import java.util.Arrays;

// 6000. 对奇偶下标分别排序.[分别排序1ms].
public class Contest6000 {
    public static void main(String[] args) {
        Solution6000 solution6000 = new Solution6000();
        int[] sortEvenOdd = solution6000.sortEvenOdd(new int[]{4, 3});
//        int[] sortEvenOdd = solution6000.sortEvenOdd(new int[]{4, 1, 2, 3});
        System.out.println(Arrays.toString(sortEvenOdd));
    }
}

/**
 * 示例 1：
 *
 * 输入：nums = [4,1,2,3]
 * 输出：[2,3,4,1]
 * 解释：
 * 首先，按非递增顺序重排奇数下标（1 和 3）的值。
 * 所以，nums 从 [4,1,2,3] 变为 [4,3,2,1] 。
 * 然后，按非递减顺序重排偶数下标（0 和 2）的值。
 * 所以，nums 从 [4,1,2,3] 变为 [2,3,4,1] 。
 * 因此，重排之后形成的数组是 [2,3,4,1] 。
 * 示例 2：
 *
 * 输入：nums = [2,1]
 * 输出：[2,1]
 * 解释：
 * 由于只有一个奇数下标和一个偶数下标，所以不会发生重排。
 * 形成的结果数组是 [2,1] ，和初始数组一样。
 */

/**
 * 空间换时间
 */
class Solution6000 {
    public int[] sortEvenOdd(int[] nums) {
        int len = nums.length;
        int flag = 0;
        if ((len & 1) == 1) {
            flag = 1;
        }
        int first[] = new int[len / 2 + flag];
        int second[] = new int[len / 2];
        int firstIndex = 0, secondIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 0) {
                first[firstIndex++] = nums[i];
            } else {
                second[secondIndex++] = nums[i];
            }
        }
        Arrays.sort(first);
        Arrays.sort(second);
        firstIndex = 0;
        secondIndex = second.length-1;
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 0) {
                nums[i] = first[firstIndex++];
            } else {
                nums[i] = second[secondIndex--];
            }
        }
        return nums;
    }
}
