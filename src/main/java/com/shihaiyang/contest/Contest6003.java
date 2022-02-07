package com.shihaiyang.contest;
// 6003. 移除所有载有违禁货物车厢所需的最少时间.[动态规划 43ms 100%].
public class Contest6003 {
    public static void main(String[] args) {
        Solution6003 solution6003 = new Solution6003();
        int minimumTime = solution6003.minimumTime("1100101");
        System.out.println(minimumTime);
    }
}

/**
 * 输入：s = "1100101"
 * 输出：5
 * 解释：
 * 一种从序列中移除所有载有违禁货物的车厢的方法是：
 * - 从左端移除一节车厢 2 次。所用时间是 2 * 1 = 2 。
 * - 从右端移除一节车厢 1 次。所用时间是 1 。
 * - 移除序列中间位置载有违禁货物的车厢。所用时间是 2 。
 * 总时间是 2 + 1 + 2 = 5 。
 *
 * 一种替代方法是：
 * - 从左端移除一节车厢 2 次。所用时间是 2 * 1 = 2 。
 * - 从右端移除一节车厢 3 次。所用时间是 3 * 1 = 3 。
 * 总时间也是 2 + 3 = 5 。
 *
 * 5 是移除所有载有违禁货物的车厢所需要的最少单位时间数。
 * 没有其他方法能够用更少的时间移除这些车厢。
 */

/**
 * 想到了动态规划。但是没想到怎么弄，没厘清楚花费怎么算。
 * 分成左右两部分。枚举分割点，取左右和最小的分割点。
 * 1. 确认状态
 *      最后结果 ：  排除n所有1的最少花费
 *              排除在i位置的1，要么是选择直接排除，花费2.要么选择排除前面所有的，花费i-1。取小的
 *              如果位置i的值为0，就不需要移出，花费跟spend[i-1]相同
 *      化成子问题 ： 排除n所有1的最少花费，那排除n-1也是最少花费，但是有一个左右的问题
 *      从右侧也可以算一次，右侧的算法类似
 * 2. 状态转移方程：size=len-1
 *      左侧：left[i] = min(left[i-1]+2, i+1)
 *      右侧：right[i] = min(right[i-1]+2, i+1)
 *      总ans = min(ans, left[i] + right[size-1-i])   [下标i算在左侧，右侧就不算了。]
 *      找枚举点，分开左右两部分，左右花费之和，最小的就是最优枚举点，最小花费就是解。
 * 3. 初始状态 ans = left[0] + right[size-1]
 * 4. 计算顺序  spend[0,1,2]
 *      原则：计算n的时候 n-1已经算出。
 */
class Solution6003 {
    public int minimumTime(String s) {
        char[] chars = s.toCharArray();
        int size = chars.length-1;
        if (size == 0) {
            return chars[0] == '1' ? 1 : 0;
        }
        int left[] = new int[s.length()];
        int right[] = new int[s.length()];
        // 初始状态
        if (chars[0] == '1') {
            left[0] = 1;
        }
        if (chars[size] == '1') {
            right[0] = 1;
        }
        for (int i = 1; i <= size; i++) {
            // 从左侧算花费  (0...size)
            if (chars[i] == '1') {
                left[i] = Math.min(left[i - 1] + 2, i + 1);
            } else {
                left[i] = left[i - 1];
            }
            // 从右侧算花费 (size ... 0)
            if (chars[size - i] == '1') {
                right[i] = Math.min(right[i - 1] + 2, i + 1);
            } else {
                right[i] = right[i - 1];
            }
        }
        // 总花费  枚举分割点算左侧花费，右侧对于分割点开区间 左[0,i] 右(i,size]
        int ans = left[0] + right[size-1];
        for (int i = 1; i <= size; i++) {
            ans = Math.min(ans, left[i] + right[size - i]);
        }
        return ans;
    }
}