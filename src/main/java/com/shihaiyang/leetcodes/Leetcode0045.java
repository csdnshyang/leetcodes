package com.shihaiyang.leetcodes;

// 0045. 跳跃游戏 II.[动态规划].
// 0045. 跳跃游戏 II.[贪心算法1ms, 动态规划100ms].
// https://leetcode-cn.com/problems/jump-game-ii/solution/45-tiao-yue-you-xi-iitan-xin-suan-fa-by-zoibq/
public class Leetcode0045 {
    public static void main(String[] args) {
        Solution0045Greedy solution0045 = new Solution0045Greedy();
        int jump = solution0045.jump(new int[]{2,3,1,1,4});
        System.out.println(jump);
    }
}

/**
 *给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 *
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 */

/**
 * 动态规划时间太久了...试试贪心算法..greedy algorithm  GA
 * 贪心算法的思路是，每一跳都把可跳的最后一个节点当做截止范围. 从起跳点到截止点之间,再找一个可跳最远的节点，继续起跳.
 * 每次起跳，步数+1.
 * 1,1,2,1,1
 */
class Solution0045Greedy {
    public int jump(int[] nums) {
        if(nums.length<2){
            return 0;
        }
        // 初始end为0，步数为0
        int maxDestination = 0;
        int end = maxDestination;
        int step = 0;
        for (int i = 0; i < nums.length; i++) {
            // 当i>大于上一跳的截止点时. 说明进入了新一跳的区间内。 step+1。重新计算end为上一跳的最大值。
            // [2,3,1,1,4]  初始从0开始.  当i=1时,说明从第一步跳出来了.进入第一步可跳的区间.
            // 此时end更新为上一跳,即[0]的最大跳跃长度.
            // 从i=1到i=nums[0]之间都是第一步的范围
            if (i > end) {
                end = maxDestination;
                step++;
            }

            // 从当前步数的范围内的节点，如果能跳到结尾, 就说明从当前步数再跳一步就可到最后  即step+1
            // 如果可跳跃最大位置 >= nums.length-1    [1,2,3,4] length=4, 跳到下标为3即可  length-1
            maxDestination = Math.max(maxDestination, nums[i] + i);
            if (maxDestination >= nums.length-1) {
                return step+1;
            }
        }
        return step;
    }
}

/**
 * 最值类动态规划
 * 1. 确定状态
 *      最终结果：这个思路应该主要是记录最小步数，最远距离应该是辅助。走到位置n时，最远记录为k。如果走到n-1的最远距离>n,最小步数应该=n-1，
 *      从左向右时，如果最小步数k走到n-i的最远距离是i，那么f[n-i]到f[i]应该都是设置成n-i的步数k+1
 *      化成子问题： 应该从左到右思考，最少k步到第n-i个节点的最少步+ nums[n-i] == n i就是最小步数  n的步数是k+1
 *          farthest[i] = (i+nums[i], farthest[i-1]) //i可跳最远距离
 *          i可跳的最远距离所需的步骤为 跳到i的最少步数step[i]+1
 *          之前也可能有过i-x的节点已经跳到过后面的节点，并且设置了步数了 即step[i+x]  所以要取min(step[i]+1, step[i+n])
 *          for(j<farthest[i]-i)  // 能跳到的最远距离内，都设置下最小步数
 *            step[i+j]=min(step[i+j], step[i]+1)  // 可以跳到的最远距离的最小步数
 * 2. 状态转移方程
 *      farthest[i] = max(i+nums[i], farthest[i-1])
 *      j<farthest[i]-i => </farthest[i]-i>step[i+j]=min(step[i+j], step[i]+1)
 * 3. 初始状态
 *      step[0]=0
 *      farthest[0]=nums[0]
 *      while(j < farthest[0]) farthest[i+j]=step[0]+1; //判断i+j < nums.length
 * 4. 计算顺序
 *      farthest[1],farthest[2],farthest[3]...farthest[n]
 *      step[1],step[2],step[3]...step[n]
 */
class Solution0045 {
    public int jump(int[] nums) {
        // 特判
        if(nums.length==1){
            return 0;
        }
        int farthest[] = new int[nums.length];
        int step[] = new int[nums.length];

        // 初始状态
        farthest[0] = nums[0];
        step[0]=0;
        if(farthest[0] == nums.length-1){
            return step[0]+1;
        }
        int i,j;
        for (j = 1; j <= farthest[0]; j++) {
            if (j < nums.length) {
                step[j]= step[0]+1;
            }
        }

        // 状态专辑方程
        // 最远距离：取上一节点的最远距离和当前可跳最远距离的最大值
        // 最小步数：向后计算，计算当前节点后面可跳最远距离的最小步数
        // 计算顺序 从左到右
        // farthest[1],farthest[2]...farthest[n]
        // 原则 计算step[n]时，step[n-1]已经算出来
        for (i = 1; i < nums.length; i++) {
            // i可跳最远距离
            farthest[i] = Math.max(farthest[i - 1], i + nums[i]);
            if(farthest[i] == nums.length-1){
                return step[i]+1;
            }
            // i可跳的最远距离内都设置下最小步数
            for (j = 1; j <= farthest[i] - i; j++) {
                if (i + j < nums.length) {
                    if (step[i+j] == 0) {
                        step[i + j] = step[i]+1;
                    }else {
                        step[i + j] = Math.min(step[i + j], step[i] + 1);
                    }
                }
            }
        }
        return step[nums.length - 1];
    }
}

