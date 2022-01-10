
package com.shihaiyang.leetcodes;
// 102. 二叉树的层序遍历.[层次遍历+Map存储层次].
public class Leetcode0102 {
}
class Solution0102 {
  public List<List<Integer>> levelOrder(TreeNode root) {
    Queue<TreeNode> queue = new LinkedBlockingQueue<>();
    Map<TreeNode, Integer> levelMap = new HashMap<>();
    List<List<Integer>> ret = new ArrayList<>();
    if(root != null){
      queue.add(root);
      levelMap.put(root, 0);
      while(!queue.isEmpty()){
        TreeNode poll = queue.poll();
        int level = levelMap.get(poll);
        if(ret.size()<= level){
          ret.add(new ArrayList());
        }
        ret.get(level).add(poll.val);
        if(poll.left != null){
          queue.add(poll.left);
          levelMap.put(poll.left, level+1);
        }
        if(poll.right != null){
          queue.add(poll.right);
          levelMap.put(poll.right, level+1);
        }
      }
    }
    return ret;
  }
}
