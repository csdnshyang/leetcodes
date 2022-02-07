package com.shihaiyang.contest;

import java.math.BigDecimal;

// 5994. 查找给定哈希值的子串

/**
 *  * "xmmhdakfursinye"
 *  * 96
 *  * 45
 *  * 15
 *  * 21
 */
public class Leetcode5994 {
    public static void main(String[] args) {
        Solution5994 solution5994 = new Solution5994();
//        String leetcode = solution5994.subStrHash("fbxzaad", 31, 100, 3, 32);
//        String leetcode = solution5994.subStrHash("leetcode", 7, 20, 2, 0);
        String leetcode = solution5994.subStrHash("xqgfatvtlwnnkxipmipcpqwbxihxblaplpfckvxtihonijhtezdnkjmmk",22, 51,41,9);
        System.out.println(leetcode);
    }
}
/**
 * 给定整数 p 和 m ，一个长度为 k 且下标从 0 开始的字符串 s 的哈希值按照如下函数计算：
 * hash(s, p, m) = (val(s[0]) * p0 + val(s[1]) * p1 + ... + val(s[k-1]) * pk-1) mod m.
 * 其中 val(s[i]) 表示 s[i] 在字母表中的下标，从 val('a') = 1 到 val('z') = 26 。
 * 给你一个字符串 s 和整数 power，modulo，k 和 hashValue 。
 * 请你返回 s 中 第一个 长度为 k 的 子串 sub ，满足 hash(sub, power, modulo) == hashValue 。
 * 测试数据保证一定 存在 至少一个这样的子串。
 * 子串 定义为一个字符串中连续非空字符组成的序列。
 * 输入：s = "leetcode", power = 7, modulo = 20, k = 2, hashValue = 0
 * 输出："ee"
 * 解释："ee" 的哈希值为 hash("ee", 7, 20) = (5 * 1 + 5 * 7) mod 20 = 40 mod 20 = 0 。
 * "ee" 是长度为 2 的第一个哈希值为 0 的子串，所以我们返回 "ee" 。
 * <p>
 * 输入：s = "fbxzaad", power = 31, modulo = 100, k = 3, hashValue = 32
 * 输出："fbx"
 * 解释："fbx" 的哈希值为 hash("fbx", 31, 100) = (6 * 1 + 2 * 31 + 24 * 312) mod 100 = 23132 mod 100 = 32 。
 * "bxz" 的哈希值为 hash("bxz", 31, 100) = (2 * 1 + 24 * 31 + 26 * 312) mod 100 = 25732 mod 100 = 32 。
 * "fbx" 是长度为 3 的第一个哈希值为 32 的子串，所以我们返回 "fbx" 。
 * 注意，"bxz" 的哈希值也为 32 ，但是它在字符串中比 "fbx" 更晚出现。
 *
 * "xqgfatvtlwnnkxipmipcpqwbxihxblaplpfckvxtihonijhtezdnkjmmk",22, 51,41,9
 * "xqgfatvtlwnnkxipmipcpqwbxihxblaplpfckvxti"
 */

/**
 * 反向滑动窗口。
 * 把除法改为乘法。
 * 每次滑动时，后k-1个会成为下一组的前k-1个，所以都要统一除以k。但是除法不好算。
 * 如果从后计算。则每个滑动窗口的前k-1个会是下一个窗口的后k-1，每个都要乘k。好计算取模。
 * 取模的化简
 * (a*b) % p = (a%p * b%p) % p
 * (a+b) % p = (a%p + b%p) % p
 * (a^b) % p = ((a%p)^b) % p
 * (a-b) % p = (a%p - b%p) % p
 */
class Solution5994 {
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        int index = -1;
        for (int i = s.length() - k - 1; i >= 0; i--) {
            long hashSub = 0;
            for (int j = 0; j < k; j++) {
                hashSub += hashChar(s.charAt(i + j), j, power, modulo);
                hashSub %= modulo;
            }
            if (hashSub == hashValue) {
                index = i;
            }
        }
        return index == -1 ? "" : s.substring(index, k + index);
    }

    // val(s[0]) * p0
    public long hashChar(char c, int k, int power, int modulo) {
        long index = c - 'a' + 1;
        power %= modulo;
        long pow = (long)Math.pow(power, k);
        pow %= modulo;
        return (index * pow) % modulo;
    }
}
