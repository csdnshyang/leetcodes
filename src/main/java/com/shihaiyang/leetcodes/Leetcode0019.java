package com.shihaiyang.leetcodes;

/**
0019. 删除链表的倒数第 N 个结点
*/
public class Leetcode0019 {
    public static void main(String[] args) {
        Solution0019 solution0019 = new Solution0019();
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/**
 三指针。
 从头数n个，然后第二个开始同步移动。知道第一个指针下一个指向null时，第二个即倒数N个。
 第三指针要记录第二个指针前一个。
 */

/**
 * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗：36.5 MB, 在所有 Java 提交中击败了31.27%的用户
 * 通过测试用例：208 / 208
 */
class Solution0019 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first, second, secondPre=null;
        first = head;
        for(int i=0;i<n;i++){
            if(first.next != null){
                first = first.next;
            }
        }
        second=head;
        while(first.next != null){
            first=first.next;
            secondPre=second;
            second=second.next;
        }
        if(secondPre !=null){
            secondPre.next=second.next;
        }
        if(second == head){
            head = null;
        }
        return head;
    }
}
