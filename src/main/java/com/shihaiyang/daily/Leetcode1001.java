package com.shihaiyang.daily;

import java.util.*;

// 1001. 网格照明.[].
public class Leetcode1001 {
    public static void main(String[] args) {
        Solution1001 solution1001 = new Solution1001();
//        int lamps[][] = new int[][]{{0,0}, {4,4}};
//        int queries[][] = new int[][]{{1,1}, {1,0}};
        int lamps[][] = new int[][]{{0, 0}, {0, 4}};
        int queries[][] = new int[][]{{0, 4}, {0, 1}, {1, 4}};
//        int lamps[][] = new int[][]{{1, 1}};
//        int queries[][] = new int[][]{{2, 0}, {1, 0}};
        int[] gridIllumination = solution1001.gridIllumination(5, lamps, queries);
        System.out.println(Arrays.toString(gridIllumination));
    }
}

/**
 * 在大小为 n x n 的网格 grid 上，每个单元格都有一盏灯，最初灯都处于 关闭 状态。
 * 给你一个由灯的位置组成的二维数组 lamps ，其中 lamps[i] = [rowi, coli] 表示 打开 位于 grid[rowi][coli] 的灯。
 * 即便同一盏灯可能在 lamps 中多次列出，不会影响这盏灯处于 打开 状态。
 * 当一盏灯处于打开状态，它将会照亮 自身所在单元格 以及同一 行 、同一 列 和两条 对角线 上的 所有其他单元格 。
 * 另给你一个二维数组 queries ，其中 queries[j] = [rowj, colj] 。
 * 对于第 j 个查询，如果单元格 [rowj, colj] 是被照亮的，则查询结果为 1 ，否则为 0 。
 * 在第 j 次查询之后 [按照查询的顺序] ，关闭 位于单元格 grid[rowj][colj] 上及相邻 8 个方向上（与单元格 grid[rowi][coli] 共享角或边）的任何灯。
 * 返回一个整数数组 ans 作为答案， ans[j] 应等于第 j 次查询 queries[j] 的结果，1 表示照亮，0 表示未照亮。
 * <p>
 * 输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
 * 输出：[1,0]
 * 解释：最初所有灯都是关闭的。在执行查询之前，打开位于 [0, 0] 和 [4, 4] 的灯。
 * 第 0 次查询检查 grid[1][1] 是否被照亮（蓝色方框）。该单元格被照亮，所以 ans[0] = 1 。然后，关闭红色方框中的所有灯。
 * 第 1 次查询检查 grid[1][0] 是否被照亮（蓝色方框）。该单元格没有被照亮，所以 ans[1] = 0 。然后，关闭红色矩形中的所有灯。
 * <p>
 * 输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,1]]
 * 输出：[1,1]
 * 输入：n = 5, lamps = [[0,0],[0,4]], queries = [[0,4],[0,1],[1,4]]
 * 输出：[1,1,0]
 */

/**
 * 二维数组遍历问题。点亮灯设置为2，照亮的设置为1，不亮设置为0。
 * 遍历第一个数组，设置坐标为2。
 * 设置同一行，同一列为1。
 * 设置对角线为1。[-1,-1],[1,1]
 * 遍历第二个数组，判断是否>0,>0则为1.
 * 熄灭周围的灯。[-1,-1],[-1,0],[1,1][1,0][1,-1][...]
 * 如果>1,则设置为0，再把同行同列，斜对角的设置为0。
 * 还得看是否被其他的等照亮。
 * 其实根据数学判断是否也可以。
 * 同行同列就是一个坐标想等。
 * 对角线就是两个坐标的差想到。只要在这个位置就说明是亮着的。
 * 第一个坐标轴第一次不需要遍历。
 * 暴力遍历会超时。得换Map<Map>
 * <p>
 * ---本来以为是个简单题。一通思考。上述为思考过程。被超时打的体无完肤。看题解，一个还看不懂。真棒。高数知识都用上了。。。
 * 这道题行，列都好说，主要是斜对角线，亮还是不亮。想复杂的一点是想到了y=2x 3x的情况咋办...其实就没有这种情况，只有y=x y=-x的情况。
 * 斜对角线就是斜率是1..这儿想多了...
 * 四种情况：
 * row存在 好说  但是要记录count，同一行上多个灯，就+1，每次灭一个行，就-1
 * col存在 好说  同上。
 * y=x的对角线上存在灯   在同一条对角线上，就是指在同一个y=x+b这条直线上。斜率相等，截距相等。截距算是高中知识，但应该是有人不知道啥意思的，百度下吧。
 * y=-x的对角线上存在灯  在同一条对角线上  y=-x+b  跟上一条对角线垂直，斜率-1。
 * 同一条线上，不管怎样的x,y 都满足y=-x+b。灯泡的位置就可以算出b，查询的时候，就可以判断查询点是否满足y=-x+已知的b即可。
 * 另外，灯泡也可能是在同一条斜线上，所以需要记录count，有一个亮灯+1，灭一个灯就-1
 * 查询的同时要灭了周围存在的灯，所以要时间复杂度O(1)的情况操作灭灯，二维转一维的技巧  row*n+col = 一维坐标(也可以直接用Map+Set)[这个Long型给整的自闭]
 * 参考了墨鳌&宫水三叶的题解
 */
class Solution1001 {
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        int[][] around = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int ans[] = new int[queries.length];
        // Long 型整自闭了...
        long N = n;

        Map<Integer, Integer> rowCount = new HashMap<>(lamps.length);
        Map<Integer, Integer> colCount = new HashMap<>(lamps.length);
        Map<Integer, Integer> diagonalCount = new HashMap<>(lamps.length);
        Map<Integer, Integer> antiDiagonalCount = new HashMap<>(lamps.length);

        Set<Long> light = new HashSet<>();

        // 处理亮灯情况
        for (int k = 0; k < lamps.length; k++) {
            int x = lamps[k][0];
            int y = lamps[k][1];
            // y=x+b => b=y-x
            int diag = y - x;
            // y=-x+b => b=y+x
            int antiDiag = y + x;
            // 重复的灯，就不设置+1了。因为灭灯也只灭一次
            if (light.add(x * N + y)) {
                increase(rowCount, x);
                increase(colCount, y);
                increase(diagonalCount, diag);
                increase(antiDiagonalCount, antiDiag);
            }
        }

        // 判断灯亮 + 灭灯
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            // y=x+b => b=y-x
            int diag = y - x;
            // y=-x+b => b=y+x
            int antiDiag = y + x;
            // 判断是否亮着
            boolean rowFlag = rowCount.containsKey(x);
            boolean colFlag = colCount.containsKey(y);
            boolean diagFlag = diagonalCount.containsKey(diag);
            boolean antiDiagFlag = antiDiagonalCount.containsKey(antiDiag);
            ans[i] = (rowFlag || colFlag || diagFlag || antiDiagFlag) ? 1 : 0;

            // 关闭周围的灯
            for (int j = 0; j < around.length; j++) {
                int x2 = x + around[j][0];
                int y2 = y + around[j][1];
                // y=x+b => b=y-x
                int diag2 = y2 - x2;
                // y=-x+b => b=y+x
                int antiDiag2 = y2 + x2;
                if (x2 >= 0 && x2 < n && y2 >= 0 && y2 < n) {
                    // 灯存在。
                    if (light.contains(x2 * N + y2)) {
                        light.remove(x2 * N + y2);
                        decrease(rowCount, x2);
                        decrease(colCount, y2);
                        decrease(diagonalCount, diag2);
                        decrease(antiDiagonalCount, antiDiag2);
                    }
                }
            }
        }
        return ans;
    }

    private void decrease(Map<Integer, Integer> rowCount, int x2) {
        if (rowCount.get(x2) == 1) {
            rowCount.remove(x2);
        } else {
            rowCount.put(x2, rowCount.get(x2) - 1);
        }
    }

    private void increase(Map<Integer, Integer> rowCount, int x) {
        rowCount.put(x, rowCount.getOrDefault(x, 0) + 1);
    }
}