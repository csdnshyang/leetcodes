package com.shihaiyang.leetcodes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 0720. 词典中最长的单词.[排序+前缀Hash 16ms].
public class Leetcode0720 {
    public static void main(String[] args) {
        Solution0720 solution0720 = new Solution0720();
//        String longestWord = solution0720.longestWord(new String[]{"w","wo","wor","worl", "world"});
//        String longestWord = solution0720.longestWord(new String[]{"a","banana","app","appl","ap","apply","apple"});
        String longestWord = solution0720.longestWord(new String[]{"yo","ew","fc","zrc","yodn","fcm","qm","qmo","fcmz","z","ewq","yod","ewqz","y"});
        System.out.println(longestWord);
    }
}

/**
 * 给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。
 * 若无答案，则返回空字符串。
 * 示例 1：
 * 输入：
 * words = ["w","wo","wor","worl", "world"]
 * 输出："world"
 * 解释：
 * 单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
 * ["a","banana","app","appl","ap","apply","apple"]
 * apple
 * ["yo","ew","fc","zrc","yodn","fcm","qm","qmo","fcmz","z","ewq","yod","ewqz","y"]
 */
class Solution0720 {
    public String longestWord(String[] words) {
        // 字典序排序[a,ab,c,cda,fa,fab]
        Arrays.sort(words);
        String maxStr = "";
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            // 除了第一个字母，都要保证之前出现过前缀
            if (words[i].length() == 1 || map.containsKey(words[i].substring(0, words[i].length() - 1))) {
                if (maxStr.length() < words[i].length()) {
                    maxStr = words[i];
                }
                map.put(words[i], words[i]);
            }
        }
        return maxStr;
    }
}
