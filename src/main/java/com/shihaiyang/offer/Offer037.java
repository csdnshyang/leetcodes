package com.shihaiyang.offer;
//剑指 Offer II 037. 小行星碰撞
// Offer037. 小行星碰撞.[数组+栈 8ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * 给定一个整数数组 asteroids，表示在同一行的小行星。
 * 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。
 * 每一颗小行星以相同的速度移动。
 * 找出碰撞后剩下的所有小行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。
 * 如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 * 示例 1：
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 * 示例 2：
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 * 示例 3：
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 * 示例 4：
 * 输入：asteroids = [-2,-1,1,2]
 * 输出：[-2,-1,1,2]
 * 解释：-2 和 -1 向左移动，而 1 和 2 向右移动。 由于移动方向相同的行星不会发生碰撞，所以最终没有行星发生碰撞。
 * 提示：
 * 2 <= asteroids.length <= 104
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 */
public class Offer037 {
    SolutionOffer037 solutionOffer037 = new SolutionOffer037();

    @Test
    @DisplayName("[5,10,-5] -> [5,10]")
    public void case1() {
        int[] asteroidCollision = solutionOffer037.asteroidCollision(new int[]{5, 10, -5});
        Assertions.assertArrayEquals(asteroidCollision, new int[]{5, 10});
    }
    @Test
    @DisplayName("[8,-8] -> []")
    public void case2() {
        int[] asteroidCollision = solutionOffer037.asteroidCollision(new int[]{8,-8});
        Assertions.assertArrayEquals(asteroidCollision, new int[]{});
    }
    @Test
    @DisplayName("[10,2,-5] -> [10]")
    public void case3() {
        int[] asteroidCollision = solutionOffer037.asteroidCollision(new int[]{10,2,-5});
        Assertions.assertArrayEquals(asteroidCollision, new int[]{10});
    }
    @Test
    @DisplayName("[-2,-1,1,2] -> [-2,-1,1,2]")
    public void case4() {
        int[] asteroidCollision = solutionOffer037.asteroidCollision(new int[]{-2,-1,1,2});
        Assertions.assertArrayEquals(asteroidCollision, new int[]{-2,-1,1,2});
    }
    @Test
    @DisplayName("[-2,-2,1,-2] -> [-2,-2,-2]")
    public void case5() {
        int[] asteroidCollision = solutionOffer037.asteroidCollision(new int[]{-2,-2,1,-2});
        Assertions.assertArrayEquals(asteroidCollision, new int[]{-2,-2,-2});
    }
}

/**
 * 使用栈
 * 正的向左，负的向右  左正右负才能相撞
 * 如果第一个是向左，这个就不会被撞。
 * 最后一个向右，那他也不会被撞。
 * 所以遍历集合，
 * 遇到负，与栈中正比较。
 * 遇到正，入栈。
 */
class SolutionOffer037 {
    public int[] asteroidCollision(int[] asteroids) {
        int[] leftMove = new int[asteroids.length];
        int leftIndex = 0;
        Stack<Integer> rightMove = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            int val = asteroids[i];
            if (rightMove.isEmpty() && val < 0) {
                leftMove[leftIndex++] = val;
                continue;
            }
            if (val > 0) {
                rightMove.push(val);
            } else {
                boolean have = true;
                while (!rightMove.isEmpty()) {
                    Integer pre = rightMove.pop();
                    if (pre > -val) {
                        rightMove.push(pre);
                        have = false;
                        break;
                    } else if (pre == -val) {
                        have = false;
                        break;
                    }
                }
                if (have) {
                    leftMove[leftIndex++] = val;
                }
            }
        }
        // 拼接向右和向左
        int ret[] = new int[leftIndex + rightMove.size()];
        System.arraycopy(leftMove, 0, ret, 0, leftIndex);
        int rightIndex = leftIndex + rightMove.size();
        while (!rightMove.isEmpty()) {
            ret[--rightIndex] = rightMove.pop();
        }
        return ret;
    }
}