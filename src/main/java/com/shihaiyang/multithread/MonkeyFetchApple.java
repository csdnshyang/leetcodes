package com.shihaiyang.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonkeyFetchApple {
    // 一个9个苹果。使用volatile保证每次修改后对其他读线程的可见性
    private volatile int total = 9;
    private final Lock reentrantLock = new ReentrantLock(true);
    private final Condition condition = reentrantLock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        MonkeyFetchApple monkeyFetchApple = new MonkeyFetchApple();
        Thread thread1 = new Thread(() -> {
            // 第一只猴子，每次拿2个苹果
            monkeyFetchApple.fetch(2);
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            // 第二只猴子，每次拿3个苹果
            monkeyFetchApple.fetch(3);
        }, "thread2");

        thread1.start();
        thread2.start();
    }

    public void fetch(int n) {
        reentrantLock.lock();
        try {
            for (; ; ) {
                System.out.println("Thread.currentThread()=" + Thread.currentThread().getName());
                System.out.println("剩余total=" + total);
                if (total < n) {
                    System.out.println("剩余数量不够，停止当前线程：" + Thread.currentThread().getName());
                    break;
//                    Thread.currentThread().interrupt();
                } else {
                    total = total - n;
                    System.out.println("拿取：" + n);
                }
                // 通知其他线程(猴子)来拿苹果
                condition.signal();
                // 拿过一次后，阻塞当前线程，直到被其他线程通知唤醒
                condition.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}
