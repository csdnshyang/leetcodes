package com.shihaiyang.offer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

// Offer II 081. 允许重复选择元素的组合.[回溯2ms].

/**
 * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
 * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是不同的。 
 * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
 * 示例 1：
 * 输入: candidates = [2,3,6,7], target = 7
 * 输出: [[7],[2,2,3]]
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 * 示例 4：
 * 输入: candidates = [1], target = 1
 * 输出: [[1]]
 * 示例 5：
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 */
public class Offer081 {
    SolutionOffer081 solutionOffer081 = new SolutionOffer081();

    @Test
    public void case1() {
        List<List<Integer>> list = solutionOffer081.combinationSum(new int[]{2, 3, 5}, 8);
        Assertions.assertTrue(list.equals(List.of(List.of(2,2,2,2), List.of(2,3,3), List.of(3,5))));
    }

    @Test
    public void case2() {
        List<List<Integer>> lists = solutionOffer081.combinationSum(new int[]{2, 3, 6, 7}, 7);
        Assertions.assertTrue(lists.equals(List.of(List.of(2, 2, 3),List.of(7))));
    }
}

/**
 * 回溯算法。
 * 思路：
 * 可以重复添加一个元素。
 * 可以添加重复元素就是在backTrace的时候，初始是从本身开始递归。
 * 没有重复的就是需要从下一个下标开始递归。
 * 剪枝：
 * 如果值已经等于target，直接返回。
 * 如果<=target的时候才去递归。
 */
class SolutionOffer081 {
    List<List<Integer>> ret;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ret = new ArrayList<>();
        backTrace(0, candidates, new ArrayList<>(), target, 0);
        return ret;
    }

    private void backTrace(int start, int[] candidates, List<Integer> added, int target, int sum) {
        if (sum == target) {
            ret.add(new ArrayList<>(added));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (sum + candidates[i] <= target) {
                added.add(candidates[i]);
                backTrace(i, candidates, added, target, sum + candidates[i]);
                added.remove(added.size() - 1);
            }
        }
    }
}