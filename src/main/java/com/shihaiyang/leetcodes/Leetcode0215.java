package com.shihaiyang.leetcodes;

import java.util.PriorityQueue;

// 215. 数组中的第K个最大元素.[1.大顶堆积+2.快速排序].
public class Leetcode0215 {
    public static void main(String[] args) {
        Solution02152 solution02152 = new Solution02152();
        int kthLargest = solution02152.findKthLargest(new int[]{3,2,1,5,6,4}, 2);
        System.out.println(kthLargest);
        Solution0215 solution0215 = new Solution0215();
        int kthLargest2 = solution0215.findKthLargest(new int[]{3,2,1,5,6,4}, 2);
        System.out.println(kthLargest2);
    }
}
/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 */

/**
 * 这根据用java一句话就实现了呢...
 * 大顶堆积
 * 优先队列
 * 换个思路是维护len-k个大顶堆积。第K大，其实就是比后面的len-k个大。5个的第2大，那么就是比后面(5-2)个大,应该是容量=5-2+1=4个的堆顶
 */
class Solution0215 {
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length-k+1;
        PriorityQueue<Integer> queue = new PriorityQueue<>(len, (v1, v2) -> v2 - v1);
        for (int i = 0; i < len; i++) {
            queue.add(nums[i]);
        }
        for (int i=len;i<nums.length;i++){
            Integer peek = queue.peek();
            if (peek > nums[i]){
                queue.poll();
                queue.add(nums[i]);
            }
        }
        return queue.peek();
    }
}

/**
 * 快速排序.
 */
class Solution02152 {
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