package com.shihaiyang.leetcodes;
// 0617. 合并二叉树.[递归0ms].
// https://leetcode-cn.com/problems/merge-two-binary-trees/solution/0617-he-bing-er-cha-shu-di-gui-0ms-100-b-zi6g/
public class Leetcode0617 {
}

/**
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 * 示例 1:
 * 输入:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * 输出:
 * 合并后的树:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 */

/**
 * 递归.
 */
class Solution0617 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        root1 = mergeRecursion(root1, root2);
        return root1;
    }

    // 都往desc上靠
    public TreeNode mergeRecursion(TreeNode desc, TreeNode src) {
        // 两个节点全为空，结束递归
        if (desc == null && src == null) {
            return null;
        // desc为空时，换成src。往desc上靠
        } else if (desc == null) {
            desc = src;
            desc.left = mergeRecursion(desc.left, null);
            desc.right = mergeRecursion(desc.right, null);
        // src为空时，直接递归左右孩子即可
        } else if (src == null) {
            mergeRecursion(desc.left, null);
            mergeRecursion(desc.right, null);
        // desc,src都不为空时.合并val
        } else{
            desc.val += src.val;
            desc.left = mergeRecursion(desc.left, src.left);
            desc.right = mergeRecursion(desc.right, src.right);
        }
        return desc;
    }
}