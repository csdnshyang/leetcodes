package com.shihaiyang.offer;

import com.shihaiyang.leetcodes.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

// Offer II 046. 二叉树的右侧视图[层级遍历 3ms].
public class Offer046 {
}

/**
 * 层级遍历的最后一个元素
 */
class SolutionOffer046 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        if (root != null) {
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    if (poll.left != null) {
                        queue.add(poll.left);
                    }
                    if (poll.right != null) {
                        queue.add(poll.right);
                    }
                    if (i == size - 1) {
                        ret.add(poll.val);
                    }
                }
            }
        }
        return ret;
    }
}