package com.shihaiyang.leetcodes;

import java.util.HashSet;
import java.util.Set;

// 0160. 相交链表.[双指针1ms(骚)+集合7ms].
// https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/acm-xuan-shou-tu-jie-leetcode-xiang-jiao-c8zo/
public class Leetcode0160 {
}

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 */

/**
 * 方法1：遍历第一个链表，用Set暂存，再遍历第二个，判断是否存在. 时间复杂度O(m+n) 空间复杂度O(m)
 */
class Solution0160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }
}

/**
 * 方法2：双指针。看到知春路金刀的思路。非常之骚。
 * 思路就是循环两遍，  2+3 = 3+2  ...加法符合交换律。
 * 假如A的前半段长度：2   B的前半段长度：3
 * 相交的后半段长度是多少都无所谓，比如是x。
 * 循环两遍是指A遍历到头之后，指向B的头，再遍历。B遍历到头之后指向A的头，再遍历。
 * 所以最终。
 * A走了距离：2+x+3+x
 * B走了距离：3+x+2+x
 * 由此我们可以知道...如果两个相交，第二次走到相交点，必遇到  (2+x+3=3+x+2)
 */
class Solution0160DoublePointer {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA, q = headB;
        int flagA = 0, flagB = 0;
        while (p != null && q != null) {
            // 相等，说明相交了.  2+x+3 = 3+x+2了
            if (p == q) {
                return p;
            }
            // 右移，第一次到头就设置为另一个链表头结点，继续右移
            if (p.next == null && flagA == 0) {
                // 此时完成了2+x, 切换到3+x
                flagA = 1;
                p = headB;
            }else{
                p = p.next;
            }
            if (q.next == null && flagB == 0) {
                // 此时完成了3+x, 切换到2+x
                flagB = 1;
                q = headA;
            } else {
                q = q.next;
            }
        }
        // 第二次右移到null，说明不想交.
        return null;
    }
}
