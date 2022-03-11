package com.shihaiyang.daily;

import java.util.ArrayList;
import java.util.List;

// 0589. N 叉树的前序遍历[递归].
public class Leetcode0589 {
}

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution0589 {
    List<Integer> ret = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        dfs(root);
        return ret;
    }

    public void dfs(Node root) {
        if (root == null) {
            return;
        }
        ret.add(root.val);
        for (Node node : root.children) {
            dfs(node);
        }
    }
}
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};