package com.shihaiyang.leetcodes;

import java.util.PriorityQueue;
import java.util.Stack;

// 0155. 最小栈.[辅助栈5ms+辅助优先队列6ms].
// https://leetcode-cn.com/problems/min-stack/solution/155-zui-xiao-zhan-fu-zhu-zhan-5msfu-zhu-aoue8/
public class Leetcode0155 {
    /**
     *  * ["MinStack","push","push","push",
     *  * [[],[2147483646],[2147483646],[2147483647],
     *  "top","pop","getMin","pop","getMin","pop",
     *  [],[],[],[],[],[],
     *  "push","top","getMin","push","top","getMin","pop","getMin"]
     *  [2147483647],[],[],[-2147483648],[],[],[],[]]
     */
    public static void main(String[] args) {
        MinStackAuxiliaryStack minStack = new MinStackAuxiliaryStack();
        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.push(2147483647);
        minStack.push(-2147483648);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());

    }
}

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 */

/**
 * 辅助栈方式
 * 辅助栈中存放val入职之后的最小值
 * 即入栈钱，比较辅助栈顶与插入的值中的最小值。就是当前val入栈之后的最小值。
 * 然后同时入栈，同时出栈。
 */
class MinStackAuxiliaryStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;
    public MinStackAuxiliaryStack() {
        minStack = new Stack<>();
        minStack.push(Integer.MAX_VALUE);
        stack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        minStack.push(Math.min(minStack.peek(), val));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
/**
 * 常数内检索到最小元素栈。
 * 辅助队列方式，加一个小顶堆积的优先队列。
 * 用一个优先队列，小顶堆积，每次push，pop的时候排序一下子。  [需要注意的是，可能会出现int值越界的情况，所以判断优先级队列的时候特判了下]
 */
class MinStack {
    PriorityQueue<Integer> priorityQueue;
    Stack<Integer> stack;
    public MinStack() {
        priorityQueue = new PriorityQueue<>((v1, v2) -> {
            // 防止int越界
            if (v1 > 0 && v2 < 0) {
                return 1;
            }
            if (v1 < 0 && v2 > 0) {
                return -1;
            }
            return v1 - v2;
        });
        stack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        priorityQueue.add(val);
    }

    public void pop() {
        Integer pop = stack.pop();
        priorityQueue.remove(pop);
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return priorityQueue.peek();
    }
}
