package com.shihaiyang.contest;

import java.util.ArrayList;
import java.util.List;

// 5998. 拆分成最多数目的偶整数之和.[贪心算法].
public class Contest5998 {
}

/**
 * 就是一个贪心算法..
 */
class Solution5998 {
    public List<Long> maximumEvenSplit(long finalSum) {
        if ((finalSum & 1) == 1) {
            return new ArrayList<>();
        }
        List<Long> ret = new ArrayList<>();
        for (long i = 2; i <= finalSum; i += 2) {
            finalSum -= i;
            ret.add(i);
        }
        long l = ret.get(ret.size() - 1) + finalSum;
        ret.set(ret.size() - 1, l);
        return ret;
    }
}
