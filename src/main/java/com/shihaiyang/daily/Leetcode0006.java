package com.shihaiyang.daily;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

// 0006. Z 字形变换[BFS+标记已扫 5ms].
/*
将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：

P   A   H   N
A P L S I I G
Y   I   R
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
请你实现这个将字符串进行指定行数变换的函数：
string convert(string s, int numRows);
 

示例 1：

输入：s = "PAYPALISHIRING", numRows = 3
输出："PAHNAPLSIIGYIR"
示例 2：
输入：s = "PAYPALISHIRING", numRows = 4
输出："PINALSIGYAHRPI"
解释：
P     I    N
A   L S  I G
Y A   H R
P     I
示例 3：

输入：s = "A", numRows = 1
输出："A"
 */
public class Leetcode0006 {
    Solution0006 solution0006 = new Solution0006();

    @Test
    public void testcase1() {
        Assertions.assertEquals(solution0006.convert("PAYPALISHIRING", 2), "PYAIHRNAPLSIIG");
    }
    @Test
    public void testcase2() {
        Assertions.assertEquals(solution0006.convert("PAYPALISHIRING", 4), "PINALSIGYAHRPI");
    }
    @Test
    public void testcase3() {
        Assertions.assertEquals(solution0006.convert("A", 1), "A");
    }
    @Test
    public void testcase4() {
        Assertions.assertEquals(solution0006.convert("AB", 1), "AB");
    }
    @Test
    public void testcase5() {
        Assertions.assertEquals(solution0006.convert("ABCD", 3), "ABDC");
    }
    @Test
    public void testcase6() {
        Assertions.assertEquals(solution0006.convert("ABCDE", 4), "ABCED");
    }
}
/*
可以尝试使用数学计算的方式
一共多少轮呢？比如说len=8,num=4时，每一轮是2(4-1)  所以每一轮是2*(num-1)个。
        所以一共是 len/(2*(num-1)) + 1轮
第一行
    0*2*(num-1)  1*2*(num-1)  [i*2*(num-1)]   i in 0..轮数  并且入队列。
第二行，从第二行开始到倒数第二行，每一行的一轮里都有两个。
    第一轮  1+(0*2*(4-2))=1  1+(1*2*(4-2))=5   [1] [1+2*(num-2[行数])]
第三行：
    第一轮  2+(0*2*(4-3))=2  2+(1*2*(4-3))=4   [2] [2+2(num-line)]
最后一行
    第一轮  0+n-1=3   6+n-1=9
上面的能写。比较长。

可以使用BFS
纯BFS的话。
使用第一行的节点作为一轮BFS的首个节点。
第一行的一轮只有一个节点。
从第二行到倒数第二行，每一轮都有两个节点。分别是  poll , poll+(2 * (numRows - line));
最后一行一轮只有一个节点。

添加一个下标数组来标识是否已经入队过了。
 */
class Solution0006 {
    public String convert(String s, int numRows) {
        char[] chars = s.toCharArray();
        // 遍历过的节点
        boolean[] used = new boolean[chars.length];
        Queue<Integer> queue = new LinkedList<>();
        StringBuffer ret = new StringBuffer();
        // 第一行  第一个是0，第二个是2*(numRow-1)  第三个是2*2*(numRow-1)
        int index = 0;
        while (index < chars.length) {
            ret.append(chars[index]);
            used[index] = true;
            queue.add(index + 1);
            index += Math.max(2 * (numRows - 1), 1);
        }
        // 中间行 // 第二行开始，每一行有两个元素
        int line = 1;
        while (!queue.isEmpty() && line < numRows) {
            line++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int poll = queue.poll();
                if (poll < chars.length && !used[poll]) {
                    ret.append(chars[poll]);
                    used[poll] = true;
                }
                int second = poll + (2 * (numRows - line));
                if (second < chars.length && !used[second]) {
                    ret.append(chars[second]);
                    used[second] = true;
                }
                // 入库下一行的第一个元素下标
                int next = poll + 1;
                if (next < chars.length && !used[next]) {
                    queue.add(next);
                }
            }
        }
        // 最后一行
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (poll < chars.length && !used[poll]) {
                ret.append(chars[poll]);
            }
        }
        return ret.toString();
    }
}