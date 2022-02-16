package com.shihaiyang.offer;
// Offer II 060. 出现频率最高的 k 个数字[小顶堆+map 12ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给定一个整数数组 nums 和一个整数 k ，请返回其中出现频率前 k 高的元素。可以按 任意顺序 返回答案。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 */
public class Offer060 {
    SolutionOffer060 solutionOffer060 = new SolutionOffer060();

    @Test
    public void case1() {
        int[] ints = solutionOffer060.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        Assertions.assertArrayEquals(ints, new int[]{1, 2});
    }
    @Test
    public void case2() {
        int[] ints = solutionOffer060.topKFrequent(new int[]{1}, 1);
        Assertions.assertArrayEquals(ints, new int[]{1});
    }
}

/**
 * map+优先队列
 */
class SolutionOffer060 {
    Map<Integer, Integer> map = new HashMap<>();
    PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    public int[] topKFrequent(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int count = map.getOrDefault(nums[i], 0) + 1;
            map.put(nums[i], count);
        }
        for (Map.Entry<Integer, Integer> ent : map.entrySet()) {
            queue.add(new int[]{ent.getKey(), ent.getValue()});
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int[] ret = new int[k];
        while (!queue.isEmpty()) {
            ret[--k] = queue.poll()[0];
        }
        return ret;
    }
}