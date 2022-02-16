package com.shihaiyang.offer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

// Offer II 063. 替换单词.[前缀树6ms].

/**
 * 在英语中，有一个叫做 词根(root) 的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。
 * 例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 * 现在，给定一个由许多词根组成的词典和一个句子，需要将句子中的所有继承词用词根替换掉。
 * 如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 * 需要输出替换之后的句子。
 * <p>
 * 输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 * 输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * 输出："a a b c"
 * 输入：dictionary = ["a", "aa", "aaa", "aaaa"], sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"
 * 输出："a a a a a a a a bbb baba a"
 * 输入：dictionary = ["catt","cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 * 输入：dictionary = ["ac","ab"], sentence = "it is abnormal that this solution is accepted"
 * 输出："it is ab that this solution is ac"
 */
public class Offer063 {
    SolutionOffer063 solutionOffer063 = new SolutionOffer063();

    @Test
    public void case1() {
        String words = solutionOffer063.replaceWords(List.of("cat", "bat", "rat"), "the cattle was rattled by the battery");
        Assertions.assertEquals(words, "the cat was rat by the bat");
    }
    @Test
    public void case2() {
        String words = solutionOffer063.replaceWords(List.of("a","b","c"), "aadsfasf absbs bbab cadsfafs");
        Assertions.assertEquals(words, "a a b c");
    }
    @Test
    public void case3() {
        String words = solutionOffer063.replaceWords(List.of("a", "aa", "aaa", "aaaa"), "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa");
        Assertions.assertEquals(words, "a a a a a a a a bbb baba a");
    }
    @Test
    public void case4() {
        String words = solutionOffer063.replaceWords(List.of("catt","cat","bat","rat"), "the cattle was rattled by the battery");
        Assertions.assertEquals(words, "the cat was rat by the bat");
    }
    @Test
    public void case5() {
        String words = solutionOffer063.replaceWords(List.of("ac","ab"), "it is abnormal that this solution is accepted");
        Assertions.assertEquals(words, "it is ab that this solution is ac");
    }

    @Test
    public void case6() {
        List<String> strings = List.of("e", "k", "c", "harqp", "h", "gsafc", "vn", "lqp", "soy", "mr", "x", "iitgm", "sb", "oo", "spj", "gwmly", "iu", "z", "f", "ha", "vds", "v", "vpx", "fir", "t", "xo", "apifm", "tlznm", "kkv", "nxyud", "j", "qp", "omn", "zoxp", "mutu", "i", "nxth", "dwuer", "sadl", "pv", "w", "mding", "mubem", "xsmwc", "vl", "farov", "twfmq", "ljhmr", "q", "bbzs", "kd", "kwc", "a", "buq", "sm", "yi", "nypa", "xwz", "si", "amqx", "iy", "eb", "qvgt", "twy", "rf", "dc", "utt", "mxjfu", "hm", "trz", "lzh", "lref", "qbx", "fmemr", "gil", "go", "qggh", "uud", "trnhf", "gels", "dfdq", "qzkx", "qxw");
        String sen = "ikkbp miszkays wqjferqoxjwvbieyk rvjxtlqfnlmwdoezh";
        String ac = "i miszkays w rvjxtlqf";
        String replaceWords = solutionOffer063.replaceWords(strings, sen);
        Assertions.assertEquals(ac, replaceWords);
    }

}

/**
 * 构造前缀树
 * 遍历每个字符串的字符，遇到isEnd就返回。
 */
class SolutionOffer063 {
    class TrieNode{
        TrieNode[] kids;
        boolean isValid;
        public TrieNode(){
            kids = new TrieNode[26];
        }
    }

    TrieNode root = new TrieNode();
    public String replaceWords(List<String> dictionary, String sentence) {
        String[] words = new String[dictionary.size()];
        for(int i = 0; i < words.length; ++i) words[i] = dictionary.get(i);
        //建树过程
        for(String word : words){
            insert(root, word);
        }
        String[] strs = sentence.split(" ");
        for(int i = 0; i < strs.length; ++i){
            //如果可以在树中找到对应单词的前缀，那么将这个单词替换为它的前缀
            // 存在前缀与替换前缀判断方式不同
            if(search(root, strs[i])){
                strs[i] = replace(strs[i], root);
            }
        }
        //用StringBuilder来把字符串数组还原成原字符串句子的转换目标字符串
        StringBuilder sb = new StringBuilder();
        for(String s : strs){
            sb.append(s).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    //建前缀树模版
    public void insert(TrieNode root, String s){
        TrieNode node = root;
        for(char ch : s.toCharArray()){
            if(node.kids[ch - 'a'] == null) node.kids[ch - 'a'] = new TrieNode();
            node = node.kids[ch - 'a'];
        }
        node.isValid = true;
    }
    //查询是否存在传入的字符串的前缀
    public boolean search(TrieNode root, String s){
        TrieNode node = root;
        for(char ch : s.toCharArray()){
            if(node.isValid == true) break;
            if(node.kids[ch - 'a'] == null) return false;
            node = node.kids[ch - 'a'];
        }
        return true;
    }
    //将传入的字符串替换为它在前缀树中的前缀字符串
    public String replace(String s, TrieNode root){
        TrieNode node = root;
        StringBuilder sb = new StringBuilder();
        for(char ch : s.toCharArray()){
            if(node.isValid || node.kids[ch - 'a'] == null) break;
            node = node.kids[ch - 'a'];
            sb.append(ch);
        }
        return sb.toString();
    }
}