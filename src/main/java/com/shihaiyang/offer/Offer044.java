package com.shihaiyang.offer;

import com.shihaiyang.leetcodes.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

// 剑指 Offer II 044. 二叉树每层的最大值
// Offer044. 二叉树每层的最大值[保准层序遍历].

/**
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 * 示例1：
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 * 解释:
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 * 示例2：
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 * 解释:
 *           1
 *          / \
 *         2   3
 * 示例3：
 * 输入: root = [1]
 * 输出: [1]
 * 示例4：
 * 输入: root = [1,null,2]
 * 输出: [1,2]
 * 解释:
 *            1
 *             \
 *              2
 * 示例5：
 * 输入: root = []
 * 输出: []
 * 提示：
 * 二叉树的节点个数的范围是 [0,104]
 * -231 <= Node.val <= 231 - 1
 */
public class Offer044 {
    SolutionOffer044 solutionOffer044 = new SolutionOffer044();

    @Test
    public void case1() {
        TreeNode root = TreeNode.generate(new Integer[]{1, 3, 2, 5, 3, null, 9});
        List<Integer> integers = solutionOffer044.largestValues(root);
        boolean equals = integers.equals(List.of(1, 3, 9));
        Assertions.assertTrue(equals);
    }
    @Test
    public void case2() {
        TreeNode root = TreeNode.generate(new Integer[]{1,2,3});
        List<Integer> integers = solutionOffer044.largestValues(root);
        Assertions.assertTrue(integers.equals(List.of(1, 3)));
    }
    @Test
    public void case3() {
        TreeNode root = TreeNode.generate(new Integer[]{1,null,2});
        List<Integer> integers = solutionOffer044.largestValues(root);
        Assertions.assertTrue(integers.equals(List.of(1, 2)));
    }
}

/**
 * 层序遍历。每一层的最大值存储起来
 */
class SolutionOffer044 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        if (root != null) {
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                int max = Integer.MIN_VALUE;
                for (int i = 0; i < size; i++) {
                    TreeNode treeNode = queue.poll();
                    max = Math.max(max, treeNode.val);
                    if (treeNode.left != null) {
                        queue.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        queue.add(treeNode.right);
                    }
                }
                ret.add(max);
            }
        }
        return ret;
    }
}