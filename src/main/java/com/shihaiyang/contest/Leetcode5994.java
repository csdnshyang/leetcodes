package com.shihaiyang.contest;

// 5994. 查找给定哈希值的子串.[反向滑动窗口,取模公式消除大数10ms].

/**
 * * "xmmhdakfursinye"
 * * 96
 * * 45
 * * 15
 * * 21
 */
public class Leetcode5994 {
    public static void main(String[] args) {
        Solution5994 solution5994 = new Solution5994();
//        String leetcode = solution5994.subStrHash("fbxzaad", 31, 100, 3, 32);
//        String leetcode = solution5994.subStrHash("leetcode", 7, 20, 2, 0);
        String leetcode = solution5994.subStrHash("xqgfatvtlwnnkxipmipcpqwbxihxblaplpfckvxtihonijhtezdnkjmmk", 22, 51, 41, 9);
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
 * <p>
 * "xqgfatvtlwnnkxipmipcpqwbxihxblaplpfckvxtihonijhtezdnkjmmk",22, 51,41,9
 * "xqgfatvtlwnnkxipmipcpqwbxihxblaplpfckvxti"
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
 * 防止数字越界，所以要利用乘法计算出power^k%p的值。提公因式power。
 * (a * power^1 + b * power^2) % p
 * = (b*power*powder + a*power ) %p
 * = ((b*power+a) * power)  %p
 * = ((b*power+a) %p * power) %p
 * 这样切分计算，就不会出现大数。
 */
class Solution5994 {
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        // 计算第一个子串  size=5  k=2   第一个子串[4,3]
        char[] arr = s.toCharArray();
        int ans = 0, n = s.length();
        long hash = 0, mod = 1;
        // k-1个初始化
        for (int i = n - 1; i >= n - k + 1; i--) {
            mod = mod * power % modulo;
            hash = power * (hash + index(arr[i])) % modulo;
        }
        // 一次计算前面的子串
        for (int i = s.length() - k; i >= 0; i--) {
            // k-1个加上头的1个，凑k个  第一个是p^0=1 即charIndex本身
            hash = (index(arr[i]) + hash) % modulo;
            if (hash == hashValue) {
                ans = i;
            }
            // 去除最后一个，给下一个子串用  最后一个(charIndex*power^(k-1)) % p = (charIndex * (power^(k-1) % p)) % p
            // ∵ power^(k-1)%p = mod
            // ∴ (charIndex * (power^(k-1) % p)) % p = (charIndex*mod) %p
            long lastHash = (index(arr[i + k - 1]) * mod) % modulo;
            // 把去除最后一个字符的hash*power当做下一个子串的后k-1个字符的hash值
            // hash - lastHash 就行，但是防止hash更小出现负数，所以+modulo。
            hash = power * (hash - lastHash + modulo) % modulo;
        }
        return s.substring(ans, k + ans);
    }

    public int index(char c) {
        return c - 'a' + 1;
    }
}
