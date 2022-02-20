package com.shihaiyang.contest;

import com.shihaiyang.leetcodes.ListNode;

// 6013. 合并零之间的节点[].
public class Contest6013 {
}

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
class Solution6013 {
    public ListNode mergeNodes(ListNode head) {
        ListNode p = head.next;
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        ListNode pre = null;
        while (p != null) {
            if (p.val != 0) {
                cur.val += p.val;
                p = p.next;
            }
            if (p.val == 0) {
                ListNode node = new ListNode();
                cur.next = node;
                pre = cur;
                cur = node;
                p = p.next;
            }
        }
        pre.next = null;
        return dummy;
    }
}