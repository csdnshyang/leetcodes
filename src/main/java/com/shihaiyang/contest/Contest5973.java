package com.shihaiyang.contest;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

// 5973. 价格范围内最高排名的 K 样物品.[BFS].
public class Contest5973 {
    public static void main(String[] args) {
        Solution5973 solution5973 = new Solution5973();
//        int [][] gird = new int[][]{
//                {1,2,0,1},
//                {1,3,0,1},
//                {0,2,5,1}
//        };
//        List<List<Integer>> listList = solution5973.highestRankedKItems(gird, new int[]{2, 5}, new int[]{0, 0}, 3);

        int [][] gird = new int[][]{
                {1,1,1},
                {0,0,1},
                {2,3,4}
        };
        List<List<Integer>> listList = solution5973.highestRankedKItems(gird, new int[]{2,3}, new int[]{0, 0}, 3);
        System.out.println(listList.toString());
    }
}

/**
 * 输入：grid = [[1,2,0,1],[1,3,0,1],[0,2,5,1]], pricing = [2,5], start = [0,0], k = 3
 * 输出：[[0,1],[1,1],[2,1]]
 * 解释：起点为 (0,0) 。
 * 价格范围为 [2,5] ，我们可以选择的物品坐标为 (0,1)，(1,1)，(2,1) 和 (2,2) 。
 * 这些物品的排名为：
 * - (0,1) 距离为 1
 * - (1,1) 距离为 2
 * - (2,1) 距离为 3
 * - (2,2) 距离为 4
 * 所以，给定价格范围内排名最高的 3 件物品的坐标为 (0,1)，(1,1) 和 (2,1) 。
 *
 * [[1,2,0,1],[1,3,0,1],[0,2,5,1]]
 * [2,5]
 * [0,0]
 * 3
 * [[0,1],[1,1],[2,1]]
 */

/**
 * BFS 广度
 * step[][]
 * top k 优先队列  k
 */
class Solution5973 {
    List<List<Integer>> ret = new ArrayList<>();
    PriorityQueue<int[]> queue;
    Queue<int[]> scanQueue = new LinkedBlockingQueue<>();

    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        // step 数组
        int step[][] = new int[grid.length][grid[0].length];
        for (int i = 0; i < step.length; i++) {
            Arrays.fill(step[i], Integer.MAX_VALUE);
        }

        // top k 队列
        queue = new PriorityQueue<>(k, (v1, v2) -> {
            if (step[v2[0]][v2[1]] != step[v1[0]][v1[1]]) {
                return step[v1[0]][v1[1]] - step[v2[0]][v2[1]];
            }
            if (grid[v2[0]][v2[1]] != grid[v1[0]][v1[1]]) {
                return grid[v1[0]][v1[1]] - grid[v2[0]][v2[1]] ;
            }
            if (v2[0] != v1[0]) {
                return v1[0] - v2[0];
            }
            return v1[1] - v2[1];
        });
        // BFS 广度遍历
        int row = start[0], col = start[1];
        step[row][col] = 0;
        scanQueue.add(new int[]{row, col});
        bfs(grid, pricing, k, ret, step);
        while (!queue.isEmpty() && ret.size() < k) {
            int[] poll = queue.poll();
            ret.add(List.of(poll[0], poll[1]));
        }
        return ret;
    }

    public void bfs(int[][] grid, int[] pricing, int k, List<List<Integer>> ret, int[][] step) {
        while (!scanQueue.isEmpty()) {
            int[] poll = scanQueue.poll();
            int row = poll[0], col = poll[1];
            if (grid[row][col] >= pricing[0] && grid[row][col] <= pricing[1]) {
                queue.add(new int[]{row, col});
            }
            // 边界
            if (step[row][col] == k && queue.size() >= k) {
                continue;
            }
            // 边界
            if (row + 1 < grid.length && (step[row][col] < k || queue.size()<k)
                    && step[row + 1][col] == Integer.MAX_VALUE
                    && grid[row + 1][col] != 0) {
                scanQueue.add(new int[]{row + 1, col});
                step[row + 1][col] = step[row][col] + 1;
            }
            if (col + 1 < grid[0].length && (step[row][col] < k || queue.size()<k)
                    && step[row][col+1] == Integer.MAX_VALUE
                    && grid[row][col+1] != 0) {
                scanQueue.add(new int[]{row, col + 1});
                step[row][col + 1] = step[row][col] + 1;
            }
            if (row - 1 >= 0 && (step[row][col] < k || queue.size()<k)
                    && step[row-1][col] == Integer.MAX_VALUE
                    && grid[row-1][col] != 0) {
                scanQueue.add(new int[]{row - 1, col});
                step[row - 1][col] = step[row][col] + 1;
            }
            if (col - 1 >= 0 && (step[row][col] < k || queue.size()<k)
                    && step[row][col-1] == Integer.MAX_VALUE
                    && grid[row][col-1] != 0) {
                scanQueue.add(new int[]{row, col - 1});
                step[row][col - 1] = step[row][col] + 1;
            }
        }
    }


}

