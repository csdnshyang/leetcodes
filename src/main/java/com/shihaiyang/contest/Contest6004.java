package com.shihaiyang.contest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// 6004. 得到 0 的操作数.[遍历].
public class Contest6004 {
    Solution6004 solution6004 = new Solution6004();
    @Test
    public void case1() {
        Assertions.assertEquals(solution6004.countOperations(3, 2), 3);
        Assertions.assertEquals(solution6004.countOperations(3, 3), 1);
    }
}
class Solution6004 {
    public int countOperations(int num1, int num2) {
        int big = num1 > num2 ? num1 : num2;
        int small = num1 <= num2 ? num1 : num2;
        int ans = 0;
        while (small > 0) {
            int tmp = big - small;
            big = tmp > small ? tmp : small;
            small = tmp <= small ? tmp : small;
            ans++;
        }
        return ans;
    }
}
