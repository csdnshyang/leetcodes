package com.shihaiyang.offer;

import com.shihaiyang.leetcodes.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

// Offer II 055. 二叉搜索树迭代器.[非递归算法拆分 16ms].

/**
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 *
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 *
 * 可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
 */
public class Offer055 {
    @Test
    public void case1() {
        TreeNode root = Codec.generate("7, 3, 15, null, null, 9, 20");
        BSTIterator bSTIterator = new BSTIterator(root);
        Assertions.assertEquals(bSTIterator.next(), 3);    // 返回 3
        Assertions.assertEquals(bSTIterator.next(), 7);    // 返回 7
        Assertions.assertTrue(bSTIterator.hasNext()); // 返回 True
        Assertions.assertEquals(bSTIterator.next(), 9);    // 返回 9
        Assertions.assertTrue(bSTIterator.hasNext()); // 返回 True
        Assertions.assertEquals(bSTIterator.next(), 15);    // 返回 15
        Assertions.assertTrue(bSTIterator.hasNext()); // 返回 True
        Assertions.assertEquals(bSTIterator.next(), 20);    // 返回 20
        Assertions.assertFalse(bSTIterator.hasNext()); // 返回 False
    }
}

/**
 * 非递归算法中序遍历。
 * 把循环过程当做next
 * 把跳出条件当成hasNext
 */
class BSTIterator {
    TreeNode p;
    Deque<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        p = root;
        stack = new LinkedList<>();
    }

    public int next() {
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
        p = stack.pop();
        int ret = p.val;
        p = p.right;
        return ret;
    }

    public boolean hasNext() {
        return !(p == null && stack.isEmpty());
    }
}
