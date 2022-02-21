package com.shihaiyang.offer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Offer II 082. 含有重复元素集合的组合[回溯 2ms].

/**
 * 给定一个可能有重复数字的整数数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次，解集不能包含重复的组合。 
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 * 1 <= candidates[i] <= 50
 */
public class Offer082 {
    SolutionOffer082 solutionOffer082 = new SolutionOffer082();

    @Test
    public void case1() {
        List<List<Integer>> list = solutionOffer082.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        Assertions.assertTrue(list.equals(List.of(List.of(1, 1, 6), List.of(1, 2, 5), List.of(1, 7), List.of(2, 6))));
    }
}

/**
 * 回溯
 * 每个出现的数字可使用一次，出现两次1就能使用两次。
 * 这个递归的时候，就直接使用下一个索引
 */
class SolutionOffer082 {
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 排序
        Arrays.sort(candidates);
        backTrace(candidates, new ArrayList<>(), 0, target);
        return ret;
    }

    /**
     * 四个参数
     * @param candidates 元素集合  必须
     * @param list 每一个结果集合  必须
     * @param start  必须  从某个位置开始遍历，如果可以重复使用，递归时start可能会不变。不可以重复使用元素，start递归时会+1
     * @param target 目标值  根据情况
     */
    private void backTrace(int[] candidates, List<Integer> list, int start, int target) {
        if (0 == target) {
            ret.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 去除重复集合
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (candidates[i] <= target) {
                list.add(candidates[i]);
                backTrace(candidates, list, i + 1, target - candidates[i]);
                list.remove(list.size() - 1);
            } else {
                // 因为已经排序。如果i已经大于target，后面一定大于
                break;
            }
        }
    }
}