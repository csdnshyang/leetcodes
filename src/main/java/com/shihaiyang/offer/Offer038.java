package com.shihaiyang.offer;

// 剑指 Offer II 038. 每日温度
// Offer038. 每日温度[单调栈 160ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * 请根据每日 气温 列表 temperatures ，重新生成一个列表，要求其对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 */
public class Offer038 {
    SolutionOffer038 solutionOffer038 = new SolutionOffer038();

    @Test
    public void case1() {
        int[] temperatures = solutionOffer038.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        Assertions.assertArrayEquals(temperatures, new int[]{1,1,4,2,1,1,0,0});
    }
    @Test
    public void case2() {
        int[] temperatures = solutionOffer038.dailyTemperatures(new int[]{30,40,50,60});
        Assertions.assertArrayEquals(temperatures, new int[]{1,1,1,0});
    }
    @Test
    public void case3() {
        int[] temperatures = solutionOffer038.dailyTemperatures(new int[]{30,60,90});
        Assertions.assertArrayEquals(temperatures, new int[]{1,1,0});
    }
}

/**
 * 单调栈。栈中存放二元组，温度和下一个更高温度的天数
 * {54, 1} // 代表当前温度54，下一个最高温度步数1。就是说54下一个就是更高温度。可能是60或者70等等。
 * 先进栈的数更大
 * 如果入栈时发现val>peek  那就出栈 并且步数要加上出栈的元素的步数。循环比较；
 * 直到val<peek,val入栈，天数为出栈的元素天数之和
 * 反向遍历。
 * 如果栈为空，就把val入栈，天数为0
 */

/**
 * 换一个思路。栈中存放下标。
 */
class SolutionOffer038 {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ret = new int[temperatures.length];
        Stack<int[]> monotoneStack = new Stack<>();
        for (int i = temperatures.length - 1; i >= 0; i--) {
            if (monotoneStack.isEmpty()) {
                monotoneStack.push(new int[]{temperatures[i], 0});
                ret[i] = 0;
                continue;
            }
            if (monotoneStack.peek()[0] > temperatures[i]) {
                monotoneStack.push(new int[]{temperatures[i], 1});
                ret[i] = 1;
                continue;
            }
            int step = 1;
            while (!monotoneStack.isEmpty()) {
                int[] pop = monotoneStack.pop();
                if (monotoneStack.isEmpty()) {
                    monotoneStack.push(new int[]{temperatures[i], 0});
                    ret[i] = 0;
                    break;
                }
                if (monotoneStack.peek()[0] > temperatures[i]) {
                    monotoneStack.push(new int[]{temperatures[i], step + pop[1]});
                    ret[i] = step + pop[1];
                    break;
                }
                step += pop[1];
            }
        }
        return ret;
    }
}