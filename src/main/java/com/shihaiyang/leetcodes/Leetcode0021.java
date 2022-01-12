package com.shihaiyang.leetcodes;

// 0021. 合并两个有序链表.[双指针+链表右移].
public class Leetcode0021 {

}

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 */

/**
 * 简单题
 * 就是两个指针，比较右移，判空
 */
class SolutionTwoPointer {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode p = dummy;
        while (list1 != null || list2 != null) {
            if (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    p.next = list1;
                    list1 = list1.next;
                } else {
                    p.next = list2;
                    list2 = list2.next;
                }
            }else if (list1 != null) {
                p.next = list1;
                list1 = list1.next;
            }else if (list2 != null) {
                p.next = list2;
                list2 = list2.next;
            }
            p=p.next;
            p.next=null;
        }
        return dummy.next;
    }
}