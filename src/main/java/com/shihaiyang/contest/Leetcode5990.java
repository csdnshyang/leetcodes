package com.shihaiyang.contest;

import java.util.*;

// 5990. 找出数组中的所有孤独数字
public class Leetcode5990 {
    public static void main(String[] args) {
        Solution5990 solution5990 = new Solution5990();
        List<Integer> lonely = solution5990.findLonely(new int[]{75,35,59,66,69,53,37,16,60,98,11,33,3,85,59,65,59,44,34,89,72,47});
//        List<Integer> lonely = solution5990.findLonely(new int[]{10, 6, 5, 8});
        System.out.println(lonely.toString());
    }
}

class Solution5990 {
    public List<Integer> findLonely(int[] nums) {
        Map<Integer, Boolean> map = new LinkedHashMap<>();
        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(nums[i])) {
                map.put(nums[i], true);
            }else if(map.containsKey(nums[i] + 1) || map.containsKey(nums[i] - 1)){
                if (map.containsKey(nums[i] + 1)) {
                    map.put(nums[i], true);
                    map.put(nums[i] + 1, true);
                }
                if (map.containsKey(nums[i] - 1)) {
                    map.put(nums[i], true);
                    map.put(nums[i] - 1, true);
                }
            }else {
                map.put(nums[i], false);
            }
        }
        List<Integer> ret = new ArrayList<>();
        map.forEach((integer, aBoolean) -> {
            if (!aBoolean) {
                ret.add(integer);
            }
        });
        return ret;
    }
}
