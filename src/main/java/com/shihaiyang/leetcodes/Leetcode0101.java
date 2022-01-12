package com.shihaiyang.leetcodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

// 0101. 对称二叉树.[递归0ms, 遍历5ms].
// https://leetcode-cn.com/problems/symmetric-tree/solution/101-dui-cheng-er-cha-shu-di-gui-0ms-bian-v9v8/
public class Leetcode0101 {
}

/**
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 */

/**
 * 用递归简单又快速
 */
class Solution0101Recursion {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return childIsMatch(root.left, root.right);
    }
    // 递归比较两个节点是否相等
    // 左节点的左孩子与右节点的右孩子相等 && 左节点的右孩子与右节点的左孩子相等  && 左右节点值相等
    public boolean childIsMatch(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (nodeIsMatch(left, right) && childIsMatch(left.left,right.right) && childIsMatch(left.right ,right.left)) {
            return true;
        }
        return false;
    }
    public boolean nodeIsMatch(TreeNode a, TreeNode b) {
        if (a != null && b != null && a.val == b.val) {
            return true;
        }
        return false;
    }
}

/**
 * 层级遍历。
 * 每一层的长度n
 * 遍历每一层的时候，检查下一层，如果都对称，再加入队列。
 * 如果不对称，直接返回false
 *
 */
class Solution0101 {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        List<TreeNode> list = new ArrayList<>();
        if (root == null) {
            return true;
        }
        if (!nodeIsMatch(root.left , root.right)) {
            return false;
        }
        int filter = 0;
        queue.add(root);
        list.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (size > 0 && i < size / 2) {
                    if (!childIsMatch(list.get(filter+i), list.get(filter+size-1-i))) {
                        return false;
                    }
                }
                if (poll.left != null) {
                    queue.add(poll.left);
                    list.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                    list.add(poll.right);
                }
            }
            filter += size;
        }
        return true;
    }

    public boolean childIsMatch(TreeNode left, TreeNode right) {
        if (!nodeIsMatch(left.left,right.right) || !nodeIsMatch(left.right ,right.left)) {
            return false;
        }
        return true;
    }

    public boolean nodeIsMatch(TreeNode a, TreeNode b) {
        if ((a==null && b==null) || (a != null && b != null && a.val == b.val)) {
            return true;
        }
        return false;
    }
}
