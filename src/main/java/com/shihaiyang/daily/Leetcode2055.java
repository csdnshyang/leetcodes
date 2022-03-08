package com.shihaiyang.daily;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// 2055. 蜡烛之间的盘子.[前缀和+预处理 8ms].
/*
给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0 开始的字符串 s ，它只包含字符 '*' 和 '|' ，其中 '*' 表示一个 盘子 ，'|' 表示一支 蜡烛 。
同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] 表示 子字符串 s[lefti...righti] （包含左右端点的字符）。对于每个查询，你需要找到 子字符串中 在 两支蜡烛之间 的盘子的 数目 。如果一个盘子在 子字符串中 左边和右边 都 至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间 。
比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。子字符串中在两支蜡烛之间的盘子数目为 2 ，子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
示例 1:
输入：s = "**|**|***|", queries = [[2,5],[5,9]]
输出：[2,3]
解释：
- queries[0] 有两个盘子在蜡烛之间。
- queries[1] 有三个盘子在蜡烛之间。
示例 2:
输入：s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
输出：[9,0,0,0,0]
解释：
- queries[0] 有 9 个盘子在蜡烛之间。
- 另一个查询没有盘子在蜡烛之间。
3 <= s.length <= 105
 */
public class Leetcode2055 {
    Solution2055 solution2055 = new Solution2055();

    @Test
    public void testcase1() {
        int[][] queries = new int[][]{
                {1,17},
                {4,5},
                {14,17},
                {5,11},
                {15,16}
        };
        int[] betweenCandles = solution2055.platesBetweenCandles("***|**|*****|**||**|*", queries);
        Assertions.assertArrayEquals(betweenCandles, new int[]{9,0,0,0,0});
    }
    @Test
    public void testcase2() {
        int[][] queries = new int[][]{
                {2,5},
                {5,9},
        };
        int[] betweenCandles = solution2055.platesBetweenCandles("**|**|***|", queries);
        Assertions.assertArrayEquals(betweenCandles, new int[]{2,3});
    }
}

/*
前缀和。
统计某个长度左边的蜡烛个数。

找到里这个盘子最近的蜡烛。lefti就找右边最近的一个。righti就找左边的最近一个。
左右各遍历一遍能知道最近的一个左右蜡烛。

再利用前缀和来预计算蜡烛左边的盘子数，
计算两个蜡烛之间的盘子数。
 */
class Solution2055 {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        // 盘子左侧第一个蜡烛索引
        int[] lefti = new int[s.length()];
        // 盘子右侧第一个蜡烛索引
        int[] righti = new int[s.length()];
        // 蜡烛左侧盘子数
        int[] cnti = new int[s.length()];

        int cnt = -1;
        int starCnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                cnt = i;
                cnti[i] = starCnt;
            } else {
                starCnt++;
            }
            lefti[i] = cnt;
        }
        cnt = s.length();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '|') {
                cnt = i;
            }
            righti[i] = cnt;
        }
        int[] ret = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int starti = righti[queries[i][0]];
            int endi = lefti[queries[i][1]];
            if (starti == s.length() || endi == -1 || endi <= starti) {
                ret[i] = 0;
            } else {
                ret[i] = cnti[endi] - cnti[starti];
            }
        }
        return ret;
    }
}