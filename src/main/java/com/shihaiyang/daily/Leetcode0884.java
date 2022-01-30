package com.shihaiyang.daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 0884. 两句话中的不常见单词.[map+遍历 2ms].
public class Leetcode0884 {
    public static void main(String[] args) {
        Solution0884 solution0884 = new Solution0884();
        String[] uncommonFromSentences = solution0884.uncommonFromSentences("this apple is sweet", "this apple is sour");
        System.out.println(Arrays.toString(uncommonFromSentences));
    }
}

/**
 * 句子 是一串由空格分隔的单词。每个 单词 仅由小写字母组成。
 * 如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 没有出现 ，那么这个单词就是 不常见的 。
 * 给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织。
 * 输入：s1 = "this apple is sweet", s2 = "this apple is sour"
 * 输出：["sweet","sour"]
 */

/**
 * map
 */
class Solution0884 {
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> map = new HashMap<>();
        String[] strings = s1.split(" ");
        for (int i = 0; i < strings.length; i++) {
            map.put(strings[i], map.getOrDefault(strings[i], 0) + 1);
        }
        String[] strings2 = s2.split(" ");
        for (int i = 0; i < strings2.length; i++) {
            map.put(strings2[i], map.getOrDefault(strings2[i], 0) + 1);
        }
        String[] tmp = new String[map.size()];
        int index = 0;
        for (String key : map.keySet()) {
            if (map.get(key) == 1) {
                tmp[index++] = key;
            }
        }
        String[] ret = new String[index];
        System.arraycopy(tmp, 0, ret, 0, index);
        return ret;
    }
}
