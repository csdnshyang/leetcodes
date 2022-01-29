package com.shihaiyang.daily;

import java.util.*;

// 1765. 地图中的最高点.[多源BFS 36ms].
public class Leetcode1765 {
    public static void main(String[] args) {
        Solution1765 solution1765 = new Solution1765();
        int[][] isWater = new int[][]{
                {0, 0, 1},
                {1, 0, 0},
                {0, 0, 0},
        };
        int[][] highestPeak = solution1765.highestPeak(isWater);
        Arrays.stream(highestPeak).forEach(ints -> System.out.println(Arrays.toString(ints)));
    }
}

/**
 * 给你一个大小为 m x n 的整数矩阵 isWater ，它代表了一个由 陆地 和 水域 单元格组成的地图。
 * 如果 isWater[i][j] == 0 ，格子 (i, j) 是一个 陆地 格子。
 * 如果 isWater[i][j] == 1 ，格子 (i, j) 是一个 水域 格子。
 * 你需要按照如下规则给每个单元格安排高度：
 * 每个格子的高度都必须是非负的。
 * 如果一个格子是是 水域 ，那么它的高度必须为 0 。
 * 任意相邻的格子高度差 至多 为 1 。当两个格子在正东、南、西、北方向上相互紧挨着，就称它们为相邻的格子。（也就是说它们有一条公共边）
 * 找到一种安排高度的方案，使得矩阵中的最高高度值 最大 。
 * 请你返回一个大小为 m x n 的整数矩阵 height ，其中 height[i][j] 是格子 (i, j) 的高度。如果有多种解法，请返回 任意一个 。
 * 输入：isWater = [[0,0,1],[1,0,0],[0,0,0]]
 * 输出：[[1,1,0],[0,1,1],[1,2,2]]
 * 解释：所有安排方案中，最高可行高度为 2 。
 * 任意安排方案中，只要最高高度为 2 且符合上述规则的，都为可行方案。
 */

/**
 * 多源BFS
 * 这个是围着水域扩散，扩散一层高度可以+1
 * 另外可能会遇到另外的水域点，需要取高度小的。比如一个坐标在第一水域扩散第三层，但是第二个水域点扩散在第一层，那只能是1。
 * 所以从每个水域点BFS扩散一遍，每个坐标取小的层数(如果按当前这个水域点扩散得到的高度 < 已经存储的高度，就设置为当前水域点的高度）
 */
class Solution1765 {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < isWater.length; i++) {
            for (int j = 0; j < isWater[0].length; j++) {
                if (isWater[i][j] == 1) {
                    isWater[i][j] = 0;
                    queue.add(new int[]{i, j});
                } else {
                    isWater[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int height = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            height++;
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                // 如果已经存储的高度大于当前扩散所得的水域高度(height)，那么就设置成height. 取小。
                if (poll[0] + 1 < m && isWater[poll[0] + 1][poll[1]] > height) {
                    queue.offer(new int[]{poll[0] + 1, poll[1]});
                    isWater[poll[0] + 1][poll[1]] = height;
                }
                if (poll[0] - 1 >= 0 && isWater[poll[0] - 1][poll[1]] > height) {
                    queue.offer(new int[]{poll[0] - 1, poll[1]});
                    isWater[poll[0] - 1][poll[1]] = height;
                }
                if (poll[1] + 1 < n && isWater[poll[0]][poll[1] + 1] > height) {
                    queue.offer(new int[]{poll[0], poll[1] + 1});
                    isWater[poll[0]][poll[1] + 1] = height;
                }
                if (poll[1] - 1 >= 0 && isWater[poll[0]][poll[1] - 1] > height) {
                    queue.offer(new int[]{poll[0], poll[1] - 1});
                    isWater[poll[0]][poll[1] - 1] = height;
                }
            }
        }
        return isWater;
    }
}