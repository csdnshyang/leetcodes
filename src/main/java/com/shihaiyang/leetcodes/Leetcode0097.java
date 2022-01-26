package com.shihaiyang.leetcodes;

// 0097. 交错字符串.[动态规划存在类4ms].
public class Leetcode0097 {
    public static void main(String[] args) {
        Solution0097 solution0097 = new Solution0097();
//        boolean interleave = solution0097.isInterleave("", "a", "b");
        boolean interleave = solution0097.isInterleave("db", "b", "cbb");
        System.out.println(interleave);
    }
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
 * f(i,j)是指s1 i个字符与s2 j个字符拼成s3.
 *  拼接的方式有讲究  f(i,j)可以满足条件有两种场景，
 *  1.f(i-1, j)可以拼接并且s3[i+j] = s1[i]
 *  2.f(i, j-1)可以拼接并且s3[i+j] = s2[j]
 *  动态规划存在类
 * 1. 确认状态  arr[n+1][m+1] 表示由
 *      最终结果  arr[n+1][m+1] 表示由s1的n个字符(arr[n])和s2的m(arr[][m])个字符组中 s3的m+n个字符
 *      化成子问题  有s1的n-1个字符和s2的m个字符组成  s3的n+m-1个字符或者  s1的n个字符和s2的m-1个字符组成s3的n+m-1个字符
 *          这儿需要转化一下  s1的1个字符指arr[1]，但是指的是chars1[0]下标 同理s2的1个字符指arr[][1]，但是指的是chars2[0]下标
 *          判断s3第二个字符时指s3[1]下标可能出现的情况为
 *          1. 在s1的1个字符和s2的0个字符成立的前提下，有s1的2个字符和s2的0个字符组成s3的2个字符: s1出  arr[2][0]
 *              即  arr[1][0] && chars1[2-1] == chars3[2+0-1]
 *          2. 在s1的1个字符和s2的0个字符成立的前提下，有s1的1个字符和s2的1个字符组成s3的2个字符: s2出  arr[1][1]
 *              即  arr[1][0] && chars2[1-1] == chars3[1+1-1]
 *          3. 在s1的0个字符和s2的1个字符成立的前提下，有s1的1个字符和s2的1个字符组成s3的2个字符: s1出  arr[1][1]
 *              即  arr[0][1] && chars1[1-1] == chars3[1+1-1]
 *          4. 在s1的0个字符和s2的1个字符成立的前提下，有s1的0个字符和s2的2个字符组成s3的2个字符: s2出  arr[0][2]
 *              即  arr[0][1] && chars2[2-1] == chars3[0+2-1]
 *    由此可以判断，用单纯的双指针不可取，即需要把这四种情况都考虑进去才可。
 *    可以再抽象成问题是在arr[i][j]成立的情况下 第i+j个字符应该有几种可能
 *      1. arr[i-1][j]成立，那么最后一个字符(i+j)必须是s1出，这样才能组成arr[i][j]成立
 *      2. arr[i][j-1]成立，那么最后一个字符(i+j)必须是s2出，这样才能组成arr[i][j]成立
 * 2. 状态转移方程
 *      arr[i][j]=arr[i-1][j] && char1[i-1]==char3[i+j-1]  或者
 *      arr[i][j]=arr[i][j-1] && char2[j-1]==char3[i+j-1]
 * 3. 初始状态
 * 4. 计算顺序
 */
class Solution0097 {
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        char[] chars3 = s3.toCharArray();
        if (chars1.length + chars2.length != chars3.length) {
            return false;
        }

        boolean arr[][] = new boolean[chars1.length + 1][chars2.length + 1];
        arr[0][0] = true;

        // 首行首列，只考虑一种情况
        for (int i = 1; i <= chars1.length; i++) {
            arr[i][0] = arr[i-1][0] && chars1[i - 1] == chars3[i-1];
        }
        for (int i = 1; i <= chars2.length; i++) {
            arr[0][i] = arr[0][i-1] && chars2[i - 1] == chars3[i-1];
        }

        // 非首行首列都考虑两种情况
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                // 第i+j个字符的第一种情况 i+j-1个字符成立基于i-1个a字符串字符+j个b字符串字符成立的情况下，第i+j个字符是a字符串出的第i个字符。
                // a的0字符与b的0字符共两个字符，构成c的2个字符  a取1，b取1，c取1+1-1
                // 最终结果在arr[1][1]位置  两个条件 arr[0][1] && chars1[0]=chars3[1]
                // 表示 a0个字符b1个字符的前提成立，并且a的1个字符与c的第二个字符相同
                arr[i][j] = arr[i - 1][j] && chars3[i + j - 1] == chars1[i - 1];
                // 另一种情况最终结果在arr[1][1]位置  两个条件 arr[1][0] && chars2[0]=chars3[1]
                // 表示 a1个字符b0个字符的前提成立，并且b的1个字符与c的第二个字符相同
                arr[i][j] = arr[i][j] || (arr[i][j - 1] && chars3[i + j - 1] == chars2[j - 1]);
            }
        }
        return arr[chars1.length][chars2.length];
    }
}