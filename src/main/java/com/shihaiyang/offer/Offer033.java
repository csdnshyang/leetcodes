package com.shihaiyang.offer;

import java.util.*;

// 剑指 Offer II 033. 变位词组
// Offer033. 变位词组[map+字符数组key 9ms].
public class Offer033 {
    public static void main(String[] args) {
        SolutionOffer033 solutionOffer033 = new SolutionOffer033();
//        List<List<String>> lists = solutionOffer033.groupAnagrams(new String[]{"a"});
        List<List<String>> lists = solutionOffer033.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        lists.stream().forEach(strings -> {
            strings.stream().forEach(s -> System.out.print(s+"."));
            System.out.println();
        });
    }
}

/**
 * 给定一个字符串数组 strs ，将 变位词 组合在一起。 可以按任意顺序返回结果列表。
 * 注意：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * strs[i] 仅包含小写字母
 */

/**
 * map
 */
class SolutionOffer033 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String arrStr = Arrays.toString(chars);
            List<String> list = map.getOrDefault(arrStr, new ArrayList<>());
            list.add(str);
            map.put(arrStr, list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 本来想转成int数组。看了其他人的思路。直接对字符数组排序也可。
     * 而且字符范围大也不需要改程序。
     */
    public int[] change(String string) {
        int arr[] =new int[26];
        char[] chars = string.toCharArray();
        for (char aChar : chars) {
            arr[aChar - 'a']++;
        }
        return arr;
    }
}