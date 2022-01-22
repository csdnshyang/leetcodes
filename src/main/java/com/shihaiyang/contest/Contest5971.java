package com.shihaiyang.contest;

import java.util.Arrays;

// 5971. 打折购买糖果的最小开销.[排序].
public class Contest5971 {
    public static void main(String[] args) {
        Solution5971 solution5971 = new Solution5971();
        int minimumCost = solution5971.minimumCost(new int[]{5, 5});
        System.out.println(minimumCost);
    }
}

/**
 * 排序 从后往前两位两位取
 */
class Solution5971 {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int sum = 0;
        int index=0;
        for (int i = cost.length - 1; i >= 0; i--) {
            if (index % 3 == 0 || index % 3 == 1) {
                sum += cost[i];
            }
            index++;
        }
        return sum;
    }
}