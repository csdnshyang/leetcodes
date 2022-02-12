package com.shihaiyang.daily;

// 1189. “气球” 的最大数量.[所需字符数组计数取min 1ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
 * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
 * 示例 1：
 * 输入：text = "nlaebolko"
 * 输出：1
 * 示例 2：
 * 输入：text = "loonbalxballpoon"
 * 输出：2
 * 示例 3：
 * 输入：text = "leetcode"
 * 输出：0
 */
public class Leetcode1189 {
    Solution1189 solution1189 = new Solution1189();

    @Test
    public void case1() {
        Assertions.assertEquals(solution1189.maxNumberOfBalloons("nlaebolko"), 1);
    }
    @Test
    public void case2() {
        Assertions.assertEquals(solution1189.maxNumberOfBalloons("loonbalxballpoon"), 2);
    }
    @Test
    public void case3() {
        Assertions.assertEquals(solution1189.maxNumberOfBalloons("leetcode"), 0);
    }
    @Test
    public void case4() {
        Assertions.assertEquals(solution1189.maxNumberOfBalloons("bbloonalxallpoon"), 2);
    }
}

/**
 * balloon
 * 所有a b l o n 字母计数
 * 组成balloon需要 1个a 1个b 2个l 2个o 1个n
 * 遍历－吧。当出现-1的时候，说明不够了。
 */
class Solution1189 {
    public int maxNumberOfBalloons(String text) {
        int[] count = new int[26];
        char[] chars = text.toCharArray();
        for (char aChar : chars) {
            count[aChar - 'a']++;
        }
        int ans = Integer.MAX_VALUE;
        for (char c : new char[]{'a', 'b', 'l', 'o', 'n'}) {
            if (c == 'l' || c == 'o') {
                ans = Math.min(ans, count[c - 'a'] >> 1);
            } else {
                ans = Math.min(ans, count[c - 'a']);
            }
        }
        return ans;
    }
}
