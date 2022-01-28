package com.shihaiyang.offer;

import com.shihaiyang.leetcodes.ListNode;

import java.util.Stack;

// 剑指 Offer II 025. 链表中的两数相加
// Offer025. 链表中的两数相加.[栈，返回新链表5ms].
public class Offer025 {
}

/**
 * 给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * 输出：[7,8,0,7]
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[8,0,7]
 */

/**
 * 不能翻转. 栈
 */
class SolutionOffer025 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        ListNode dummy = new ListNode();
        int flag = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || flag == 1) {
            int val = flag;
            if (!stack1.isEmpty()) {
                val += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                val += stack2.pop();
            }
            if (val >= 10) {
                val = val % 10;
                flag = 1;
            } else {
                flag = 0;
            }
            ListNode tmp = new ListNode();
            tmp.val = val;
            tmp.next = dummy.next;
            dummy.next = tmp;
        }
        return dummy.next;
    }
}
