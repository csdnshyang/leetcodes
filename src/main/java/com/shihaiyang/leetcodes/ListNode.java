package com.shihaiyang.leetcodes;
// 通用单链表
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static ListNode generate(int[] arr) {
        ListNode dummy = new ListNode();
        ListNode p = dummy;
        for (int i = 0; i < arr.length; i++) {
            ListNode temp = new ListNode();
            temp.val = arr[i];
            p.next =temp;
            p = temp;
        }
        return dummy.next;
    }
 }
