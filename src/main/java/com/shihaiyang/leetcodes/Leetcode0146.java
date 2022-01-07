package com.shihaiyang.leetcodes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 */
public class Leetcode0146 {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1); // 缓存是 {1=1}
        lRUCache.put(1, 1); // 缓存是 {1=1, 2=2}
        lRUCache.put(2, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.put(4, 1); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(2));    // 返回 3
    }
}

/**
 * 借助LinkedHashMap
 */
class LRUCache2 extends LinkedHashMap {
    private int max;
    public LRUCache2(int capacity) {
        super(capacity, 0.75f, true);
        max = capacity;
    }

    public int get(int key) {
        return (int) getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return super.size()>max;
    }
}
/**
 * 自己写LRU的话需要怎么做？
 * 需要一个Map，一个doubly-linked list
 * 手动双链表吧...用LinkedHashMap太bug了.
 */
class LRUCache {
    private int max;
    private HashMap<Integer, Node> map;
    // head最少使用，tail最新查询
    private Node head,tail;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        max = capacity;
    }

    public int get(int key) {
        Node val = map.get(key);
        if (val == null){
            return -1;
        }
        moveToLast(val);
        return val.val;
    }

    public void put(int key, int value) {
        if(!map.containsKey(key)){
            Node node = addNoe(key, value);
            map.put(key, node);
        }else {
            Node node = map.get(key);
            node.val = value;
            moveToLast(node);
        }
        if (removeEldestEntry()){
            remove();
        }
    }

    private Node addNoe(int key, int value) {
        Node node = new Node();
        node.key=key;
        node.val=value;
        if (map.size() == 0){
            head=node;
            tail=node;
        }else {
            node.pre=tail;
            tail.next=node;
            tail=node;
        }
        return node;
    }

    private void moveToLast(Node val) {
        if(val != tail){
            if(val == head){
                head=head.next;
            }
            // move to the last
            Node pre = val.pre, next = val.next;
            if(pre != null){
                pre.next=next;
            }
            next.pre=pre;
            tail.next = val;
            val.pre=tail;
            tail = val;
        }
    }

    private boolean removeEldestEntry() {
        return map.size() > max;
    }

    private void remove() {
        int key = head.key;
        head = head.next;
        head.pre=null;
        map.remove(key);
    }
}
class Node{
    int key;
    int val;
    Node pre;
    Node next;
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */