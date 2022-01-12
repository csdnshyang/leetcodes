package com.shihaiyang.leetcodes;

import java.util.Stack;

// 0104. 二叉树的最大深度.[非递归后序遍历].
// https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/104-er-cha-shu-de-zui-da-shen-du-fei-di-7g5qp/
public class Leetcode0104 {
}

/**
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 */

/**
 * 非递归后序遍历  LRD
 * 1. 初始化 stack，flag，maxLength top(java可省略)
 * 2. 判空 while do
 * 3. 入栈
 * 4. 左下移动
 * 5. 出栈
 * 6. 判断flag
 *  7. 入栈
 *  8. 右下移动
 * 9. else 操作：叶子节点 比较maxLength
 *  10. p=null;
 * 11. 结束while
 * 12. 输出maxLength
 */
class Solution0104 {
    public int maxDepth(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> flagStack = new Stack<>();
        Integer flag, maxLength=0;
        TreeNode p = root;
        if (root == null) {
            return 0;
        }
        do {
            while (p != null) {
                stack.push(p);
                flagStack.push(0);
                p = p.left;
            }
            p = stack.pop();
            flag = flagStack.pop();
            if (flag == 0) {
                stack.push(p);
                flagStack.push(1);
                p = p.right;
            } else {
                if (p.left == null && p.right == null) {
                    maxLength = Math.max(stack.size()+1, maxLength);
                }
                p = null;
            }

        } while (!(p == null && stack.isEmpty()));
        return maxLength;
    }
}
