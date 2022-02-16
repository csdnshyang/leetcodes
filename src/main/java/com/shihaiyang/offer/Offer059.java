package com.shihaiyang.offer;

// Offer II 059. 数据流的第 K 大数值.[堆 12ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

/**
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * <p>
 * 请实现 KthLargest 类：
 * <p>
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 * 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 */
public class Offer059 {
    @Test
    public void case1() {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        Assertions.assertEquals(kthLargest.add(3), 4);   // return 4
        Assertions.assertEquals(kthLargest.add(5), 5);   // return 5
        Assertions.assertEquals(kthLargest.add(10), 5);  // return 5
        Assertions.assertEquals(kthLargest.add(9), 8);   // return 8
        Assertions.assertEquals(kthLargest.add(4), 8);   // return 8
    }
//    ["KthLargest","add","add","add","add","add"]
//            [[1,[]],[-3],[-2],[-4],[0],[4]]
    @Test
    public void case2() {
        KthLargest kthLargest = new KthLargest(1, new int[]{});
        Assertions.assertEquals(kthLargest.add(-3), -3);
        Assertions.assertEquals(kthLargest.add(-2), -2);
        Assertions.assertEquals(kthLargest.add(-4), -2);
        Assertions.assertEquals(kthLargest.add(0), 0);
        Assertions.assertEquals(kthLargest.add(4), 4);
    }
}

/**
 * 第k大，就放一个长度k小顶堆积。根节点就是k大。每次新节点都放入，再取出头结点。
 */
class KthLargest {
    PriorityQueue<Integer> queue;
    int quality;
    public KthLargest(int k, int[] nums) {
        queue = new PriorityQueue<>();
        quality = k;
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        queue.add(val);
        if (queue.size() > quality) {
            queue.poll();
        }
        return queue.peek();
    }
}