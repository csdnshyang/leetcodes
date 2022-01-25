package com.shihaiyang.leetcodes;

// 0097. 交错字符串.[动态规划].
public class Leetcode0097 {

}

/**
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 * 示例 1：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * 示例 2：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 */

/**
 * 动态规划存在类
 * f(i,j)是指s1 i个字符与s2 j个字符拼成s3.
 * 拼接的方式有讲究  f(i,j)可以满足条件有两种场景，
 * 1.f(i-1, j)可以拼接并且s3[i+j] = s1[i]
 * 2.f(i, j-1)可以拼接并且s3[i+j] = s2[j]
 */
class Solution0097 {
    public boolean isInterleave(String s1, String s2, String s3) {

    }
}