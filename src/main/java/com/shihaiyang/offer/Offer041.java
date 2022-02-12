package com.shihaiyang.offer;
// 剑指 Offer II 041. 滑动窗口的平均值
// Offer041. 滑动窗口的平均值.[队列39ms 循环链表36ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。
 *
 * 实现 MovingAverage 类：
 *
 * MovingAverage(int size) 用窗口大小 size 初始化对象。
 * double next(int val) 成员函数 next 每次调用的时候都会往滑动窗口增加一个整数，请计算并返回数据流中最后 size 个值的移动平均值，即滑动窗口里所有数字的平均值。
 *  
 *
 * 示例：
 *
 * 输入：
 * inputs = ["MovingAverage", "next", "next", "next", "next"]
 * inputs = [[3], [1], [10], [3], [5]]
 * 输出：
 * [null, 1.0, 5.5, 4.66667, 6.0]
 *
 * 解释：
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // 返回 1.0 = 1 / 1
 * movingAverage.next(10); // 返回 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // 返回 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // 返回 6.0 = (10 + 3 + 5) / 3
 */
public class Offer041 {

    @Test
    public void case1() {
        MovingAverage movingAverage = new MovingAverage(3);
        Assertions.assertEquals(movingAverage.next(1), 1.0); // 返回 1.0 = 1 / 1
        Assertions.assertEquals(movingAverage.next(10), 5.5); // 返回 5.5 = (1 + 10) / 2
        Assertions.assertEquals(movingAverage.next(3), 4.66667); // 返回 4.66667 = (1 + 10 + 3) / 3
        Assertions.assertEquals(movingAverage.next(5), 6.0); // 返回 6.0 = (10 + 3 + 5) / 3
    }
}

/**
 * 队列方式
 */
class MovingAverageQueue {
    double sum = 0;
    Queue<Integer> queue;
    int size;
    public MovingAverageQueue(int size) {
        queue = new LinkedBlockingQueue<>();
        this.size = size;
    }

    public double next(int val) {
        if (queue.size() == size) {
            int poll = queue.poll();
            sum -= poll;
        }
        queue.add(val);
        sum += val;
        return sum / queue.size();
    }
}

/**
 * 思路利用滚动指针。
 * index从=开始   index=index%size;  如果<size 就说明size不够。如果>size，就取模，并替换掉对应的值。再算平均值。
 */
class MovingAverage {
    int[] arr;
    int index = 0;
    double sum = 0;
    public MovingAverage(int size) {
        arr = new int[size];
    }

    public double next(int val) {
        int old = arr[index % arr.length];
        arr[(index++) % arr.length] = val;
        int real;
        if (index < arr.length) {
            real = index;
        } else {
            real = arr.length;
            sum -= old;
        }
        sum += val;
        return sum / real;
    }
}

