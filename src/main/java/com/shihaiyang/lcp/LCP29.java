package com.shihaiyang.lcp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// LCP 29. 乐团站位[DFS+边界判断.超时/数学面积公式].
/*
某乐团的演出场地可视作 num * num 的二维矩阵 grid（左上角坐标为 [0,0])，每个位置站有一位成员。
乐团共有 9 种乐器，乐器编号为 1~9，每位成员持有 1 个乐器。
为保证声乐混合效果，成员站位规则为：自 grid 左上角开始顺时针螺旋形向内循环以 1，2，...，9 循环重复排列。
例如当 num = 5 时，站位如图所示

请返回位于场地坐标 [Xpos,Ypos] 的成员所持乐器编号。

示例 1：
输入：num = 3, Xpos = 0, Ypos = 2
输出：3

示例 2：
输入：num = 4, Xpos = 1, Ypos = 2
输出：5

解释：
提示：
1 <= num <= 10^9
0 <= Xpos, Ypos < num
 */
public class LCP29 {
    SolutionLCP29 solutionLCP29 = new SolutionLCP29();

    @Test
    public void testcase1() {
        int layout = solutionLCP29.orchestraLayout(5, 0, 4);
        Assertions.assertEquals(layout, 5);
    }
    @Test
    public void testcase2() {
        int layout = solutionLCP29.orchestraLayout(5, 1, 4);
        Assertions.assertEquals(layout, 6);
    }
    @Test
    public void testcase3() {
        int layout = solutionLCP29.orchestraLayout(5, 1, 3);
        Assertions.assertEquals(layout, 1);
    }
}
/*
就是按 → ↓ ← ↑ → 方向深度遍历。
遍历时候进行计数，如果遇到边界就更换方向。
===居然超出内存了....
模拟一个方格吧...别用真实的二维数组了...
===改成模拟方格
StackOverFlow
===改成非递归
时间超时...行吧。我这个方式就是不行...
======只能用数学方式了
最外层个数 f(n)=4(n-1),想象每一条边占一个对角。
往内一层就去掉了两层，所以只有n-2个了 f(n-2)=4(n-2-1)

面积求差
外部面积-所在圈面积 % 9 +1就等于所在圈的左上角是第几个数。
左上角的坐标是所在圈数i [i-1,i-1]. 比如1圈就是最外圈，左上角[0,0] 第二圈左上角[1,1]
所以总体思路可以尝试
先确定所在圈，第一个坐标，以及第一个坐标对应的计数。
然后做一次dfs即可。一圈就可以找到他到底在哪儿。或者生算也可以算出。
 */
class SolutionLCP29 {
    public int orchestraLayout(int n, int xPos, int yPos) {
        //一共几圈
        int quan=(n+1)/2;
        long num=n;
        //第几圈
        int layer = Math.min(Math.min(yPos,xPos),Math.min( n - xPos - 1, n - yPos - 1))+1;
        //总面积
        long area=num*num;
        //当前所在圈面积
        long zhong=(num-2*(layer-1));
        zhong*=zhong;
        //求差 +1 得到当前圈左上角编号
        long index=(area-zhong)%9+1;
        //右边界
        int right=n-layer;
        //左边界
        int left=layer-1;
        if(xPos==left){
            //在 --- 上
            index+=yPos-left;
        }else if(yPos==right){
            //在   |上
            index+=right-left;
            index+=xPos-left;
        }else if(xPos==right){
            //在 __ 上
            index+=2*(right-left);
            index+=right-yPos;
        }else{
            //在 |  上
            index+=3*(right-left);
            index+=right-xPos;
        }
        return (int)(index%9==0?9:index%9);
    }
}