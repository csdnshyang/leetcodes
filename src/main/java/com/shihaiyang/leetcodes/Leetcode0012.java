package com.shihaiyang.leetcodes;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 0012. 整数转罗马数字.[贪心算法+双数组(900/400/90/40/9/4)].
public class Leetcode0012 {
    public static void main(String[] args) {
        Solution00122 solution = new Solution00122();
        String roman = solution.intToRoman(2998);
        System.out.println(roman);
    }
}

/**
 * 贪心算法.
 * 即每次选择最大的。
 */
class Solution00122 {
    public String intToRoman(int num) {
        Integer[] romanInt = new Integer[]{1000,900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanStr = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX","V", "IV", "I"};
        String roman = "";
        int i=0;
        while(i<13){
            while (num>=romanInt[i]){
                roman+=romanStr[i];
                num-=romanInt[i];
            }
            i++;
        }
        return roman;
    }
}

class Solution0012 {
    public String intToRoman(int num) {
        Map<Integer, Map<Integer, String>> map = getMap();
        String roman = "";
        int flag=1;
        while (flag <= 3){
            int curr = num % 10;
            switch (curr) {
                case 9:
                    roman = map.get(flag).get(1)+map.get(flag).get(10)+roman;
                    break;
                case 4:
                    roman = map.get(flag).get(1)+map.get(flag).get(5)+roman;
                    break;
                case 5:
                    roman = map.get(flag).get(5)+ roman;
                    break;
                default:
                    if (curr > 5) {
                        curr = curr - 5;
                        for (int i = 0; i < curr; i++) {
                            roman = map.get(flag).get(1) + roman;
                        }
                        roman = map.get(flag).get(5) + roman;
                    } else {
                        for (int i = 0; i < curr; i++) {
                            roman = map.get(flag).get(1) + roman;
                        }
                    }

            }
            num = num / 10;
            flag++;
        }
        for(int i=0; i<num;i++){
            roman=map.get(3).get(10)+roman;
        }
        return roman;
    }

    public static Map<Integer, Map<Integer, String>> getMap(){
        Map<Integer, String> map1 = Stream.of(new Object[][]{
                {1, "I"},
                {5, "V"},
                {10, "X"}
        }).collect(Collectors.toMap(d->(Integer)d[0], d->(String)d[1]));
        Map<Integer, String> map2 = Stream.of(new Object[][]{
                {1, "X"},
                {5, "L"},
                {10, "C"}
        }).collect(Collectors.toMap(d->(Integer)d[0], d-> (String)d[1]));
        Map<Integer, String> map3 = Stream.of(new Object[][]{
                {1, "C"},
                {5, "D"},
                {10, "M"}
        }).collect(Collectors.toMap(d->(Integer)d[0], d->(String)d[1]));

        Map<Integer, Map<Integer, String>> map = Stream.of(new Object[][]{
                {1, map1},
                {2, map2},
                {3, map3}
        }).collect(Collectors.toMap(d->(Integer)d[0], d->(Map<Integer, String>)d[1]));
        return map;
    }
}
