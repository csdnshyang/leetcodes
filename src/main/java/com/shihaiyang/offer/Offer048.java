package com.shihaiyang.offer;
//Offer II 048. 序列化与反序列化二叉树[完全二叉树层序遍历 16ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * -1000 <= Node.val <= 1000
 */
public class Offer048 {
    @Test
    public void case1() {
        Codec codec = new Codec();
        String hash = "1,2,3,null,4,5,6,null,null,null,null,null,null,";
        String code = "1,2,3,null,4,5,6";
        Assertions.assertEquals(codec.serialize(codec.deserialize(code)), hash);
    }
    @Test
    public void case2() {
        Codec codec = new Codec();
        String hash = "";
        Assertions.assertEquals(codec.serialize(codec.deserialize(hash)), hash);
    }
    @Test
    public void case3() {
        Codec codec = new Codec();
        String hash = "1,null,null,";
        Assertions.assertEquals(codec.serialize(codec.deserialize(hash)), hash);
    }
    @Test
    public void case4() {
        Codec codec = new Codec();
        String hash = "1,2,null,null,null,";
        Assertions.assertEquals(codec.serialize(codec.deserialize(hash)), hash);
    }
}

