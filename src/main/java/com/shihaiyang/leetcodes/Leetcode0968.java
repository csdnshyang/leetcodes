package com.shihaiyang.leetcodes;

// 0968. 监控二叉树.[后序遍历+贪心算法 0ms 100%].
public class Leetcode0968 {

}
/**
 *给定一个二叉树，我们在树的节点上安装摄像头。
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * 计算监控树的所有节点所需的最小摄像头数量。
 * 输入：[0,0,null,0,0]
 * 输出：1
 * 解释：如图所示，一台摄像头足以监控所有节点。
 *           0
 *         0 --此处装摄像头
 *       0  0
 */

/**
 * 贪心算法。
 * 因为一个摄像头在中间的话，可以监控上一层和下一层，所以尽量在中间。
 * 也就是尽量叶子节点尽量的不放摄像头，而放在叶子的父上。
 * 空节点应该设置为覆盖状态。这样子节点是不覆盖状态。
 * 采用后续遍历，先判断左右孩子。
 * 区分状态
 * 0：无覆盖
 * 1：有摄像头
 * 2：有覆盖
 */
class Solution0968 {
    int result = 0;
    public int minCameraCover(TreeNode root) {
        int camera = camera(root);
        if (camera == 0) {
            result++;
        }
        return result;
    }
    public int camera(TreeNode root) {
        // 空节点返回已覆盖，这样保证子节点是未覆盖状态
        if (root == null) {
            return 2;
        }
        // 先处理左右子节点
        int left = camera(root.left);
        int right = camera(root.right);
        // 有一个子没有被覆盖，父节点装摄像头
        if (left == 0 || right == 0) {
            result++;
            return 1;
        }
        // 子有一个摄像头，父节点被覆盖
        if (left == 1 || right == 1) {
            return 2;
        }
        // 子节点都是已覆盖，父节点未覆盖
        if (left == 2 && right == 2) {
            return 0;
        }
        return -1;
    }
}