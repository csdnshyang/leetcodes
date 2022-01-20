package com.shihaiyang.daily;

import java.util.ArrayList;
import java.util.List;
// 2029. 石子游戏 IX.
public class Leetcode2029 {
    public static void main(String[] args) {
        Solution2029 solution2029 = new Solution2029();
        boolean stoneGameIX = solution2029.stoneGameIX(new int[]{1,1,7,10,8,17,10,20,2,10});
        System.out.println(stoneGameIX);
    }
}
/**
 *Alice 和 Bob 再次设计了一款新的石子游戏。现有一行 n 个石子，每个石子都有一个关联的数字表示它的价值。给你一个整数数组 stones ，其中 stones[i] 是第 i 个石子的价值。
 * Alice 和 Bob 轮流进行自己的回合，Alice 先手。每一回合，玩家需要从 stones 中移除任一石子。
 * 如果玩家移除石子后，导致 所有已移除石子 的价值 总和 可以被 3 整除，那么该玩家就 输掉游戏 。
 * 如果不满足上一条，且移除后没有任何剩余的石子，那么 Bob 将会直接获胜（即便是在 Alice 的回合）。
 * 假设两位玩家均采用 最佳 决策。如果 Alice 获胜，返回 true ；如果 Bob 获胜，返回 false 。
 * 示例 1：
 * 输入：stones = [2,1]
 * 输出：true
 * 解释：游戏进行如下：
 * - 回合 1：Alice 可以移除任意一个石子。
 * - 回合 2：Bob 移除剩下的石子。
 * 已移除的石子的值总和为 1 + 2 = 3 且可以被 3 整除。因此，Bob 输，Alice 获胜。
 * 示例 2：
 * 输入：stones = [2]
 * 输出：false
 * 解释：Alice 会移除唯一一个石子，已移除石子的值总和为 2 。
 * 由于所有石子都已移除，且值总和无法被 3 整除，Bob 获胜。
 * 示例 3：
 * 输入：stones = [5,1,2,4,3]
 * 输出：false
 * 解释：Bob 总会获胜。其中一种可能的游戏进行方式如下：
 * - 回合 1：Alice 可以移除值为 1 的第 2 个石子。已移除石子值总和为 1 。
 * - 回合 2：Bob 可以移除值为 3 的第 5 个石子。已移除石子值总和为 = 1 + 3 = 4 。
 * - 回合 3：Alices 可以移除值为 4 的第 4 个石子。已移除石子值总和为 = 1 + 3 + 4 = 8 。
 * - 回合 4：Bob 可以移除值为 2 的第 3 个石子。已移除石子值总和为 = 1 + 3 + 4 + 2 = 10.
 * - 回合 5：Alice 可以移除值为 5 的第 1 个石子。已移除石子值总和为 = 1 + 3 + 4 + 2 + 5 = 15.
 * Alice 输掉游戏，因为已移除石子值总和（15）可以被 3 整除，Bob 获胜。
 * [1,1,7,10,8,17,10,20,2,10]
 * true
 */

/**
 * 无限循环。
 * while(i<length)
 * 剪枝
 * sum+=?
 * if(sum%3==0 && size < nums.length-1)  continue;
 * 跳出条件  sum%3==0  {if size&1 == 1 return false   else return true}
 * size==nums.length return false
 */
class Solution2029 {
    public boolean stoneGameIX(int[] stones) {
        List<Integer> selected = new ArrayList<>();
        char[] used = new char[stones.length];
        int sum = 0;
        int emptyCycle = 0;
        while (selected.size() < stones.length) {
            emptyCycle = 0;
            for (int i = 0; i < stones.length; i++) {
                if (used[i] == '1') {
                    continue;
                }
                int preCompute = sum + stones[i];
                if (preCompute % 3 == 0 && selected.size() < stones.length - 1) {
                    // 整除3，且还有别的，就选别的
                    continue;
                } else if (preCompute % 3 == 0 && selected.size() == stones.length - 1) {
                    // 最后一个，且整除，根据奇偶数判断
                    if ((stones.length & 1) == 1) {
                        return false;
                    } else {
                        return true;
                    }
                }
                System.out.println(sum+"==");
                sum = preCompute;
                used[i] = '1';
                selected.add(stones[i]);
                emptyCycle++;
            }
            // 空转，说明有空余数字，但每个加上等于3.
            if (emptyCycle == 0) {
                if ((selected.size() & 1) == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        System.out.println(selected.toString());
        // 全移除，无法整除
        return false;
    }
}
