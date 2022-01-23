package com.shihaiyang.daily;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

// 2034. 股票价格波动.[优先队列+TreeMap].
public class Leetcode2034 {
    public static void main(String[] args) {
        StockPrice2 stockPrice = new StockPrice2();
        stockPrice.update(1, 10); // 时间戳为 [1] ，对应的股票价格为 [10] 。
        stockPrice.update(2, 5);  // 时间戳为 [1,2] ，对应的股票价格为 [10,5] 。
        stockPrice.current();     // 返回 5 ，最新时间戳为 2 ，对应价格为 5 。
        stockPrice.maximum();     // 返回 10 ，最高价格的时间戳为 1 ，价格为 10 。
        stockPrice.update(1, 3);  // 之前时间戳为 1 的价格错误，价格更新为 3 。
        // 时间戳为 [1,2] ，对应股票价格为 [3,5] 。
        stockPrice.maximum();     // 返回 5 ，更正后最高价格为 5 。
        stockPrice.update(4, 2);  // 时间戳为 [1,2,4] ，对应价格为 [3,5,2] 。
        stockPrice.minimum();
    }
}

/**
 * 给你一支股票价格的数据流。数据流中每一条记录包含一个 时间戳 和该时间点股票对应的 价格 。
 * 不巧的是，由于股票市场内在的波动性，股票价格记录可能不是按时间顺序到来的。某些情况下，有的记录可能是错的。如果两个有相同时间戳的记录出现在数据流中，前一条记录视为错误记录，后出现的记录 更正 前一条错误的记录。
 * 请你设计一个算法，实现：
 * 更新 股票在某一时间戳的股票价格，如果有之前同一时间戳的价格，这一操作将 更正 之前的错误价格。
 * 找到当前记录里 最新股票价格 。最新股票价格 定义为时间戳最晚的股票价格。
 * 找到当前记录里股票的 最高价格 。
 * 找到当前记录里股票的 最低价格 。
 * 请你实现 StockPrice 类：
 * StockPrice() 初始化对象，当前无股票价格记录。
 * void update(int timestamp, int price) 在时间点 timestamp 更新股票价格为 price 。
 * int current() 返回股票 最新价格 。
 * int maximum() 返回股票 最高价格 。
 * int minimum() 返回股票 最低价格 。
 * StockPrice stockPrice = new StockPrice();
 * stockPrice.update(1, 10); // 时间戳为 [1] ，对应的股票价格为 [10] 。
 * stockPrice.update(2, 5);  // 时间戳为 [1,2] ，对应的股票价格为 [10,5] 。
 * stockPrice.current();     // 返回 5 ，最新时间戳为 2 ，对应价格为 5 。
 * stockPrice.maximum();     // 返回 10 ，最高价格的时间戳为 1 ，价格为 10 。
 * stockPrice.update(1, 3);  // 之前时间戳为 1 的价格错误，价格更新为 3 。
 *                           // 时间戳为 [1,2] ，对应股票价格为 [3,5] 。
 * stockPrice.maximum();     // 返回 5 ，更正后最高价格为 5 。
 * stockPrice.update(4, 2);  // 时间戳为 [1,2,4] ，对应价格为 [3,5,2] 。
 * stockPrice.minimum();     // 返回 2 ，最低价格时间戳为 4 ，价格为 2 。
 */

/**
 *
 */
class StockPrice2 {
    Map<Integer, Integer> map;
    TreeMap<Integer, Integer> keys;
    int[] lastPrice = new int[2];
    public StockPrice2() {
        map = new HashMap<>();
        keys = new TreeMap<>((a, b) -> b-a);
    }

    public void update(int timestamp, int price) {
        Integer oldPrice = map.put(timestamp, price);
        if (oldPrice != null) {
            if (keys.containsKey(oldPrice)) {
                Integer integer = keys.get(oldPrice);
                if (integer == 1) {
                    keys.remove(oldPrice);
                } else {
                    keys.put(oldPrice, integer - 1);
                }
            }
        }
        keys.put(price, keys.getOrDefault(price, 0) + 1);
        if (timestamp >= lastPrice[0]) {
            lastPrice = new int[]{timestamp, price};
        }
    }

    public int current() {
        return lastPrice[1];
    }

    public int maximum() {
        return keys.firstKey();
    }

    public int minimum() {
        return keys.lastKey();
    }
}

/**
 * Map存储时间和价格
 * 优先队列存储最大最小
 */
class StockPrice {
    Map<Integer, Price> map;
    PriorityQueue<Price> minPriority;
    PriorityQueue<Price> maxPriority;
    int[] lastPrice = new int[2];
    public StockPrice() {
        map = new HashMap<>();
        minPriority = new PriorityQueue<>((a, b) -> a.price - b.price);
        maxPriority = new PriorityQueue<>((a, b) -> b.price - a.price);
    }

    public void update(int timestamp, int price) {
        Price pr = new Price(price);
        Price put = map.put(timestamp, pr);
        if (put != null) {
            put.error = true;
        }
        minPriority.add(pr);
        maxPriority.add(pr);
        if (timestamp >= lastPrice[0]) {
            lastPrice = new int[]{timestamp, price};
        }
    }

    public int current() {
        return lastPrice[1];
    }

    public int maximum() {
        while (!maxPriority.isEmpty() && maxPriority.peek().error) {
            maxPriority.poll();
        }
        return maxPriority.peek().price;
    }

    public int minimum() {
        while (!minPriority.isEmpty() && minPriority.peek().error) {
            minPriority.poll();
        }
        return minPriority.peek().price;
    }
}
class Price{
    public Integer price;
    public Boolean error;

    public Price(Integer price) {
        this.price = price;
        this.error = false;
    }
}
