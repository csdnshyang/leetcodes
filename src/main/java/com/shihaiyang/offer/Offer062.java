package com.shihaiyang.offer;

// Offer II 062. 实现前缀树[嵌套数组+是否结束 33ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/QC3q1f
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * word 和 prefix 仅由小写英文字母组成
 */
public class Offer062 {
    @Test
    public void case1() {
        Trie trie = new Trie();
        Assertions.assertTrue(!trie.startsWith("a")); // 返回 True
        trie.insert("apple");
        Assertions.assertTrue(trie.search("apple"));   // 返回 True
        Assertions.assertTrue(!trie.search("app"));     // 返回 False
        Assertions.assertTrue(trie.startsWith("app")); // 返回 True
        trie.insert("app");
        Assertions.assertTrue(trie.search("app"));     // 返回 True
    }
}

/**
 * 字典树来存储，嵌套map
 * 记录是否是结尾。isEnd。
 * 记录层级，layer。
 */
class Trie {
    Trie[] tries;
    int layer;
    int isEnd = 0;
    public Trie() {
        tries = new Trie[26];
        layer = 0;
    }
    public void insert(String word) {
        Trie p = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (p.tries[index] == null) {
                p.tries[index] = new Trie();
                p.tries[index].layer = p.layer + 1;
            }
            p = p.tries[index];
        }
        p.isEnd++;
    }

    // 完整匹配了 true
    public boolean search(String word) {
        Trie p = searchPrefix(word);
        if (p != null && p.layer == word.length() && p.isEnd > 0) {
            return true;
        }
        return false;
    }

    // 如果已经有这个前缀 true
    public boolean startsWith(String prefix) {
        Trie p = searchPrefix(prefix);
        if (p != null && p.layer == prefix.length()) {
            return true;
        }
        return false;
    }

    public Trie searchPrefix(String prefix) {
        Trie p = this;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (p.tries[index] != null) {
                p = p.tries[index];
            } else {
                return null;
            }
        }
        return p;
    }
}
