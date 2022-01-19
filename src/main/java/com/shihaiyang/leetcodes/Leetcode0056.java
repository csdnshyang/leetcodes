package com.shihaiyang.leetcodes;

import java.util.Arrays;

// 0056. 合并区间.[排序8ms]
public class Leetcode0056 {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1,4},
                {4,5},
                {8,10},
                {15,18}
        };
        Solution0056 solution0056 = new Solution0056();
        int[][] merge = solution0056.merge(arr);
        for (int i = 0; i < merge.length; i++) {
            System.out.println(Arrays.toString(merge[i]));
        }
    }
}

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */

/**
 * 用map来存，key是起始，value是终止。
 * 判断是否有比新的起始大的起始，如果大，就判断终止是否大于起始，如果大，就说明重复。更新
 * 如果终止小于存储的终止。就忽略这个。
 * 如果起始比存储的终止还大，就不重复。
 *
 * 写了很多，通过不了.
 * 先排序一下...
 */
class Solution0056 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        int[][] arr = new int[intervals.length][2];
        int start = intervals[0][0], end = intervals[0][1];
        int index = 0;
        for (int i = 1; i < intervals.length; i++) {
            // 新的一对
            if (intervals[i][0] > end) {
                arr[index][0]=start;
                arr[index++][1]=end;
                start = intervals[i][0];
                end = intervals[i][1];
            } else if (intervals[i][1] >= end) {
                end = intervals[i][1];
            }
        }
        arr[index][0]=start;
        arr[index][1]=end;
        int[][] arr2 = new int[index+1][2];
        System.arraycopy(arr, 0, arr2, 0, index+1);
        return arr2;
    }
}