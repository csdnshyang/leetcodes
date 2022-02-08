package com.shihaiyang.contest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//5995. 字符串分组[并查集+位运算 1s]
public class Leetcode5995 {
    public static void main(String[] args) {
        Solution5995 solution5995 = new Solution5995();
//        int[] groupStrings = solution5995.groupStrings(new String[]{"web","a","te","hsx","v","k","a","roh"});
        int[] groupStrings = solution5995.groupStrings(new String[]{"web","web","web"});
//        int[] groupStrings = solution5995.groupStrings(new String[]{"a", "b", "ab", "cde"});
        System.out.println(Arrays.toString(groupStrings));
    }
}

/**
 * 给你一个下标从 0 开始的字符串数组 words 。每个字符串都只包含 小写英文字母 。words 中任意一个子串中，每个字母都至多只出现一次。
 * 如果通过以下操作之一，我们可以从 s1 的字母集合得到 s2 的字母集合，那么我们称这两个字符串为 关联的 ：
 * 往 s1 的字母集合中添加一个字母。
 * 从 s1 的字母集合中删去一个字母。
 * 将 s1 中的一个字母替换成另外任意一个字母（也可以替换为这个字母本身）。
 * 数组 words 可以分为一个或者多个无交集的 组 。如果一个字符串与另一个字符串关联，那么它们应当属于同一个组。
 * 注意，你需要确保分好组后，一个组内的任一字符串与其他组的字符串都不关联。可以证明在这个条件下，分组方案是唯一的。
 * 请你返回一个长度为 2 的数组 ans ：
 * ans[0] 是 words 分组后的 总组数 。
 * ans[1] 是字符串数目最多的组所包含的字符串数目。
 * 输入：words = ["a","b","ab","cde"]
 * 输出：[2,3]
 * 解释：
 * - words[0] 可以得到 words[1] （将 'a' 替换为 'b'）和 words[2] （添加 'b'）。所以 words[0] 与 words[1] 和 words[2] 关联。
 * - words[1] 可以得到 words[0] （将 'b' 替换为 'a'）和 words[2] （添加 'a'）。所以 words[1] 与 words[0] 和 words[2] 关联。
 * - words[2] 可以得到 words[0] （删去 'b'）和 words[1] （删去 'a'）。所以 words[2] 与 words[0] 和 words[1] 关联。
 * - words[3] 与 words 中其他字符串都不关联。
 * 所以，words 可以分成 2 个组 ["a","b","ab"] 和 ["cde"] 。最大的组大小为 3
 */

/**
 * 字符串改为位运算
 * 1. 添加或者删除  用异或  添加：0^1=1     删除：1^1=0
 * 2. 替换一个  替换比较复杂
 * 先找到哪一位是存在的  n>>i==1 说明第i位是存在的 比如 110100>>2=1101; 1101&1==1; => 能说明第2(从0开始)位==1，即存在。
 * 再把第2位替换成其他的位
 * 移除第2位用异或  110100^100=110000;
 * 替换成其他的任意以为用或运算  110000|1=110001;替换成第0位;  110000|10=110010;替换成第1位;
 * 所以替换的完整操作为   n^(n>>i) | (1<<j)  // i=2;j=[0..26)
 * 并查集 把能匹配到的字符，合并到一起。
 * 1 2 3 4 5 6
 * 1 2 3 4 5 6
 * merge(1,2) => 把1合并到2中 ，把2的父设置为1的父。如果查到的不与自身相等说明被合并了，要递归查，直到查到与本身想等。
 * find(1)=1;  find(2)2; => set(1,2);
 * 2 2 3 4 5 6
 * 1 2 3 4 5 6
 * find(1)=1;  find(2)=1 != 2; => find(find(2))=1;说明查到了2的并查集是谁。
 * merge(3,1) => 把3合并到1中，把1的父设置为3的父
 * find(3)=3; find(1)=2 != 1; => find(find(1))=2;  set(3,2);// 1得父是2，设置3的父为2
 * 2 2 2 4 5 6
 * 1 2 3 4 5 6
 * merge(1,4) => 把1合并到4中，把4的父设置为1的父.  1的父直接找到2，再把2的父设置为4.则所有2的子的父统一变成了4。
 * find(1)=2!=1; find(find(1))=2; find(4)=4;  set(2,4);
 * 2 4 2 4 5 6
 * 1 2 3 4 5 6
 */
class Solution5995 {
    public int[] groupStrings(String[] words) {
        UnionFind unionFind = new UnionFind();
        for (String word : words) {
            unionFind.add(wordToInt(word));
        }
        unionFind.merge();
        return new int[]{unionFind.count, unionFind.maxGroup};
    }

    public int wordToInt(String word) {
        char[] chars = word.toCharArray();
        int ret = 0;
        for (char aChar : chars) {
            int i = aChar - 'a';
            ret |= (1 << i);
        }
        return ret;
    }
}

/**
 * 并查集模板
 */
class UnionFind {
    Map<Integer, Integer> map = new HashMap<>(), size = new HashMap<>();
    int count = 0;
    int maxGroup = 0;

    /**
     * 合并操作
     */
    public void merge() {
        for (Integer x : map.keySet()) {
            for (int i = 0; i < 26; i++) {
                int y = x ^ (1 << i);
                // 判断添加或者删除一个字符是否有匹配
                merge(x, y);
                if (((x >> i) & 1) == 1) {
                    for (int k = 0; k < 26; k++) {
                        int y2 = x ^ (1 << i) | (1 << k);
                        // 判断替换某一位是否匹配
                        merge(x, y2);
                    }
                }
            }
        }
    }

    /**
     * 集
     * 初始化并查集map
     * @param x
     */
    public void add(int x) {
        if (!map.containsKey(x)) {
            map.put(x, x);
            count++;
        }
        size.put(x, size.getOrDefault(x, 0) + 1);
        maxGroup = Math.max(maxGroup, size.get(x));
    }

    /**
     * 查
     * 查询x的父级
     * @param x
     * @return
     */
    public int find(int x) {
        if (map.get(x) != x) {
            map.put(x, find(map.get(x)));
        }
        return map.get(x);
    }

    /**
     * 并
     * 合并x和y。设置y为x的父级。
     * @param x
     * @param y
     */
    public void merge(int x, int y) {
        if (!map.containsKey(y)) {
            return;
        }
        x = find(x);
        y = find(y);
        if (x == y) {
            return;
        }
        count--;
        // 1,1  2,1  1的父设置成了2  2就有2个
        map.put(x, y);
        Integer sizex = size.getOrDefault(x, 0);
        Integer sizey = size.getOrDefault(y, 0);
        size.put(y, sizex + sizey);
        maxGroup = Math.max(maxGroup, sizex + sizey);
    }
}