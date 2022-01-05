package com.shihaiyang.leetcodes;

public class Leetcode0013 {
    public static void main(String[] args) {
        Solution0013 solution = new Solution0013();
        int romanToInt = solution.romanToInt("III");
        System.out.println(romanToInt);
    }
}
class Solution0013 {
    public int romanToInt(String s) {
        Integer[] romanInt = new Integer[]{1000,900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanStr = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX","V", "IV", "I"};
        int roman= 0;
        int i=0;
        while(!s.equals("")){
            while(s.startsWith(romanStr[i])){
                roman+=romanInt[i];
                s=s.replaceFirst(romanStr[i], "");
            }
            i++;
        }
        return roman;
    }
}
