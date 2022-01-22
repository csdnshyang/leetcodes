package com.shihaiyang.daily;

import java.util.*;
// 1345. 跳跃游戏 IV.[BFS 50ms]
public class Leetcode1345 {
    public static void main(String[] args) {
        Solution1345 solution1345 = new Solution1345();
        int minJumps = solution1345.minJumps(new int[]{7, 7, 2, 1, 7, 7, 7, 3, 4, 1});
//        int minJumps = solution1345.minJumps(new int[]{7,6,9,6,9,6,9,7});
        System.out.println(minJumps);
    }
}

/**
 * 给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
 * 每一步，你可以从下标 i 跳到下标：
 * i + 1 满足：i + 1 < arr.length
 * i - 1 满足：i - 1 >= 0
 * j 满足：arr[i] == arr[j] 且 i != j
 * 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
 * 注意：任何时候你都不能跳到数组外面。
 * 示例 1：
 * 输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
 * 输出：3
 * 解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
 * 示例 2：
 * 输入：arr = [7]
 * 输出：0
 * 解释：一开始就在最后一个元素处，所以你不需要跳跃。
 * 示例 3：
 * 输入：arr = [7,6,9,6,9,6,9,7]
 * 输出：1
 * 解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
 * [7,7,2,1,7,7,7,3,4,1]
 * 3
 */

/**
 * BFS  广度遍历
 * 通过一个map存储value相同的index
 * 通过一个队列存储每一个节点可跳跃的节点
 * 通过一个数组剪枝，存储每一个步骤的需要的步数。
 * 从一个节点arr[0]开始，把可以扫描的节点放到队列。
 * 如果队列不为空，再对队列中的索引执行相同的操作。
 * 如果扫描过就剪枝
 * 如果出现i=arr.length-1就退出。
 */
class Solution1345 {
    public int minJumps(int[] arr) {
        if (arr.length == 1) {
            return 0;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] scanned = new int[arr.length];
        Arrays.fill(scanned, Integer.MAX_VALUE);
        for (int i = arr.length - 1; i >= 0; i--) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], new ArrayList<>());
            }
            map.get(arr[i]).add(i);
        }
        queue.addLast(0);
        scanned[0] = 0;
        while (!queue.isEmpty()) {
            Integer index = queue.poll(), step = scanned[index];
            if (index == arr.length - 1) {
                return step;
            }
            // 如果下一个存在，加入队列，更新步数。扫描的步数如果么有设置，再设置，如果设置过就剪枝。
            if (index + 1 < arr.length && scanned[index + 1] == Integer.MAX_VALUE) {
                queue.addLast(index + 1);
                scanned[index + 1] = step + 1;
            }
            // 如果上一个存在，加入队列，更新步数。扫描的步数如果么有设置，再设置，如果设置过就剪枝。
            if (index - 1 >= 0 && scanned[index - 1] == Integer.MAX_VALUE) {
                queue.addLast(index - 1);
                scanned[index - 1] = step + 1;
            }
            // 如果相同值，加入队列，更新步数。扫描的步数如果么有设置，再设置，如果设置过就剪枝。
            List<Integer> indexes = map.getOrDefault(arr[index], new ArrayList<>());
            for (int j = 0; j < indexes.size(); j++) {
                if (scanned[indexes.get(j)] == Integer.MAX_VALUE) {
                    queue.addLast(indexes.get(j));
                    scanned[indexes.get(j)] = step + 1;
                }
            }
            // 如果扫描过相同的值，step就设置过，下一次相同值扫描时，因为已经设置过step，所以不需要在设置，所以map中value可以删除。
            map.remove(arr[index]);
        }
        return -1;
    }
}
