package com.shihaiyang.leetcodes;

import java.util.Stack;
// 0230. 二叉搜索树中第K小的元素.[中序遍历 0ms 100%].
public class Leetcode0230 {

}

/**
 * 二叉线索树就是二叉排序树
 * 中序遍历 LDR 第k个节点就是 0ms 非递归终于争气一次
 */
class Solution0230 {
    public int kthSmallest(TreeNode root, int k) {
        int i = 1;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        if (root != null) {
            do {
                while (p != null) {
                    stack.push(p);
                    p = p.left;
                }
                p = stack.pop();
                if (i++ == k) {
                    return p.val;
                }
                p = p.right;
            } while (!(p == null && stack.isEmpty()));
        }
        return 0;
    }
}
