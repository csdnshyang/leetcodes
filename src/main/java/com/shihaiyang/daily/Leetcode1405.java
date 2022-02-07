package com.shihaiyang.daily;

import java.util.PriorityQueue;

// 1405. 最长快乐字符串.[优先级队列 1ms].
public class Leetcode1405 {
    public static void main(String[] args) {
        Solution1405 solution1405 = new Solution1405();
        String diverseString = solution1405.longestDiverseString(11, 8, 0);
        System.out.println(diverseString);
    }
}

/**
 * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 * s 是一个尽可能长的快乐字符串。
 * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
 * s 中只含有 'a'、'b' 、'c' 三种字母。
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 * 输入：a = 1, b = 1, c = 7
 * 输出："ccaccbcc"
 * 解释："ccbccacc" 也是一种正确答案。
 * 输入：a = 2, b = 2, c = 1
 * 输出："aabbc"
 * 输入：a = 7, b = 1, c = 0
 * 输出："aabaa"
 * 解释：这是该测试用例的唯一正确答案。
 */

/**
 * 贪心算法 + 优先队列
 * 每次取相对较多，但是不能是上一次的。一次最多有2个
 * 如果最多的>=其他两个2倍。 第二次的时候取一个
 * 如果最多的<=其他的两倍  都取最多的情况，即2个
 */
class Solution1405 {
    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<CharCount> priorityQueue = new PriorityQueue<CharCount>((a1,a2) -> a2.count - a1.count);
        if (a > 0) {
            priorityQueue.add(new CharCount(a, 'a'));
        }
        if (b > 0) {
            priorityQueue.add(new CharCount(b, 'b'));
        }
        if (c > 0) {
            priorityQueue.add(new CharCount(c, 'c'));
        }

        StringBuffer ret = new StringBuffer();
        char lastIndex = '0';
        while (priorityQueue.size() > 0) {
            CharCount first = priorityQueue.poll();
            if (lastIndex != first.aChar) {
                if (first.count >= 2) {
                    first.count -= 2;
                    ret.append(first.aChar).append(first.aChar);
                } else {
                    first.count--;
                    ret.append(first.aChar);
                }
                lastIndex = first.aChar;
            } else {
                CharCount second = priorityQueue.poll();
                if (second == null) {
                    break;
                }
                CharCount peek = priorityQueue.peek();
                int third = peek == null ? 0 : peek.count;
                if ((first.count + 2) <= (second.count + third) * 2 && second.count >= 2) {
                    second.count -= 2;
                    ret.append(second.aChar).append(second.aChar);
                } else {
                    second.count--;
                    ret.append(second.aChar);
                }
                lastIndex = second.aChar;
                if (second.count > 0) {
                    priorityQueue.add(second);
                }
            }
            if (first.count > 0) {
                priorityQueue.add(first);
            }
        }
        return ret.toString();
    }
}
class CharCount{
    public int count;
    public char aChar;
    public CharCount(int count, char aChar) {
        this.count = count;
        this.aChar = aChar;
    }
}
