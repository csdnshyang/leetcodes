package com.shihaiyang.contest;

import java.util.BitSet;

// 6002. 设计位集
public class Contest6002 {
    public static void main(String[] args) {
        Bitset bs = new Bitset(2); // bitset = "00000".
        bs.flip();
        bs.unfix(1);
        bs.fix(1);     // 将 idx = 1 处的值更新为 1 ，此时 bitset = "01010" 。
        bs.fix(1);     // 将 idx = 1 处的值更新为 1 ，此时 bitset = "01010" 。
        bs.unfix(1);
        System.out.println(bs.all());      // 返回 False ，bitset 中的值不全为 1 。
        System.out.println(bs.count());    // 返回 2 ，当前有 2 位的值为 1 。
        System.out.println(bs.toString()); // 返回 "01010" ，即 bitset 的当前组成情况。

//        bs.fix(3);     // 将 idx = 3 处的值更新为 1 ，此时 bitset = "00010" 。
//        bs.flip();     // 翻转每一位上的值，此时 bitset = "10101" 。
//        bs.unfix(0);   // 将 idx = 0 处的值更新为 0 ，此时 bitset = "00101" 。
//        bs.flip();     // 翻转每一位上的值，此时 bitset = "11010" 。
//        System.out.println(bs.one());      // 返回 True ，至少存在一位的值为 1 。
//        bs.unfix(0);   // 将 idx = 0 处的值更新为 0 ，此时 bitset = "01010" 。
    }
}

/**
 * 请你实现 Bitset 类。
 *
 * Bitset(int size) 用 size 个位初始化 Bitset ，所有位都是 0 。
 * void fix(int idx) 将下标为 idx 的位上的值更新为 1 。如果值已经是 1 ，则不会发生任何改变。
 * void unfix(int idx) 将下标为 idx 的位上的值更新为 0 。如果值已经是 0 ，则不会发生任何改变。
 * void flip() 翻转 Bitset 中每一位上的值。换句话说，所有值为 0 的位将会变成 1 ，反之亦然。
 * boolean all() 检查 Bitset 中 每一位 的值是否都是 1 。如果满足此条件，返回 true ；否则，返回 false 。
 * boolean one() 检查 Bitset 中 是否 至少一位 的值是 1 。如果满足此条件，返回 true ；否则，返回 false 。
 * int count() 返回 Bitset 中值为 1 的位的 总数 。
 * String toString() 返回 Bitset 的当前组成情况。注意，在结果字符串中，第 i 个下标处的字符应该与 Bitset 中的第 i 位一致。
 * 示例：
 * 输入
 * ["Bitset", "fix", "fix", "flip", "all", "unfix", "flip", "one", "unfix", "count", "toString"]
 * [[5], [3], [1], [], [], [0], [], [], [0], [], []]
 * 输出
 * [null, null, null, null, false, null, null, true, null, 2, "01010"]
 *
 * 解释
 * Bitset bs = new Bitset(5); // bitset = "00000".
 * bs.fix(3);     // 将 idx = 3 处的值更新为 1 ，此时 bitset = "00010" 。
 * bs.fix(1);     // 将 idx = 1 处的值更新为 1 ，此时 bitset = "01010" 。
 * bs.flip();     // 翻转每一位上的值，此时 bitset = "10101" 。
 * bs.all();      // 返回 False ，bitset 中的值不全为 1 。
 * bs.unfix(0);   // 将 idx = 0 处的值更新为 0 ，此时 bitset = "00101" 。
 * bs.flip();     // 翻转每一位上的值，此时 bitset = "11010" 。
 * bs.one();      // 返回 True ，至少存在一位的值为 1 。
 * bs.unfix(0);   // 将 idx = 0 处的值更新为 0 ，此时 bitset = "01010" 。
 * bs.count();    // 返回 2 ，当前有 2 位的值为 1 。
 * bs.toString(); // 返回 "01010" ，即 bitset 的当前组成情况。
 */
class Bitset {
    BitSet bitSet;
    int size;
    int index;
    int count = 0;
    public Bitset(int size) {
        bitSet = new BitSet(size);
        this.size = size;
        this.index = size - 1;
    }

    // void fix(int idx) 将下标为 idx 的位上的值更新为 1 。如果值已经是 1 ，则不会发生任何改变。
    public void fix(int idx) {
        if (!bitSet.get(index - idx)) {
            count++;
        }
        bitSet.set(index-idx);
    }

    // void unfix(int idx) 将下标为 idx 的位上的值更新为 0 。如果值已经是 0 ，则不会发生任何改变。
    public void unfix(int idx) {
        if (bitSet.get(index - idx)) {
            count--;
        }
        bitSet.clear(index-idx);
    }
    // void flip() 翻转 Bitset 中每一位上的值。换句话说，所有值为 0 的位将会变成 1 ，反之亦然。
    public void flip() {
        count = size - count;
        bitSet.flip(0, size);
    }
    // boolean all() 检查 Bitset 中 每一位 的值是否都是 1 。如果满足此条件，返回 true ；否则，返回 false 。
    public boolean all() {
        return count == size;
    }

    // boolean one() 检查 Bitset 中 是否 至少一位 的值是 1 。如果满足此条件，返回 true ；否则，返回 false 。
    public boolean one() {
        return count > 0;
    }
    // int count() 返回 Bitset 中值为 1 的位的 总数 。
    public int count() {
        return count;
    }

    // String toString() 返回 Bitset 的当前组成情况。注意，在结果字符串中，第 i 个下标处的字符应该与 Bitset 中的第 i 位一致。
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (bitSet.get(i)) {
                sb.insert(0, 1);
            } else {
                sb.insert(0, 0);
            }
        }
        return sb.toString();
    }
}

/**
 * Your Bitset object will be instantiated and called as such:
 * Bitset obj = new Bitset(size);
 * obj.fix(idx);
 * obj.unfix(idx);
 * obj.flip();
 * boolean param_4 = obj.all();
 * boolean param_5 = obj.one();
 * int param_6 = obj.count();
 * String param_7 = obj.toString();
 */