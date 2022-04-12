package com.shihaiyang.daily;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// 0806. 写字符串需要的行数.[模拟].
/*
我们要把给定的字符串 S 从左到右写到每一行上，每一行的最大宽度为100个单位，如果我们在写某个字母的时候会使这行超过了100 个单位，那么我们应该把这个字母写到下一行。我们给定了一个数组 widths ，这个数组 widths[0] 代表 'a' 需要的单位， widths[1] 代表 'b' 需要的单位，...， widths[25] 代表 'z' 需要的单位。

现在回答两个问题：至少多少行能放下S，以及最后一行使用的宽度是多少个单位？将你的答案作为长度为2的整数列表返回。

示例 1:
输入:
widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
S = "abcdefghijklmnopqrstuvwxyz"
输出: [3, 60]
解释:
所有的字符拥有相同的占用单位10。所以书写所有的26个字母，
我们需要2个整行和占用60个单位的一行。
示例 2:
输入:
widths = [4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
S = "bbbcccdddaaa"
输出: [2, 4]
解释:
除去字母'a'所有的字符都是相同的单位10，并且字符串 "bbbcccdddaa" 将会覆盖 9 * 10 + 2 * 4 = 98 个单位.
最后一个字母 'a' 将会被写到第二行，因为第一行只剩下2个单位了。
所以，这个答案是2行，第二行有4个单位宽度。
 */
public class Leetcode0806 {
    Solution0806 solution0806 = new Solution0806();

    @Test
    public void testcase1() {
        int[] numberOfLines = solution0806.numberOfLines(new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, "abcdefghijklmnopqrstuvwxyz");
        Assertions.assertArrayEquals(new int[]{3, 60}, numberOfLines);
    }
    @Test
    public void testcase2() {
        int[] numberOfLines = solution0806.numberOfLines(new int[]{4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10}, "bbbcccdddaaa");
        Assertions.assertArrayEquals(new int[]{2,4}, numberOfLines);
    }
}

/*
模拟
知道每个字母占用的宽度
遍历字母，依次减去字母的宽度。
如果剩余宽度小于字母宽度。就从下一行开始。
每一行开始宽度100
 */
class Solution0806 {
    public int[] numberOfLines(int[] widths, String s) {
        int len = 100;
        int row = 1;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (len < widths[index]) {
                len = 100;
                row++;
            }
            len -= widths[index];
        }
        return new int[]{row, 100 - len};
    }
}