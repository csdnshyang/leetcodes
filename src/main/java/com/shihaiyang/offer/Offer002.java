package com.shihaiyang.offer;

import java.util.Arrays;

// Offer002. 二进制加法.[解析字符].
// 剑指 Offer II 002. 二进制加法
//
public class Offer002 {
    public static void main(String[] args) {
        SolutionOffer002 solutionOffer002 = new SolutionOffer002();
        String binary = solutionOffer002.addBinary("1", "111");
        System.out.println(binary);
    }
}

/**
 * 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * 输入: a = "11", b = "10"
 * 输出: "101"
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */

/**
 * 一位一位运算
 */
class SolutionOffer002 {
    public String addBinary(String a, String b) {
        int max = Math.max(a.length(), b.length());
        char[] chars = new char[max+1];
        int jin = 0;
        for (int i = 0; i <= max; i++) {
            int first = 0, second = 0;
            if (a.length() - i -1 >= 0) {
                first = a.charAt(a.length() - i - 1) - '0';
            }
            if (b.length() - i -1 >= 0) {
                second = b.charAt(b.length() - i - 1) - '0';
            }
            int sum = first + second + jin;
            if (sum > 1) {
                sum = sum % 2;
                jin = 1;
            } else {
                jin = 0;
            }
            chars[max - i] = ("" + sum).charAt(0);
        }
        if (chars[0] == '0') {
            chars = Arrays.copyOfRange(chars, 1, max + 1);
        }
        return String.valueOf(chars);
    }
}
