package com.shihaiyang.offer;
// Offer II 076. 数组中的第 k 大的数字[小顶堆积 5ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 */
public class Offer076 {
    SolutionOffer076 solutionOffer076 = new SolutionOffer076();

    @Test
    public void case1() {
        Assertions.assertEquals(5, solutionOffer076.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }
    @Test
    public void case2() {
        Assertions.assertEquals(4, solutionOffer076.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }
}

/**
 * 优先级队列(小顶堆积)
 */
class SolutionOffer076 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k);
        for (int i = 0; i < nums.length; i++) {
            priorityQueue.add(nums[i]);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        return priorityQueue.peek();
    }
}