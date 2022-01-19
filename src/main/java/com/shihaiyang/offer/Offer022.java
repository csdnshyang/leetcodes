package com.shihaiyang.offer;

import com.shihaiyang.leetcodes.ListNode;

// 剑指 Offer II 022. 链表中环的入口节点
// Offer022. 链表中环的入口节点.[快慢指针+慢指针 0ms 100%].
public class Offer022 {
}

/**
 * 快慢指针+慢指针
 * 快慢指针相遇之后，再加一个慢指针。
 * 两个慢指针再次相遇的时候，就是环起点
 */
class SolutionOffer022 {
    public ListNode detectCycle(ListNode head) {
        ListNode fast=head, slow=head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // 相遇
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        ListNode slow2 = head;
        while (slow != slow2) {
            slow = slow.next;
            slow2 = slow2.next;
        }
        return slow2;
    }
}