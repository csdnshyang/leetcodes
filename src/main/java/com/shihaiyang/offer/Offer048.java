package com.shihaiyang.offer;
//Offer II 048. 序列化与反序列化二叉树[完全二叉树层序遍历 16ms].

import com.shihaiyang.leetcodes.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * -1000 <= Node.val <= 1000
 */
public class Offer048 {
    @Test
    public void case1() {
        Codec codec = new Codec();
        String hash = "1,2,3,null,4,5,6,null,null,null,null,null,null,";
        Assertions.assertEquals(codec.serialize(codec.deserialize(hash)), hash);
    }
    @Test
    public void case2() {
        Codec codec = new Codec();
        String hash = "";
        Assertions.assertEquals(codec.serialize(codec.deserialize(hash)), hash);
    }
    @Test
    public void case3() {
        Codec codec = new Codec();
        String hash = "1,null,null,";
        Assertions.assertEquals(codec.serialize(codec.deserialize(hash)), hash);
    }
    @Test
    public void case4() {
        Codec codec = new Codec();
        String hash = "1,2,null,null,null,";
        Assertions.assertEquals(codec.serialize(codec.deserialize(hash)), hash);
    }
}

/**
 * 序列化成字符串，反序列化成树；
 * 层序遍历试试
 */
class Codec {

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
        Integer rootVal = Integer.valueOf(split[0]);
        TreeNode root = new TreeNode(rootVal);
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (index <split.length && !split[index].equals("null")) {
                poll.left = new TreeNode(Integer.parseInt(split[index]));
                queue.add(poll.left);
            }
            index++;
            if (index <split.length && !split[index].equals("null")) {
                poll.right = new TreeNode(Integer.parseInt(split[index]));
                queue.add(poll.right);
            }
            index++;
        }
        return root;
    }
}
