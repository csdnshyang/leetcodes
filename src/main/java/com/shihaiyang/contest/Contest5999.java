package com.shihaiyang.contest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

// 5999. 统计数组中好三元组数目.[树状数组..是个啥?].

/**
 * 给你两个下标从 0 开始且长度为 n 的整数数组 nums1 和 nums2 ，两者都是 [0, 1, ..., n - 1] 的 排列 。
 * 好三元组 指的是 3 个 互不相同 的值，且它们在数组 nums1 和 nums2 中出现顺序保持一致。
 * 换句话说，如果我们将 pos1v 记为值 v 在 nums1 中出现的位置，pos2v 为值 v 在 nums2 中的位置，
 * 那么一个好三元组定义为 0 <= x, y, z <= n - 1 ，且 pos1x < pos1y < pos1z 和 pos2x < pos2y < pos2z 都成立的 (x, y, z) 。
 * 请你返回好三元组的 总数目 。
 * 示例 1：
 * 输入：nums1 = [2,0,1,3], nums2 = [0,1,2,3]
 * 输出：1
 * 解释：
 * 总共有 4 个三元组 (x,y,z) 满足 pos1x < pos1y < pos1z ，分别是 (2,0,1) ，(2,0,3) ，(2,1,3) 和 (0,1,3) 。
 * 这些三元组中，只有 (0,1,3) 满足 pos2x < pos2y < pos2z 。所以只有 1 个好三元组。
 * 示例 2：
 * 输入：nums1 = [4,0,1,3,2], nums2 = [4,1,0,2,3]
 * 输出：4
 * 解释：总共有 4 个好三元组 (4,0,3) ，(4,0,2) ，(4,1,3) 和 (4,1,2) 。
 * n == nums1.length == nums2.length
 * 3 <= n <= 105
 */
public class Contest5999 {
    Solution5999Real solution5999 = new Solution5999Real();

    @Test
    public void case1() {
        long triplets = solution5999.goodTriplets(
                new int[]{13, 14, 10, 2, 12, 3, 9, 11, 15, 8, 4, 7, 0, 6, 5, 1},
                new int[]{8, 7, 9, 5, 6, 14, 15, 10, 2, 11, 4, 13, 3, 12, 1, 0});
        Assertions.assertEquals(triplets, 77);
    }

    @Test
    public void case2() {
        long triplets = solution5999.goodTriplets(
                new int[]{4, 0, 1, 3, 2},
                new int[]{4, 1, 0, 2, 3});
        Assertions.assertEquals(triplets, 4);
    }
}

/**
 * 正解是统计入库的个数和未入库的个数。
 * 统计左侧已经入库的数字个数和右侧未入库的数字个数。
 * 用第二个数来做标记。
 * 第二个数前面的第一个数，应该保证是在左侧，且已入库的。
 * 第二个数后面的第三个数，应该保证是在右侧，且未入库的。
 * 只要扫描每个数的时候，在第二个数组中去统计，对应下标，左侧入库的个数*右侧未入库的个数。就是改下标可能出现的三好数组。
 */
class Solution5999Real {
    private int lowBit(int x) {
        return x & -x;
    }

    private long query(long bit[], int index) {
        long ans = 0;
        while (index > 0) {
            ans += bit[index];
            index -= lowBit(index);
        }
        return ans;
    }

    private void update(long[] bit, int index, long val) {
        while (index < bit.length) {
            bit[index] += val;
            index += lowBit(index);
        }
    }

    public long goodTriplets(int[] nums1, int[] nums2) {
        Map<Integer, Integer> discreteMap = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            discreteMap.put(nums1[i], i + 1);
        }

        long[] bit1 = new long[nums1.length + 1];
        long[] bit2 = new long[nums1.length + 1];
        long ans = 0;
        for (int i = 0; i < nums2.length; i++) {
            int discrete = discreteMap.get(nums2[i]);
            long pairCount = query(bit2, discrete - 1);
            long singleCount = query(bit1, discrete - 1);
            update(bit2, discrete, singleCount);
            update(bit1, discrete, 1);
            ans += pairCount;
        }
        return ans;
    }
}

/**
 * map+剪枝  超时
 */
class Solution5999 {
    public long goodTriplets(int[] nums1, int[] nums2) {
        long ret = 0;
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map1.put(nums1[i], i);
        }
        for (int i = 0; i < nums2.length; i++) {
            map2.put(nums2[i], i);
        }
        for (int i = 0; i < nums1.length; i++) {
            Integer fir = map2.get(nums1[i]);
            for (int j = i + 1; j < nums1.length; j++) {
                Integer sec = map2.get(nums1[j]);
                if (fir > sec) {
                    continue;
                }
                for (int k = sec + 1; k < nums2.length; k++) {
                    if (map1.get(nums2[k]) > j) {
                        ret++;
                    }
                }
            }
        }
        return ret;
    }
}