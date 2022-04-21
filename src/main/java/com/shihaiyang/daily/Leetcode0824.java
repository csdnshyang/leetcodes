package com.shihaiyang.daily;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// 0824. 山羊拉丁文.[模拟].
/*
给你一个由若干单词组成的句子 sentence ，单词间由空格分隔。每个单词仅由大写和小写英文字母组成。

请你将句子转换为 “山羊拉丁文（Goat Latin）”（一种类似于 猪拉丁文 - Pig Latin 的虚构语言）。山羊拉丁文的规则如下：

如果单词以元音开头（'a', 'e', 'i', 'o', 'u'），在单词后添加"ma"。
例如，单词 "apple" 变为 "applema" 。
如果单词以辅音字母开头（即，非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。
例如，单词 "goat" 变为 "oatgma" 。
根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从 1 开始。
例如，在第一个单词后添加 "a" ，在第二个单词后添加 "aa" ，以此类推。
返回将 sentence 转换为山羊拉丁文后的句子。
示例 1：

输入：sentence = "I speak Goat Latin"
输出："Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
示例 2：

输入：sentence = "The quick brown fox jumped over the lazy dog"
输出："heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 */
public class Leetcode0824 {
    Solution0824 solution0824 = new Solution0824();

    @Test
    public void testcase1() {
        String s = solution0824.toGoatLatin("I speak Goat Latin");
        Assertions.assertEquals(s, "Imaa peaksmaaa oatGmaaaa atinLmaaaaa");
    }
    @Test
    public void testcase2() {
        String s = solution0824.toGoatLatin("The quick brown fox jumped over the lazy dog");
        Assertions.assertEquals(s, "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa");
    }
}
/*
分割
判断每个单词的首字母是否元音，如果非元音，改变顺序
后面加ma后缀
加上i+1个a
 */
class Solution0824 {
    public String toGoatLatin(String sentence) {
        StringBuffer buffer = new StringBuffer();
        String[] split = sentence.split(" ");
        for (int i = 0; i < split.length; i++) {
            char c = split[i].charAt(0);
            if (!isYuan(c)) {
                buffer.append(split[i].substring(1)).append(split[i].substring(0, 1)).append("ma");
            } else {
                buffer.append(split[i]).append("ma");
            }
            for (int j = 0; j <= i; j++) {
                buffer.append("a");
            }
            buffer.append(" ");
        }
        return buffer.toString().trim();
    }

    private boolean isYuan(char c) {
        char[] chars = new char[]{'a', 'e', 'i', 'o', 'u','A', 'E', 'I', 'O', 'U'};
        for (char aChar : chars) {
            if (c == aChar) {
                return true;
            }
        }
        return false;
    }
}
