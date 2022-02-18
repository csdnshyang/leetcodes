package com.shihaiyang.offer;
// Offer II 070. 排序数组中只出现一次的数字[二分查找 0ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给定一个只包含整数的有序数组 nums ，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字。
 * 示例 1:
 *
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 */
public class Offer070 {
    SolutionOffer070 solutionOffer070 = new SolutionOffer070();

    @Test
    public void case1() {
        int duplicate = solutionOffer070.singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8});
        Assertions.assertEquals(duplicate, 2);
    }
    @Test
    public void case2() {
        int duplicate = solutionOffer070.singleNonDuplicate(new int[]{3,3,7,7,10,11,11});
        Assertions.assertEquals(duplicate, 10);
    }
}

/**
 * 第一种就是异或运算。遍历一次O(n)
 * 第二种就是二分查找。
 * 一对出现的情况，下标为：偶数i，奇数i+1比如  [0]==[1]   [2]==[3]
 * 如果出现了一个单个的数字，那么情况就变成了相反的情况，相等的两个元素下标为：奇数i，偶数i+1比如 [5]==[6]而不是  [4]==[5]
 * 寻找规律，通过  1^0=1  1^1=0  可以知道   0^1=1  2^1=3  3^1=2 通过异或就能找到与之一对的另一个下标。
 * 如果这两个下标相等，就说明还没有出现单个的数。在右侧
 * 如果这两个下标的值不相等，说明已经出现了，在左侧，
 * 单个的数出现的下标一定是偶数下标。
 */
class SolutionOffer070 {
    public int singleNonDuplicate(int[] nums) {
        // left到right都可能是答案
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            // 相等，说明还没出现单个数，右移
            if (nums[mid] == nums[mid ^ 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 关键点，跳出的情况，是left==right
        // 单个数出现在偶数下标，就是left，其实right也可以。
        return nums[left];
    }
}