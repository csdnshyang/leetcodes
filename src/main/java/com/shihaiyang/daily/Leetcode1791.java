package com.shihaiyang.daily;

// 1791. 找出星型图的中心节点[判断0ms].
public class Leetcode1791 {

}
class Solution1791 {
    public int findCenter(int[][] edges) {
        if (edges[0][0] == edges[1][1] || edges[0][0] == edges[1][0]) {
            return edges[0][0];
        } else {
            return edges[0][1];
        }
    }
}