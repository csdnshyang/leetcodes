package com.shihaiyang.offer;

import java.util.Arrays;
import java.util.PriorityQueue;

// 剑指 Offer II 005. 单词长度的最大乘积
// Offer005. 单词长度的最大乘积.[位运算+暴力循环8ms].
public class Offer005 {
    public static void main(String[] args) {
        SolutionOffer005 solutionOffer005 = new SolutionOffer005();
//        int maxProduct = solutionOffer005.maxProduct(new String[]{"abcw", "baz", "foo", "bar", "fxyz", "abcdef"});
//        int maxProduct = solutionOffer005.maxProduct(new String[]{"a","ab","abc","d","cd","bcd","abcd"});
        int maxProduct = solutionOffer005.maxProduct(new String[]{"a","ab","abc","abcd"});
        System.out.println(maxProduct);
    }
}
/**
 * 给定一个字符串数组 words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。
 * 假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
 * 输入: words = ["abcw","baz","foo","bar","fxyz","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "fxyz"。它们不包含相同字符，且长度的乘积最大。
 * 输入: words = ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 */

/**
 * 用二进制位来判断单词是否有重复字符。
 * 将[length，bitMask]二元组放在数组中
 * 暴力二层遍历...
 */
class SolutionOffer005 {
    public int maxProduct(String[] words) {
        // 存储可用的乘积
        int[][] arr = new int[words.length][];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int bitMask = 0;
            for (int j = 0; j < word.length(); j++) {
                int count = word.charAt(j) - 'a' + 1;
                bitMask |= 1 << count;
            }
            arr[i] = new int[]{word.length(), bitMask};
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if ((arr[i][0] & arr[j][0]) == 0) {
                    max = Math.max(max, arr[i][1] * arr[j][1]);
                }
            }
        }
        return max;
    }
}
/**
 * 用二进制位来判断单词是否有重复字符。
 * 将[length，bitMask]二元组放在数组中。排序
 * n路归并
 * 将数组头到尾放入到优先队列
 * n路归并。知道取出一个乘积最大，且不重复的
 */
class SolutionOffer005Priority {
    public int maxProduct(String[] words) {
        // 存储可用的乘积
        int[][] arr = new int[words.length][];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int bitMask = 0;
            for (int j = 0; j < word.length(); j++) {
                int count = word.charAt(j) - 'a' + 1;
                bitMask |= 1 << count;
            }
            arr[i] = new int[]{word.length(), bitMask};
        }
        // 排序
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        // 放入队列
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(arr.length,
                (a, b) -> (arr[b[0]][0] * arr[b[1]][0]) - (arr[a[0]][0] * arr[a[1]][0]));
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.add(new int[]{i, 0});
        }
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            int[] first = arr[poll[0]];
            int[] second = arr[poll[1]];
            if ((first[1] & second[1]) == 0) {
                return first[0] * second[0];
            } else {
                if (poll[1] + 1 < arr.length) {
                    priorityQueue.add(new int[]{poll[0], poll[1] + 1});
                }
            }
        }
        return 0;
    }
}
