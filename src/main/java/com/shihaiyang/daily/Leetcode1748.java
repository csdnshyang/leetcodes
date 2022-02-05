package com.shihaiyang.daily;

// 1748. 唯一元素的和.[数组计数 0ms].
public class Leetcode1748 {
    public static void main(String[] args) {
        Solution1748 solution1748 = new Solution1748();
        int sumOfUnique = solution1748.sumOfUnique(new int[]{1, 2, 3, 2});
        System.out.println(sumOfUnique);
    }
}

class Solution1748 {
    public int sumOfUnique(int[] nums) {
        int ret = 0;
        int count[] = new int[101];
        for (int num : nums) {
            count[num]++;
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 1) {
                ret += i;
            }
        }
        return ret;
    }
}