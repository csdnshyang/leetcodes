package com.shihaiyang.leetcodes;

import java.util.ArrayList;
import java.util.List;

// 0229. 求众数 II.[MooreVote摩尔投票法1ms].
// https://leetcode-cn.com/problems/majority-element-ii/solution/229-qiu-zhong-shu-iimo-er-tou-piao-fa-2m-4xvh/
public class Leetcode0229 {
    public static void main(String[] args) {
        Solution0229Moore solution0229Moore = new Solution0229Moore();
//        List<Integer> integers = solution0229Moore.majorityElement(new int[]{1, 1, 1, 2, 3, 7, 8, 1, 6, 9});
//        List<Integer> integers = solution0229Moore.majorityElement(new int[]{3,3,1,1,1,1,2,4,4,3,3,3,4,4});
//        List<Integer> integers = solution0229Moore.majorityElement(new int[]{4,2,1,1});
//        List<Integer> integers = solution0229Moore.majorityElement(new int[]{4, 5, 3, 4, 4, 1, 0, -1, -2, 4, 6, 7, 8, 4});
        List<Integer> integers = solution0229Moore.majorityElement(new int[]{2,2});
        integers.stream().forEach(integer -> System.out.println(integer));
    }
}

/**
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 */
/**
 *  摩尔投票法：如果获取"大多数",即>n/2的数。因为"大多数"大于其他"所有少数之和"。
 *  设置一个candidate=nums[0],vote=0.
 *  如果遇到!=candidate,vote-1,
 *  如果==candidate,vote+1.
 *  如果vote==0,当前数值替换candidate,vote=1.
 *  直到最后的candidate即最终大多数.
 */

/**
 * 摩尔投票法
 * 超过 n/3 的数应该最多只有2个。也可能有1个。  具体有几个这个最后用反证法，再遍历一次验证下。
 * 设置两个候选人。
 * 初始化候选人 （这原来也想的不对，初始化应该设置成投票都是0。两个候选人设置同一个。最后反证法的时候，else if）
 * candidate1=nums[0], candidate2=nums[0]. vote1=0,vote2=0.  // 这样初始化不需要特判了都. 优雅多了。还提升1ms.
 * if==candidate1,vote1+1
 * else if==candidate2,vote2+1
 * else (这块有个，先去比较==0,如果有可以易主的，先易主.易主之后就不用vote-1了。只有不需要易主，切跟所有候选人不相同的时候，vote-1)
 *  if vote1==0,candidate1易主
 *  else if vote2==0,candidate2易主
 *  else vote1-1,vote2-1.
 * 最后遍历一次，反证一下candidate1,candidate2是否超过 3/n
 *  if candidate1 num1+1
 *  else candidate2 num2+1  // 防止candidate1 == candidate2 的情况
 * if num1 > n/3  add(candidate1)
 * if num2 > n/3  add(candidate2)
 */
class Solution0229Moore {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        // 初始化候选人.. 这样初始化能减少特判，优雅多了
        int candidate1 = nums[0],candidate2 = nums[0];
        int vote1=0,vote2=0;
        for (int i=0; i < nums.length; i++) {
            if (nums[i] == candidate1) {
                vote1++;
            } else if (nums[i] == candidate2) {
                vote2++;
            } else {
                // 先判断是否有票数0的，如果有票数为0的，先替换候选人，票数=1
                // 如果没有票数为0的，再所有候选人票数-1
                if (vote1 == 0) {
                    candidate1 = nums[i];
                    vote1 = 1;
                }else if (vote2 == 0) {
                    candidate2 = nums[i];
                    vote2 = 1;
                }else {
                    vote1--;
                    vote2--;
                }
            }
        }
        // 最后反证法
        int num1=0,num2=0;
        for (int i = 0; i < nums.length; i++) {
            // 有可能candidate1==candidate2 用else if
            if (candidate1 == nums[i]) num1++;
            else if (candidate2 == nums[i]) num2++;
        }
        if (num1 > nums.length / 3) ret.add(candidate1);
        if (num2 > nums.length / 3) ret.add(candidate2);
        return ret;
    }
}
