package com.shihaiyang.offer;

import java.util.HashMap;
import java.util.Map;

// 剑指 Offer II 010. 和为 k 的子数组
// Offer010. 和为 k 的子数组.[前缀和24ms].
// https://leetcode-cn.com/problems/QTMn0o/solution/offer010-he-wei-k-de-zi-shu-zu-qian-zhui-wn1d/
public class Offer010 {
    public static void main(String[] args) {
        SolutionOffer010 solutionOffer010 = new SolutionOffer010();
        int sum = solutionOffer010.subarraySum(new int[]{1,-1,0}, 0);
        System.out.println(sum);
    }
}
/**
 * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
 * 示例 1 :
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2
 * 解释: 此题 [1,1] 与 [1,1] 为两种不同的情况
 * 示例 2 :
 * 输入:nums = [1,2,3], k = 3
 * 输出: 2
 * [1,-1,0]
 * 0
 */

/**
 * 滑动窗口典型题。
 * 题目共性关键字：连续子数组
 * 本体特性：start喝end都要到结尾
 *
 * 设置start，end两个范围
 * end先移动，扩大范围
 * 每次>=target的时候，end停止。
 * start移动，缩小范围。
 * 每次==target时，count++;
 *
 * 提交了几次发现.....滑动窗口解不了...
 * 前缀和解决...
 * 遍历一遍，生成前缀和数组。把前缀和的值和个数存入map
 * 在计算元素i的时候，判断map中是否有前缀和=preSum[i+1]-k 的key
 * 取得前缀和map中的数量count。就是从当前索引往前能满足和为k的count
 * 在依次+=每个元素
 */
class SolutionOffer010 {
    public int subarraySum(int[] nums, int k) {
        int count=0;
        int preSum[] = new int[nums.length + 1];
        Map<Integer, Integer> preSumMap = new HashMap<>();
        preSum[0]=0;
        preSumMap.put(preSum[0], 1);
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
            Integer orDefault = preSumMap.getOrDefault(preSum[i + 1] - k, 0);
            count += orDefault;
            if (preSumMap.containsKey(preSum[i + 1])) {
                preSumMap.put(preSum[i + 1], preSumMap.get(preSum[i + 1]) + 1);
            } else {
                preSumMap.put(preSum[i + 1], 1);
            }

        }
        return count;
    }
}
