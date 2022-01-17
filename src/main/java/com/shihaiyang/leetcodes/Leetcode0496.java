package com.shihaiyang.leetcodes;

import java.util.*;

// 0496. 下一个更大元素 I.[典型单调栈 3ms].
// https://leetcode-cn.com/problems/next-greater-element-i/solution/0496-xia-yi-ge-geng-da-yuan-su-idian-xin-4gv4/
public class Leetcode0496 {
    public static void main(String[] args) {
        Solution0496 solution0496 = new Solution0496();
        int[] greaterElement = solution0496.nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2});
        System.out.println(Arrays.toString(greaterElement));
    }
}
/**
 * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
 * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
 * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
 * 示例 1：
 * 输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出：[-1,3,-1]
 * 解释：nums1 中每个值的下一个更大元素如下所述：
 * - 4 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
 * - 1 ，用加粗斜体标识，nums2 = [1,3,4,2]。下一个更大元素是 3 。
 * - 2 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
 * 示例 2：
 * 输入：nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出：[3,-1]
 * 解释：nums1 中每个值的下一个更大元素如下所述：
 * - 2 ，用加粗斜体标识，nums2 = [1,2,3,4]。下一个更大元素是 3 。
 * - 4 ，用加粗斜体标识，nums2 = [1,2,3,4]。不存在下一个更大元素，所以答案是 -1 。
 */

/**
 * 共性：下一个更大元素，都可以想下单调栈。空间换时间..
 * 单调栈+Map
 * 单调栈来快速找到下一个更大元素，并存储map。
 * 在遍历目标数组，map中查询。
 *
 * 单调栈的含义。就是永远保持单调栈都是值有序的。或者越来越大，或者越来越小。本体是要越来越大。
 * 从后往前遍历原始数组。
 * 入栈，保证栈顶元素是比入栈元素大。
 * 如果栈顶大于入栈元素，就正常入栈。
 * 如果栈顶小于入栈元素，就说明这个元素入栈之后，就不单调了。所以要先出栈，再比较栈中下一个元素是否满足。
 * 如果能入栈，就说明栈顶是他下一个最大元素。
 * 如果入栈时，栈为空。则没有下一个更大元素。
 */
class Solution0496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreater = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        // 从后向前
        for (int i = nums2.length - 1; i >= 0; i--) {
            int val = nums2[i];
            // 先判断栈顶是否大于入栈元素，不大于就出栈，比较栈中下一个元素
            while (!stack.isEmpty() && stack.peek() < val) {
                stack.pop();
            }
            // 空栈直接入栈
            if (stack.isEmpty()) {
                stack.push(val);
            }else {
                // 不是非空，说明栈顶就是下一个最大，存入map
                nextGreater.put(val, stack.peek());
                stack.push(val);
            }
        }
        for (int i = 0; i < nums1.length; i++) {
            // 如果不存在默认-1.直接替换原来的栈中值即可.
            nums1[i] = nextGreater.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }
}
