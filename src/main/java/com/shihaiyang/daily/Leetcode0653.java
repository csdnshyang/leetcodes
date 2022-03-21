package com.shihaiyang.daily;

import com.shihaiyang.leetcodes.TreeNode;

import java.util.*;

// 0653. 两数之和 IV - 输入 BST.[树遍历+Map].
/*
给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。

 

示例 1：


输入: root = [5,3,6,2,4,null,7], k = 9
输出: true
示例 2：


输入: root = [5,3,6,2,4,null,7], k = 28
输出: false
 */
public class Leetcode0653 {
}

/*
树的遍历+Map缓存
前序
 */
class Solution0653 {
    public boolean findTarget(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        Map<Integer, Byte> already = new HashMap<>();
        TreeNode p = root;
        if (root != null) {
            do {
                while (p != null) {
                    stack.add(p);
                    p = p.left;
                }
                p = stack.poll();
                if (already.containsKey(k - p.val)) {
                    return true;
                }
                already.put(p.val, Byte.valueOf("1"));
                p = p.right;
            } while (!(p == null && stack.isEmpty()));
        }
        return false;
    }
}