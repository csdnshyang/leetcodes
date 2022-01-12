package com.shihaiyang.leetcodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 0094. 二叉树的中序遍历.[栈应用,非递归 100%].
public class Leetcode0094 {

}

class Solution0094 {
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        List<Integer> ret = new ArrayList<>();
        if (root != null) {
            do {
                while (p != null) {
                    stack.push(p);
                    p = p.left;
                }
                p = stack.pop();
                ret.add(p.val);
                p = p.right;
            } while ( ! (p==null && stack.isEmpty()));
        }
        return ret;
    }
}