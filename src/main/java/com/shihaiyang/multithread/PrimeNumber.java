package com.shihaiyang.multithread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程求500内的质数
 *
 * 求质数的方法抽象
 * 起多线程取数字，求质数，判断是否是质数。加入到返回中。
 * 线程安全的集合
 */
public class PrimeNumber {
    AtomicInteger num;
    AtomicInteger primeCount;

    public PrimeNumber() {
        num = new AtomicInteger(1);
        primeCount = new AtomicInteger(0);
    }

    public int getNum() {
        return num.getAndIncrement();
    }

    public void addPrime() {
        primeCount.incrementAndGet();
    }

    public AtomicInteger getPrimeCount() {
        return primeCount;
    }

    public static void main(String[] args) {
        Integer count = 50000;
        PrimeNumber primeNumber = new PrimeNumber();
        long start = System.currentTimeMillis();
        PrimeNumber primeNumber1 = new PrimeNumber();
        for (int i = 0; i < count; i++) {
            new Ana(primeNumber1).run();
        }

        System.out.println("单线程用时：" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50000));
        for (int i = 0; i < count; i++) {
            executor.submit(new Ana(primeNumber));
        }
        System.out.println("多线程用时:"+(System.currentTimeMillis() - start));
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(primeNumber.getNum() + "质数个数：" + primeNumber.getPrimeCount());
        System.out.println(primeNumber1.getNum() + "质数个数：" + primeNumber1.getPrimeCount());
    }

}

class Ana implements Runnable {
    PrimeNumber primeNumber;

    public Ana(PrimeNumber primeNumber) {
        this.primeNumber = primeNumber;
    }

    @Override
    public void run() {
        int num = primeNumber.getNum();
        boolean checkNum = checkNum(num);
        if (checkNum) {
            primeNumber.addPrime();
//            System.out.println(Thread.currentThread().getName() + ":" + num + " 是质数.");
        }
    }

    public boolean checkNum(int num) {
        for (int i = num - 1; i >= 1; i--) {
            int gcd = gcd(num, i);
            if (gcd != 1) {
                return false;
            }
        }
        return true;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
