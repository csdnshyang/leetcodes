package com.shihaiyang.offer;
// 剑指 Offer II 008. 和大于等于 target 的最短子数组
// Offer008. 和大于等于 target 的最短子数组.[滑动窗口典型应用1ms].
// https://leetcode-cn.com/problems/2VG8Kg/solution/offer008-he-da-yu-deng-yu-target-de-zui-uysd8/
public class Offer008 {
    public static void main(String[] args) {
        SolutionOffer008 solutionOffer008 = new SolutionOffer008();
        int minSubArrayLen = solutionOffer008.minSubArrayLen(6, new int[]{10,2,3});
        System.out.println(minSubArrayLen);
    }
}

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0 。
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * 6
 * [10,2,3]
 */

/**
 * 滑动窗口应用 （别人写的代码真优雅...自己写了两倍的代码各种判断,哎.垃圾）
 * 滑动窗口的原理。定义一个窗口，就是一个小的数组。start=0，end=0
 * while end<nums.length // 最外层判断的是end的大小，如果end已经到结尾，把最后一个元素加入sum依然<target. 那么start左侧就不需要再缩小了，因为越缩越小。不会有符合条件的结果了
 *      sum+=end元素  // end位置加入到sum中, 接着判断sum大小
 *      while sum>=target  // 如果sum >= target，进入内层循环，记录长度。缩小窗口
 *          min(len, min)  //表示已经满足>=target的需求.此时记录 min=min(长度, min)
 *          sum+=start++; // 尝试左侧缩小窗口，start后移,直到再次小于target。
 *      end++  // 跳出内层while说明已经小于sum. end右移,右侧放大窗口
 */
class SolutionOffer008 {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int start=0,end=0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= target) {
                int len = end - start + 1;
                min = Math.min(len, min);
                sum -= nums[start--];
            }
            end++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
