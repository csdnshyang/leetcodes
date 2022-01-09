package com.shihaiyang.leetcodes;

import java.util.HashMap;
import java.util.Map;
// 14. 最长公共前缀.[最短字串+依次比较字符].
public class Leetcode0014 {
    public static void main(String[] args) {
        Solution0014 solution0014 = new Solution0014();
        String prefix = solution0014.longestCommonPrefix(new String[]{"a"});
        System.out.println(prefix);
    }
}
/***
最短字串+依次比较字符
*/
class Solution0014 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length==0){
            return "";
        }
        int minLen=strs[0].length();
        for(int i=0;i<strs.length;i++){
            if(minLen > strs[i].length())
                minLen=strs[i].length();
        }
        Map<Character, Character> charMap = new HashMap<>();
        int longestCommon=0;
        int found=0;
        for(int i=0;i<minLen;i++){
            charMap.put(strs[0].charAt(i),strs[0].charAt(i));
            for(int j=0;j<strs.length;j++){
                if(!charMap.containsKey(strs[j].charAt(i))){
                    found=1;
                    longestCommon=i;
                    break;
                }
            }
            charMap.remove(strs[0].charAt(i));
            if(found!=0){
                break;
            }
        }
        if (found==0){
            longestCommon=minLen;
        }
        return strs[0].substring(0, longestCommon);
    }
}
