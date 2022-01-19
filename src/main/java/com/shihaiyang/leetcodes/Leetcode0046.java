package com.shihaiyang.leetcodes;

import java.util.ArrayList;
import java.util.List;

// 0046. 全排列.[回溯法1ms].
public class Leetcode0046 {
    public static void main(String[] args) {
        Solution0046 solution0046 = new Solution0046();
        List<List<Integer>> permute = solution0046.permute(new int[]{1, 2, 3});
        System.out.println(permute);
    }
}

/**
 *给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */

/**
 * 回溯法. 1ms
 */
class Solution0046 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        // 用于剪枝
        boolean used[] = new boolean[nums.length];
        backtrace(nums, used, ret, new ArrayList<>());
        return ret;
    }

    /**
     *
     * @param nums 原始数据
     * @param used 用于剪枝，存储某个索引是否使用过
     * @param ret 最终数据集
     * @param cur 每一次的字符串，添加到最终数据集时需要copy一份出来。
     */
    public void backtrace(int[] nums, boolean used[], List<List<Integer>> ret, List<Integer> cur) {
        if (nums.length == cur.size()) {
            ret.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 剪枝
            if (used[i]) {
                continue;
            }
            // 添加一个字符进去
            cur.add(nums[i]);
            used[i] = true;
            backtrace(nums, used, ret, cur);
            // 回退 把刚才字符移出
            // 进来1 剪枝之后进2，此时下一层遍历只剩3.  当2这次遍历完成后，把2退出来，保证进3的时候，2还是未使用状态.
            // 每一层都如此。只有上层的传进来数据影响每次结果，本层内的遍历不应该影响下一次遍历
            cur.remove(cur.size() - 1);
            used[i] = false;
        }
    }
}