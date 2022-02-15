package com.shihaiyang.offer;

// Offer II 054. 所有大于等于节点的值之和[两次中序遍历求和+改值2ms].

import com.shihaiyang.leetcodes.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *给定一个二叉搜索树，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
 *
 *  
 *
 * 提醒一下，二叉搜索树满足下列约束条件：
 *
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * 示例 2：
 *
 * 输入：root = [0,null,1]
 * 输出：[1,null,1]
 * 示例 3：
 *
 * 输入：root = [1,0,2]
 * 输出：[3,3,2]
 * 示例 4：
 *
 * 输入：root = [3,2,4,1]
 * 输出：[7,9,4,10]
 * 树中的节点数介于 0 和 104 之间。
 */
public class Offer054 {
    SolutionOffer054 solutionOffer054 = new SolutionOffer054();

    @Test
    public void case1() {
        TreeNode root = Codec.generate("4,1,6,0,2,5,7,null,null,null,3,null,null,null,8");
        TreeNode treeNode = solutionOffer054.convertBST(root);
        Assertions.assertTrue(new Codec().serialize(treeNode).startsWith("30,36,21,36,35,26,15,null,null,null,33,null,null,null,8"));
    }
    @Test
    public void case2() {
        TreeNode root = Codec.generate("0,null,1");
        TreeNode treeNode = solutionOffer054.convertBST(root);
        Assertions.assertTrue(new Codec().serialize(treeNode).startsWith("1,null,1"));
    }
    @Test
    public void case3() {
        TreeNode root = Codec.generate("1,0,2");
        TreeNode treeNode = solutionOffer054.convertBST(root);
        Assertions.assertTrue(new Codec().serialize(treeNode).startsWith("3,3,2"));
    }
    @Test
    public void case4() {
        TreeNode root = Codec.generate("3,2,4,1");
        TreeNode treeNode = solutionOffer054.convertBST(root);
        Assertions.assertTrue(new Codec().serialize(treeNode).startsWith("7,9,4,10"));
    }
    @Test
    public void case5() {
        TreeNode root = Codec.generate("");
        TreeNode treeNode = solutionOffer054.convertBST(root);
        Assertions.assertTrue(new Codec().serialize(treeNode).startsWith(""));
    }
}

/**
 * 中序遍历 * 2
 * 第一次算总和
 * 第二次改值
 */
class SolutionOffer054 {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        mid(root, 1);
        return mid(root, 0);
    }

    public TreeNode mid(TreeNode root, int i) {
        TreeNode tmp = root;
        int top = -1;
        TreeNode[] stack = new TreeNode[10000];
        if (root != null) {
            do {
                while (tmp != null) {
                    stack[++top] = tmp;
                    tmp = tmp.left;
                }
                tmp = stack[top--];
                if (i == 1) {
                    sum += tmp.val;
                } else {
                    int val = tmp.val;
                    tmp.val = sum;
                    sum -= val;
                }
                tmp = tmp.right;
            } while (!(tmp == null && top == -1));
        }
        return root;
    }
}