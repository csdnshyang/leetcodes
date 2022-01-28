package com.shihaiyang.daily;

import java.util.Arrays;

// 1996. 游戏中弱角色的数量.[排序,记录最大防御92ms].
public class Leetcode1996 {
    public static void main(String[] args) {
        Solution1996 solution1996 = new Solution1996();
        int properties[][] = new int[][]{
                {10,1},
                {5,1},
                {7,10},
                {4,1},
                {5,9},
                {6,9},
                {7,2},
                {1,10},

        };
        int number = solution1996.numberOfWeakCharacters(properties);
        System.out.println(number);
    }
}

/**
 * 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击 和 防御 。
 * 给你一个二维整数数组 properties ，其中 properties[i] = [attacki, defensei] 表示游戏中第 i 个角色的属性。
 * 如果存在一个其他角色的攻击和防御等级 都严格高于 该角色的攻击和防御等级，则认为该角色为 弱角色 。
 * 更正式地，如果认为角色 i 弱于 存在的另一个角色 j ，那么 attackj > attacki 且 defensej > defensei 。
 * 返回 弱角色 的数量。
 * 输入：properties = [[2,2],[3,3]]
 * 输出：1
 * 解释：第一个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 * 输入：properties = [[1,5],[10,4],[4,3]]
 * 输出：1
 * 解释：第三个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 * [[7,7],[1,2],[9,7],[7,3],[3,10],[9,8],[8,10],[4,3],[1,5],[1,5]]
 * 6
 * [[10,1],[5,1],[7,10],[4,1],[5,9],[6,9],[7,2],[1,10]]
 * 4
 */

/**
 * 排序
 */
class Solution1996 {
    public int numberOfWeakCharacters(int[][] properties) {
        // 攻击降序  防御升序
        Arrays.sort(properties, (a, b) -> b[0] == a[0] ? a[1] - b[1] : b[0] - a[0]);
        int maxDefense = properties[0][1];
        int count = 0;
        for (int i = 1; i < properties.length; i++) {
            if (maxDefense > properties[i][1]) {
                count++;
            }else {
                maxDefense = properties[i][1];
            }
        }
        return count;
    }
}