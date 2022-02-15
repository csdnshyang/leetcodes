package com.shihaiyang.offer;

import com.shihaiyang.leetcodes.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Offer II 049. 从根节点到叶节点的路径数字之和[后序遍历1ms]

/**
 * 给定一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 *
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 * 示例 2：
 *
 *
 * 输入：root = [4,9,0,5,1]
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * 从根到叶子节点路径 4->0 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 */
public class Offer049 {
    SolutionOffer049 solutionOffer049 = new SolutionOffer049();
    @Test
    public void case1() {
        TreeNode root = Codec.generate("4,9,0,5,1");
        int sumNumbers = solutionOffer049.sumNumbers(root);
        Assertions.assertEquals(sumNumbers,1026);
    }
}

/**
 * 前续遍历
 * 只要遇到叶子就计算一下。
 */
class SolutionOffer049 {
    public int sumNumbers(TreeNode root) {
        TreeNode p = root;
        int top = -1;
        TreeNode[] stack = new TreeNode[10000];
        int[] stack2 = new int[10000];
        int ans = 0;
        int num = 0;
        if (root != null) {
            do {
                while (p != null) {
                    stack[++top] = p;
                    stack2[top] = 0;
                    num = num * 10 + p.val;
                    p = p.left;
                }
                p = stack[top];
                int flag = stack2[top--];
                if (flag == 0) {
                    stack[++top] = p;
                    stack2[top] = 1;
                    p = p.right;
                } else {
                    if (p.left == null && p.right == null) {
                        ans += num;
                    }
                    num = (num - p.val) / 10;
                    p = null;
                }
            } while (!(p == null && top == -1));
        }
        return ans;
    }
}