package com.shihaiyang.leetcodes;

import java.util.ArrayList;
import java.util.List;

// 0448. 找到所有数组中消失的数字.[In-Place Hash 3ms].
public class Leetcode0448 {
    public static void main(String[] args) {
        Solution0448InPlaceHash solution0448 = new Solution0448InPlaceHash();
        List<Integer> disappearedNumbers = solution0448.findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
        disappearedNumbers.stream().forEach(integer -> System.out.print(integer + ","));
        System.out.println();
    }
}
/**
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 * 输入：nums = [4,3,2,7,8,2,3,1]  [-4,-3,-2,-7,8,2,-3,-1]
 * 输出：[5,6]
 * 输入：nums = [1,1]
 * 输出：[2]
 */

/**
 * 直接按数组的指进行访问，最终访问不到的就是缺少的值
 * 思路对了。但是绕远了一点.遍历每一个，然后把nums[i]的值直接替换成负数就能标记出出现过
 * 原地hash
 */
class Solution0448InPlaceHashOfficial {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int scan = (nums[i] - 1) % len;
            nums[scan] = nums[scan] + len;
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (nums[i] <= len) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}

class Solution0448InPlaceHash {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int scan = Math.abs(nums[i]) - 1;
            if (nums[scan] > 0) {
                nums[scan] = -nums[scan];
            }
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}

class Solution0448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int scan = nums[i];
            while (scan != 0 && nums[scan - 1] != 0) {
                int newScan = nums[scan - 1];
                nums[scan - 1] = 0;
                scan = newScan;
            }
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}