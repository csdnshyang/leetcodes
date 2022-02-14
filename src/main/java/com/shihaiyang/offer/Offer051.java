package com.shihaiyang.offer;

// Offer II 050. 向下的路径节点之和.[递归+前缀和+Map].

import com.shihaiyang.leetcodes.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 示例 1：
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 */
public class Offer051 {
}

/**
 * dfs+前缀和+map
 */
class SolutionOffer051 {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);
        int ret = dfs(root, 0L, prefix, targetSum);
        return ret;
    }

    public int dfs(TreeNode node, long curr, Map<Long, Integer> prefix, int targetSum) {
        if (node == null) {
            return 0;
        }
        int ret = 0;
        curr += node.val;

        ret += prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs(node.left, curr, prefix, targetSum);
        ret += dfs(node.right, curr, prefix, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);
        return ret;
    }
}