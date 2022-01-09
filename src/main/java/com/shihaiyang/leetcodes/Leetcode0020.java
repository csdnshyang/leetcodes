package com.shihaiyang.leetcodes;

import java.util.Stack;
/**
20. 有效的括号
*/
public class Leetcode0020 {
    public static void main(String[] args) {
        Solution0020 solution0020 = new Solution0020();
        boolean valid = solution0020.isValid("()[][}{}");
        System.out.println(valid);
    }
}

/**
 * 利用栈先进先出
 * 执行用时：1 ms, 在所有 Java 提交中击败了98.87%的用户
 * 内存消耗：36.3 MB, 在所有 Java 提交中击败了80.76%的用户
 */
class Solution0020 {
    public boolean isValid(String s) {
        Stack<Character> characterStack = new Stack<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (isStart(c)){
                characterStack.push(c);
            }else {
                if (characterStack.empty()) {
                    return false;
                }
                Character pop = characterStack.pop();
                if (!checkMatch(pop, c)){
                    return false;
                }
            }
        }
        if (!characterStack.empty()){
            return false;
        }
        return true;
    }
    public boolean isStart(char a){
        if(a=='(' || a=='[' || a=='{')return true;
        return false;
    }
    public boolean checkMatch(Character a, char b){
        if(a=='(' && b==')') return true;
        if(a=='[' && b==']') return true;
        if(a=='{' && b=='}') return true;
        return false;
    }
}
