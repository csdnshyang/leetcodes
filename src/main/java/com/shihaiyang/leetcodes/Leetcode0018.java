package com.shihaiyang.leetcodes;

import java.util.*;

public class Leetcode0018 {
    public static void main(String[] args) {
        Solution0018 solution0016 = new Solution0018();
        List<List<Integer>> lists = solution0016.fourSum(new int[]{2,2,2,2,2}, 8);
        lists.stream().forEach(list->list.stream().forEach(integer -> System.out.println(integer)));
    }
}
/***
 四指针
 双层循环加后面双指针。
 n^3的时间复杂度
 去掉map，使用比较+移动指针方式效率更高
 */
class Solution0018 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int i,j,m,n;
        List<List<Integer>> ret = new ArrayList<>();
        if(nums.length<4){
            return ret;
        }
        Arrays.sort(nums);
        for(i=0;i<nums.length-3;i++){
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }
            for(j=i+1;j<nums.length-2;j++){
                if(j > i+1 && nums[j] == nums[j-1]){
                    continue;
                }
                m=j+1;
                n=nums.length-1;
                while(m<n){
                    int sum = nums[i]+nums[j]+nums[m]+nums[n];
                    if(sum == target){
                        ret.add(List.of(nums[i], nums[j], nums[m], nums[n]));
                        while(m+1<n && nums[m]==nums[m+1]){
                            m++;
                        }
                        m++;
                        while(m<n-1 && nums[n-1]==nums[n]){
                            n--;
                        }
                        n--;
                    }else if(sum > target){
                        n--;
                    }else {
                        m++;
                    }
                }
            }
        }
        return ret;
    }
}