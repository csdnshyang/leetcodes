package com.shihaiyang.offer;

// Offer II 071. 按权重生成随机数[前缀和+二分查找 2ms]
// 负载均衡算法，按权重。数据量小的话其实可以直接空间换时间，有几个权重就备份几个。

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * 给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始），请写一个函数 pickIndex ，
 * 它可以随机地获取下标 i，选取下标 i 的概率与 w[i] 成正比。
 * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），
 * 而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。
 * 也就是说，选取下标 i 的概率为 w[i] / sum(w) 。
 * 示例 1：
 * 输入：
 * inputs = ["Solution","pickIndex"]
 * inputs = [[[1]],[]]
 * 输出：
 * [null,0]
 * 解释：
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
 * 示例 2：
 *
 * 输入：
 * inputs = ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * inputs = [[[1,3]],[],[],[],[],[]]
 * 输出：
 * [null,1,1,1,1,0]
 * 解释：
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
 *
 * 由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * 诸若此类。
 */
public class Offer071 {
    @Test
    public void case1() {
        SolutionOffer071 solution = new SolutionOffer071(new int[]{2, 3, 5});
        solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
        solution.pickIndex(); // 返回 1
        solution.pickIndex(); // 返回 1
        solution.pickIndex(); // 返回 1
        solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4
    }
}

/**
 * 前缀和+二分查找
 * 前缀和就是用数组记录每个元素的前缀和，从1开始
 * 二分查找是，随机一个值之后。找这个值在那个区间。  1，4，10  找5的时候，应该在>4 <10的区间
 */
class SolutionOffer071 {
    int[] prefixSum;
    Random random;
    public SolutionOffer071(int[] w) {
        prefixSum = new int[w.length];
        random = new Random();
        for (int i = 0; i < w.length; i++) {
            if (i == 0) {
                prefixSum[i] += w[i];
            } else {
                prefixSum[i] += w[i]+prefixSum[i-1];
            }
        }
    }

    public int pickIndex() {
        int nextInt = random.nextInt(prefixSum[prefixSum.length - 1]) + 1;
        int left = 0, right = prefixSum.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (prefixSum[mid] == nextInt) return mid;
            if (prefixSum[mid] < nextInt) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}