package com.shihaiyang.offer;

import com.shihaiyang.leetcodes.ListNode;

// 剑指 Offer II 027. 回文链表
// Offer027. 回文链表.[快慢指针+反转链表 3ms].
public class Offer027 {
    public static void main(String[] args) {
        ListNode node = ListNode.generate(new int[]{1,2,3,3,2,1});
        SolutionOffer027 solutionOffer027 = new SolutionOffer027();
        boolean palindrome = solutionOffer027.isPalindrome(node);
        System.out.println(palindrome);
    }
}

/**
 * 快慢指针+反转链表
 * O(1) 时间复杂度
 */
class SolutionOffer027 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 快慢指针找到后一半的首元素 偶数元素是后半段，奇数元素是中心元素的后半
        ListNode slow = head, fast = head, pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 此时slow已经在中心的后一个位置。如果从结尾到slow与从头到slow每一个元素都相等。则是回文。
        ListNode next = slow.next;
        // 后半段首节点断开，防止死循环
        slow.next = null;
        // 翻转
        while (next != null) {
            pre = slow;
            slow = next;
            next = next.next;
            slow.next = pre;
        }
        ListNode newHead = slow;
        while (head != null && newHead != null) {
            if (head.val != newHead.val) {
                return false;
            }
            newHead = newHead.next;
            head = head.next;
        }
        return true;
    }
}