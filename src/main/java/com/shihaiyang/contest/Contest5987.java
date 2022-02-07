package com.shihaiyang.contest;

import java.util.PriorityQueue;

// 5987. 删除元素后和的最小差值.[动态规划+枚举分割点+大小顶堆积 120ms].
public class Contest5987 {
    public static void main(String[] args) {
        Solution5987 solution5987 = new Solution5987();
//        long minimumDifference = solution5987.minimumDifference(new int[]{7, 9, 5, 8, 1, 3});
        long minimumDifference = solution5987.minimumDifference(new int[]{3,1,2});
        System.out.println(minimumDifference);
    }
}

/**
 * 给你一个下标从 0 开始的整数数组 nums ，它包含 3 * n 个元素。
 *
 * 你可以从 nums 中删除 恰好 n 个元素，剩下的 2 * n 个元素将会被分成两个 相同大小 的部分。
 *
 * 前面 n 个元素属于第一部分，它们的和记为 sumfirst 。
 * 后面 n 个元素属于第二部分，它们的和记为 sumsecond 。
 * 两部分和的 差值 记为 sumfirst - sumsecond 。
 *
 * 比方说，sumfirst = 3 且 sumsecond = 2 ，它们的差值为 1 。
 * 再比方，sumfirst = 2 且 sumsecond = 3 ，它们的差值为 -1 。
 * 请你返回删除 n 个元素之后，剩下两部分和的 差值的最小值 是多少。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,1,2]
 * 输出：-1
 * 解释：nums 有 3 个元素，所以 n = 1 。
 * 所以我们需要从 nums 中删除 1 个元素，并将剩下的元素分成两部分。
 * - 如果我们删除 nums[0] = 3 ，数组变为 [1,2] 。两部分和的差值为 1 - 2 = -1 。
 * - 如果我们删除 nums[1] = 1 ，数组变为 [3,2] 。两部分和的差值为 3 - 2 = 1 。
 * - 如果我们删除 nums[2] = 2 ，数组变为 [3,1] 。两部分和的差值为 3 - 1 = 2 。
 * 两部分和的最小差值为 min(-1,1,2) = -1 。
 * 示例 2：
 *
 * 输入：nums = [7,9,5,8,1,3]
 * 输出：1
 * 解释：n = 2 。所以我们需要删除 2 个元素，并将剩下元素分为 2 部分。
 * 如果我们删除元素 nums[2] = 5 和 nums[3] = 8 ，剩下元素为 [7,9,1,3] 。和的差值为 (7+9) - (1+3) = 12 。
 * 为了得到最小差值，我们应该删除 nums[1] = 9 和 nums[4] = 1 ，剩下的元素为 [7,5,8,3] 。和的差值为 (7+5) - (8+3) = 1 。
 * 观察可知，最优答案为 1 。
 */

/**
 * 分为左右两边。
 * 这个跟6003很像。分为左右两部分
 * 左边的和-右边的和 最小的值.
 * k=n/3
 * 左边部分0-2k  右边3k-k  分别从2k个数中选择k个，左边取和最小，右边取和最大。
 * 前缀和的优先队列采用大顶堆积，当新元素< 原最大值，就用新元素插入队列
 * 后缀和的优先队列采用小顶堆积，当新元素> 原最小值，就把新元素插入队列
 */
class Solution5987 {
    public long minimumDifference(int[] nums) {
        int k = nums.length / 3;
        // 从小到大
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(k);
        // 从大到小
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(k, (a, b) -> b - a);
        long max[] = new long[k+1];
        long min[] = new long[k+1];
        for (int i = 0; i < k; i++) {
            int num = nums[i];
            minQueue.add(num);
            min[0] += num;
            maxQueue.add(nums[i + 2 * k]);
            max[0] += nums[i + 2 * k];
        }
        // 0-5 k=2 [3..2]
        for (int i = 1; i <= k; i++) {
            // 3,2
            int index = 2 * k - i;
            int num = nums[index];
            int change = 0;
            if (num > maxQueue.peek()) {
                int minVal = maxQueue.poll();
                maxQueue.add(num);
                change = num - minVal;
            }
            max[i] = max[i - 1] + change;
        }
        for (int i = 1; i <= k; i++) {
            // 2, 3
            int index = k + i - 1;
            int num = nums[index];
            int change = 0;
            if (num < minQueue.peek()) {
                int pollMin = minQueue.poll();
                minQueue.add(num);
                change = pollMin - num;
            }
            // [1,2]
            min[i] = min[i - 1] - change;
        }
        // [max:2 - min:0]
        long ans = min[0] - max[k];
        for (int i = 0; i <= k; i++) {
            ans = Math.min(ans, min[i] - max[k - i]);
        }
        return ans;
    }
}