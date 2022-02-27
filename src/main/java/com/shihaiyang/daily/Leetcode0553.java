package com.shihaiyang.daily;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// 0553. 最优除法.[贪心算法,除法法则 0ms].
/*
给定一组正整数，相邻的整数之间将会进行浮点除法操作。例如， [2,3,4] -> 2 / 3 / 4 。

但是，你可以在任意位置添加任意数目的括号，来改变算数的优先级。你需要找出怎么添加括号，才能得到最大的结果，并且返回相应的字符串格式的表达式。
你的表达式不应该含有冗余的括号。

示例：

输入: [1000,100,10,2]
输出: "1000/(100/10/2)"
解释:
1000/(100/10/2) = 1000/((100/10)/2) = 200
但是，以下加粗的括号 "1000/((100/10)/2)" 是冗余的，
因为他们并不影响操作的优先级，所以你需要返回 "1000/(100/10/2)"。

其他用例:
1000/(100/10)/2 = 50
1000/(100/(10/2)) = 50
1000/100/10/2 = 0.5
1000/100/(10/2) = 2
 */
public class Leetcode0553 {
    Solution0553 solution0553 = new Solution0553();

    @Test
    public void testcase1() {
        String division = solution0553.optimalDivision(new int[]{1000, 100, 10, 2});
        Assertions.assertEquals(division, "1000/(100/10/2)");
    }

    @Test
    public void testcase2() {
        String division = solution0553.optimalDivision(new int[]{1000, 12, 10, 2, 18});
        Assertions.assertEquals(division, "1000/(12/10/2/18)");
    }
    @Test
    public void testcase3() {
        String division = solution0553.optimalDivision(new int[]{1000, 12});
        Assertions.assertEquals(division, "1000/12");
    }
}

/*
数组中每个元素的大小都在 [2, 1000] 之间。
这道题是一道数学题啊...
a/b/c都大于2的话的话
a/(b/c) >= (a/b)/c
因为第一个是让分母变小，那么整个会变大
第二个是让分子变小，那么整个会变小。

所以对于此题，就吧第一个数字后面全部括起来，让后面分母变小，整个数最大。
 */
class Solution0553 {
    public String optimalDivision(int[] nums) {
        StringBuffer stringBuffer = new StringBuffer();
        if (nums.length == 1) {
            return stringBuffer.append(nums[0]).toString();
        } else if (nums.length == 2) {
            return stringBuffer.append(nums[0]).append('/').append(nums[1]).toString();
        }

        stringBuffer.append(nums[0]).append("/(");
        for (int i = 1; i < nums.length - 1; i++) {
            stringBuffer.append(nums[i]).append("/");
        }
        stringBuffer.append(nums[nums.length - 1]).append(')');
        return stringBuffer.toString();
    }
}