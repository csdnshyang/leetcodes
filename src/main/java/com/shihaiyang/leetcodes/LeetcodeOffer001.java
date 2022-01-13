package com.shihaiyang.leetcodes;
// Offer001. 整数除法.[减法1722ms].
// https://leetcode-cn.com/problems/xoh6Oh/solution/jian-zhi-offer-ii-001-zheng-shu-chu-fa-j-0sts/
public class LeetcodeOffer001 {
    public static void main(String[] args) {
        SolutionOffer001 solutionOffer001 = new SolutionOffer001();
        int divide = solutionOffer001.divide(7, 3);
        System.out.println(divide);
        divide = solutionOffer001.divide(1, 1);
        System.out.println(divide);
        divide = solutionOffer001.divide(Integer.MIN_VALUE, 2);
        System.out.println(divide);
        divide = solutionOffer001.divide(Integer.MIN_VALUE, -2);
        System.out.println(divide);
    }
}

/**
 * 给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。
 */
class SolutionOffer001 {
    public int divide(int a, int b) {
        int count = 0;
        int flag = 1;
        if (a == 0) {
            return 0;
        }
        if (b == 1) {
            return a;
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        if (b == -1) {
            return -a;
        }
        if (a<0 && b<0 && a>b){
            return 0;
        }
        if (a>0 && b>0 && a<b){
            return 0;
        }

        if (a>0&&b<0){
            flag = -1;
            a=-a;
        }else if (a<0&&b>0){
            flag = -1;
            b=-b;
        }

        if (a > 0 && b > 0) {
            while (a >= b) {
                a -= b;
                count++;
            }
        }else {
            while (a <= b) {
                a -= b;
                count++;
            }
        }
        return flag * count;
    }
}
