package com.shihaiyang.daily;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// 0537. 复数乘法[简单模拟 6ms]
/*
复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：

实部 是一个整数，取值范围是 [-100, 100]
虚部 也是一个整数，取值范围是 [-100, 100]
i2 == -1
给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。

 

示例 1：

输入：num1 = "1+1i", num2 = "1+1i"
输出："0+2i"
解释：(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
示例 2：

输入：num1 = "1+-1i", num2 = "1+-1i"
输出："0+-2i"
解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
 */
public class Leetcode0537 {
    Solution0537 solution0537 = new Solution0537();

    @Test
    public void case1() {
        String multiply = solution0537.complexNumberMultiply("1+1i", "1+1i");
        Assertions.assertEquals(multiply, "0+2i");
    }

    @Test
    public void case2() {
        String multiply = solution0537.complexNumberMultiply("1+-1i", "1+-1i");
        Assertions.assertEquals(multiply, "0+-2i");
    }

    @Test
    public void case3() {
        String multiply = solution0537.complexNumberMultiply("1+-1i", "11+-12i");
        Assertions.assertEquals(multiply, "-1+-23i");
    }
}

/*
i2是i的平方
i2是-1，能跟数字约掉，最后剩下一个数字，一个i的倍数。
 */
class Solution0537 {
    public String complexNumberMultiply(String num1, String num2) {
        String[] split = num1.substring(0, num1.length() - 1).split("\\+");
        String[] split2 = num2.substring(0, num2.length() - 1).split("\\+");

        int num = Integer.valueOf(split[0]) * Integer.valueOf(split2[0]);
        int letter = Integer.valueOf(split[0]) * Integer.valueOf(split2[1]) + Integer.valueOf(split[1]) * Integer.valueOf(split2[0]);
        ;
        int square = Integer.valueOf(split[1]) * Integer.valueOf(split2[1]);

        num = num + -1 * square;
        String ret = num + "+";
        ret += (letter + "i");

        return ret;
    }
}