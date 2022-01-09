/**
找到最大的高度
两边分别计算。
 */
// 42. 接雨水.[最大指针+双指针].
// 有时候1ms,有时候0ms.
/**
执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：38.2 MB, 在所有 Java 提交中击败了31.81%的用户
*/
class Solution {
    public int trap(int[] height) {
      if(height.length<=2){
        return 0;
      }
      int maxIndex=0,maxVal=0;
      // 寻找最大索引
      for(int i=0;i<height.length;i++){
        if(height[maxIndex] < height[i]){
          maxIndex=i;
          maxVal=height[i];
        }
      }
      int sum=0;
      int unuseful=0;
      int i=0,j=i+1;
      // 计算左侧
      while(j<=maxIndex){
        if(height[i] >= height[j]  && j != maxIndex){
          unuseful+=height[j];
        }else{
          int len=j-i-1;
          sum += len*height[i];
          sum -= unuseful;
          unuseful=0;
          i=j;
        }
        j++;
      }
      i=height.length-1;
      j=i-1;
      unuseful=0;
      // 计算右侧
      while(j>=maxIndex){
        if(height[i] >= height[j] && j != maxIndex){
          unuseful+=height[j];
        }else{
          int len=i-j-1;
          sum += (len*height[i]);
          sum -= unuseful;
          unuseful=0;
          i=j;
        }
        j--;
      }
      return sum;
    }
}
