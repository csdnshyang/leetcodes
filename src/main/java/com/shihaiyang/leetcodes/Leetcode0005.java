// 05. 最长回文子串.[双指针].
// 修改账号
class Solution {
    public String longestPalindrome(String s) {
      if(s.length() <= 1){
          return s;
      }
      int maxLen=0,start=0,end=0;
      int left=0,right=0;
      for(int index=0;index<s.length()-1;index++){
        left=index;
        right=index;
        for(left=index,right=index;(left-1)>=0 && (right+1)<s.length() && s.charAt(left-1) == s.charAt(right+1);left--,right++);
        int length = right-left;
        if(maxLen<length){
          maxLen=length;
          start=left;
          end=right;
        }
        if(s.charAt(index) == s.charAt(index+1)){
          for(left=index,right=index+1;(left-1)>=0 && (right+1)<s.length() && s.charAt(left-1) == s.charAt(right+1);left--,right++);
          length = right-left;
          if(maxLen<length){
            maxLen=length;
            start=left;
            end=right;
          }
        }
      }
      return s.substring(start, end+1);
    }
}
