package com.shihaiyang.offer;

import java.util.*;

// 剑指 Offer II 030. 插入、删除和随机访问都是 O(1) 的容器
// Offer030. 插入、删除和随机访问都是 O(1) 的容器.[数组+map 20ms].
public class Offer030 {
    public static void main(String[] args) {
        RandomizedSet randomSet = new RandomizedSet();  // 初始化一个空的集合
        System.out.println(randomSet.insert(1));; // 向集合中插入 1 ， 返回 true 表示 1 被成功地插入
        System.out.println(randomSet.remove(2));; // 返回 false，表示集合中不存在 2
        System.out.println(randomSet.insert(2));; // 向集合中插入 2 返回 true ，集合现在包含 [1,2]
        System.out.println(randomSet.getRandom());; // getRandom 应随机返回 1 或 2
        System.out.println(randomSet.remove(1));; // 从集合中移除 1 返回 true 。集合现在包含 [2]
        System.out.println(randomSet.insert(2));; // 2 已在集合中，所以返回 false
        System.out.println(randomSet.getRandom());; // 由于 2 是集合中唯一的数字，getRandom 总是返回 2
    }
}
/**
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构：
 *
 * insert(val)：当元素 val 不存在时返回 true ，并向集合中插入该项，否则返回 false 。
 * remove(val)：当元素 val 存在时返回 true ，并从集合中移除该项，否则返回 false 。
 * getRandom：随机返回现有集合中的一项。每个元素应该有 相同的概率 被返回。
 *
 * RandomizedSet randomSet = new RandomizedSet();  // 初始化一个空的集合
 * randomSet.insert(1); // 向集合中插入 1 ， 返回 true 表示 1 被成功地插入
 * randomSet.remove(2); // 返回 false，表示集合中不存在 2
 * randomSet.insert(2); // 向集合中插入 2 返回 true ，集合现在包含 [1,2]
 * randomSet.getRandom(); // getRandom 应随机返回 1 或 2
 * randomSet.remove(1); // 从集合中移除 1 返回 true 。集合现在包含 [2]
 * randomSet.insert(2); // 2 已在集合中，所以返回 false
 * randomSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2
 */

/**
 * 新增操作：在数组中添加一个元素。并且在map中标记该元素的index
 * 删除操作：找到元素在数组中位置，设置该位置为最后一个元素值，移除数组最后一个位置。这样时间复杂度O(1).并且更新map中最后一个元素的index
 * 随机操作：根据size 随机一个下标，获取下标对应的值。
 */
class RandomizedSet {
    Map<Integer, Integer> map;
    List<Integer> list;
    int size = 0;
    Random random;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            list.add(val);
            map.put(val, size);
            size++;
            return true;
        }
        return false;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        Integer lastVal = list.get(size - 1);
        map.put(lastVal, index);
        map.remove(val);
        list.set(index, lastVal);
        list.remove(size - 1);
        size--;
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        // 0..size-1
        int index = random.nextInt(size);
        return list.get(index);
    }
}
