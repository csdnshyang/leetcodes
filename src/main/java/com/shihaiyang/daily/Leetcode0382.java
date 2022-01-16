package com.shihaiyang.daily;


import com.shihaiyang.leetcodes.ListNode;

import java.util.Random;
// 0382. 链表随机节点.[初始化到数组 12ms].
public class Leetcode0382 {
}

/**
 * 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
 * 实现 Solution 类：
 * Solution(ListNode head) 使用整数数组初始化对象。
 * int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
 */

/**
 * 放数组。随机值，然后从数组取..
 */
class Solution0382 {
    int[] data = new int[10000];
    int count = 0;
    Random random;
    public Solution0382(ListNode head) {
        random = new Random();
        while (head != null) {
            data[count++] = head.val;
            head = head.next;
        }
    }

    public int getRandom() {
        int anInt = random.ints(0, count).iterator().nextInt();
        return data[anInt];
    }
}
