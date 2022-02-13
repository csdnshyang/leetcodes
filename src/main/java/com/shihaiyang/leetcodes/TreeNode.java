package com.shihaiyang.leetcodes;
// 通用树节点
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    // [1,3,2,5,3,null,9]
    public static TreeNode generate(Integer[] vals) {
        if (vals.length == 0) {
            return new TreeNode();
        }
        TreeNode[] arr = new TreeNode[vals.length];
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] != null) {
                TreeNode node = new TreeNode(vals[i]);
                arr[i] = node;
                int parent = (i-1) / 2;
                boolean leftFlag = ((i & 1) == 1);
                if (leftFlag) {
                    arr[parent].left = node;
                } else {
                    arr[parent].right = node;
                }
            }
        }
        return arr[0];
    }
}
