package com.shihaiyang.contest;

import java.util.ArrayList;
import java.util.List;

// 5981. 分组得分最高的所有下标.[两次遍历 8ms].
public class Leetcode5981 {
    public static void main(String[] args) {
        Solution5981 solution5981 = new Solution5981();
        List<Integer> maxScoreIndices = solution5981.maxScoreIndices(new int[]{0, 0, 1, 0});
        System.out.println(maxScoreIndices.toString());
    }
}

/**
 * 输入：nums = [0,0,1,0]
 * 输出：[2,4]
 * 解释：按下标分组
 * - 0 ：numsleft 为 [] 。numsright 为 [0,0,1,0] 。得分为 0 + 1 = 1 。
 * - 1 ：numsleft 为 [0] 。numsright 为 [0,1,0] 。得分为 1 + 1 = 2 。
 * - 2 ：numsleft 为 [0,0] 。numsright 为 [1,0] 。得分为 2 + 1 = 3 。
 * - 3 ：numsleft 为 [0,0,1] 。numsright 为 [0] 。得分为 2 + 0 = 2 。
 * - 4 ：numsleft 为 [0,0,1,0] 。numsright 为 [] 。得分为 3 + 0 = 3 。
 * 下标 2 和 4 都可以得到最高的分组得分 3 。
 * 注意，答案 [4,2] 也被视为正确答案。s
 */

/**
 * 遍历第一次，左边0分，右边总分。
 * 再遍历第二次。
 * 如果==0，左+1，右不变。
 * 如果==1，左不变，右-1
 * max
 * 如果>max，记录新坐标。
 * 如果=max，add 坐标
 */
class Solution5981 {
    public List<Integer> maxScoreIndices(int[] nums) {
        int max = 0;
        int left=0, right=0;
        for (int num : nums) {
            if (num == 1) {
                right++;
            }
        }
        List<Integer> indexes = new ArrayList<>();
        max = right;
        indexes.add(0);
        for (int i = 0; i <nums.length; i++) {
            if (nums[i] == 0) {
                left++;
            } else {
                right--;
            }
            if (max < left + right) {
                indexes.clear();
                indexes.add(i + 1);
                max = left + right;
            } else if (max == left + right) {
                indexes.add(i + 1);
            }
        }
        return indexes;
    }
}