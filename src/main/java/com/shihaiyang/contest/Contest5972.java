package com.shihaiyang.contest;

// 5972. 统计隐藏数组数目.[].
public class Contest5972 {
    public static void main(String[] args) {
        Solution5972 solution5972 = new Solution5972();
        int ofArrays = solution5972.numberOfArrays(new int[]{1,-3,4}, 1, 7);
//        int ofArrays = solution5972.numberOfArrays(new int[]{3,-4,5,1,-2}, -4, 5);
        System.out.println(ofArrays);
    }
}
/**
 * 输入：differences = [1,-3,4], lower = 1, upper = 6
 * 输出：2
 * 解释：符合要求的隐藏数组为：
 * - [3, 4, 1, 5]
 * - [4, 5, 2, 6]
 * 所以返回 2 。
 * 示例 2：
 *
 * 输入：differences = [3,-4,5,1,-2], lower = -4, upper = 5
 * 输出：4
 * 解释：符合要求的隐藏数组为：
 * - [-3, 0, -4, 1, 2, 0]
 * - [-2, 1, -3, 2, 3, 1]
 * - [-1, 2, -2, 3, 4, 2]
 * - [0, 3, -1, 4, 5, 3]
 * 所以返回 4 。
 */

/**
 * 以0来遍历一遍diff数组，看下min和max。min到lower个数+max到upper的个数就是个数
 */
class Solution5972 {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long min = 0, max = 0, start = 0;
        for (int j = 0; j < differences.length; j++) {
            start += differences[j];
            min = Math.min(start, min);
            max = Math.max(start, max);
        }
        long count = (min - lower) + (upper - max)+1;
        if (count < 0) {
            return 0;
        }
        return (int)count;
    }
}
