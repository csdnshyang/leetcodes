package com.shihaiyang.offer;
// 剑指 Offer II 035. 最小时间差
// Offer II 035. 最小时间差.[转分钟+容斥原理 1ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 * 示例 1：
 * 输入：timePoints = ["23:59","00:00"]
 * 输出：1
 * 示例 2：
 * 输入：timePoints = ["00:00","23:59","00:00"]
 * 输出：0
 *2 <= timePoints <= 2 * 104
 * timePoints[i] 格式为 "HH:MM"
 */
public class Offer035 {
    @Test
    public void case1() {
        SolutionOffer035 solutionOffer035 = new SolutionOffer035();
        int minDifference = solutionOffer035.findMinDifference(List.of("23:59", "00:00"));
        Assertions.assertEquals(minDifference, 1);
    }
    @Test
    public void case2() {
        SolutionOffer035 solutionOffer035 = new SolutionOffer035();
        int minDifference = solutionOffer035.findMinDifference(List.of("00:00","23:59","00:00"));
        Assertions.assertEquals(minDifference, 0);
    }
}

/**
 * 转成分钟
 * 需要注意的是,有可能出现 00:00  23:59  转一圈的情况，相差就是1分钟。
 * 所以需要加上24*60分钟再比较  1439 1440
 * 一个2880大小的数组。把所有时间放进去之后，比较间隔最小的。
 */
class SolutionOffer035 {
    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 1440) {
            return 0;
        }
        int all[] = new int[2880];
        for (String timePoint : timePoints) {
            int changeToMin = changeToMin(timePoint);
            if (all[changeToMin] == 1) {
                return 0;
            }
            all[changeToMin] = 1;
            all[changeToMin + 1440] = 1;
        }
        int ans = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < 2880; i++) {
            if (all[i] == 1) {
                if (index != -1) {
                    ans = Math.min(ans, i - index);
                }
                index = i;
            }
        }
        return ans;
    }

    private int changeToMin(String str) {
        int hour = (str.charAt(0) - '0') * 10 + (str.charAt(1) - '0');
        int min = (str.charAt(3) - '0') * 10 + (str.charAt(4) - '0');
        return hour * 60 + min;
    }
}