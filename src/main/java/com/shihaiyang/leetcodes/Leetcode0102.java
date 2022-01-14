
package com.shihaiyang.leetcodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

// 0102. 二叉树的层序遍历.[层次遍历+记录每层节点数-快了1ms].
public class Leetcode0102 {
}
/**
借助Map来存储层次
其他思路:
每一层开始之前，记录这一层的节点数.即queue.size().
*/
class Solution0102 {
  public List<List<Integer>> levelOrder(TreeNode root) {
    Queue<TreeNode> queue = new LinkedBlockingQueue<>();
    List<List<Integer>> ret = new ArrayList<>();
    if(root != null){
      queue.add(root);
      int level=0;
      while(!queue.isEmpty()){
        ret.add(new ArrayList());
        int size = queue.size();
        for(int i=0;i<size;i++){
          TreeNode poll = queue.poll();
          ret.get(level).add(poll.val);
          if(poll.left != null){
            queue.add(poll.left);
          }
          if(poll.right != null){
            queue.add(poll.right);
          }
        }
        level++;
      }
    }
    return ret;
  }
}
