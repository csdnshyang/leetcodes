package com.shihaiyang.leetcodes;
// 0206. 反转链表.[双指针].
// https://leetcode-cn.com/problems/reverse-linked-list/solution/206-fan-zhuan-lian-biao-shuang-zhi-zhen-zs0pl/
public class Leetcode0206 {
}
/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */

/**
 * 双指针就可以搞.之前有一个分段反转的。这个是那个的基础.
 */
class Solution0206 {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = head, next = head.next;
        head.next = null;
        while (next != null) {
            ListNode temp = pre;
            pre = next;
            next = next.next;
            pre.next = temp;
        }
        return pre;
    }
}
