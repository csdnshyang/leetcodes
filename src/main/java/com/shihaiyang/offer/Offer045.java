package com.shihaiyang.offer;

import com.shihaiyang.leetcodes.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Offer II 045. 二叉树最底层最左边的值[2ms].

/**
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 *
 * 假设二叉树中至少有一个节点。
 * 示例 1:
 * 输入: root = [2,1,3]
 * 输出: 1
 * 示例 2:
 * 输入: [1,2,3,4,null,5,6,null,null,7]
 * 输出: 7
 */
public class Offer045 {
    SolutionOffer045 solutionOffer045 = new SolutionOffer045();

    @Test
    public void case1() {
        TreeNode root = TreeNode.generate(new Integer[]{2, 1, 3});
        int bottomLeftValue = solutionOffer045.findBottomLeftValue(root);
        Assertions.assertEquals(bottomLeftValue, 1);
    }
    @Test
    public void case2() {
        TreeNode root = TreeNode.generate(new Integer[]{1,2,3,4,null,5,6,null,null,7});
        int bottomLeftValue = solutionOffer045.findBottomLeftValue(root);
        Assertions.assertEquals(bottomLeftValue, 7);
    }
}

/**
 * 后序遍历
 * 根据深度来记录最底层最左节点
 */
class SolutionOffer045 {
    public int findBottomLeftValue(TreeNode root) {
        TreeNode p = root;
        int top = -1;
        TreeNode[] stack = new TreeNode[10000];
        int[] stack2 = new int[10000];
        int ans = 0;
        int max = -1;
        if (root != null) {
            do {
                while (p != null) {
                    stack[++top] = p;
                    stack2[top] = 0;
                    p = p.left;
                }
                p = stack[top];
                int flag = stack2[top--];
                if (flag == 0) {
                    stack[++top] = p;
                    stack2[top] = 1;
                    p = p.right;
                } else {
                    System.out.println(top + "--" + p.val);
                    if (p.left == null && p.right == null && top > max) {
                        max = top;
                        ans = p.val;
                    }
                    p = null;
                }
            } while (!(p == null && top == -1));
        }
        return ans;
    }
}