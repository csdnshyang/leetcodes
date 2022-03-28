package com.shihaiyang.daily;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// 0693. 交替位二进制数.[位运算简单模拟].
public class Leetcode0693 {
    Solution0693 solution0693 = new Solution0693();

    @Test
    public void testcase1() {
        Assertions.assertTrue(solution0693.hasAlternatingBits(5));
        Assertions.assertTrue(!solution0693.hasAlternatingBits(7));
        Assertions.assertTrue(!solution0693.hasAlternatingBits(11));
        System.out.println(Integer.MAX_VALUE);
        Assertions.assertTrue(!solution0693.hasAlternatingBits(Integer.MAX_VALUE));
        Assertions.assertTrue(!solution0693.hasAlternatingBits(Integer.MAX_VALUE-1));
    }
}

/*
暴力遍历一下
 */
class Solution0693 {
    public boolean hasAlternatingBits(int n) {
        boolean flag = (n & 1) == 1;
        while (n > 0) {
            n >>= 1;
            if (flag && (n & 1) == 1) {
                return false;
            }
            if (!flag && (n & 1) == 0) {
                return false;
            }
            flag = !flag;
        }
        return true;
    }
}