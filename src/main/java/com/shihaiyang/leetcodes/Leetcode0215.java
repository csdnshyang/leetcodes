package com.shihaiyang.leetcodes;

import java.util.PriorityQueue;

// 215. 数组中的第K个最大元素.[1.大顶堆积+2.快速排序].
public class Leetcode0215 {
    public static void main(String[] args) {
        Solution02152 solution0215 = new Solution02152();
        int kthLargest = solution0215.findKthLargest(new int[]{3, 7,6,5,2}, 2);
        System.out.println(kthLargest);
    }
}
/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 */

/**
 * 这根据用java一句话就实现了呢...
 * 大顶堆积
 * 优先队列
 */
class Solution0215 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }
        int kth = 0;
        for (int i = 0; i < k; i++) {
            kth = queue.poll();
        }
        return kth;
    }
}

/**
 * 再加一个快排.
 */
class Solution02152 {
    /**
     * 从大到小排列
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        partition(nums, 0, nums.length - 1);
        int kth = nums[k];
        return kth;
    }

    // 从大到小排列
    public void partition(int[] nums, int low, int high) {
        // 小指针 >= 大指针 跳出
        if(low>=high){
            return;
        }
        // 基准值
        int pivot=nums[low];
        int left=low,right=high;
        while(left<right){
            // 右侧 > 基准,停止. 右侧<=基准,左移
            while(left<right && nums[right] <= pivot){
                right--;
            }
            // 左侧 < 基准,停止. 左侧>=基准,右移
            // 3 7 5 6 2. j最终会在6的位置，因为i<j i=5时，满足while条件,继续右移,i=j=6.最终i会跟j相等=6. 不是5!!! 5<6-> 5++ -> 6.
            while(left<right && nums[left] >= pivot){
                left++;
            }
            // 交换. 可能会出现i=j 的情况,就不会走这个交换了.
            if(left<right){
                swap(nums, left, right);
            }
        }
        // 基准值的准确位置. 得到 左侧>=基准，右侧<=基准
        // left可能等于low. 当右侧全部比基准小是,left不动.
        swap(nums, low, left);
        partition(nums, low, left-1);
        partition(nums, left+1, high);
    }

    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}