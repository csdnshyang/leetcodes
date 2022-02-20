package com.shihaiyang.contest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

// 6014. 构造限制重复的字符串[贪心+优先队列].
public class Contest6014 {
    Solution6014 solution6014 = new Solution6014();
    @Test
    public void case1() {
        String limitedString = solution6014.repeatLimitedString("cczazcc", 3);
        Assertions.assertEquals(limitedString, "zzcccac");
    }
    @Test
    public void case2() {
        String limitedString = solution6014.repeatLimitedString("aababab", 2);
        Assertions.assertEquals(limitedString, "bbabaa");
    }
    @Test
    public void case3() {
        String limitedString = solution6014.repeatLimitedString("xyutfpopdynbadwtvmxiemmusevduloxwvpkjioizvanetecnuqbqqdtrwrkgt", 1);
        Assertions.assertEquals(limitedString, "zyxyxwxwvwvuvuvututstrtrtqpqpqponononmlmkmkjigifiededededcbaba");
    }
}
class Solution6014 {
    public String repeatLimitedString(String s, int repeatLimit) {
        // {字符,个数}
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            chars[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (chars[i] > 0) {
                priorityQueue.add(new int[]{i, chars[i]});
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            int cnt = Math.min(repeatLimit, poll[1]);
            for (int i = 0; i < cnt; i++) {
                stringBuffer.append((char)('a' + poll[0]));
            }
            poll[1] -= cnt;
            if (poll[1] > 0) {
                // 需要过渡
                if (!priorityQueue.isEmpty()) {
                    int[] guodu = priorityQueue.poll();
                    stringBuffer.append((char) ('a' + guodu[0]));
                    guodu[1] -= 1;
                    if (guodu[1] > 0) {
                        priorityQueue.add(guodu);
                    }
                } else {
                    break;
                }
                priorityQueue.add(poll);
            }
        }
        return stringBuffer.toString();
    }
}
