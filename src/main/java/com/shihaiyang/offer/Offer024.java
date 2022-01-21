package com.shihaiyang.offer;

import com.shihaiyang.leetcodes.ListNode;

// 剑指 Offer II 024. 反转链表
// Offer024. 反转链表.[双指针 0ms].
public class Offer024 {
}

/**
 * 给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。
 */
class SolutionOffer024 {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        // 记录head 和 head.next
        ListNode pre = null, newHead = head, next = head.next;
        // head.next 指向null
        newHead.next = null;
        // next为空时，说明newHead是最后一个节点
        while (next != null) {
            // 整体后移，newHead指向pre。
            pre = newHead;
            newHead = next;
            next = next.next;
            newHead.next = pre;
        }
        return newHead;
    }
}
