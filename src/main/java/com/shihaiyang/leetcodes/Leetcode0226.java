package com.shihaiyang.leetcodes;

// 0226. 翻转二叉树.[递归0ms].
// https://leetcode-cn.com/problems/invert-binary-tree/solution/226-fan-zhuan-er-cha-shu-by-shi-hai-yang-wp8h/
public class Leetcode0226 {
}

/**
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */

/**
 * 递归方式
 */
class Solution0226 {
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    public void invert(TreeNode treeNode) {
        if (treeNode == null) {
            return ;
        }
        TreeNode tmp = treeNode.left;
        treeNode.left = treeNode.right;
        treeNode.right = tmp;
        invertTree(treeNode.left);
        invertTree(treeNode.right);
    }
}