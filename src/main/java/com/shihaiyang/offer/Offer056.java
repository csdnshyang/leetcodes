package com.shihaiyang.offer;

// Offer II 056. 二叉搜索树中两个节点之和.[中序遍历+存储值 3ms].

import com.shihaiyang.leetcodes.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 给定一个二叉搜索树的 根节点 root 和一个整数 k , 请判断该二叉搜索树中是否存在两个节点它们的值之和等于 k 。
 * 假设二叉搜索树中节点的值均唯一。
 * 示例 1：
 * <p>
 * 输入: root = [8,6,10,5,7,9,11], k = 12
 * 输出: true
 * 解释: 节点 5 和节点 7 之和等于 12
 * 示例 2：
 * <p>
 * 输入: root = [8,6,10,5,7,9,11], k = 22
 * 输出: false
 * 解释: 不存在两个节点值之和为 22 的节点
 */
public class Offer056 {
    SolutionOffer056 solutionOffer056 = new SolutionOffer056();
    @Test
    public void case1() {
        TreeNode root = Codec.generate("8,6,10,5,7,9,11");
        Assertions.assertTrue(solutionOffer056.findTarget(root, 12));
    }
    @Test
    public void case2() {
        TreeNode root = Codec.generate("8,6,10,5,7,9,11");
        Assertions.assertTrue(!solutionOffer056.findTarget(root, 22));
    }
}

/**
 * 中序遍历+存储已便利值
 */
class SolutionOffer056 {
    Set<Integer> already = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        TreeNode p = root;
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) {
            do {
                while (p != null) {
                    stack.add(p);
                    p = p.left;
                }
                p = stack.pop();
                if (already.contains(k - p.val)) {
                    return true;
                }
                already.add(p.val);
                p = p.right;
            } while (!(p == null && stack.isEmpty()));
        }
        return false;
    }
}
