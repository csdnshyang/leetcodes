package com.shihaiyang.daily;
// 0717. 1比特与2比特字符[最后一个0前连续1个数比较 0ms].
public class Leetcode0717 {

}

/**
 * 只与最后一个0前面的连续1的个数有关。
 * 如果中间出现了一次0，那么从0前面的就是一个独立的了。
 * 10|110
 * 110|110
 * 1110|110
 * 这三种情况是一样的。都是从竖线左边的0开始，左边就是独立的了。所以只跟最后一个0前面连续的1个数有关。
 * 偶数，那么最后是1bit
 * 奇数，那么最后必然是2bit
 */
class Solution0717 {
    public boolean isOneBitCharacter(int[] bits) {
        int cnt = 0;
        for (int i = bits.length - 2; i >= 0 && bits[i] == 1; i--) {
            cnt++;
        }
        if ((cnt & 1) == 1) {
            return false;
        } else {
            return true;
        }
    }
}
