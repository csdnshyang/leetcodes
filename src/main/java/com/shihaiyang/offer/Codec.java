package com.shihaiyang.offer;

import com.shihaiyang.leetcodes.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化成字符串，反序列化成树；
 * 层序遍历试试
 */
public class Codec {

    public static TreeNode generate(String string) {
        return new Codec().deserialize(string);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer stringBuffer = new StringBuffer();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                if (poll == null) {
                    stringBuffer.append("null").append(",");
                }else{
                    queue.add(poll.left);
                    queue.add(poll.right);
                    stringBuffer.append(poll.val).append(",");
                }
            }
        }
        return stringBuffer.toString();
    }

    // Decodes your encoded data to tree.
    // 1,2,3,x,4,5,6,
    public TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        }
        String[] split = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        Integer rootVal = Integer.valueOf(split[0].trim());
        TreeNode root = new TreeNode(rootVal);
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (index <split.length && !split[index].equals("null")) {
                poll.left = new TreeNode(Integer.parseInt(split[index].trim()));
                queue.add(poll.left);
            }
            index++;
            if (index <split.length && !split[index].equals("null")) {
                poll.right = new TreeNode(Integer.parseInt(split[index].trim()));
                queue.add(poll.right);
            }
            index++;
        }
        return root;
    }
}
