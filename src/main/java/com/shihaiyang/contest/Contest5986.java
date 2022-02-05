package com.shihaiyang.contest;

import java.util.ArrayList;
import java.util.List;

// 5986. 设置时间的最少代价.[暴力 1ms].
public class Contest5986 {
    public static void main(String[] args) {
        Solution5986 solution5986 = new Solution5986();
//        int costSetTime = solution5986.minCostSetTime(0, 1,2, 76);
//        int costSetTime = solution5986.minCostSetTime(1, 2, 1, 600);
        int costSetTime = solution5986.minCostSetTime(1, 2, 1, 6016);
//        int costSetTime = solution5986.minCostSetTime(0, 100000, 100000, 6039);
        System.out.println(costSetTime);
    }
}
/**
 * 输入：startAt = 1, moveCost = 2, pushCost = 1, targetSeconds = 600
 * 输出：6
 * 解释：以下为设置加热时间的所有方法。
 * - 1 0 0 0 ，表示 10 分 0 秒。
 *   手指一开始就在数字 1 处，输入 1 （代价为 1），移到 0 处（代价为 2），输入 0（代价为 1），输入 0（代价为 1），输入 0（代价为 1）。
 *   总代价为：1 + 2 + 1 + 1 + 1 = 6 。这是所有方案中的最小代价。
 * - 0 9 6 0，表示 9 分 60 秒。它也表示 600 秒。
 *   手指移到 0 处（代价为 2），输入 0 （代价为 1），移到 9 处（代价为 2），输入 9（代价为 1），移到 6 处（代价为 2），输入 6（代价为 1），移到 0 处（代价为 2），输入 0（代价为 1）。
 *   总代价为：2 + 1 + 2 + 1 + 2 + 1 + 2 + 1 = 12 。
 * - 9 6 0，微波炉自动补全为 0960 ，表示 9 分 60 秒。
 *   手指移到 9 处（代价为 2），输入 9 （代价为 1），移到 6 处（代价为 2），输入 6（代价为 1），移到 0 处（代价为 2），输入 0（代价为 1）。
 *   总代价为：2 + 1 + 2 + 1 + 2 + 1 = 9 。
 * 示例 2：
 * 输入：startAt = 0, moveCost = 1, pushCost = 2, targetSeconds = 76
 * 输出：6
 * 解释：最优方案为输入两个数字 7 6，表示 76 秒。
 * 手指移到 7 处（代价为 1），输入 7 （代价为 2），移到 6 处（代价为 1），输入 6（代价为 2）。总代价为：1 + 2 + 1 + 2 = 6
 * 其他可行方案为 0076 ，076 ，0116 和 116 ，但是它们的代价都比 6 大。
 */

/**
 * 暴力解。
 * 计算每一个分钟秒数对应的值，取最小呗。不知道会不会超时.
 * 跳过一些必然不可的.
 * 从最大的分钟和最小的秒数开始 600 10:00   76 1:16
 * 想复杂了，其实只有最多两种情况1000  960 没了，不可能再有8120这种秒数>100的清空了..
 * 针对这两种情况，取一个最小值即可...
 * 0960 960肯定取960，前缀0直接忽略
 */
class Solution5986 {
    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int minCost = Integer.MAX_VALUE;
        int maxMin = targetSeconds / 60;
        int minSec = targetSeconds % 60;
        if (maxMin == 100) {
            maxMin--;
            minSec += 60;
        }
        List<Integer> all = new ArrayList<>();
        while (maxMin >= 0 && minSec < 100) {
            int val = maxMin * 100 + minSec;
            all.add(val);
            maxMin--;
            minSec += 60;
        }

        for (Integer integer : all) {
            int shuzi[] = new int[4];
            int i = 3;
            while (integer > 0) {
                int a = integer % 10;
                shuzi[i--] = a;
                integer /= 10;
            }
            int copyStartAt = startAt;
            int sum = 0;
            int flag = 0;
            for (int j = 0; j < 4; j++) {
                if (shuzi[j] == 0 && flag == 0) {
                    continue;
                }
                if (copyStartAt == shuzi[j]) {
                    sum += pushCost;
                } else {
                    sum += (pushCost + moveCost);
                }
                copyStartAt = shuzi[j];
                flag = 1;
            }
            minCost = Math.min(sum, minCost);
        }
        return minCost;
    }
}