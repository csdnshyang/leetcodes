package com.shihaiyang.daily;

// 1716. 计算力扣银行的钱.[].
public class Leetcode1716 {
    public static void main(String[] args) {
        Solution1716 solution1716 = new Solution1716();
        int totalMoney = solution1716.totalMoney(4);
        System.out.println(totalMoney);
    }
}

/**
 * Hercy 想要为购买第一辆车存钱。他 每天 都往力扣银行里存钱。
 * 最开始，他在周一的时候存入 1 块钱。从周二到周日，他每天都比前一天多存入 1 块钱。在接下来每一个周一，他都会比 前一个周一 多存入 1 块钱。
 * 给你 n ，请你返回在第 n 天结束的时候他在力扣银行总共存了多少块钱。
 * 输入：n = 4
 * 输出：10
 * 解释：第 4 天后，总额为 1 + 2 + 3 + 4 = 10 。
 * 输入：n = 10
 * 输出：37
 * 解释：第 10 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37 。注意到第二个星期一，Hercy 存入 2 块钱。
 */
class Solution1716 {
    public int totalMoney(int n) {
        // 每周打底
        int weekly = 7 * (1 + 7) / 2;
        // 一共有几个满周
        int week = n / 7;
        // 满周的总数
        int fullWeek = (weekly + weekly + (week-1) * 7) * week / 2;

        // 最后一周几天
        int day = n % 7;
        // 最后一周总数
        int lastWeekFirstDay = week+1;
        int lastWeekLastDay = lastWeekFirstDay + day-1;
        int lastWeek = (lastWeekFirstDay+lastWeekLastDay) * day / 2;

        return lastWeek + fullWeek;
    }
}