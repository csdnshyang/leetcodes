package com.shihaiyang.leetcodes;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

// 0103. 二叉树的锯齿形层序遍历.[层次遍历+空间换时间(记录节点层次)+反转List].

/**
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 */

/**
 * 从左向右 放队列
 * 从右向左 用栈转一下(或者反转一下list....)
 */
public class Leetcode0103 {
}

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        Map<TreeNode, Integer> levelMap = new HashMap<>();
        List<List<Integer>> ret = new ArrayList<>();
        if (root != null){
            queue.add(root);
            levelMap.put(root, 0);
            while(!queue.isEmpty()){
                TreeNode peek = queue.poll();
                Integer level = levelMap.get(peek);
                if (peek.left != null) {
                    queue.add(peek.left);
                    levelMap.put(peek.left, level+1);
                }
                if (peek.right != null) {
                    queue.add(peek.right);
                    levelMap.put(peek.right, level+1);
                }
                if (ret.size() <= level) {
                    ret.add(new ArrayList<>());
                }
                List<Integer> levelItems = ret.get(level);
                levelItems.add(peek.val);
            }
            for(int i=0;i<ret.size();i++){
                // 偶数层反转list
                if(i % 2==1){
                    Collections.reverse(ret.get(i));
                }
            }
        }
        return ret;
    }
}
