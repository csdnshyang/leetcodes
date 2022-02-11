package com.shihaiyang.offer;
// 剑指 Offer II 039. 直方图最大矩形面积
// Offer039. 直方图最大矩形面积.[单调栈 5ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * 给定非负整数数组 heights ，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * 输入： heights = [2,4]
 * 输出： 4
 * 提示：
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 */
public class Offer039 {
    SolutionOffer039 solutionOffer039 = new SolutionOffer039();

    @Test
    public void case1() {
        int rectangleArea = solutionOffer039.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3});
        Assertions.assertEquals(rectangleArea, 10);
    }
    @Test
    public void case2() {
        int rectangleArea = solutionOffer039.largestRectangleArea(new int[]{2, 4, 1});
        Assertions.assertEquals(rectangleArea, 4);
    }
    @Test
    public void case3() {
        int rectangleArea = solutionOffer039.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3, 11, 12, 2, 1, 4});
        Assertions.assertEquals(rectangleArea, 22);
    }
    @Test
    public void case4() {
        int rectangleArea = solutionOffer039.largestRectangleArea(new int[]{2, 4, 3, 6, 2, 2, 2});
        Assertions.assertEquals(rectangleArea, 14);
    }
    @Test
    public void case5() {
        int rectangleArea = solutionOffer039.largestRectangleArea(new int[]{2, 1, 2});
        Assertions.assertEquals(rectangleArea, 3);
    }
    // [5,4,1,2]
    @Test
    public void case6() {
        int rectangleArea = solutionOffer039.largestRectangleArea(new int[]{5, 4, 1, 2});
        Assertions.assertEquals(rectangleArea, 8);
    }
    // [4,2,0,3,2,5]
    @Test
    public void case7() {
        int rectangleArea = solutionOffer039.largestRectangleArea(new int[]{4,2,0,3,2,5});
        Assertions.assertEquals(rectangleArea, 6);
    }
}

/**
 * 滑动窗口
 * 移动滑动窗口
 * 先获取当前窗口的最小值 min(min, arr[i])
 * 再获取最大值 max(min*(right-left), max)
 * 感觉需要双向的滑动窗口(因为可能在右侧)
 * 好像还是不行,在中间呢？
 *
 * ====================
 * 看题解：单调栈
 * 保证栈递增。
 * 如果新入栈元素比栈顶大，就入栈。
 * 如果新入栈元素比栈顶小，就出栈，并计算；
 * 计算方式，第一个栈顶到比入栈元素小之前的所有出栈元素之间的面积。
 *
 * ==================
 * 单调栈也想错了。。
 * 出栈的时候算法。出栈时候算的是以出栈元素为顶的面积。
 * 栈中元素比出栈元素小。入栈元素也比出栈元素小。但是栈顶元素至入栈元素之间的元素都比栈顶元素大。
 * 所以以出栈元素为顶的面积为(出栈元素出栈后的栈顶到入栈元素之间)
 * 比如栈中 -1, 0, 2, 4    入栈的为6  4的面积应该是  height[4] * (6-2-1) // 因为3，5都比4大。所以要算4要带上3，5
 * height[top] * (i-peek-1)
 */
class SolutionOffer039 {
    public int largestRectangleArea(int[] heights) {
        int ans = 0;
        int[] stack = new int[heights.length];
        int top = -1;
        for (int i = 0; i < heights.length; i++) {
            while (top != -1 && heights[stack[top]] >= heights[i]) {
                // 计算
                int height = heights[stack[top--]];
                int pre = top == -1 ? -1 : stack[top];
                ans = Math.max(ans, (i - pre - 1) * height);
            }
            stack[++top] = i;
        }
        while (top != -1) {
            // 计算
            int height = heights[stack[top--]];
            int pre = top == -1 ? -1 : stack[top];
            ans = Math.max(ans, (heights.length - pre - 1) * height);
        }
        return ans;
    }
}