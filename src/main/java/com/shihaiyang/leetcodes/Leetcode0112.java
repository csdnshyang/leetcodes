package com.shihaiyang.leetcodes;

import java.util.Stack;

// 0112. 路径总和.[递归0ms后续遍历2ms].
public class Leetcode0112 {

}
/**
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 * 叶子节点 是指没有子节点的节点。
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * 解释：等于目标和的根节点到叶节点路径如上图所示。
 */

/**
 * 递归
 */
class Solution0112Recursion {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (targetSum == root.val && root.left == null && root.right == null) {
            return true;
        }
        return hasPathSum(root.left, targetSum - root.val) | hasPathSum(root.right, targetSum - root.val);
    }
}

/**
 * 后续遍历
 * 记录一个路径上的总和，每次入栈出栈的时候，和对应的加减。
 * 当是叶子节点的时候，判断总和是否与target相等。
 */
class Solution0112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> flagStack = new Stack<>();
        int sum = 0;
        TreeNode p = root;
        if (root != null) {
            do {
                while (p != null) {
                    stack.push(p);
                    flagStack.push(0);
                    sum += p.val;
                    p = p.left;
                }
                p = stack.pop();
                Integer flag = flagStack.pop();
                if (flag == 0) {
                    stack.push(p);
                    flagStack.push(1);
                    p = p.right;
                } else {
                    if (p.left == null && p.right == null) {
                        if (sum == targetSum) {
                            return true;
                        }
                    }
                    sum -= p.val;
                    p = null;
                }
            }while ( !(p == null && stack.isEmpty()));
        }
        return false;
    }
}

