package com.shihaiyang.offer;
// 剑指 Offer II 043. 往完全二叉树添加节点
// Offer043. 往完全二叉树添加节点.[层级遍历 16ms].

import com.shihaiyang.leetcodes.TreeNode;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大，第 n 层有 2n-1 个节点）的，并且所有的节点都尽可能地集中在左侧。
 * 设计一个用完全二叉树初始化的数据结构 CBTInserter，它支持以下几种操作：
 * CBTInserter(TreeNode root) 使用根节点为 root 的给定树初始化该数据结构；
 * CBTInserter.insert(int v)  向树中插入一个新节点，节点类型为 TreeNode，值为 v 。
 * 使树保持完全二叉树的状态，并返回插入的新节点的父节点的值；
 * CBTInserter.get_root() 将返回树的根节点。
 * 输入：inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
 * 输出：[null,1,[1,2]]
 * 输入：inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
 * 输出：[null,3,4,[1,2,3,4,5,6,7,8]]
 */
public class Offer043 {

}

/**
 * 层级遍历
 * 借助于队列。遍历每一层放置到队列中。
 * 如果队列头结点左右子为空，新结点就从左往右放置。
 * 如果左右节点都不为空，那就说明是一个完全的父节点，出队。
 * 遍历左右子节点时都把子节点入队。
 */
class CBTInserter {
    Queue<TreeNode> queue;
    TreeNode root;
    public CBTInserter(TreeNode root) {
        this.root = root;
        queue = new LinkedBlockingQueue<>();
        if (root != null) {
            queue.add(root);
            while (!queue.isEmpty()) {
                if (queue.peek().left != null) {
                    queue.add(queue.peek().left);
                }else {
                    break;
                }
                if (queue.peek().right != null) {
                    queue.add(queue.poll().right);
                } else {
                    break;
                }
            }
        }
    }

    public int insert(int v) {
        if (queue.peek().left == null) {
            queue.peek().left = new TreeNode(v);
            queue.add(queue.peek().left);
            return queue.peek().val;
        }else{
            queue.peek().right = new TreeNode(v);
            queue.add(queue.peek().right);
            return queue.poll().val;
        }
    }

    public TreeNode get_root() {
        return root;
    }
}


