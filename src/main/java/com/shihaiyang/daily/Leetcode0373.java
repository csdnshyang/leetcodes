package com.shihaiyang.daily;

import java.util.ArrayList;
import java.util.List;

// 0373. 查找和最小的 K 对数字.[双指针].
public class Leetcode0373 {
    public static void main(String[] args) {
        Solution0373 solution0373 = new Solution0373();
        List<List<Integer>> lists = solution0373.kSmallestPairs(
                new int[]{1,2},
                new int[]{3},
                3
        );
        lists.stream().forEach(integers -> {
            integers.stream().forEach(integer -> System.out.print(integer+","));
            System.out.println();
        });
    }
}

/**
 * 给定两个以 升序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 *
 * [1,1,2]
 * [1,2,3]
 * 10
 * [[1,1],[1,1],[2,1],[1,2],[1,2],[2,2],[1,3],[1,3],[2,3]]
 */

/**
 * 双指针.
 * 想简单了..这个得回溯啊..
 * 优先队列..
 */
class Solution0373 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        int i = 0, j = 0;
        while (ret.size() < k) {
            if (i == nums1.length-1 && j == nums2.length-1) {
                ret.add(List.of(nums1[i], nums2[j]));
                break;
            }
            if (i == nums1.length-1) {
                ret.add(List.of(nums1[i], nums2[j]));
                j++;
            }else if (j == nums2.length-1) {
                ret.add(List.of(nums1[i], nums2[j]));
                i++;
            }else if((nums1[i+1]-nums1[i]) < (nums2[j+1]-nums2[j])) {
                ret.add(List.of(nums1[i], nums2[j]));
                i++;
            } else {
                ret.add(List.of(nums1[i], nums2[j]));
                j++;
            }
        }
        return ret;
    }
}
