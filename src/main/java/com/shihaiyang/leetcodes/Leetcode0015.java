package com.shihaiyang.leetcodes;

import java.util.*;
// 0015. 三数之和.[三指针(单指针+夹逼)+移动指针去重].
public class Leetcode0015 {
    public static void main(String[] args) {
        Solution0015 solution = new Solution0015();
        List<List<Integer>> lists = solution.threeSum(new int[]{-1,0,1,2,-1,-4,-2,-3,3,0,4});
        lists.stream().forEach(l->l.stream().forEach(integer -> System.out.println(integer)));
    }
}

/**
 * 三数之和
 * 排序+三指针+去重
 */
class Solution0015 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret =new ArrayList<>();
        if (nums.length<3){
            return ret;
        }
        Arrays.sort(nums);
        Map<String, Integer> map = new HashMap<>();
        int i,j,k;
        for(i=0;i<nums.length-2;i++){
            j=i+1;
            k=nums.length-1;
            while(j<k){
                int sum = nums[i]+nums[j]+nums[k];
                if(sum==0){
                    if(!map.containsKey(""+nums[i]+nums[j]+nums[k])){
                        ret.add(List.of(nums[i],nums[j], nums[k]));
                        map.put(""+nums[i]+nums[j]+nums[k], 1);
                    }
                    k--;
                    j++;
                }else if(sum>0){
                    k--;
                }else {
                    j++;
                }
            }
        }
        return ret;
    }
}
