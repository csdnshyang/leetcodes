package com.shihaiyang.contest;

import java.util.Arrays;

// 5984. 拆分数位后四位数字的最小和.[排序 0ms].
public class Contest5984 {
    public static void main(String[] args) {
        Solution5984 solution5984 = new Solution5984();
        int minimumSum = solution5984.minimumSum(2932);
        System.out.println(minimumSum);
    }
}

/**
 * 获取四位的值。
 * 排除0。
 * 尽量短的位数
 * 示例 1：
 * 输入：num = 2932
 * 输出：52
 * 解释：可行的 [new1, new2] 数对为 [29, 23] ，[223, 9] 等等。
 * 最小和为数对 [29, 23] 的和：29 + 23 = 52 。
 * 示例 2：
 * 输入：num = 4009
 * 输出：13
 * 解释：可行的 [new1, new2] 数对为 [0, 49] ，[490, 0] 等等。
 * 最小和为数对 [4, 9] 的和：4 + 9 = 13 。
 */
class Solution5984 {
    public int minimumSum(int num) {
        int tmp[] = new int[4];
        int index = 0;
        while (num > 0) {
            if (num % 10 != 0) {
                tmp[index++] = num % 10;
            }
            num /= 10;
        }
        int last[] = new int[index];
        System.arraycopy(tmp, 0, last, 0, index);
        Arrays.sort(last);
        if (index == 4) {
            return last[0] * 10 + last[2] + last[1] * 10 + last[3];
        } else if (index == 3) {
            return last[0] * 10 + last[2] + last[1];
        } else if (index == 2) {
            return last[0] + last[1];
        } else {
            return last[0];
        }
    }
}
