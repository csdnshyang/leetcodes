package com.shihaiyang.offer;
// Offer II 047. 二叉树剪枝[后续遍历 0ms].

import com.shihaiyang.leetcodes.TreeNode;

/**
 * 给定一个二叉树 根节点 root ，树的每个节点的值要么是 0，要么是 1。请剪除该二叉树中所有节点的值为 0 的子树。
 * 节点 node 的子树为 node 本身，以及所有 node 的后代。
 * 示例 1:
 * 输入: [1,null,0,0,1]
 * 输出: [1,null,0,null,1]
 * 解释:
 * 只有红色节点满足条件“所有不包含 1 的子树”。
 * 右图为返回的答案。
 * 示例 2:
 * 输入: [1,0,1,0,0,0,1]
 * 输出: [1,null,1,null,1]
 * 解释:
 * 示例 3:
 * 输入: [1,1,0,1,1,0,1,0]
 * 输出: [1,1,0,1,1,null,1]
 * 解释:
 */
public class Offer047 {
}

/**
 * 后续遍历一次
 */
class SolutionOffer047 {
    public TreeNode pruneTree(TreeNode root) {
        TreeNode p = root;
        int top = -1;
        TreeNode[] stack = new TreeNode[10000];
        int[] stack2 = new int[10000];
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
                    if (p.left == null && p.right == null && p.val == 0) {
                        if (p == root) {
                            root = null;
                        }else if (stack[top].left == p) {
                            stack[top].left = null;
                        } else {
                            stack[top].right = null;
                        }
                    }
                    p = null;
                }
            } while (!(p == null && top == -1));
        }
        return root;
    }
}
