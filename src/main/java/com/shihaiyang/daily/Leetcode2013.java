package com.shihaiyang.daily;

import java.util.HashMap;
import java.util.Map;

// 2013. 检测正方形.[Map,int效率高41ms].
public class Leetcode2013 {
    public static void main(String[] args) {
        DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[]{3, 10});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{3, 2});
        detectSquares.count(new int[]{11, 10}); // 返回 1 。你可以选择：
        //   - 第一个，第二个，和第三个点
        detectSquares.count(new int[]{14, 8});  // 返回 0 。查询点无法与数据结构中的这些点构成正方形。
        detectSquares.add(new int[]{11, 2});    // 允许添加重复的点。
        detectSquares.count(new int[]{11, 10}); // 返回 2 。你可以选择：
    }
}

/**
 * map 嵌套存储，一直两个点的情况下，剩余两个点是已知的。判断两个点是否存在即可。
 *
 */
class DetectSquares {
    Map<Integer, Map<Integer, Integer>> row2col;
    public DetectSquares() {
        row2col = new HashMap<>();
    }

    public void add(int[] point) {
        int x = point[0];
        int y = point[1];
        Map<Integer, Integer> col2cnt = row2col.getOrDefault(x, new HashMap<>());
        col2cnt.put(y, col2cnt.getOrDefault(y, 0) + 1);
        row2col.put(x, col2cnt);
    }

    public int count(int[] point) {
        int row1 = point[0];
        int row1col1 = point[1];
        int count = 0;
        Map<Integer, Integer> col2cnt = row2col.getOrDefault(row1, new HashMap<>());
        for (Map.Entry<Integer, Integer> entry : col2cnt.entrySet()) {
            int row1col2 = entry.getKey();
            if (row1col1 == row1col2) {
                continue;
            }
            int c1 = entry.getValue();
            int len = row1col2 - row1col1;
            int[] col = new int[]{row1 + len, row1 - len};
            for (int cy : col) {
                Map<Integer, Integer> row2 = row2col.getOrDefault(cy, new HashMap<>());
                int c2 = row2.getOrDefault(row1col1, 0), c3 = row2.getOrDefault(row1col2, 0);
                count += c1 * c2 * c3;
            }
        }
        return count;
    }
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */