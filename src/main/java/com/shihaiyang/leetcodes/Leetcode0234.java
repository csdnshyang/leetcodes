package com.shihaiyang.leetcodes;

// 0234. 回文链表.[快慢指针+反转链表4ms].
// https://leetcode-cn.com/problems/palindrome-linked-list/solution/0234-hui-wen-lian-biao-kuai-man-zhi-zhen-fi9n/
public class Leetcode0234 {
}

/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 输入：head = [1,2]
 * 输出：false
 */

/**
 * 快慢指针.  O(n)时间  O(1)空间
 * 反转链表
 * fast.next==null 奇数个节点。slow 左右散开  (slow.next, next指针)
 * fast.next.next==null 偶数个节点  slow, slow.next 分别左右散开
 */
class Solution0234 {
    public boolean isPalindrome(ListNode head) {
        ListNode fast=head, slow=head;
        ListNode toLeft=null,toRight=null;
        ListNode pre = null, next = null;
        while (fast != null) {
            if (fast.next == null) {
                toRight = slow.next;
                toLeft = next;
                break;

            } else if (fast.next.next == null) {
                toRight = slow.next;
                toLeft = slow;
                toLeft.next = next;
                break;
            } else {
                // 把前半段反转
                if (next == null) {
                    next = slow;
                } else{
                    pre = next;
                    next = slow;
                }
                slow = slow.next;
                fast = fast.next.next;
                next.next = pre;
            }
        }
        while (toLeft != null && toRight != null) {
            if (toLeft.val != toRight.val) {
                return false;
            }
            toLeft = toLeft.next;
            toRight = toRight.next;
        }
        return true;
    }
}


