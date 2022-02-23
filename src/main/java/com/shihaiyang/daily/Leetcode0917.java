package com.shihaiyang.daily;

// 0917. 仅仅反转字母.[双指针0ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 给你一个字符串 s ，根据下述规则反转字符串：
 * <p>
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 * 示例 1：
 * 输入：s = "ab-cd"
 * 输出："dc-ba"
 * 示例 2：
 * 输入：s = "a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * 示例 3：
 * 输入：s = "Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 * s 仅由 ASCII 值在范围 [33, 122] 的字符组成
 */
public class Leetcode0917 {
    Solution0917 solution0917 = new Solution0917();

    @Test
    public void case1() {
        String onlyLetters = solution0917.reverseOnlyLetters("ab-cd");
        Assertions.assertEquals(onlyLetters, "dc-ba");
    }
    @Test
    public void case2() {
        String onlyLetters = solution0917.reverseOnlyLetters("a-bC-dEf-ghIj");
        Assertions.assertEquals(onlyLetters, "j-Ih-gfE-dCba");
    }
    @Test
    public void case3() {
        String onlyLetters = solution0917.reverseOnlyLetters("Test1ng-Leet=code-Q!");
        Assertions.assertEquals(onlyLetters, "Qedo1ct-eeLg=ntse-T!");
    }
    @Test
    public void case4() {
        String onlyLetters = solution0917.reverseOnlyLetters( "7_28]");
        Assertions.assertEquals(onlyLetters,  "7_28]");
    }

}

/**
 * 双指针
 * 遇到非字母的就跳过。left>right就跳出
 */
class Solution0917 {
    public String reverseOnlyLetters(String s) {
        int left = 0, right = s.length() - 1;
        char[] ret = s.toCharArray();
        while (left < right) {
            while (left< right && !Character.isLetter(ret[left])) {
                left++;
            }
            while (left< right && !Character.isLetter(ret[right])) {
                right--;
            }
            if (left< right) {
                char temp = ret[left];
                ret[left++] = ret[right];
                ret[right--] = temp;
            }
        }
        return String.valueOf(ret);
    }
}