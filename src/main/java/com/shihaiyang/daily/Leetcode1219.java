package com.shihaiyang.daily;

// 1219. 黄金矿工.[回溯 2ms].
public class Leetcode1219 {
    public static void main(String[] args) {
        Solution1219 solution1219 = new Solution1219();
        int grid[][] = new int[][]{
                {1,0,7},
                {2,0,6},
                {3,4,5},
                {0,3,0},
                {9,0,20},
        };
        int maximumGold = solution1219.getMaximumGold(grid);
        System.out.println(maximumGold);
    }
}

/**
 * 你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。
 * 每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。
 * 为了使收益最大化，矿工需要按以下规则来开采黄金：
 * 每当矿工进入一个单元，就会收集该单元格中的所有黄金。
 * 矿工每次可以从当前位置向上下左右四个方向走。
 * 每个单元格只能被开采（进入）一次。
 * 不得开采（进入）黄金数目为 0 的单元格。
 * 矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
 * 输入：grid = [[0,6,0],[5,8,7],[0,9,0]]
 * 输出：24
 * 解释：
 * [[0,6,0],
 *  [5,8,7],
 *  [0,9,0]]
 * 一种收集最多黄金的路线是：9 -> 8 -> 7。
 * 输入：grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * 输出：28
 * 解释：
 * [[1,0,7],
 *  [2,0,6],
 *  [3,4,5],
 *  [0,3,0],
 *  [9,0,20]]
 * 一种收集最多黄金的路线是：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7。
 */

/**
 * DFS + 回溯 + 多源
 * 走过就标记为0
 * 多源暴力DFS + 回溯
 * 多源dfs就是每个非0点都尝试一下，看能获取最大的是多少。
 * 回溯是指，每个访问过的点都设置为0.当遍历结束再设置回去，这样省去了每次都copy一个二维数组的空间。
 * 深度遍历DFS不需要额外的空间，上下左右扫即可。
 * 广度遍历BFS需要队列，一层一层扫。
 *
 * 1. 遍历每一个节点，对每个节点进行深度遍历。
 * 2. 遍历过程：比较获取的gold。
 *      然后改为0，
 *      再进行深度遍历
 *      遍历结束之后再赋值回原值。
 * 3. 返回最大的gold
 */
class Solution1219 {
    static int dirs[][] = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int ans = 0;
    public int getMaximumGold(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    dfs(grid, i, j, 0);
                }
            }
        }
        return ans;
    }

    public void dfs(int[][] grid, int x1, int y1, int gold){
        int rec = grid[x1][y1];
        gold += rec;
        ans = Math.max(ans, gold);
        // 设置为0，标识已经扫描过
        grid[x1][y1] = 0;
        for (int i = 0; i < dirs.length; i++) {
            int x = x1 + dirs[i][0];
            int y = y1 + dirs[i][1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != 0) {
                dfs(grid, x, y, gold);
            }
        }
        // 回溯：设置会原值
        grid[x1][y1] = rec;
    }
}