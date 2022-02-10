package com.shihaiyang.offer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//剑指 Offer II 034. 外星语言是否排序
// Offer034. 外星语言是否排序.[暴力+映射 0ms].
/**
 * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
 *示例 1：
 * 输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * 输出：true
 * 解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
 * 示例 2：
 * 输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * 输出：false
 * 解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。
 * 示例 3：
 * 输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * 输出：false
 * 解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小
 */
public class Offer034 {
    @Test
    void case1() {
        SolutionOffer034 solutionOffer034 = new SolutionOffer034();
        boolean alienSorted = solutionOffer034.isAlienSorted(new String[]{"apple","app"}, "abcdefghijklmnopqrstuvwxyz");
        Assertions.assertEquals(alienSorted, false);
    }
    @Test
    void case2() {
        SolutionOffer034 solutionOffer034 = new SolutionOffer034();
        boolean alienSorted = solutionOffer034.isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz");
        Assertions.assertEquals(alienSorted, true);
    }
    @Test
    void case3() {
        SolutionOffer034 solutionOffer034 = new SolutionOffer034();
        boolean alienSorted = solutionOffer034.isAlienSorted(new String[]{"word","world","row"}, "worldabcefghijkmnpqstuvxyz");
        Assertions.assertEquals(alienSorted, false);
    }
}
/**
 * 暴力一个一个比较。比较的时候怎么快速知道大小呢。
 * 把order数组的26个字母的顺序映射都数组中。
 * 比如bac 映射成数组就是 [1,0,2] 即a排1， b排0  c排2
 * 这样出现两个字符串  bac  abc 的时候，依次比较字符，只要看字符在数组中的值大小即可。
 * [b] = 0  [a] = 1   所以 bac < abc
 */
class SolutionOffer034 {
    public boolean isAlienSorted(String[] words, String order) {
        int orderArr[] = new int[26];
        char[] chars = order.toCharArray();
        // 初始化真实字符顺序映射关系
        for (int i = 0; i < 26; i++) {
            orderArr[chars[i] - 'a'] = i;
        }
        // 两两比较.
        for (int i = 0; i < words.length - 1; i++) {
            char[] small = words[i].toCharArray();
            char[] big = words[i + 1].toCharArray();
            int len = Math.min(small.length, big.length);
            int j = 0;
            for (; j < len; j++) {
                if (orderArr[small[j] - 'a'] > orderArr[big[j] - 'a']) {
                    return false;
                } else if (orderArr[small[j] - 'a'] < orderArr[big[j] - 'a']) {
                    break;
                }
            }
            if (len == j && small.length > big.length) {
                return false;
            }
        }
        return true;
    }
}