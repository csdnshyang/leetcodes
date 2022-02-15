package com.shihaiyang.offer;
// Offer II 052. 展平二叉搜索树[中序遍历 0ms].

import com.shihaiyang.leetcodes.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给你一棵二叉搜索树，请 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 * 示例 1：
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * 示例 2：
 * 输入：root = [5,1,7]
 * 输出：[1,null,5,null,7]
 */
public class Offer052 {
    SolutionOffer052 solutionOffer052 = new SolutionOffer052();

    @Test
    public void case1() {
        TreeNode root = Codec.generate("5,3,6,2,4,null,8,1,null,null,null,7,9");
        TreeNode treeNode = solutionOffer052.increasingBST(root);
        Assertions.assertEquals(treeNode.val, 1);
        String serialize = new Codec().serialize(treeNode);
        Assertions.assertTrue(serialize.startsWith("1,null,2,null,3,null,4"));
    }
    @Test
    public void case2() {
        TreeNode root = Codec.generate("5,1,7");
        TreeNode treeNode = solutionOffer052.increasingBST(root);
        Assertions.assertEquals(treeNode.val, 1);
        String serialize = new Codec().serialize(treeNode);
        Assertions.assertTrue(serialize.startsWith("1,null,5,null,7"));
    }
}

/**
 * 中序遍历。然后每一个节点都作为上一个节点的右子节点。
 * 递归吧
 */
class SolutionOffer052 {
    TreeNode p = new TreeNode(0), dummy = p;
    public TreeNode increasingBST(TreeNode root) {
        if (root != null) {
            layer(root);
        }
        return dummy.right;
    }

    public void layer(TreeNode node) {
        if (node.left != null) {
            layer(node.left);
        }
        p.right = new TreeNode(node.val);
        p = p.right;
        if (node.right != null) {
            layer(node.right);
        }
    }
}

