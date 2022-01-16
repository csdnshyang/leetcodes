package com.shihaiyang.leetcodes;

// 0416. 分割等和子集.[存在类动态规划(优化空间) 23ms].
// https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/0416-fen-ge-deng-he-zi-ji-cun-zai-lei-do-8dvs/
public class Leetcode0416 {
    public static void main(String[] args) {
        Solution0416Optimize solution0416 = new Solution0416Optimize();
        boolean canPartition = solution0416.canPartition(new int[]{1,5,10,6});
        System.out.println(canPartition);
    }
}

/**
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 示例 1：
 * 输入：nums = [1,5,11,5]   [1,5,5,11]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * [2,2,1,1]
 * true
 * [2,2,3,5]
 * false
 * [1,5,10,6]
 * true
 */

/**
 * 想简单了..是一道动态规划.  0，1背包问题
 * 存在类动态规划。是否存在一个子数组，和等于target.
 * 本题比较特殊，target是 二分之一总和.
 * 如果和为奇数，则不可能分为两个和相等的子序列
 * 主要思路是一系列数中，分析没一个数都是有两个状态，放或者不放。
 * 1. 确认状态
 *      最终结果：n个数中是否有k个数，使得和为target。
 *      化成子问题：
 *          第n个数不选，即n-1个数中，是否有k个数已经何为target。
 *          第n个数选，即n-1个数中，有k-1个数使得和为target-nums[n]
 * 2. 状态转移方程
 *      nums 中n个元素
 *      dp[0..n-1][0..target]  dp[i][j] 一维i表示数组中的第i个元素.二维j表示和为j。值为true/false。表示是否可以组成和为j。
 *      dp[i][j] = dp[i-1][j-nums[i]]  //   nums[i] < j 选择了第i个元素,就判断 dp[i-1][j-num[i]]作为和是否可以拼成.
 *                                     // 这注意是比较num[i-1] 选择了nums[i] 应该比较nums[i-1]这个元素能不能满足和j-nums[i-1]
 *      dp[i][j] = dp[i-1][j]  // 前i-1个是否已经组成了和为j.
 *      dp[i][j] = j == dp[i][j] -> dp[i][j]=true;     // 元素本身==j
 * 3. 初始状态
 *      dp[0..i][0] 好像不需要初始化  dp[i][0] dp[i][j]好像是一样的
 *      dp[0][dp[0]] = true;
 * 4. 计算顺序
 *      dp[0][0]dp[0][1]dp[0][2]
 *      dp[1][0]dp[1][1]...
 *  原则：dp[i][j]计算的时候  dp[i-1][j]  dp[i][j-nums[i]]已经算出.
 */
class Solution0416 {
    public boolean canPartition(int[] nums) {
        if (nums.length == 1) {
            return false;
        }
        int sum=0;
        for(int i:nums){
            sum += i;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;
        boolean dp[][] = new boolean[nums.length][target+1];
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < target + 1; j++) {
                // 初始先把i-1的结果拿来. 有可能走不到最后两个if.如果nums[i]>j的时候. 所以先把上一个数的状态拿来
                dp[i][j] = dp[i - 1][j];
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j) {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }
            }
        }
        return dp[nums.length - 1][target];
    }
}

/**
 * 动态规划优化一下空间复杂度。
 * 从二维压缩到一维。
 * 其实根本思路是，新增一个元素的时候，完全是依赖于上一个元素的状态的。
 * 相当于每次新增都把上一个元素的状态舍弃，复制一份放到新元素的状态里。
 *
 * 另外一个就是要target遍历从后往前。为了防止前面的计算影响上一个元素的最终状态。
 *  例如  [2,2,3,5] 这个，我中间就返回了true。就是因为 2+2 和为4是true。如果从头计算，那么就会产生4+2=6为ture的情况
 *  所以从target一直到0。这样放第二个2的时候，不会把target=6设置为true. 主要就是防止多加数据。
 */
class Solution0416Optimize {
    public boolean canPartition(int[] nums) {
        int sum=0;
        for (int i : nums){
            sum += i;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;
        boolean dp[] = new boolean[target + 1];
        if (nums[0] <= target){
            dp[nums[0]] = true;
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == target) {
                return true;
            }
            for (int j = target; j - nums[i] < target && j - nums[i] > 0; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}