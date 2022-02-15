package com.shihaiyang.offer;

// Offer II 053. 二叉搜索树中的中序后继[中序遍历+标记状态3ms].

import com.shihaiyang.leetcodes.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 * 节点 p 的后继是值比 p.val 大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。
 * 示例 1：
 * 输入：root = [2,1,3], p = 1
 * 输出：2
 * 解释：这里 1 的中序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
 * 示例 2：
 * 输入：root = [5,3,6,2,4,null,null,1], p = 6
 * 输出：null
 * 解释：因为给出的节点没有中序后继，所以答案就返回 null 了。
 */
public class Offer053 {
    SolutionOffer053 solutionOffer053 = new SolutionOffer053();

    @Test
    public void case1() {
        TreeNode root = Codec.generate("2,1,3");
        TreeNode node = solutionOffer053.inorderSuccessor(root, new TreeNode(1));
        Assertions.assertEquals(node.val, 2);
    }
    @Test
    public void case2() {
        TreeNode root = Codec.generate("5,3,6,2,4,null,null,1");
        TreeNode node = solutionOffer053.inorderSuccessor(root, new TreeNode(6));
        Assertions.assertNull(node);
    }
}

/**
 * 中序遍历 非递归
 */
class SolutionOffer053 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode tmp = root;
        int top = -1;
        boolean find = false;
        TreeNode[] stack = new TreeNode[10000];
        if (root != null) {
            do {
                while (tmp != null) {
                    stack[++top] = tmp;
                    tmp = tmp.left;
                }
                tmp = stack[top--];
                if (find) {
                    return tmp;
                }
                if (tmp.val == p.val) {
                    find = true;
                }
                tmp = tmp.right;
            } while (!(tmp == null && top == -1));
        }
        return null;
    }
}