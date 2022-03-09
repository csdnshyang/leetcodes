package com.shihaiyang.contest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

// 6016. Excel 表中某个范围内的单元格
public class Contest6016 {
    @Test
    public void testcase1() {
        Solution6016 solution6016 = new Solution6016();
        List<String> strings = solution6016.cellsInRange("K1:P3");
        Assertions.assertTrue(strings.equals(List.of("K1")));
    }
}

class Solution6016 {
    public List<String> cellsInRange(String s) {
        String[] strings = s.split(":");
        String start = strings[0];
        String end = strings[1];

        char startChar = start.charAt(0);
        char startNum = start.charAt(1);
        char endChar = end.charAt(0);
        char endNum = end.charAt(1);

        List<String> ret = new ArrayList<>();
        for (int i = 0; i <= (endChar - startChar); i++) {
            for (int j = 0; j <= (endNum - startNum); j++) {
                char c = (char) (startChar + i);
                char n = (char) (startNum + j);
                ret.add(new StringBuffer().append(c).append(n).toString());
            }
        }
        return ret;
    }
}