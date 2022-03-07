package com.shihaiyang.daily;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// 0504. 七进制数[取模+除模拟 1ms].
/*
给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
示例 1:

输入: num = 100
输出: "202"
示例 2:

输入: num = -7
输出: "-10"
 */
public class Leetcode0504 {
    Solution0504 solution0504 = new Solution0504();

    @Test
    public void testcase1() {
        String toBase7 = solution0504.convertToBase7(100);
        Assertions.assertEquals(toBase7, "202");
    }
    @Test
    public void testcase2() {
        String toBase7 = solution0504.convertToBase7(-7);
        Assertions.assertEquals(toBase7, "-10");
    }
    @Test
    public void testcase3() {
        String toBase7 = solution0504.convertToBase7(-11);
        Assertions.assertEquals(toBase7, "-14");
    }
}
/*
用 取模和除法试试 来计算
 */
class Solution0504 {
    public String convertToBase7(int num) {
        StringBuffer ret = new StringBuffer();
        if (num == 0) {
            return "0";
        }
        boolean flag = true;
        if (num < 0) {
            flag = false;
            num = -num;
        }
        while (num > 0) {
            int bit = num % 7;
            ret.insert(0, bit);
            num = num / 7;
        }
        return flag ? ret.toString() : ret.insert(0,"-").toString();
    }
}