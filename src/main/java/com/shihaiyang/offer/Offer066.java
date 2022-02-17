package com.shihaiyang.offer;
// Offer II 066. 单词之和.[前缀树记录权值 10ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 实现一个 MapSum 类，支持两个方法，insert 和 sum：
 * <p>
 * MapSum() 初始化 MapSum 对象
 * void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
 * int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 * 示例：
 * 输入：
 * inputs = ["MapSum", "insert", "sum", "insert", "sum"]
 * inputs = [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * 输出：
 * [null, null, 3, null, 5]
 * 解释：
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 */
public class Offer066 {

    @Test
    public void case1() {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        Assertions.assertEquals(mapSum.sum("ap"), 3);
        mapSum.insert("app", 2);
        mapSum.insert("apc", 2);
        mapSum.insert("bpc", 2);
        Assertions.assertEquals(7, mapSum.sum("ap"));
        Assertions.assertEquals(5, mapSum.sum("app"));
        Assertions.assertEquals(2, mapSum.sum("b"));
    }
}

class MapSum {
    TrieNode root;
    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode p = root;
        for (int i = 0; i < key.length(); i++) {
            p = p.get(key.charAt(i));
        }
        p.setVal(val);
    }

    public int sum(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); i++) {
            p = p.get(prefix.charAt(i));
        }
        return sumNode(p);
    }

    public int sumNode(TrieNode node) {
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            if (node.child[i] != null) {
                sum += sumNode(node.child[i]);
            }
        }
        return sum + node.val;
    }

    class TrieNode{
        TrieNode[] child;
        int val;

        public TrieNode() {
            child = new TrieNode[26];
            val = 0;
        }

        public TrieNode get(char c) {
            if (child[c - 'a'] == null) {
                child[c - 'a'] = new TrieNode();
            }
            return child[c - 'a'];
        }

        public void setVal(int val) {
            this.val = val;
        }
    }
}