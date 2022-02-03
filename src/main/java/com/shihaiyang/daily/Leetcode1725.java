package com.shihaiyang.daily;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

// 1725. 可以形成最大正方形的矩形数目.[].
public class Leetcode1725 {
    public static void main(String[] args) {
        Solution1725 solution1725 = new Solution1725();
        int rec[][] = new int[][]{
                {5,8},
                {3,9},
                {5,12},
                {16,5},
        };
        int goodRectangles = solution1725.countGoodRectangles(rec);
        System.out.println(goodRectangles);
    }
}

/**
 * 给你一个数组 rectangles ，其中 rectangles[i] = [li, wi] 表示第 i 个矩形的长度为 li 、宽度为 wi 。
 * 如果存在 k 同时满足 k <= li 和 k <= wi ，就可以将第 i 个矩形切成边长为 k 的正方形。例如，矩形 [4,6] 可以切成边长最大为 4 的正方形。
 * 设 maxLen 为可以从矩形数组 rectangles 切分得到的 最大正方形 的边长。
 * 请你统计有多少个矩形能够切出边长为 maxLen 的正方形，并返回矩形 数目 。
 * 输入：rectangles = [[5,8],[3,9],[5,12],[16,5]]
 * 输出：3
 * 解释：能从每个矩形中切出的最大正方形边长分别是 [5,3,5,5] 。
 * 最大正方形的边长为 5 ，可以由 3 个矩形切分得到。
 */

/**
 * HashMap + maxVal
 */
class Solution1725 {
    public int countGoodRectangles(int[][] rectangles) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxVal = 0;
        for (int i = 0; i < rectangles.length; i++) {
            int min = Math.min(rectangles[i][0], rectangles[i][1]);
            map.put(min, map.getOrDefault(min, 0) + 1);
            maxVal = Math.max(min, maxVal);
        }
        return map.get(maxVal);
    }
}
/**
 * TreeMap
 */
class Solution1725TreeMap {
    public int countGoodRectangles(int[][] rectangles) {
        TreeMap<Integer, Integer> treeMap = new TreeMap((a, b) -> (int)b - (int)a);
        for (int i = 0; i < rectangles.length; i++) {
            int min = Math.min(rectangles[i][0], rectangles[i][1]);
            treeMap.put(min, treeMap.getOrDefault(min, 0) + 1);
        }
        return treeMap.firstEntry().getValue();
    }
}
