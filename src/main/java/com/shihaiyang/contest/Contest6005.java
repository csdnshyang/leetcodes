package com.shihaiyang.contest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

// 6005. 使数组变成交替数组的最少操作数.[遍历分不同情况 100ms].
public class Contest6005 {
    Solution6005 solution6005 = new Solution6005();

    @Test
    public void case1() {
        Assertions.assertEquals(solution6005.minimumOperations(new int[]{1,2,2,2,2}), 2);
        Assertions.assertEquals(solution6005.minimumOperations(new int[]{2,2}), 1);
        Assertions.assertEquals(solution6005.minimumOperations(new int[]{3,1,3,2,4,3}), 3);
    }

    @Test
    public void case2() {
        Assertions.assertEquals(solution6005.minimumOperations(new int[]{48,38,42,18,13,1,97,88,82,48,54,16,78,59,52,30,40,77,59,87,71,28}), 20);
    }
}

/**
 * 最大。次大。写了100行....
 * 太不优雅了。
 * 算出最大和次之的两个，找可用的奇偶个数。总数-奇偶个数
 * 不需要区分奇偶
 * 如果最大的数字相等，就需要找次大，次大里选一个大的，最大就选另一个。 sum-ji[0]-ou[1]
 * 如果最大的数字不相等，就直接用。  sum-ji-ou.
 */
class Solution6005 {
    public int minimumOperations(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        Map<Integer, Integer> odd = new HashMap<>();
        Map<Integer, Integer> even = new HashMap<>();
        for (int i = 0; i < nums.length; i += 2) {
            Integer count = odd.getOrDefault(nums[i], 0) + 1;
            odd.put(nums[i], count);
        }
        for (int i = 1; i < nums.length; i += 2) {
            Integer count = even.getOrDefault(nums[i], 0) + 1;
            even.put(nums[i], count);
        }
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(odd.entrySet());
        list.sort((a,b) -> b.getValue() - a.getValue());
        List<Map.Entry<Integer,Integer>> list2 = new ArrayList<>(even.entrySet());
        list2.sort((a,b) -> b.getValue() - a.getValue());
        // 找到最大的数字,数量  以及次大的数量
        int oldVal = list.get(0).getKey();
        int oddCount = list.get(0).getValue();
        int oddSecond = list.size() > 1 ? list.get(1).getValue() : 0;
        int evenVal = list2.get(0).getKey();
        int evenCount = list2.get(0).getValue();
        int evenSecond = list2.size() > 1 ? list2.get(1).getValue() : 0;

        // 求最大可用数量
        int ans = 0;
        // 如果最大的数字相同，则必须有一个选次之。选一个最大+次之   次之+最大 两个中大的那个。
        // 如果最大的数字不相同，直接用
        if (oldVal == evenVal) {
            int max = Math.max(oddCount + evenSecond, oddSecond + evenCount);
            ans = Math.max(ans, max);
        } else {
            ans = oddCount + evenCount;
        }
        return nums.length - ans;
    }
}
