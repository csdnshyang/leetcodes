package com.shihaiyang.leetcodes;

// 11. 盛最多水的容器.[典型双指针].
public class Leetcode0011 {
    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        Solution0011 solution = new Solution0011();
        int maxArea = solution.maxArea(height);
        System.out.println(maxArea);
    }
}
// 双指针问题
/**
 找到两个指针中间面积最大的两个下标
 一个从头，一个从尾。
 小的向内移动。才有可能找到最大的面积。
 */
class Solution0011 {
    public int maxArea(int[] height) {
        int i=0,j=height.length-1;
        int max = 0;
        while(i<j){
            if(height[i] < height[j]){
                int area= height[i]*(j-i);
                if(max < area) max=area;
                i++;
            }else{
                int area= height[j]*(j-i);
                if(max < area) max=area;
                j--;
            }
        }
        return max;
    }
}
