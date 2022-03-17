package com.shihaiyang.daily;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        allOne.inc("hello");
        allOne.dec("leet");
        Assertions.assertEquals(allOne.getMaxKey(), "hello"); // 返回 "hello"
        Assertions.assertEquals(allOne.getMinKey(), "hello"); // 返回 "hello"
        allOne.dec("leet");
        allOne.inc("leet");
        allOne.inc("leet");
        allOne.inc("leet2");
        allOne.inc("leet3");
        Assertions.assertEquals(allOne.getMaxKey(), "hello"); // 返回 "hello"
        Assertions.assertEquals(allOne.getMinKey(), "leet3"); // 返回 "hello"
    }

    //["AllOne","inc","inc","inc","inc","getMaxKey","inc","inc","inc","dec","inc","inc","inc","getMaxKey"]
    //[[],["hello"],["goodbye"],["hello"],["hello"],[],["leet"],["code"],["leet"],["hello"],["leet"],["code"],["code"],[]]
    @Test
    public void testcase2() {
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("goodbye");
        allOne.inc("hello");
        allOne.inc("hello");
        allOne.inc("leet");
        allOne.inc("code");
        allOne.inc("leet");
        allOne.dec("hello");
        allOne.inc("leet");
        allOne.inc("code");
        allOne.inc("code");
        Assertions.assertTrue(allOne.getMaxKey().equals("leet") || allOne.getMaxKey().equals("code"));
    }

}

/*
Map+doubly-linked
Map存储字符串对应的Node节点。
Node是带哨兵的双向链表。
从小打大的顺序。
inc的时候
如果不存在，判断是next是哨兵或者next>1,就在root后加节点。
如果存在，如果是next是哨兵，或者next > +1，就在cur后面加节点。
dec的时候
如果cur cnt=1;直接从node key中移除
如果cur pre==root或者pre<cnt-1   ,pre后加节点
否则pre key加key元素
如果node key是空，node移除

 */
class AllOne {
    DNode root; // 哨兵
    Map<String, DNode> map;

    public AllOne() {
        map = new HashMap<>();
        // 带头尾结点
        root = new DNode("", 0);
        root.next = root;
        root.pre = root;
    }

    public void inc(String key) {
        /*
        新增：
        如果存在key
            curNode.pre 的cnt是否等于inc。
                如果相等，直接加入key
                如果不相等，说明pre的cnt更大，需要在pre后加一个inc节点
            key从curNode中移除;如果key为空，移除curNode;
         */
        if (map.containsKey(key)) {
            DNode curNode = map.get(key);
            DNode next = curNode.next;
            if (next == root || next.cnt > curNode.cnt + 1) {
                map.put(key, curNode.insert(new DNode(key, curNode.cnt + 1)));
            } else {
                next.keys.add(key);
                map.put(key, next);
            }
            // 从key从当前node移除，如果key为空，就移除node
            curNode.keys.remove(key);
            if (curNode.keys.isEmpty()) {
                curNode.remove();
            }
        } else {
            /*
            如果不存在
                root.next==root,说明没有节点。在head后加新节点
                tail.pre.cnt == 1,说明有只出现一次的节点,tail.pre中加入key
                tail.pre.cnt > 1,说明没有只出现一次的节点,tail.pre后加入新节点
             */
            if (root.next == root || root.next.cnt > 1) {
                map.put(key, root.insert(new DNode(key, 1)));
            } else {
                root.next.keys.add(key);
                map.put(key, root.next);
            }
        }
    }

    /*
    cnt-1情况
    如果存在key
        dec=curNode.cnt-1;
        curNode.next.cnt == dec; 在next.key中加入key
        !=dec curNode后面加入新node
        curNode中移除key;如果curNode.key.isEmpty();移除curNode
     */
    public void dec(String key) {
        if (map.containsKey(key)) {
            DNode curNode = map.get(key);
            if (curNode.cnt == 1) {
                map.remove(key);
            }else{
                DNode pre = curNode.pre;
                if (pre == root || pre.cnt < curNode.cnt - 1) {
                    map.put(key, pre.insert(new DNode(key, curNode.cnt - 1)));
                } else {
                    pre.keys.add(key);
                    map.put(key, pre);
                }
            }
            curNode.keys.remove(key);
            if (curNode.keys.isEmpty()) {
                curNode.remove();
            }
        }
    }

    public String getMaxKey() {
        return root.pre != null ? root.pre.keys.iterator().next() : "";
    }

    public String getMinKey() {
        return root.next != null ? root.next.keys.iterator().next() : "";
    }
}

class DNode {
    public Set<String> keys;
    public int cnt = 0;
    public DNode pre;
    public DNode next;

    public DNode(String key, int cnt) {
        this.keys = new HashSet<>();
        this.keys.add(key);
        this.cnt = cnt;
    }

    // 在this后加入一个node节点
    public DNode insert(DNode dNode) {
        dNode.pre = this;
        dNode.next = this.next;
        dNode.next.pre = dNode;
        dNode.pre.next = dNode;
        return dNode;
    }

    public void remove() {
        this.pre.next = this.next;
        this.next.pre = this.pre;
    }
}