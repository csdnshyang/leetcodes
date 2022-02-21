package com.shihaiyang.offer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

// Offer II 075. 数组相对排序[treemap计数]
public class Offer075 {
    SolutionOffer075 solutionOffer075 = new SolutionOffer075();

    @Test
    public void case1() {
        int[] sortArray = solutionOffer075.relativeSortArray(new int[]{2,3,1,3,2,4,6,37,9,2,19}, new int[]{2,1,4,3,9,6});
        Assertions.assertArrayEquals(sortArray, new int[]{2,2,2,1,4,3,3,9,6,19,37});
    }
    @Test
    public void case2() {
        int[] sortArray = solutionOffer075.relativeSortArray(new int[]{22,28,8,6,17,44}, new int[]{22,28,8,6});
        Assertions.assertArrayEquals(sortArray, new int[]{22,28,8,6,17,44});
    }
}

/**
 * 有序map，存储个数。
 * 按照arr2顺序，填充会ARR1
 */
class SolutionOffer075 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int i : arr1) {
            count.put(i, count.getOrDefault(i, 0) + 1);
        }
        int index = 0;
        for (int i : arr2) {
            Integer cnt = count.get(i);
            Arrays.fill(arr1, index, index + cnt, i);
            count.remove(i);
            index += cnt;
        }
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            Integer cnt = entry.getValue();
            Arrays.fill(arr1, index, index + cnt, entry.getKey());
            index += cnt;
        }
        return arr1;
    }
}
