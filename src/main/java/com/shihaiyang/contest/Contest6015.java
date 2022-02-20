package com.shihaiyang.contest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

// 6015. 统计可以被 K 整除的下标对数目[gcd+map计数+分情况].
public class Contest6015 {
    @Test
    public void case1() {
        Solution6015 solution6015 = new Solution6015();
        long countPairs = solution6015.coutPairs(new int[]{1, 2, 3, 4, 5}, 2);
        Assertions.assertEquals(countPairs, 7);
    }
    @Test
    public void case2() {
        Solution6015 solution6015 = new Solution6015();
        long countPairs = solution6015.coutPairs(new int[]{1, 2, 3, 4}, 5);
        Assertions.assertEquals(countPairs, 0);
    }
    @Test
    public void case3() {
        Solution6015 solution6015 = new Solution6015();
        long countPairs = solution6015.coutPairs(new int[]{8,10,2,5,9,6,3,8,2}, 6);
        Assertions.assertEquals(countPairs, 18);
    }
}

/**
 * 遍历一次记录 %k==0 与最大公约数的个数，存map
 * gcd(a, b) {return b==0 ? a : gcd(b, a%b);}
 */
class Solution6015 {
    public long coutPairs(int[] nums, int k) {
        long ret = 0;

        Map<Integer, Integer> gcdMap = new HashMap<>();
        int count = 0;
        //遍历一次
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % k == 0) {
                count++;
            } else {
                int gcd = gcd(k, nums[i] % k);
                gcdMap.put(gcd, gcdMap.getOrDefault(gcd, 0) + 1);
            }
        }
        // 本身能直接%k的，可以与其他所有的数匹配
        ret += (long)count * (nums.length - 1);
        // count个之间会有重复
        ret -= (long)count * (count - 1) / 2;
        // 剩下的枚举能否与其他的key相乘在%k
        long tmp = 0;
        for (Integer key : gcdMap.keySet()) {
            // 等于1就没有公约数，不能整除k，也不能跟其他的数字相乘再整除k
            if (key == 1) {
                continue;
            }
            for (Integer key2 : gcdMap.keySet()) {
                if ((key * key2) % k == 0) {
                    if (key.equals(key2)) {
                        int same = gcdMap.get(key);
                        // 跟自己相乘，直接公式算
                        ret += (long)same * (same - 1) / 2;
                    } else {
                        // 与其他相乘，得都算完了统一除2  可能出现3*7 + 7*3 的情况所以要都算完了再除
                        tmp += (long)gcdMap.get(key) * gcdMap.get(key2);
                    }
                }
            }
        }
        return ret + (tmp >> 1);
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
