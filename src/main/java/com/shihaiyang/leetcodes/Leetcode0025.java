package com.shihaiyang.leetcodes;

public class Leetcode0025 {
}
/*
想到双指针+滑动窗口(这叫滑动窗口吗)
每次后一个指向前一个。
记录第一个节点，
第一个窗口中最后一个节点应该作为head。
窗口的第一个节点应该指向最后一个节点的next

-----

写了半天，没写出来。
看别人结题思路，用栈。好像是简单一点。
*/

class Solution0025 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode left=null, right=null, leftPre=null, rightNext=null;
        int notEnough=0;
        rightNext=head;
        for(int i=0;i<k;i++){
            if (rightNext==null){
                notEnough=1;
                break;
            }
            rightNext=rightNext.next;
        }
        leftPre=rightNext;
        while (notEnough == 0) {
            left= left==null ? head : left;
            right=left.next;
            for(int i=0;i<k;i++){
                left.next=leftPre;
                leftPre=left;
                left = right;
                right = right.next;
            }
            rightNext=right.next;

            // 判断是否够n个。找到rightNext节点
            for(int i=0;i<k;i++){
                if (rightNext==null){
                    notEnough=1;
                    break;
                }
                rightNext=rightNext.next;
            }
        }
        return head;
    }
}
