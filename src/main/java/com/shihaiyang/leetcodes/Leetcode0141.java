package com.shihaiyang.leetcodes;

import java.util.HashSet;
import java.util.Set;

// 0141. 环形链表.[快慢指针0ms+集合5ms].
// https://leetcode-cn.com/problems/linked-list-cycle/solution/0141-huan-xing-lian-biao-kuai-man-zhi-zh-q5ji/
public class Leetcode0141 {
}

/**
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 */

/**
 * 说实话读了两遍没读懂题目...
 * 读懂了,pos是指出题人随机会写一个pos，让最后的next指向某一个节点.
 * 如果指向null说明没有环
 * 如果指向某个节点说明有还，.next永远不能停止。
 * 方法1，借助Map，遍历到的放入Map。当遍历到存在Map中元素的，说明出现了环。 遍历完list  时间复杂度O(n)
 * 方法2，快慢指针。快指针在慢指针之后，一定会相遇.就是有case很长，且环靠后的话，快指针会在环里转多圈。 <=n次遍历  时间复杂度O(n)
 */
class Solution0141 {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }
}
class Solution0141FastSlow {
    public boolean hasCycle(ListNode head) {
        // 判空
        if(head==null){
            return false;
        }
        // 初始化
        ListNode fast = head, slow = head;
        // 无线遍历
        while (true) {
            // 只需要判断fast的后面有没有null即可，因为fast快
            if (fast.next == null || fast.next.next == null) {
                break;
            }
            // fast开始动
            fast = fast.next.next;
            slow = slow.next;
            // 移动之后还能相同，说明有环
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}