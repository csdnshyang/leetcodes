package com.shihaiyang.leetcodes;

/**
找到最大的高度
两边分别计算。
 */
// 0042. 接雨水.[双指针0ms].
// 有时候1ms,有时候0ms.
public class Leetcode0042{
  public static void main(String[] args) {
    Solution0042 solution0042 = new Solution0042();
    int trap = solution0042.trap(new int[]{0, 1});
    System.out.println(trap);
  }
}
/**
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 */

/**
执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：38.2 MB, 在所有 Java 提交中击败了31.81%的用户
双指针方式
*/
class Solution0042 {
    public int trap(int[] height) {
      int ans = 0;
      int left = 0, right = height.length - 1;
      int leftMax = 0, rightMax = 0;
      while (left < right) {
        if (height[left] < height[right]) {
          if (height[left] > leftMax) {
            leftMax = height[left];
          } else {
            ans += leftMax - height[left];
          }
          ++left;
        } else {
          if (height[right] > rightMax) {
            rightMax = height[right];
          } else {
            ans += rightMax - height[right];
          }
          right--;
        }
      }
      return ans;
    }
}
