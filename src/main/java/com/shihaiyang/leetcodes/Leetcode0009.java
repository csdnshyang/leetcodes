package com.shihaiyang.leetcodes;

import java.util.Stack;
// 0009. 用两个栈实现队列.[一个pushStack+一个popStack].
public class Leetcode0009 {

}
// 实话...这不就是初试专业课的数据结构题么...

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 */
class CQueue {
    Stack<Integer> pushStack;
    Stack<Integer> popStack;
    public CQueue() {
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }

    public void appendTail(int value) {
        pushStack.push(value);
    }

    public int deleteHead() {
        if (!popStack.empty()) {
            return popStack.pop();
        }
        if (!pushStack.empty()) {
            while (!pushStack.empty()){
                Integer pop = pushStack.pop();
                popStack.push(pop);
            }
            return popStack.pop();
        }
        return -1;
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
