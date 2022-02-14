package com.shihaiyang.daily;
// 0540. 有序数组中的单一元素[二分+异或0ms 遍历+异或1ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * 请你找出并返回只出现一次的那个数。
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * 示例 1:
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 */
public class Leetcode0540 {
    Solution0540Binary solution0540 = new Solution0540Binary();

    @Test
    public void case1() {
        int duplicate = solution0540.singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8});
        Assertions.assertEquals(duplicate, 2);
    }
    @Test
    public void case2() {
        int duplicate = solution0540.singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11});
        Assertions.assertEquals(duplicate, 10);
    }
}

/**
 * 方法1，或运算
 */
class Solution0540 {
    public int singleNonDuplicate(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }
}

/**
 * 二分查找。
 * 因为两两配对。奇数与对应的+1偶数应该是一对。如果出现了目标的单个数，那么奇数应该与-1偶数是一对，与之前结论相反。
 * 优化是通过异或来找到与之配对的另一个。
 * nums[i] == nums[i^1]
 * 两个场景，如果i是奇数，那么i^1就是-1偶数；如果i是偶数，那么i^1就是+1奇数。
 * 所以，如果nums[i] == nums[i^1]；那就说明之前是没有出现单个的，去找后面，否则去找左边。
 *
 * 二分查找写法
 */
class Solution0540Binary {
    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            // 能匹配说明之前没有出现
            if (nums[mid] == nums[mid ^ 1]) {
                // 右移mid+1
                l = mid + 1;
            } else {
                // 左移mid
                r = mid;
            }
        }
        return nums[r];
    }
}