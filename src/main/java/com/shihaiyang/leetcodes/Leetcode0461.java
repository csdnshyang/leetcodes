package com.shihaiyang.leetcodes;

// 0461. 汉明距离.[位运算+与运算0ms].
public class Leetcode0461 {
    public static void main(String[] args) {
        Solution0461 solution0461 = new Solution0461();
        int hammingDistance = solution0461.hammingDistance(1, 3);
        System.out.println(hammingDistance);
    }
}
/**
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 * 输入：x = 1, y = 4
 * 输出：2
 * 解释：
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 * 输入：x = 3, y = 1
 * 输出：1
 */

/**
 * 通过与1来判断最低位是否相同  然后同时右移位，遍历判断
 * 010001&1=1  001000&1=0           可以判断最低位是否相同，不相同基数+1
 * 010001>>1=01000   01000>>1=0100   同时右移一位
 * 01000&1=0      0100&1=0         再次判断最低位是否相同，相同重新右移判断。直到全部小于0跳出循环
 */
class Solution0461 {
    public int hammingDistance(int x, int y) {
        int count = 0;
        while (x > 0 || y > 0) {
            if ((x & 1) != (y & 1)) {
                count++;
            }
            x = x >> 1;
            y = y >> 1;
        }
        return count;
    }
}