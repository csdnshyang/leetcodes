package com.shihaiyang.offer;

// Offer II 051. 节点之和最大的路径[递归后序遍历 0ms].

import com.shihaiyang.leetcodes.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给定一个二叉树的根节点 root ，返回其 最大路径和，即所有路径上节点值之和的最大值。
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 */
public class Offer051 {
    SolutionOffer051 solutionOffer051 = new SolutionOffer051();

    @Test
    public void case1() {
        TreeNode generate = Codec.generate("1,2,3");
        int maxPathSum = solutionOffer051.maxPathSum(generate);
        Assertions.assertEquals(maxPathSum, 6);
    }

    @Test
    public void case2() {
        TreeNode generate = Codec.generate("-10,9,20,null,null,15,7");
        int maxPathSum = solutionOffer051.maxPathSum(generate);
        Assertions.assertEquals(maxPathSum, 42);
    }
}

/**
 * 后续+比较最大;
 * 左右子中取大于0的，如果小于0就舍去了。
 * 然后dfs返回该父节点最大的可能值。
 */
class SolutionOffer051 {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        //获取左右子树的最大"半径"和,当值小于0,则抛弃
        int left = Math.max(0,dfs(node.left));
        int right = Math.max(0,dfs(node.right));
        //比较最大值,每颗子树的路径最大和 都可能为整颗树的路径最大和
        max = Math.max(max, node.val + left + right);
        //返回以root为顶点的树的最大"半径"和
        return node.val + Math.max(left,right);
    }
}