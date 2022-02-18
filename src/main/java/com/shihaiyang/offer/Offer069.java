package com.shihaiyang.offer;

// Offer II 069. 山峰数组的顶部[二分查找0ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 符合下列属性的数组 arr 称为 山峰数组（山脉数组） ：
 *
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i ，即山峰顶部。
 * 示例 1：
 *
 * 输入：arr = [0,1,0]
 * 输出：1
 * 示例 2：
 *
 * 输入：arr = [1,3,5,4,2]
 * 输出：2
 * 示例 3：
 *
 * 输入：arr = [0,10,5,2]
 * 输出：1
 * 示例 4：
 *
 * 输入：arr = [3,4,5,1]
 * 输出：2
 * 示例 5：
 *
 * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
 * 输出：2
 */
public class Offer069 {
    SolutionOffer069 solutionOffer069 = new SolutionOffer069();

    @Test
    public void case1() {
        int mountainArray = solutionOffer069.peakIndexInMountainArray(new int[]{24, 69, 100, 99, 79, 78, 67, 36, 26, 19});
        Assertions.assertEquals(mountainArray, 2);
    }
    @Test
    public void case2() {
        int mountainArray = solutionOffer069.peakIndexInMountainArray(new int[]{24, 69, 100});
        Assertions.assertEquals(mountainArray, 2);
    }
    @Test
    public void case3() {
        int mountainArray = solutionOffer069.peakIndexInMountainArray(new int[]{100, 99, 79});
        Assertions.assertEquals(mountainArray, 0);
    }
    @Test
    public void case4() {
        int mountainArray = solutionOffer069.peakIndexInMountainArray(new int[]{3,4,5,1});
        Assertions.assertEquals(mountainArray, 2);
    }
}

/**
 * 二分查找。
 * 如果i >i+1 >i-1就是结果
 * 如果i < i-1 ，左移
 * 如果i < i+1 ，右移
 */
class SolutionOffer069 {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (mid == 0 || mid == arr.length - 1 || (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1])) {
                return mid;
            } else if (arr[mid] < arr[mid - 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

