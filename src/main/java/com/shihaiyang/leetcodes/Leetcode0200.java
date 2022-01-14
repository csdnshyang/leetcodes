package com.shihaiyang.leetcodes;

// 0200. 岛屿数量.[DFS-深度优先遍历+记录已遍历状态].

/**
 * DFS(depth-first search) 深度优先遍历
 * 找到一个节点的时候，先去看节点的四周。上下左右。进入上的时候，再进入上的上下左右。上的上的上下左右。
 * 所以，这个节点的上一直上到顶..深度。
 */

/**
 * 图邻接矩阵的深度遍历搜索 DFS
 *
 * 1.遍历每个节点
 * 2.是1的时候,就当做一个岛的起点，开始深度遍历
 * 3.遍历过的节点，标记为2防止死循环
 * 4.下标超出数组范围时，跳出深度遍历
 */
public class Leetcode0200 {
    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        char[][] grid2={
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        Solution0200 solution0200 = new Solution0200();
        int islands = solution0200.numIslands(grid2);
        System.out.println(islands);
    }
}

class Solution0200 {
    public int numIslands(char[][] grid) {
        int lands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int cur = grid[i][j];
                if (cur == '0') {
                    continue;
                }
                if (cur == '2') {
                    continue;
                }
                if (cur == '1') {
                    lands++;
                    dfs(grid, i, j);
                }
            }
        }
        return lands;
    }

    public void dfs(char[][] grid, int i, int j) {
        // 如果在数组内，且=='1' 说明是岛
        if(i>=0 && j>=0
                && i < grid.length && j < grid[i].length
                && grid[i][j] == '1'){
            // 标记遍历过
            grid[i][j]='2';
            // 上右下左 深度遍历
            dfs(grid, i-1, j);
            dfs(grid, i, j-1);
            dfs(grid, i+1, j);
            dfs(grid, i, j+1);
        }else{
            return;
        }
    }
}