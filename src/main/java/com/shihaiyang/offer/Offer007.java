package com.shihaiyang.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 剑指 Offer II 007. 数组中和为 0 的三个数
// Offer007. 数组中和为 0 的三个数.[固定指针+双指针夹逼 21ms].
public class Offer007 {
    public static void main(String[] args) {
        SolutionOffer007 solutionOffer007 = new SolutionOffer007();
        List<List<Integer>> lists = solutionOffer007.threeSum(new int[]{-2,0,3,-1,4,0,3,4,1,1,1,-3,-5,4,0});
        lists.stream().forEach(integers -> {
            integers.stream().forEach(integer -> System.out.print(integer+","));
            System.out.println();
        });
    }

}

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a ，b ，c ，使得 a + b + c = 0 ？请找出所有和为 0 且 不重复 的三元组。
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]  [-4,-1,-1,0,1,2]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[]
 * [-2,0,0,2,2]
 * [[-2,0,2]]
 * [-2,0,3,-1,4,0,3,4,1,1,1,-3,-5,4,0]
 * 输出：
 * [[-5,1,4],[-5,1,4],[-3,-1,4],[-3,0,3],[-2,-1,3],[-2,1,1],[-1,0,1],[-1,0,1],[0,0,0]]
 * 预期结果：
 * [[-5,1,4],[-3,-1,4],[-3,0,3],[-2,-1,3],[-2,1,1],[-1,0,1],[0,0,0]]
 */
class SolutionOffer007 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums.length <= 1) {
            return ret;
        }
        // 快排
        Arrays.sort(nums);
        // 固定一个指针， 双指针夹逼
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int start = i+1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if (sum < 0) {
                    start++;
                } else if (sum > 0) {
                    end--;
                } else {
                    ret.add(List.of(nums[i], nums[start], nums[end]));
                    start++;
                    end--;
                    while (start < end && nums[start - 1] == nums[start]) {
                        start++;
                    }
                    while (start < end && nums[end + 1] == nums[end]) {
                        end--;
                    }
                }
            }
        }
        return ret;
    }
}
