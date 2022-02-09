package com.shihaiyang.offer;

import com.shihaiyang.leetcodes.ListNode;

// 剑指 Offer II 028. 展平多级双向链表
// Offer028. 展平多级双向链表.[递归+双向链表拼接 0ms].
public class Offer028 {
}
/**
 * 示例 1：
 *
 * 输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * 输出：[1,2,3,7,8,11,12,9,10,4,5,6]
 * 解释：
 *
 * 输入的多级列表如下图所示：
 *
 *
 *
 * 扁平化后的链表如下图：
 *
 *
 * 示例 2：
 *
 * 输入：head = [1,2,null,3]
 * 输出：[1,3,2]
 * 解释：
 *
 * 输入的多级列表如下图所示：
 *
 *   1---2---NULL
 *   |
 *   3---NULL
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *  
 *
 * 如何表示测试用例中的多级链表？
 *
 * 以 示例 1 为例：
 *
 *  1---2---3---4---5---6--NULL
 *          |
 *          7---8---9---10--NULL
 *              |
 *              11--12--NULL
 * 序列化其中的每一级之后：
 *
 * [1,2,3,4,5,6,null]
 * [7,8,9,10,null]
 * [11,12,null]
 * 为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。
 *
 * [1,2,3,4,5,6,null]
 * [null,null,7,8,9,10,null]
 * [null,11,12,null]
 * 合并所有序列化结果，并去除末尾的 null 。
 *
 * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 */

/**
 * 递归。当有子时，递归遍历子。
 * 递归返回该层最后一个节点，并且让返回的节点next指向进入递归前节点的next
 */
class SolutionOffer028 {
    public Node flatten(Node head) {
        Node p = head;
        concat(p);
        return head;
    }

    public Node concat(Node head) {
        Node pre = null;
        while (head != null) {
            if (head.child != null) {
                // 当有child的时候，next改为child，但是要暂存原来的next。
                Node childTail = concat(head.child);

                // 双向链表连接
                Node oldNext = head.next;
                head.next = head.child;
                head.child = null;
                head.next.prev = head;

                // 递归返回下一层的最后节点，把最后节点与暂存的next连接。
                childTail.next = oldNext;
                if (oldNext != null) {
                    oldNext.prev = childTail;
                }
                pre = childTail;
                head = childTail.next;
            }else{
                pre = head;
                head = head.next;
            }
        }
        return pre;
    }
}

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public static Node generate(int[] arr) {
        Node dummy = new Node();
        Node p = dummy;
        for (int i = 0; i < arr.length; i++) {
            Node temp = new Node();
            temp.val = arr[i];
            p.next =temp;
            p = temp;
        }
        return dummy.next;
    }
};

