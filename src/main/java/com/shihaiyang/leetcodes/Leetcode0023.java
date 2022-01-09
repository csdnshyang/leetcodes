package com.shihaiyang.leetcodes;

import java.util.*;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
// 23. 合并K个升序链表.[典型小顶堆积].
public class Leetcode0023 {
    public static void main(String[] args) {

    }
}

/**
 * 典型的小根堆积应用
 * 使用小根堆积。不需要记录是那个链表，只需要取到最小时，把他的next放入小根堆积。
 * 每次取最小，放next
 * 换成优先队列之后，效率提升不少。从230ms优化到4ms了
 执行用时：4 ms, 在所有 Java 提交中击败了69.05%的用户
 内存消耗：40.1 MB, 在所有 Java 提交中击败了59.66%的用户
 *
 */
class Solution00232 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length<1){
            return null;
        }
        Queue<ListNode> queue = new PriorityQueue<>((v1,v2)-> v1.val-v2.val);
        for(int i=0;i<lists.length;i++){
            if(lists[i] == null){
                continue;
            }
            queue.offer(lists[i]);
        }
        ListNode node = new ListNode();
        ListNode tail = node;
        while(!queue.isEmpty()){
            ListNode poll = queue.poll();
            tail.next=poll;
            tail=poll;
            if (tail.next != null){
                queue.offer(tail.next);
            }
        }
        return node.next;
    }
}
/**
 * 合并k个链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * k个指针分别执行k个单链，每次遍历找到一个k个链表中最小的节点放入合并的链表，最小节点的链表右移
 * 1500ms 只战胜5%的java...这个方式不行啊
 * map 换成数组之后，优化到230ms，战胜8%
 */
class Solution0023 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue queue = new PriorityQueue();
        ListNode[] heads = new ListNode[lists.length];
        if (lists.length<1){
            return null;
        }
        int emptyFlag=0;
        for(int i=0;i<lists.length;i++){
            if(lists[i] == null){
                emptyFlag++;
            }
            heads[i]=lists[i];
        }

        ListNode node = new ListNode();
        ListNode p = node;
        while(emptyFlag<lists.length){
            int minValue=0;
            for(int i=0;i<heads.length;i++) {
                if(heads[i] != null){
                    minValue=heads[i].val;
                    break;
                }
            }
            int minIndex = 0;
            for (int k=0;k<heads.length;k++){
                ListNode value = heads[k];
                if(value != null){
                    if (minValue >= value.val){
                        minValue = value.val;
                        minIndex = k;
                    }
                }
            }
            ListNode value = heads[minIndex];
            if(value != null){
                heads[minIndex]=value.next;
                p.next = value;
                p=value;
                if(value.next == null){
                    emptyFlag++;
                }
            }
        }
        return node.next;
    }
}
