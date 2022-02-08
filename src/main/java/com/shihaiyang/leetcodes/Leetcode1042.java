package com.shihaiyang.leetcodes;

import java.util.*;

// 1042. 不邻接植花[遍历选择未被使用的花 17ms].
public class Leetcode1042 {
    public static void main(String[] args) {
        Solution1042 solution1042 = new Solution1042();
//        int paths[][] = new int[][]{
//                {1, 2},
//                {3, 4},
//        };
        int paths[][] = new int[][]{
                {1, 2},
                {2, 3},
                {3, 4},
                {4, 1},
                {1, 3},
                {2, 4},
        };
        int[] gardenNoAdj = solution1042.gardenNoAdj(4, paths);
//        int[] gardenNoAdj = solution1042.gardenNoAdj(1, new int[][]{});
        System.out.println(Arrays.toString(gardenNoAdj));
    }
}
/**
 *有 n 个花园，按从 1 到 n 标记。另有数组 paths ，其中 paths[i] = [xi, yi] 描述了花园 xi 到花园 yi 的双向路径。在每个花园中，你打算种下四种花之一。
 * 另外，所有花园 最多 有 3 条路径可以进入或离开.
 * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
 * 以数组形式返回 任一 可行的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。花的种类用  1、2、3、4 表示。保证存在答案。
 * 输入：n = 3, paths = [[1,2],[2,3],[3,1]]
 * 输出：[1,2,3]
 * 解释：
 * 花园 1 和 2 花的种类不同。
 * 花园 2 和 3 花的种类不同。
 * 花园 3 和 1 花的种类不同。
 * 因此，[1,2,3] 是一个满足题意的答案。其他满足题意的答案有 [1,2,4]、[1,4,2] 和 [3,2,1]
 * 输入：n = 4, paths = [[1,2],[3,4]]
 * 输出：[1,2,1,2]
 * 输入：n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * 输出：[1,2,3,4]
 */

/**
 * 遍历每一个花园，从相邻的已经设置的花园中选择一个没有设置的。
 */
class Solution1042 {
    public int[] gardenNoAdj(int n, int[][] paths) {
        Map<Integer, int[]> in = new HashMap<>();
        // 5位的一个数组  第一位标记已经到第几位了 [2,x,x,0,0]
        for (int[] path : paths) {
            int[] list = in.getOrDefault(path[0], new int[5]);
            list[++list[0]]=path[1];
            in.put(path[0], list);
            int[] list2 = in.getOrDefault(path[1], new int[5]);
            list2[++list2[0]]=path[0];
            in.put(path[1], list2);
        }
        int ret[] = new int[n];
        for (int i = 0; i < ret.length; i++) {
            int[] path = new int[4];
            if (in.containsKey(i + 1)) {
                int[] ints = in.get(i + 1);
                for (int j = 1; j <= ints[0]; j++) {
                    if (ret[ints[j] - 1] != 0) {
                        int p = ret[ints[j] - 1];
                        path[p - 1] = 1;
                    }
                }
            }
            for (int j = 0; j < 4; j++) {
                if (path[j] == 0) {
                    ret[i] = j + 1;
                    break;
                }
            }
        }
        return ret;
    }
}