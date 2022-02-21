package com.shihaiyang.offer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Offer II 084. 含有重复元素集合的全排列[排序+回溯+去重 1ms].

/**
 * 给定一个可包含重复数字的整数集合 nums ，按任意顺序 返回它所有不重复的全排列。
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class Offer084 {
    SolutionOffer084 solutionOffer084 = new SolutionOffer084();

    @Test
    public void case1() {
        List<List<Integer>> lists = solutionOffer084.permuteUnique(new int[]{1, 1, 2});
        Assertions.assertTrue(lists.equals(List.of(List.of(1, 1, 2), List.of(1, 2, 1), List.of(2, 1, 1))));
    }
    @Test
    public void case2() {
        List<List<Integer>> lists = solutionOffer084.permuteUnique(new int[]{1, 2, 3});
        List<List<Integer>> expected = List.of(List.of(1, 2, 3), List.of(1, 3, 2), List.of(2, 1, 3), List.of(2, 3, 1), List.of(3, 1, 2), List.of(3, 2, 1));
        Assertions.assertTrue(lists.equals(expected));
    }
}

/**
 * 排序+回溯+去重剪枝
 * 通过比较是否与上一个元素值相同来进行剪枝重复数据
 * 去重的时候还要加一个上一个元素[i-1]=0还未被选中，说明这个是先选后面，再选前面的情况，这个就是重复。
 * 当上一个[i-1]=1已经被选择，那么就是唯一一种相同元素被加入的情况，就是顺序的选择了相同元素，所以上一个元素是被选中状态。 [i-1]=1
 */
class SolutionOffer084 {
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        backTrace(nums, new ArrayList<>(), new int[nums.length]);
        return ret;
    }

    private void backTrace(int[] nums, List<Integer> list, int[] used) {
        if (list.size() == nums.length) {
            ret.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1) {
                continue;
            }
            // 如果与上一个元素相同，并且上一个元素没有被选。说明是11 11的关系。如果上一个元素被选了，就是唯一一种加入的情况
            // 就是顺序选择了。
            if (i > 0 && used[i-1] == 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            used[i] = 1;
            backTrace(nums, list, used);
            // 回退
            list.remove(list.size() - 1);
            used[i] = 0;
        }
    }
}
