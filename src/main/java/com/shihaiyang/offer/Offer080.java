package com.shihaiyang.offer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
// Offer II 080. 含有 k 个元素的组合.[回溯算法+剪枝(重要) 3ms].
public class Offer080 {
    SolutionOffer080 solutionOffer080 = new SolutionOffer080();

    @Test
    public void case1() {
        List<List<Integer>> list = solutionOffer080.combine(4, 2);
        Set<List<Integer>> combine = list.stream().collect(Collectors.toSet());
        Assertions.assertTrue(combine.equals(Set.of(List.of(2, 4), List.of(3, 4), List.of(2, 3), List.of(1, 2), List.of(1, 3), List.of(1, 4))));
    }
}

/**
 * 回溯算法。
 * 递归+跳出条件+剪枝+回退
 * 加上剪枝可以减少很多计算。从16ms减少到3ms
 */
class SolutionOffer080 {
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        int[] used = new int[n + 1];
        backTrace(1, used, new ArrayList<>(), k);
        return ret;
    }

    public void backTrace(int start, int[] used, List<Integer> added, int k) {
        // 剪枝 如果长度不够,就跳过
        if (added.size() + used.length - start < k) {
            return;
        }
        // 符合条件退出
        if (added.size() == k) {
            ret.add(new ArrayList<>(added));
            return;
        }
        for (int i = start; i < used.length; i++) {
            // 通过used数组来剪枝
            if (used[i] == 1) continue;
            added.add(i);
            used[i] = 1;
            // 递归
            backTrace(i, used, added, k);
            // 回退
            used[i] = 0;
            added.remove(added.size() - 1);
        }
    }
}