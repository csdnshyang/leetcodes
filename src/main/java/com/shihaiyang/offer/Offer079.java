package com.shihaiyang.offer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// Offer II 079. 所有子集.[BFS 0ms].

/**
 * 给定一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * 1 <= nums.length <= 10
 */
public class Offer079 {
    SolutionOffer079 solutionOffer079 = new SolutionOffer079();

    @Test
    public void case1() {
        Set<List<Integer>> actual = solutionOffer079.subsets(new int[]{1, 2, 3}).stream().collect(Collectors.toSet());
        Set<List<Integer>> expected = Set.of(List.of(), List.of(1), List.of(2), List.of(3), List.of(1, 2), List.of(2, 3), List.of(1, 3), List.of(1, 2, 3));
        Assertions.assertTrue(actual.equals(expected));
    }
    @Test
    public void case2() {
        Set<List<Integer>> actual = solutionOffer079.subsets(new int[]{0}).stream().collect(Collectors.toSet());
        Set<List<Integer>> expected = Set.of(List.of(), List.of(0));
        Assertions.assertTrue(actual.equals(expected));
    }
}

/**
 * 分次加元素进入ret。
 * 加入之前，先跟元素中的其他元素合并，形成新元素，放入ret中。
 */
class SolutionOffer079 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int size = ret.size();
            for (int j = 0; j < size; j++) {
                ArrayList<Integer> integers = new ArrayList<>(ret.get(j));
                integers.add(nums[i]);
                ret.add(integers);
            }
            ret.add(List.of(nums[i]));
        }
        ret.add(List.of());
        return ret;
    }
}