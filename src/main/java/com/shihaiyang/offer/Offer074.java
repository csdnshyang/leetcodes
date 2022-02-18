package com.shihaiyang.offer;

// Offer II 074. 合并区间[排序+比较 3ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 0 <= starti <= endi <= 104
 */
public class Offer074 {
    SolutionOffer074 solutionOffer074 = new SolutionOffer074();

    @Test
    public void case1() {
        int[][] merge = solutionOffer074.merge(new int[][]{
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18},
        });
        Assertions.assertArrayEquals(merge, new int[][]{{1, 6}, {8, 10}, {15, 18}});
    }
    @Test
    public void case2() {
        int[][] merge = solutionOffer074.merge(new int[][]{
                {1, 3},
                {3, 6},
        });
        Assertions.assertArrayEquals(merge, new int[][]{{1, 6}});
    }
}

/**
 * 排序+遍历比较
 * 起始值已排序。
 * 几种情况
 * 1. interval[0] > start。说明是新的区间。把暂存start，end存储，更新暂存start，end
 * 2. else，说明有交叉，暂存终止=max(end, 暂存end)
 */
class SolutionOffer074 {
    public int[][] merge(int[][] intervals) {
        int[][] temp = new int[intervals.length][];
        int index = 0;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int start = intervals[0][0], end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] > end) {
                temp[index++] = new int[]{start, end};
                start = interval[0];
                end = interval[1];
            } else {
                end = Math.max(end, interval[1]);
            }
        }
        temp[index++] = new int[]{start, end};

        int[][] ret = new int[index][];
        System.arraycopy(temp, 0, ret, 0, index);
        return ret;
    }
}
