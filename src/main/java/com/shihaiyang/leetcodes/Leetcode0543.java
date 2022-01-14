package com.shihaiyang.leetcodes;

// 0543. 二叉树的直径.[递归0ms].
// https://leetcode-cn.com/problems/diameter-of-binary-tree/solution/0543-er-cha-shu-de-zhi-jing-di-gui-by-sh-b1jt/
public class Leetcode0543 {
}

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 */

/**
 * 第一想法是递归.
 *           a
 *          / \
 *         b   c
 *        / \
 *       d   e
 *            \
 *             f
 *
 *  b左孩子长度为1 即b->d  叶子不提供长度
 *  b右孩子长度为2 即b->e->f
 *  所以b作为父节点直径为3  即左孩子1+右孩子2
 *
 *  b作为a节点的左孩子节点，他给a节点提供的长度为2  即Max(b左孩子1， b右孩子2)
 *
 *  a右孩子长度为1  即a->c  所以叶子节点不提供长度.
 *  a左孩子长度为3  即(a->b长度为1) + (b提供的长度2)
 *  所以a作为父节点直径为4  即左孩子1+右孩子3
 *
 * 一个节点作为父节点的话，他的直径为左右孩子长度之和
 *      递归获取一下左右子孩子的长度,左右孩子长度加起来就是直径.
 *      直径的概念所有父节点中最长的直径，所以在每个父节点中比较一下记录的最长直径，然后取Max
 * 一个节点作为子节点的话，他要返回给父节点他的长度
 *      max(左右孩子的长度)   就是当前节点作为孩子给父节点提供长度.
 */
class Solution0543 {
    public int diameterOfBinaryTree(TreeNode root) {
        int max[] = new int[]{0};
        diameterOfChild(root, max);
        return max[0];
    }

    public int diameterOfChild(TreeNode root, int max[]) {
        // 叶子节点，无法提供长度,返回0，结束递归
        if (root.left == null && root.right == null) {
            return 0;
        }
        // 存在左孩子，就递归左孩子+长度1
        int leftDiameter=0;
        if (root.left != null) {
            leftDiameter = diameterOfChild(root.left, max) + 1;
        }
        // 存在右孩子，就递归右孩子+长度1
        int rightDiameter = 0;
        if (root.right != null) {
            rightDiameter = diameterOfChild(root.right, max) + 1;
        }
        // 左右孩子长度之和就是当前节点作为父节点的直接
        int diameter = leftDiameter + rightDiameter;
        // 比较当前节点直径与记录的最长直径，取Max
        max[0] = Math.max(max[0], diameter);
        // 节点作为孩子节点，提供的长度为 该节点左右孩子中的长度Max
        return Math.max(leftDiameter, rightDiameter);
    }
}
