package com.shihaiyang.multithread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PrimeNumberSolution {

    public static void main(String[] args) throws InterruptedException {
        Integer count = 10000000;
        long start = System.currentTimeMillis();
        PrimeNumberContext singlePrime = new PrimeNumberContext(count);
        ExecutorService single = Executors.newSingleThreadExecutor();
        for (int i = 0; i < count; i++) {
            single.submit(new ComputePrime(singlePrime));
        }
        singlePrime.countDownLatch.await();
        single.shutdown();
        System.out.println("1个线程用时："+ (System.currentTimeMillis() - start) +"("+ singlePrime.getNum() + " / "+ singlePrime.getPrimeCount() +")");


        start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        PrimeNumberContext multiPrime = new PrimeNumberContext(count);
        for (int i = 0; i < count; i++) {
            executorService.submit(new ComputePrime(multiPrime));
        }
        multiPrime.countDownLatch.await();
        executorService.shutdown();
        System.out.println(Runtime.getRuntime().availableProcessors() + "个线程用时：" + (System.currentTimeMillis() - start) + "(" + multiPrime.getNum() + " / " + multiPrime.getPrimeCount() + ")");
    }
}

class PrimeNumberContext {
    AtomicInteger num;
    AtomicInteger primeCount;
    CountDownLatch countDownLatch;

    public PrimeNumberContext(int limit) {
        num = new AtomicInteger(1);
        primeCount = new AtomicInteger(0);
        countDownLatch = new CountDownLatch(limit);
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

    public void decreaseLatch() {
        countDownLatch.countDown();

    }
}


class ComputePrime implements Runnable {
    PrimeNumberContext primeNumber;

    public ComputePrime(PrimeNumberContext primeNumber) {
        this.primeNumber = primeNumber;
    }

    /**
     * 每次完成一个数字判定，就计数-1
     */
    @Override
    public void run() {
        if (checkNum(primeNumber.getNum())) {
            primeNumber.addPrime();
        }
        primeNumber.decreaseLatch();
    }

    public boolean checkNum(int num) {
        int limit = (int) Math.sqrt(num);
        for (int i = 2; i <= limit; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}

