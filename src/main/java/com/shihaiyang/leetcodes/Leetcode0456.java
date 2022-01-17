package com.shihaiyang.leetcodes;

import java.util.Stack;

// 0456. 132 模式[单调栈 12ms].
// https://leetcode-cn.com/problems/132-pattern/solution/0456-132-mo-shi-dan-diao-zhan-12ms-by-sh-8ru0/
public class Leetcode0456 {
    public static void main(String[] args) {
        Solution0456 solution0456 = new Solution0456();
        boolean pattern = solution0456.find132pattern(new int[]{1,0,1,-4,-3});
        System.out.println(pattern);
    }
}

/**
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，
 * 并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 * 示例 2：
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 * [1,0,1,-4,-3]
 * false
 */

/**
 * 单调栈
 * 判断单调栈的长度。长度的判断不够。比如[1,0,1,-4,-3]，结果就不正确
 * 通过单调栈来找到  132中的 32。在遍历看能不能有小于2的1.
 */
class Solution0456 {
    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int two = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            int val = nums[i];
            if (val < two) {
                return true;
            }
            while (!stack.isEmpty() && val > stack.peek()) {
                two=stack.pop();
            }
            stack.push(val);
        }
        return false;
    }
}