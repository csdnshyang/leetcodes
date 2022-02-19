package com.shihaiyang.daily;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

// 0969. 煎饼排序.[排序，交换 0ms].

/**
 * 给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
 * 一次煎饼翻转的执行过程如下：
 * 选择一个整数 k ，1 <= k <= arr.length
 * 反转子数组 arr[0...k-1]（下标从 0 开始）
 * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
 * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。
 * 任何将数组排序且翻转次数在 10 * arr.length 范围内的有效答案都将被判断为正确。
 * 示例 1：
 * 输入：[3,2,4,1]
 * 输出：[4,2,4,3]
 * 解释：
 * 我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
 * 初始状态 arr = [3, 2, 4, 1]
 * 第一次翻转后（k = 4）：arr = [1, 4, 2, 3]
 * 第二次翻转后（k = 2）：arr = [4, 1, 2, 3]
 * 第三次翻转后（k = 4）：arr = [3, 2, 1, 4]
 * 第四次翻转后（k = 3）：arr = [1, 2, 3, 4]，此时已完成排序。
 * 示例 2：
 * 输入：[1,2,3]
 * 输出：[]
 * 解释：
 * 输入已经排序，因此不需要翻转任何内容。
 * 请注意，其他可能的答案，如 [3，3] ，也将被判断为正确。
 */
public class Leetcode0969 {
    Solution0969 solution0969 = new Solution0969();

    @Test
    public void case1() {
        List<Integer> integers = solution0969.pancakeSort(new int[]{3, 2, 4, 1});
        Assertions.assertTrue(integers.equals(List.of(3,4,2,3,2)));
    }
    @Test
    public void case2() {
        List<Integer> integers = solution0969.pancakeSort(new int[]{1,2,3});
        Assertions.assertTrue(integers.equals(List.of()));
    }
}

/**
 * 从后往前遍历，如果第i个是i+1，那么就跳过。
 * 如果不相等，就while找到第i+1，找到在j位置，翻转两次。分别是从0到j，以及从0到i    0到j是让i+1到第一位，0到i是为了让i+1到i位置
 */
class Solution0969 {
    int[] arr;
    public List<Integer> pancakeSort(int[] arr) {
        this.arr = arr;
        List<Integer> ret = new ArrayList<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == (i + 1)) {
                continue;
            }
            int j = i - 1;
            while (j > 0) {
                if (arr[j] == i + 1) {
                    swap(j);
                    ret.add(j+1);
                    break;
                } else {
                    j--;
                }
            }
            swap(i);
            ret.add(i+1);
        }
        return ret;
    }

    public void swap(int k) {
        int i = 0;
        while (i < k) {
            int tmp = arr[i];
            arr[i] = arr[k];
            arr[k] = tmp;
            i++;
            k--;
        }
    }
}