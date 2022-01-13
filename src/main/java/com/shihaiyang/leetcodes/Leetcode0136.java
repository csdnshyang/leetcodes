package com.shihaiyang.leetcodes;

import java.util.HashMap;
import java.util.Map;

// 0136. 只出现一次的数字.[异或运算1ms+Map方式11ms].
// https://leetcode-cn.com/problems/single-number/solution/136-zhi-chu-xian-yi-ci-de-shu-zi-yi-huo-7t1ox/
public class Leetcode0136 {
    public static void main(String[] args) {
        Solution0136XOR solution0136 = new Solution0136XOR();
        int singleNumber = solution0136.singleNumber(new int[]{4,1,2,1,2});
        System.out.println(singleNumber);
    }
}

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * 输入: [2,2,1]
 * 输出: 1
 */

/**
 * 方案1：使用map存储一下...
 *怎么能不实用额外空间呢？
 * 方案2：位运算 用异或运算^  0^a=a   a^a=0  所以出现两次的数，异或一定得0等于没有出现...是个好思路，而且空间复杂度为常数，没有新的空间
 */
class Solution0136 {
    public int singleNumber(int[] nums) {
        Map<Integer, Character> exists = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!exists.containsKey(nums[i])) {
                exists.put(nums[i], '1');
            } else {
                exists.remove(nums[i]);
            }
        }
        return exists.entrySet().iterator().next().getKey();
    }
}
class Solution0136XOR {
    public int singleNumber(int[] nums) {
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            ret ^= nums[i];
        }
        return ret;
    }
}

