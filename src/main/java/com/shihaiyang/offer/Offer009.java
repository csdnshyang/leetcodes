package com.shihaiyang.offer;
// 剑指 Offer II 009. 乘积小于 K 的子数组
// Offer009. 乘积小于 K 的子数组.[滑动窗口4ms 100%].
public class Offer009 {
    public static void main(String[] args) {
        SolutionOffer009 solutionOffer009 = new SolutionOffer009();
        int lessThanK = solutionOffer009.numSubarrayProductLessThanK(new int[]{1,1,1}, 1);
        System.out.println(lessThanK);
    }
}

/**
 * 给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
 * 示例 1:
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8 个乘积小于 100 的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 * 输入: nums = [1,2,3], k = 0
 * 输出: 0
 * [1,1,1]
 * 1
 */

/**
 * 滑动窗口
 * 题目共性：求数组中小于(或大于)一个阈值的[连续子数组].必须要是连续的.
 * 本体特性：乘积小于阈值的数组个数。
 *      这个跟offer008的有一点区别,那个求长度最小且大于target,在每次从左缩小窗口时判断。
 *      这个是需要在每次放大窗口时判断。如果满足条件。则整个start-end范围内的数组都满足条件.
 * 乘积特性：
 *      如果某个节点加入之后，product<target;
 *         start到end之间有end-start+1种子数组都是可以的。即从start移动到end，每移动以为组成的[start..end]都是满足product<target的。即end-start+1种
 *      如果某个节点加入之后，product>=target，那么需要start右移,直到product<target.
 *         start移动完成之后,product<target了。
 *         start到end之间有end-start+1种子数组都是可以的。 所以这个逻辑是公共逻辑
 *      如果某个节点本身>target.那么包含这个节点的都不可以了。这个就比较狠了
 *         start end都要移动到节点后.这样可以保证子数组不包含这个节点.  // 这个属于特判了,不这样操作，会出现start>end的情况
 */
class SolutionOffer009 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }
        int product = 1;
        int start=0,end=0;
        int count =0;
        while (end < nums.length && start <= end) {
            // 这个属于特判了,不这样操作，会出现start>end,product=0情况
            if (nums[end] >= k) {
                end++;
                start = end;
                product = 1;
                continue;
            }
            product *= nums[end];
            while (product >= k) {
                product /= nums[start++];
            }
            // 从start-end之间如果满足的话，有end-start+1种,比如 [1, 2, 3]  target=10
            // start=0,end=2 显然这个数组都满足。那么应该有[1,2,3] [2,3] [3] 3种情况 [end-start+1]
            count += (end - start + 1);
            end++;
        }
        return count;
    }
}
