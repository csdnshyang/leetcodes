package com.shihaiyang.daily;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

// 0432. 全 O(1) 的数据结构
/*
请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。

实现 AllOne 类：

AllOne() 初始化数据结构的对象。
inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。
dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例保证：在减少计数前，key 存在于数据结构中。
getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
示例：
输入
["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
输出
[null, null, null, "hello", "hello", null, "hello", "leet"]
解释
AllOne allOne = new AllOne();
allOne.inc("hello");
allOne.inc("hello");
allOne.getMaxKey(); // 返回 "hello"
allOne.getMinKey(); // 返回 "hello"
allOne.inc("leet");
allOne.getMaxKey(); // 返回 "hello"
allOne.getMinKey(); // 返回 "leet"
 */
public class Leetcode0432 {
    @Test
    public void testcase1() {
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("hello");
        Assertions.assertEquals(allOne.getMaxKey(), "hello"); // 返回 "hello"
        Assertions.assertEquals(allOne.getMinKey(), "hello"); // 返回 "hello"
        allOne.inc("leet");
        Assertions.assertEquals(allOne.getMaxKey(), "hello"); // 返回 "hello"
        Assertions.assertEquals(allOne.getMinKey(), "leet"); // 返回 "leet"
    }
}
/*
Map+doubly-linked
Map存储字符串对应的Node节点。
Node是带有头尾指针的双向链表。当遇到最大，或者最小时，移动节点。
如果cnt=0。删除节点。
如果cnt<min.
如果cnt>max.
字符串加
1. 如果节点存在
    如果超过左侧节点。交换节点顺序。
    如果左侧节点是head节点。head节点指向当前节点。
2. 如果节点不存在
    添加到有次。更新tail节点
字符串减
1. 如果节点存在
    
 */
class AllOne {
    DNode head;
    DNode tail;
    Map<String, DNode> map;
    public AllOne() {
        map = new HashMap<>();

    }

    public void inc(String key) {
        if (map.containsKey(key)) {
            DNode dNode = map.get(key);
            int inc = dNode.inc();
            // 修改的节点左移一下。
            if (dNode.pre.cnt < inc) {

            }
        } else {
            DNode dNode = new DNode(key);
            map.put(key, dNode);
        }
    }

    public void dec(String key) {

    }

    public String getMaxKey() {
        return head.key;
    }

    public String getMinKey() {
        return tail.key;
    }
}
class DNode{
    public String key;
    public int cnt = 0;
    public DNode pre;
    public DNode next;

    public DNode(String key) {
        this.key = key;
        this.cnt = 1;
    }

    public int inc() {
        cnt++;
        return cnt;
    }

    public int dec() {
        cnt--;
        return cnt;
    }
}