package com.shihaiyang.offer;
// Offer II 072. 求平方根.[二分查找1ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给定一个非负整数 x ，计算并返回 x 的平方根，即实现 int sqrt(int x) 函数。
 * 正数的平方根有两个，只输出其中的正数平方根。
 * 如果平方根不是整数，输出只保留整数的部分，小数部分将被舍去。
 * 示例 1:
 * 输入: x = 4
 * 输出: 2
 * 示例 2:
 * 输入: x = 8
 * 输出: 2
 * 解释: 8 的平方根是 2.82842...，由于小数部分将被舍去，所以返回 2
 */
public class Offer072 {
    SolutionOffer072 solutionOffer072 = new SolutionOffer072();

    @Test
    public void case1() {
        Assertions.assertEquals(solutionOffer072.mySqrt(10), 3);
        Assertions.assertEquals(solutionOffer072.mySqrt(16), 4);
        Assertions.assertEquals(solutionOffer072.mySqrt(17), 4);
    }
    @Test
    public void case2() {
        Assertions.assertEquals(solutionOffer072.mySqrt(1), 1);
        Assertions.assertEquals(solutionOffer072.mySqrt(0), 0);
    }

    @Test
    public void case3() {
        Assertions.assertEquals(solutionOffer072.mySqrt(2), 1);
        Assertions.assertEquals(solutionOffer072.mySqrt(2147395599), 46339);
    }
}

/**
 * 二分查找。
 * while条件包含==情况
 * 左右移的时候，分别+1，-1；
 * 最后返回匹配值或者right值。因为目标值一定在right<target<left之间。就是right位置
 */
class SolutionOffer072 {
    public int mySqrt(int x) {
        int left = 1, right = x > 4 ? x >> 1 : x;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (mid < x / mid) {
                left = mid + 1;
            }else if (mid > x / mid) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return right;
    }
}