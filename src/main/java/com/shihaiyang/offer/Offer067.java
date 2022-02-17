package com.shihaiyang.offer;

// Offer II 067. 最大的异或[76ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给定一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * 示例 1：
 * <p>
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28.
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：nums = [2,4]
 * 输出：6
 * 示例 4：
 * <p>
 * 输入：nums = [8,10,2]
 * 输出：10
 * 示例 5：
 * <p>
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 */
public class Offer067 {
    SolutionOffer067 solutionOffer067 = new SolutionOffer067();

    @Test
    public void case1() {
        int maximumXOR = solutionOffer067.findMaximumXOR(new int[]{14,70,53,83,49,91,36,80,92,51,66,70});
        Assertions.assertEquals(maximumXOR, 127);
    }
    @Test
    public void case2() {
        int maximumXOR = solutionOffer067.findMaximumXOR(new int[]{3,10,5,25,2,8});
        Assertions.assertEquals(maximumXOR, 28);
    }
    @Test
    public void case3() {
        int maximumXOR = solutionOffer067.findMaximumXOR(new int[]{8,10,2});
        Assertions.assertEquals(maximumXOR, 10);
    }
    @Test
    public void case4() {
        int maximumXOR = solutionOffer067.findMaximumXOR(new int[]{0});
        Assertions.assertEquals(maximumXOR, 0);
    }
    @Test
    public void case5() {
        int maximumXOR = solutionOffer067.findMaximumXOR(new int[]{2,4});
        Assertions.assertEquals(maximumXOR, 6);
    }
}

/**
 * 长度31的二叉树
 * 从高位开始统计。
 * 如果高位能异或出1，那么就尽可能让高位异或出1.
 * 算是贪心 从高位找可以异或出1的那一个。
 * 时间复杂度 O(31*31*n)
 */
class SolutionOffer067 {
    TrieNode root;
    public int findMaximumXOR(int[] nums) {
        root = new TrieNode();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            TrieNode p = root;
            p.insert(nums[i]);
            max = Math.max(max, p.findMax(nums[i]));
        }
        return max;
    }

    class TrieNode {
        TrieNode left;
        TrieNode right;

        public TrieNode() {
        }

        public int findMax(int n) {
            TrieNode p = this;
            int ret = 0;
            for (int i = 30; i >= 0; i--) {
                int flag = (n >> i) & 1;
                if (flag == 1) {
                    if (p.left != null) {
                        ret |= 1 << i;
                        p = p.left;
                    } else {
                        p = p.right;
                    }
                } else {
                    if (p.right != null) {
                        ret |= 1 << i;
                        p = p.right;
                    } else {
                        p = p.left;
                    }
                }
            }
            return ret;
        }

        public void insert(int n) {
            TrieNode p = this;
            for (int i = 30; i >= 0; i--) {
                int flag = (n >> i) & 1;
                if (flag == 1) {
                    if (p.right == null) {
                        p.right = new TrieNode();
                    }
                    p = p.right;
                } else {
                    if (p.left == null) {
                        p.left = new TrieNode();
                    }
                    p = p.left;
                }
            }
        }
    }

}