package com.shihaiyang.daily;
// 1220. 统计元音字母序列的数目.[].
public class Leetcode1220 {
    public static void main(String[] args) {
        Solution1220 solution1220 = new Solution1220();
        int vowelPermutation = solution1220.countVowelPermutation(144);
        System.out.println(vowelPermutation);
    }
}
/**
 * 给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
 *
 * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
 * 每个元音 'a' 后面都只能跟着 'e' (e)
 * 每个元音 'e' 后面只能跟着 'a' 或者是 'i' (ai)
 * 每个元音 'i' 后面 不能 再跟着另一个 'i' (aeou)
 * 每个元音 'o' 后面只能跟着 'i' 或者是 'u' (iu)
 * 每个元音 'u' 后面只能跟着 'a'  (a)
 *
 * 由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
 * 示例 1：
 * 输入：n = 1
 * 输出：5
 * 解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
 * 示例 2：
 * 输入：n = 2
 * 输出：10
 * 解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
 * 输入：n = 5
 * 输出：68
 */

/**
 * 动态规划计数类
 * 思路是dp[i][j] i是字符长度  j是0-4.5个元音字母。  存储的数据为长度为i，最后一个字母为j指向的字母的个数.
 * 找到前一个只能是什么
 *  * 每个元音 'a' 后面都只能跟着 'e' (e)
 *  * 每个元音 'e' 后面只能跟着 'a' 或者是 'i' (ai)
 *  * 每个元音 'i' 后面 不能 再跟着另一个 'i' (aeou)
 *  * 每个元音 'o' 后面只能跟着 'i' 或者是 'u' (iu)
 *  * 每个元音 'u' 后面只能跟着 'a'  (a)
 *  a只能在  e, i, u 后面   所以dp[i]{a} = dp[i-1]{e}+dp[i-1]{i}+dp[i-1]{u}  这样来使用前一个的数据。
 *  e只能在  a, i    后面   所以dp[i]{e} = dp[i-1]{a} + dp[i-1]{i}
 *  i只能在  e, o    后面   所以dp[i]{i} = dp[i-1]{e} + dp[i-1]{o}
 *  o只能在  i       后面   所以dp[i]{o} = dp[i-1]{i}
 *  u只能在  i, o    后面   所以dp[i]{u} = dp[i-1]{i} + dp[i-1]{o}
 *
 * 1. 确认状态
 *      最终结果：长度为n以某一个元音字母结尾的组合有多少种，再得出5种字母加起来，就是最终结果.
 *      化解子方程：长度n-i 以每个元音字母结尾的组合有多少种。  以某个字母只能在某些字母之后为思考方向
 * 2. 状态转移方程
 *      dp[i][0]=dp[i-1][1]+dp[i-1][2]+dp[i-1][4]   // 长度i字母a结尾, 能在e,i,u后面
 *      dp[i][1]=dp[i-1][0]+dp[i-1][2]          // 长度为i字母e结尾，只能再a,i后面
 *      dp[i][2]=dp[i-1][1]+dp[i-1][3]             // 长度i字母i结尾，只能在e,o后面
 *      dp[i][3]=dp[i-1][2]                       // o在i后面
 *      dp[i][4]=dp[i-1][2]+dp[i-1][3]       // u在i,o后面
 * 3. 初始状态
 *      dp[1][0..4]=1   长度1的每个字母结尾有1种情况
 * 4. 计算顺序
 *      dp[1][0..4] dp[2][0..4] dp[3][0..4]
 *      原则：计算dp[i]时，dp[i-1]已经算出
 *      存储优化每次算n的时候，只依赖与n-i，所以可以压缩空间复杂度为O(1)
 *      只存储一个dp[0..4]=1;每次更新这个数组中对应结尾的个数，替换原数组个数即可.
 */
class Solution1220 {
    int MOD = (int)1e9+7;
    public int countVowelPermutation(int n) {
        long dp[] =new long[]{1,1,1,1,1};
        for (int i = 2; i <= n; i++) {
            long aa = dp[1] + dp[2] + dp[4];
            long ee = dp[0] + dp[2];
            long ii = dp[1] + dp[3];
            long oo = dp[2];
            long uu = dp[2] + dp[3];
            // 每次都要MOD一下...
            dp[0] = aa%MOD;
            dp[1] = ee%MOD;
            dp[2] = ii%MOD;
            dp[3] = oo%MOD;
            dp[4] = uu%MOD;
        }
        // 每次都要MOD一下...
        long sum = dp[0] + dp[1] + dp[2] + dp[3] + dp[4];
        return (int)(sum%MOD);
    }
}
