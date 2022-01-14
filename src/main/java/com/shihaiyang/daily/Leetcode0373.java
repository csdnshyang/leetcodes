package com.shihaiyang.daily;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// 0373. 查找和最小的 K 对数字.[优先队列+多路归并].
// https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/solution/373-cha-zhao-he-zui-xiao-de-k-dui-shu-zi-fkaq/
public class Leetcode0373 {
    public static void main(String[] args) {
        Solution0373 solution0373 = new Solution0373();
        List<List<Integer>> lists = solution0373.kSmallestPairs(
                new int[]{1,2},
                new int[]{3},
                3
        );
        lists.stream().forEach(integers -> {
            integers.stream().forEach(integer -> System.out.print(integer+","));
            System.out.println();
        });
    }
}

/**
 * 给定两个以 升序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 *
 * [1,1,2]
 * [1,2,3]
 * 10
 * [[1,1],[1,1],[2,1],[1,2],[1,2],[2,2],[1,3],[1,3],[2,3]]
 */

/**
 * 优先队列+多路归并
 * 在之前n个有序数组排序时用到过。忘却了...
 * 就是把n个数组的第一个元素放入到优先队列中.
 * 再while() 优先队列取出一个,是第k个数组中的元素，就把第k个数组中下一个元素入队.
 * 这样保证每次取到的都是最小的.
 *
 * 这个题目也是一样的。两个数组元素之和就组成了一个矩阵
 * 同一行向右都是有序的 (这个矩阵稍特殊,同一列向下也是有序的)
 * 这样就可以看做多个数组前k个值. 如果取最小值，就是取所有数组首元素中的最小值. 就优先队列可以n*logN
 * 整体思路就是把每个数组的首元素入队。
 * 取出一个最小的元素，然后再从对应的数组上把下一个元素入队。循环这个过程
 */
class Solution0373 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(nums1.length, (a, b) -> (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]]));
        for (int i = 0; i < nums1.length; i++) {
            priorityQueue.add(new int[]{i, 0});
        }

        List<List<Integer>> ret = new ArrayList<>();
        while (ret.size() < k && !priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            ret.add(List.of(nums1[poll[0]], nums2[poll[1]]));
            if (poll[1]+1 < nums2.length) {
                priorityQueue.add(new int[]{poll[0], poll[1] + 1});
            }
        }
        return ret;
    }
}
