package com.shihaiyang.leetcodes;
// 25. K 个一组翻转链表.[递归+翻转链表+返回NewHead].
public class Leetcode0025 {
}
/*
想到双指针+滑动窗口(这叫滑动窗口吗)
每次后一个指向前一个。
记录第一个节点，
第一个窗口中最后一个节点应该作为head。
窗口的第一个节点应该指向最后一个节点的next

-----

写了半天，没写出来。
看别人结题思路，用栈。好像是简单一点。
自定义

----
递归实现下试试
递归方法返回一个newHead。
方法中实现链表反转。
并且newTail指向递归方法
如果不超过k，直接返回本身。结束递归。
*/
/**
执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：38.8 MB, 在所有 Java 提交中击败了24.02%的用户 通过
测试用例：62 / 62
*/
class Solution0025 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k<=1){
            return head;
        }
        return reverseGroup(head, k);
    }
    public ListNode reverseGroup(ListNode head, int k){
        ListNode subNewHead=null, subNewTail=null, newHead=null;
        int notEnough=0;
        subNewTail = head;
        subNewHead = head;
        // 2的话，只右移一次即可
        for(int i=0;i<k-1;i++){
            subNewHead = subNewHead.next;
            if(subNewHead== null){
                notEnough=1;
                break;
            }
        }
        if (notEnough == 1){
            return head;
        }
        newHead = subNewHead.next;
        // 开始反转
        ListNode first = head;
        ListNode second = first.next;
        for(int i=0;i<k-1;i++){
            ListNode temp = first;
            first=second;
            second = second.next;
            first.next=temp;
        }
        subNewTail.next=reverseGroup(newHead, k);
        return subNewHead;
    }
}
