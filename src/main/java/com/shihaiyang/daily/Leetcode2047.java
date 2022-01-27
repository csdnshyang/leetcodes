package com.shihaiyang.daily;

// 2047. 句子中的有效单词数.[计数2ms].
public class Leetcode2047 {
    public static void main(String[] args) {
        Solution2047 solution2047 = new Solution2047();
//        int countValidWords = solution2047.countValidWords("!");
//        int countValidWords = solution2047.countValidWords(". ! 7hk  al6 l! aon49esj35la k3 7u2tkh  7i9y5  !jyylhppd et v- h!ogsouv 5");
        int countValidWords = solution2047.countValidWords("cat");
        System.out.println(countValidWords);
    }
}

/**
 * 句子仅由小写字母（'a' 到 'z'）、数字（'0' 到 '9'）、连字符（'-'）、标点符号（'!'、'.' 和 ','）以及空格（' '）组成。每个句子可以根据空格分解成 一个或者多个 token ，这些 token 之间由一个或者多个空格 ' ' 分隔。
 * 如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：
 * 仅由小写字母、连字符和/或标点（不含数字）。
 * 至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 不是有效单词）。
 * 至多一个 标点符号。如果存在，标点符号应当位于 token 的 末尾 。
 * 这里给出几个有效单词的例子："a-b."、"afad"、"ba-c"、"a!" 和 "!" 。
 * 给你一个字符串 sentence ，请你找出并返回 sentence 中 有效单词的数目 。
 * 示例 1：
 * 输入：sentence = "cat and  dog"
 * 输出：3
 * 解释：句子中的有效单词是 "cat"、"and" 和 "dog"
 * 示例 2：
 * 输入：sentence = "!this  1-s b8d!"
 * 输出：0
 * 解释：句子中没有有效单词
 * "!this" 不是有效单词，因为它以一个标点开头
 * "1-s" 和 "b8d" 也不是有效单词，因为它们都包含数字
 * 输入：sentence = "he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."
 * 输出：6
 * 解释：句子中的有效单词是 "he"、"bought"、"pencils,"、"erasers,"、"and" 和 "pencil-sharpener."
 */

/**
 * 首字母要是英文字母
 * 中间最多一个连字符，包含数字不是单词。
 * 最后可以是字母数字或者标点
 * 不符合的情况：
 * 1. 首字母不是字符或者标点  - 1
 * 2. 包含数字  2
 * 3. 两个标点或者间隔符  a.. b--
 * 4. 一个标点一个间隔符，但位置不对   a-.
 * 5. 一个标点，但位置不对  a.a
 * 6. 一个间隔符，但位置不对  a-
 */
class Solution2047 {
    public int countValidWords(String sentence) {
        char[] chars = sentence.toCharArray();
        int i = 0;
        int validWords = 0;
        while (i < chars.length) {
            // 跳过空格
            while (i < chars.length && chars[i] == ' ') {
                i++;
            }
            // 超出长度跳出
            if (i >= chars.length) {
                break;
            }
            boolean valid = true;
            // check 0:间隔符个数  1:标点个数  2:数字个数
            int check[] = new int[]{0, 0, 0};
            // 首字母不是字母或者标点，不符合
            if (!(isZifu(chars[i]) || isBiaodian(chars[i]))) {
                valid = false;
            }
            while (i < chars.length && chars[i] != ' ') {
                // 包含数字，不符合
                if (isJiange(chars[i])) {
                    check[0]++;
                }else if (isBiaodian(chars[i])) {
                    check[1]++;
                }else if (isShuzi(chars[i])) {
                    check[2]++;
                }
                i++;
            }
            // 标点或者-个数超1，不符合
            if (check[2] > 0) {
                valid = false;
            }else if (check[0] > 1 || check[1] > 1) {
                valid = false;
            } else if (check[0] == 1 && check[1] == 1) {
                // 一个标点一个-，如果最后一个不是标点或者标点前不是字符，不符合
                if (!isBiaodian(chars[i - 1]) || !isZifu(chars[i - 2])) {
                    valid = false;
                }
                // 如果只有间隔符，且最后一个字符是间隔符，不符合
            } else if (check[0] == 1 && chars[i - 1] == '-') {
                valid = false;
                // 如果只有标点，且最后一个字符不是标点，不符合
            } else if (check[1] == 1 && !isBiaodian(chars[i - 1])) {
                valid = false;
            }
            if (valid) {
                validWords++;
            }
        }
        return validWords;
    }
    private boolean isJiange(int aChar) {
        return aChar == '-';
    }
    private boolean isShuzi(char aChar) {
        return aChar >= '0' && aChar <= '9';
    }
    private boolean isBiaodian(char aChar) {
        return aChar == ',' || aChar == '.' || aChar == '!';
    }
    public boolean isZifu(char c) {
        return c >= 'a' && c <= 'z';
    }
}