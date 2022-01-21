package com.shihaiyang.offer;

import com.shihaiyang.leetcodes.ListNode;

// 剑指 Offer II 026. 重排链表
// Offer026. 重排链表.[快慢指针+翻转链表+拼接1ms].
public class Offer026 {
}

/**
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *  L0 → L1 → … → Ln-1 → Ln 
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 输入: head = [1,2,3,4]
 * 输出: [1,4,2,3]
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,5,2,4,3]
 */

class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        // 快慢指针找到中间节点
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 两个节点情况
        if (slow == head) {
            return;
        }
        // 翻转后半部分链表
        ListNode pre = null, newHead = slow.next, next = newHead.next;
        // 左半部分最后节点指向null
        slow.next = null;
        // 又半部分新的最左节点指向null
        newHead.next = null;
        while (next != null) {
            pre = newHead;
            newHead = next;
            next = next.next;
            newHead.next = pre;
        }
        // 拼接链表
        ListNode left = head, right;
        while (newHead != null) {
            right = newHead;
            newHead = newHead.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
        }
    }
}