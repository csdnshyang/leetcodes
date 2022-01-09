package com.shihaiyang.leetcodes;
//13. 罗马数字转整数.[预检查一位+判断+-].
public class Leetcode0013 {
    public static void main(String[] args) {
        Solution00134 solution = new Solution00134();
        int romanToInt = solution.romanToInt("III");
        System.out.println(romanToInt);
    }
}

/**
 * 别人思路
 * 在遍历时，往后检查一位，如果前大于后：+ 如果前小于后：-
 */
class Solution00134 {
    public int romanToInt(String s){
        int roman=0;
        for(int i=0;i<s.length();i++){
            int value = parseChar(s.charAt(i));
            int nextValue=0;
            if(i+1==s.length()){
                nextValue=0;
            }else {
                nextValue=parseChar(s.charAt(i+1));
            }
            if (value>=nextValue){
                roman+=value;
            }else {
                roman-=value;
            }
        }
        return roman;
    }
    public int parseChar(char c){
        switch (c){
            case 'M': return 1000;
            case 'D': return 500;
            case 'C': return 100;
            case 'L': return 50;
            case 'X': return 10;
            case 'V': return 5;
            case 'I': return 1;
            default: return 0;
        }
    }
}
/**
 * 其他人的方案
 * 替换 4 9 40 90 400 900 的罗马字符为唯一字符. 属于骚操作了
 */
class Solution00133 {
    public int romanToInt(String s){
        s=s.replace("CM", "a");
        s=s.replace("CD", "b");
        s=s.replace("XC", "c");
        s=s.replace("XL", "d");
        s=s.replace("IX", "e");
        s=s.replace("IV", "f");
        int roman=0;
        for(int i=0;i<s.length();i++){
            roman+=parseChar(s.charAt(i));
        }
        return roman;
    }
    public int parseChar(char c){
        switch (c){
            case 'M': return 1000;
            case 'a': return 900;
            case 'D': return 500;
            case 'b': return 400;
            case 'C': return 100;
            case 'c': return 90;
            case 'L': return 50;
            case 'd': return 40;
            case 'X': return 10;
            case 'e': return 9;
            case 'V': return 5;
            case 'f': return 4;
            case 'I': return 1;
            default: return 0;
        }
    }
}

/**
 * 换成用字符操作
 */
class Solution00132 {
    public int romanToInt(String s) {
        int roman= 0;
        int i=0;
        while(i<s.length()){
            if(s.charAt(i) == 'M'){
                roman+=1000;
                i++;
            }else if(i+1<s.length() && s.charAt(i)=='C' && s.charAt(i+1) == 'M'){
                roman+=900;
                i+=2;
            }else if(s.charAt(i) == 'D'){
                roman+=500;
                i++;
            }else if(i+1<s.length() && s.charAt(i)=='C' && s.charAt(i+1) == 'D'){
                roman+=400;
                i+=2;
            }else if(s.charAt(i) == 'C'){
                roman+=100;
                i++;
            }else if(i+1<s.length() && s.charAt(i)=='X' && s.charAt(i+1) == 'C'){
                roman+=90;
                i+=2;
            }else if(s.charAt(i) == 'L'){
                roman+=50;
                i++;
            }else if(i+1<s.length() && s.charAt(i)=='X' && s.charAt(i+1) == 'L'){
                roman+=40;
                i+=2;
            }else if(s.charAt(i) == 'X'){
                roman+=10;
                i++;
            }else if(i+1<s.length() && s.charAt(i)=='I' && s.charAt(i+1) == 'X'){
                roman+=9;
                i+=2;
            }else if(s.charAt(i) == 'V'){
                roman+=5;
                i++;
            }else if(i+1<s.length() && s.charAt(i)=='I' && s.charAt(i+1) == 'V'){
                roman+=4;
                i+=2;
            }else if(s.charAt(i) == 'I'){
                roman+=1;
                i++;
            }
        }
        return roman;
    }
}
/**
 * 字符串操作比较耗时
 */
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
