package com.shihaiyang.offer;

// Offer II 065. 最短的单词编码[反向前缀树 10ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 单词数组 words 的 有效编码 由任意助记字符串 s 和下标数组 indices 组成，且满足：
 * <p>
 * words.length == indices.length
 * 助记字符串 s 以 '#' 字符结尾
 * 对于每个下标 indices[i] ，s 的一个从 indices[i] 开始、到下一个 '#' 字符结束（但不包括 '#'）的 子字符串 恰好与 words[i] 相等
 * 给定一个单词数组 words ，返回成功对 words 进行编码的最小助记字符串 s 的长度 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：words = ["time", "me", "bell"]
 * 输出：10
 * 解释：一组有效编码为 s = "time#bell#" 和 indices = [0, 2, 5] 。
 * words[0] = "time" ，s 开始于 indices[0] = 0 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
 * words[1] = "me" ，s 开始于 indices[1] = 2 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
 * words[2] = "bell" ，s 开始于 indices[2] = 5 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
 * 示例 2：
 * <p>
 * 输入：words = ["t"]
 * 输出：2
 * 解释：一组有效编码为 s = "t#" 和 indices = [0] 。
 */
public class Offer065 {
    SolutionOffer065 solutionOffer065 = new SolutionOffer065();

    @Test
    public void case1() {
        int lengthEncoding = solutionOffer065.minimumLengthEncoding(new String[]{"time", "me", "bell"});
        Assertions.assertEquals(lengthEncoding, 10);
    }

    @Test
    public void case2() {
        int lengthEncoding = solutionOffer065.minimumLengthEncoding(new String[]{"t"});
        Assertions.assertEquals(lengthEncoding, 2);
    }

    @Test
    public void case3() {
        int lengthEncoding = solutionOffer065.minimumLengthEncoding(new String[]{"me", "time", "me", "bell"});
        Assertions.assertEquals(lengthEncoding, 10);
    }

    @Test
    public void case4() {
        int lengthEncoding = solutionOffer065.minimumLengthEncoding(new String[]{"me", "time", "abe", "bell", "el"});
        Assertions.assertEquals(lengthEncoding, 17);
    }
}

/**
 * 后缀树。
 * 叶子节点是真实单词
 */
class SolutionOffer065 {
    TrieNode root;

    public int minimumLengthEncoding(String[] words) {
        root = new TrieNode();

        Map<TrieNode, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            TrieNode p = root;
            for (int j = words[i].length() - 1; j >= 0; j--) {
                p = p.get(words[i].charAt(j));
            }
            map.put(p, words[i].length() + 1);
        }

        int len = 0;
        for (TrieNode trieNode : map.keySet()) {
            if (!trieNode.hasChild) {
                len += map.get(trieNode);
            }
        }
        return len;
    }

    class TrieNode {
        TrieNode[] child;
        boolean hasChild;

        public TrieNode() {
            child = new TrieNode[26];
            hasChild = false;
        }

        public TrieNode get(char c) {
            int index = c - 'a';
            if (child[index] == null) {
                child[index] = new TrieNode();
                hasChild = true;
            }
            return child[index];
        }
    }
}