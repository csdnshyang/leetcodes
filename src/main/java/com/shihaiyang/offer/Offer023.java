package com.shihaiyang.offer;

import com.shihaiyang.leetcodes.ListNode;

// 剑指 Offer II 023. 两个链表的第一个重合节点
// Offer023. 两个链表的第一个重合节点.[双指针1ms 100%].
public class Offer023 {
    public static void main(String[] args) {

    }
}

/**
 * 给定两个单链表的头节点 headA 和 headB ，请找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * 图示两个链表在节点 c1 开始相交：
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Intersected at '8'
 * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 */

/**
 * 如果相交，则是这样的结构。A有不相交的部分a和相交的部分x，B有不相交的部分b和相交的部分x、
 * 我们可以得出一个等式  a+x+b=b+x+a
 * A:|---a----|---x----|
 * B:|---b--|---x----|
 *
 * 代码实现就是双指针从AB开始遍历，第一次到结尾的时候，从另一个节点头部开始遍历(A遍历完指向B再遍历B)。这样就能得到上面等式
 * |---a----|---x----|---b--|-
 * |---b--|---x----|---a----|-
 */

class SolutionOffer023 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA, q = headB;
        boolean flagA = true, flagB = true;
        while (flagA || flagB || p != null || q != null) {
            if (p == q) {
                return p;
            }
            if (p.next == null && flagA) {
                p = headB;
                flagA = false;
            } else {
                p = p.next;
            }
            if (q.next == null && flagB) {
                q = headA;
                flagB = false;
            } else {
                q = q.next;
            }
        }
        return null;
    }
}