package com.shihaiyang.daily;

import java.util.Arrays;

// 1996. 游戏中弱角色的数量.[桶排序4ms].
public class Leetcode1996 {
    public static void main(String[] args) {
        Solution1996Bucket solution1996 = new Solution1996Bucket();
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
 * 攻击降序，防御升序。
 * 防御升序是规避攻击相同情况下，防御低的数据。
 * 如果攻击相同，就让防御从小到大，就是为了记录一下最后一个防御最大的。
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

/**
 * 桶排序
 * 1.找最大的攻击。
 * 2.找最大攻击的最大防御
 * 3.设置每个攻击的最大防御为，比该攻击大的最大防御。
 *      这个是理解比较困难的，比较一个是否为弱者是要比某个人的攻击小，并且比他攻击力大的人的防御低。所以真实比较防御力应该比较比他攻击力大的那个人的防御。
 *      比较攻击力为5的人是否为弱者，要比较大于5的人的最大防御
 *      比如  [5,7][5,4][6,3][6,5] 5应该比较的防御是5,6的最大防御。
 *      在比如  [5,7][5,4][6,3][6,5][7,10] 5应该比较的防御应该是10，7的最大防御。
 * 4.遍历每个值，判断是否小于防御，并且小于最大攻击
 */
class Solution1996Bucket {
    public int numberOfWeakCharacters(int[][] properties) {
        // 1. 最大攻击
        int maxAttack = 0;
        for (int[] property : properties) {
            maxAttack = Math.max(property[0], maxAttack);
        }
        // 2. 攻击的最大防御
        int maxDefenses[] = new int[maxAttack + 1];
        for (int[] property : properties) {
            maxDefenses[property[0]] = Math.max(maxDefenses[property[0]], property[1]);
        }

        // 3. 更改为比该攻击大的人的最大防御
        // [5,7][5,4][6,3][6,5] 5应该比较的防御是5, [6,5]的最大防御。
        int maxDefense = 0;
        for (int i = maxAttack; i >= 0; i--) {
            int tmp = maxDefense;
            maxDefense = Math.max(maxDefense, maxDefenses[i]);
            maxDefenses[i] = tmp;
        }
        // 4. 判断是否弱者
        int ans = 0;
        for (int[] property : properties) {
            if (property[1] < maxDefenses[property[0]]) {
                ans++;
            }
        }
        return ans;
    }
}