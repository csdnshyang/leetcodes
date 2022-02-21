package com.shihaiyang.offer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

// Offer II 083. 没有重复元素集合的全排列.[回溯1ms].
public class Offer083 {
    @Test
    public void case1() {
        SolutionOffer083 solutionOffer083 = new SolutionOffer083();
        List<List<Integer>> permute = solutionOffer083.permute(new int[]{1, 2, 3});
        List<List<Integer>> expected = List.of(List.of(1, 2, 3), List.of(1, 3, 2), List.of(2, 1, 3), List.of(2, 3, 1), List.of(3, 1, 2), List.of(3, 2, 1));
        Assertions.assertTrue(permute.equals(expected));
    }
}

class SolutionOffer083 {
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        backTrace(nums, new ArrayList<>(), new int[nums.length]);
        return ret;
    }

    /**
     * 回溯方法参数
     * @param nums 必须 元素集合
     * @param list 必须 每个结果集集合
     * @param used 可选，根据具体题来决定
     */
    private void backTrace(int[] nums, List<Integer> list, int[] used) {
        if (list.size() == nums.length) {
            ret.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1) {
                continue;
            }
            list.add(nums[i]);
            used[i] = 1;
            backTrace(nums, list, used);
            // 回退
            used[i] = 0;
            list.remove(list.size() - 1);
        }
    }
}