package com.shihaiyang.offer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//  Offer II 068. 查找插入位置[二分查找0ms].
public class Offer068 {
    SolutionOffer068 solutionOffer068 = new SolutionOffer068();

    @Test
    public void case1() {
        int insert = solutionOffer068.searchInsert(new int[]{1, 3, 5, 6}, 5);
        Assertions.assertEquals(2, insert);
    }
    @Test
    public void case2() {
        int insert = solutionOffer068.searchInsert(new int[]{1, 3, 5, 6}, 2);
        Assertions.assertEquals(1, insert);
    }
    @Test
    public void case3() {
        int insert = solutionOffer068.searchInsert(new int[]{1, 3, 5, 6}, 7);
        Assertions.assertEquals(4, insert);
    }
    @Test
    public void case4() {
        int insert = solutionOffer068.searchInsert(new int[]{1, 3, 5, 6}, 0);
        Assertions.assertEquals(0, insert);
    }
    @Test
    public void case5() {
        int insert = solutionOffer068.searchInsert(new int[]{1}, 0);
        Assertions.assertEquals(0, insert);
    }
    @Test
    public void case6() {
        int insert = solutionOffer068.searchInsert(new int[]{1,3}, 2);
        Assertions.assertEquals(1, insert);
    }
}
class SolutionOffer068 {
    int[] nums;
    public int searchInsert(int[] nums, int target) {
        this.nums = nums;
        return binarySearch(0, nums.length, target);
    }

    public int binarySearch(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                return mid;
            }
        }
        return left;
    }
}