package com.shihaiyang.daily;

import java.util.Arrays;
import java.util.List;

// 0539. 最小时间差.[转分钟+排序+比较 1ms 100%].
public class Leetcode0539 {
    public static void main(String[] args) {
        Solution0539 solution0539 = new Solution0539();
        int minDifference = solution0539.findMinDifference(List.of("00:00","04:00","22:00"));
        System.out.println(minDifference);
    }
}
/**
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 * 示例 1：
 * 输入：timePoints = ["23:59","00:00"]
 * 输出：1
 * 示例 2：
 * 输入：timePoints = ["00:00","23:59","00:00"]
 * 输出：0
 * ["00:00","04:00","22:00"]
 * 120
 */

/**
 * 做题有时候就很迷.
 * 有的题以为想到了很牛逼的解，实则垃圾。
 * 有的题以为想到个笨方法，没想到达到最优了... 1ms 100%
 */

/**
 * 转成分钟 O(n)
 * 快排序 O(n*logN)
 * 遍历一遍  O(n)
 * 我去，居然想到了差不多最优解... 1ms
 *
 */
class Solution0539 {

    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 1440) {
            return 0;
        }
        int minutes[] = new int[timePoints.size()];
        int i = 0;
        for (String s : timePoints) {
            minutes[i++] = changeMinute(s);
        }
        int min = Integer.MAX_VALUE;
        // 排序
        Arrays.sort(minutes);
        // 依次比较。
        for (int j = 1; j < minutes.length; j++) {
            // min(大值-小值, 小值+1440-大值)
            int gap = Math.min(minutes[j] - minutes[j - 1], minutes[j-1]+1440 - minutes[j-1]);
            min = Math.min(gap, min);
        }
        // 比较头尾
        int gap = Math.min(minutes[0]+1440 - minutes[minutes.length-1], minutes[minutes.length-1] - minutes[0]);
        min = Math.min(gap, min);
        return min;
    }

    public int changeMinute(String s) {
        int fen = ((s.charAt(0) - '0') * 10 + (s.charAt(1) - '0')) * 60;
        return ((s.charAt(3) - '0') * 10 + (s.charAt(4) - '0')) + fen;
    }
}
