package com.shihaiyang.offer;

import com.shihaiyang.leetcodes.ListNode;

// 剑指 Offer II 021. 删除链表的倒数第 n 个结点.[双指针 0ms 100%].
// Offer021. 删除链表的倒数第 n 个结点.[双指针 0ms 100%].
public class Offer021 {

}
/**
 * 给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 1 <= n <= sz
 */

class SolutionOffer021 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head, q = head, r = null;
        int k=0;
        while (k < n) {
            p = p.next;
            k++;
        }
        while (p != null) {
            p = p.next;
            r=q;
            q = q.next;
        }
        if (r == null) {
            head = head.next;
        } else {
            r.next = q.next;
        }
        return head;
    }
}
