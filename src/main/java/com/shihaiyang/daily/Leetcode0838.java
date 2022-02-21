package com.shihaiyang.daily;

// 0838. 推多米诺[双指针+模拟 10ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 * 示例 1：
 * 输入：dominoes = "RR.L"
 * 输出："RR.L"
 * 解释：第一张多米诺骨牌没有给第二张施加额外的力。
 * 示例 2：
 * 输入：dominoes = ".L.R...LR..L.."
 * 输出："LL.RR.LLRRLL.."
 */
public class Leetcode0838 {
    Solution0838 solution0838 = new Solution0838();
    @Test
    public void case1() {
        String dominoes = solution0838.pushDominoes("RR.L");
        Assertions.assertEquals(dominoes, "RR.L");
    }
    @Test
    public void case2() {
        String dominoes = solution0838.pushDominoes(".L.R...LR..L..");
        Assertions.assertEquals(dominoes, "LL.RR.LLRRLL..");
    }
    @Test
    public void case3() {
        String dominoes = solution0838.pushDominoes(".L..L..R...LR..L..");
        Assertions.assertEquals(dominoes, "LLLLL..RR.LLRRLL..");
    }
    @Test
    public void case4() {
        String dominoes = solution0838.pushDominoes(".R..R...");
        Assertions.assertEquals(dominoes, ".RRRRRRR");
    }
    @Test
    public void case5() {
        String dominoes = solution0838.pushDominoes("L.....RR.RL.....L.R.");
        Assertions.assertEquals(dominoes, "L.....RRRRLLLLLLL.RR");
    }
}

/**
 * 模拟 + 双指针
 * right=-1,left=-1
 * 第一次遇到L，right=-1时候，左侧直接都是L
 * 遇到R之后，如果到结束left=-1，右侧直接都是R
 * 遇到R之后，再遇到R，right更新。
 * 遇到R之后，遇到L，双指针夹逼。更新right和left为-1，继续遍历。
 */
class Solution0838 {
    public String pushDominoes(String dominoes) {
        char[] domino = dominoes.toCharArray();
        // right用来存储'R'的位置，left用来存储'L'的位置
        int right = -1, left = 0;
        for (int i = 0; i < domino.length; i++) {
            char c = domino[i];
            if (c == 'R') {
                // R...R: 要先把两个R之间设置为R，在更新right
                if (right != -1) {
                    Arrays.fill(domino, right, i, 'R');
                }
                right = i;
            } else if (c == 'L') {
                // L.. .L:要先把两个L之间都设置为L，在更新left
                // ...L :特殊情况是第一个非.字符就是L，那么0到L都填充L
                if (right == -1) {
                    Arrays.fill(domino, left, i, 'L');
                    left = i;
                } else {
                    // R...L: 双指针夹逼
                    left = i;
                    while (right < left) {
                        domino[right++] = 'R';
                        domino[left--] = 'L';
                    }
                    // 初始化双指针
                    // right=-1代表没有R出现或者R被抵消
                    // left=i说明初始位置
                    right=-1;
                    left = i;
                }
            }
        }
        // R.... : 右侧全部填充R
        if (right != -1) {
            Arrays.fill(domino, right, domino.length, 'R');
        }
        return String.valueOf(domino);
    }
}