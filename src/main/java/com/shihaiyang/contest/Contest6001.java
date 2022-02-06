package com.shihaiyang.contest;

import java.util.Arrays;

// 6001. 重排数字的最小值.[排序1ms].
public class Contest6001 {
    public static void main(String[] args) {
        Solution6001 solution6001 = new Solution6001();
        long smallestNumber = solution6001.smallestNumber(-7605);
        System.out.println(smallestNumber);
    }
}
/**
 * 示例 1：
 *
 * 输入：num = 310
 * 输出：103
 * 解释：310 中各位数字的可行排列有：013、031、103、130、301、310 。
 * 不含任何前导零且值最小的重排数字是 103 。
 * 示例 2：
 *
 * 输入：num = -7605
 * 输出：-7650
 * 解释：-7605 中各位数字的部分可行排列为：-7650、-6705、-5076、-0567。
 * 不含任何前导零且值最小的重排数字是 -7650 。
 */

/**
 *
 */
class Solution6001 {
    public long smallestNumber(long num) {
        int flag = 0;
        if (num < 0) {
            flag = 1;
        }
        long real = Math.abs(num);
        String value = String.valueOf(real);
        char[] chars = value.toCharArray();
        Arrays.sort(chars);
        if (flag == 1) {
            for (int i = 0; i < chars.length / 2; i++) {
                char tmp = chars[i];
                chars[i] = chars[chars.length-1 - i];
                chars[chars.length-1 - i] = tmp;
            }
            String valueOf = String.valueOf(chars);
            return -Long.parseLong(valueOf);
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                char min = chars[i];
                chars[i] = '0';
                chars[0] = min;
                break;
            }
        }
        String valueOf = String.valueOf(chars);
        return Long.parseLong(valueOf);
    }
}