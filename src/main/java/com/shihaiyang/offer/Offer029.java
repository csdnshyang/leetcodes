package com.shihaiyang.offer;
// 剑指 Offer II 029. 排序的循环链表
// Offer029. 排序的循环链表.[遍历,分情况判断 0ms].
public class Offer029 {
    public static void main(String[] args) {
        Node generate = Node.generate(new int[]{1, 3, 3});
        SolutionOffer029 solutionOffer029 = new SolutionOffer029();
        Node node = solutionOffer029.insert(generate, 4);
        System.out.println(node.val);
        Node p = node.next;
        while (p != node && p != null) {
            System.out.println(p.val);
            p = p.next;
        }
    }
}
/**
 * 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环非降序的。
 * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
 * 如果有多个满足条件的插入位置，你可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
 * 如果列表为空（给定的节点是 null），你需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。
 * 示例 1：
 * 输入：head = [3,4,1], insertVal = 2
 * 输出：[3,4,1,2]
 * 解释：在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3 。
 * 示例 2：
 * 输入：head = [], insertVal = 1
 * 输出：[1]
 * 解释：列表为空（给定的节点是 null），创建一个循环有序列表并返回这个节点。
 * 示例 3：
 * 输入：head = [1], insertVal = 0
 * 输出：[1,0]
 *
 * 3,4,1  4   递增序列里加   pre < p  && val <= p && p!=null && p!= null
 * 3,4,1  5   递增序列里加
 * 3,4,1, 2   转一圈，再递增序列里加
 * 3,4,1, 0
 * 3,5,5  0
 * 1,3,3  4
 */

/**
 * 区分头结点是否比插入节点大，如果大，说明需要转一圈。
 * 分情况踩坑。
 * 情况1：
 * 如果insertVal < head.val 说明有可能在后半部分，所以转一圈，找到最小值的位置开始判断。
 *      pre.val <= p.val  来找到同样值的最后一个。
 *      找到最小值p之后，通过p.val < insertVal 判断，如果小于就后移p，直到找到p.val >= insertVal  就把val插入到pre和p之间
 * else 就是情况2：
 *  如果insertVal >= head.val 说明直接从head开始往后判断即可。
 *      通过p.val < insertVal 判断，找到合适的插入位置。
 *      注意：也可能插入的是最大值，pre.val <= p.val条件也要满足，即如果已经到最大的，依然比insertVal小，则把insertVal插入到最大的后面
 */
class SolutionOffer029 {
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            head = new Node();
            head.val = insertVal;
            head.next = head;
            return head;
        }
        Node p = head.next;
        Node pre = head;
        if (head.val > insertVal) {
            // 如果头结点大于插入值，说明需要转一圈,从最小的开始。
            while (p != head && p != null && pre.val <= p.val) {
                pre = p;
                p = p.next;
            }
            // 找到最小值了.
            while (p != head && p != null && p.val < insertVal) {
                pre = p;
                p = p.next;
            }
        } else {
            // 如果已经大于头节点，接直接从头节点开始查
            while (p != head && p != null  && pre.val <= p.val && p.val < insertVal) {
                pre = p;
                p = p.next;
            }
        }

        Node temp = new Node();
        temp.val = insertVal;
        temp.next = p;
        pre.next = temp;
        return head;
    }
}
