package com.shihaiyang.leetcodes;

// 0143. 重排链表.[快慢指针+翻转链表+拼接链表].
/**
三指针或者加一个栈也可以做
 0 n 1 n-1 说明规则是最后的一位插入到前面
 不能用栈..
 ----
学到的思路:快慢指针+翻转链表
快慢指针：一个指针右移两个，一个指针右移一个。找到中间位置。
翻转链表：翻转中间位置右侧链表。
拼接两个链表
 */
class Solution0143 {
    public void reorderList(ListNode head) {
        if(head == null){
            return;
        }
        // 快慢指针 快指针后两位都不为空的情况下,慢指针右移
        ListNode slow=head, fast=head;
        while(fast.next!=null && fast.next.next !=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        // 特判
        if(slow==head){
            return;
        }
        // 翻转链表后半段
        ListNode newTail=slow.next, newHead=slow.next, p=newHead.next;
        while(p!=null){
            ListNode temp= newHead;
            newHead = p;
            p=p.next;
            newHead.next=temp;
        }
        // 两个链表结尾置空
        slow.next=null;
        newTail.next=null;
        // 拼接链表
        ListNode q=head;
        while(q != null && newHead != null){
            ListNode temp = newHead;
            newHead=newHead.next;
            temp.next=q.next;
            q.next=temp;
            q=temp.next;
        }
    }
}
