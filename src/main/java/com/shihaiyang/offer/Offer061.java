package com.shihaiyang.offer;

// Offer II 061. 和最小的 k 个数对[小顶堆积+多路归并 3ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 * 示例 1:
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 *     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 示例 2:
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 示例 3:
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 */
public class Offer061 {
    SolutionOffer061 solutionOffer061 = new SolutionOffer061();

    @Test
    public void case1() {
        List<List<Integer>> lists = solutionOffer061.kSmallestPairs(new int[]{1,7,11}, new int[]{2,4,6}, 3);
        Assertions.assertTrue(lists.equals(List.of(List.of(1, 2), List.of(1, 4), List.of(1, 6))));
    }
    @Test
    public void case2() {
        List<List<Integer>> lists = solutionOffer061.kSmallestPairs(new int[]{1,1,2}, new int[]{1,2,3}, 2);
        Assertions.assertTrue(lists.equals(List.of(List.of(1, 1), List.of(1, 1))));
    }
    @Test
    public void case3() {
        List<List<Integer>> lists = solutionOffer061.kSmallestPairs(new int[]{1,2}, new int[]{3}, 2);
        Assertions.assertTrue(lists.equals(List.of(List.of(1, 3), List.of(2, 3))));
    }
}

/**
 * 优先队列 + 多路归并
 * 维护一个k大小的队列。小顶堆积。存储最小的k个对即可
 * 多路归并：只把一个队列与第二个队列的首个元素存储到队列中。
 * 读出一个，然后把对应的那一路再入一个。
 * 这样就不用遍历完所有的元素了
 */
class SolutionOffer061 {
    PriorityQueue<int[]> priorityQueue;
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        priorityQueue = new PriorityQueue<>(k, (a, b) -> (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]]));
        for (int i = 0; i < nums1.length; i++) {
            priorityQueue.add(new int[]{i, 0});
        }

        List<List<Integer>> ret = new ArrayList<>(k);
        // 多路归并
        while (ret.size() < k && !priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            ret.add(List.of(nums1[poll[0]], nums2[poll[1]]));
            if (poll[1] < nums2.length - 1) {
                priorityQueue.add(new int[]{poll[0], poll[1] + 1});
            }
        }
        return ret;
    }
}