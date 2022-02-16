package com.shihaiyang.offer;
// Offer II 060. 出现频率最高的 k 个数字[小顶堆+map 12ms 快排+map12ms].

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给定一个整数数组 nums 和一个整数 k ，请返回其中出现频率前 k 高的元素。可以按 任意顺序 返回答案。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 */
public class Offer060 {
    SolutionOffer060Quick solutionOffer060 = new SolutionOffer060Quick();

    @Test
    public void case1() {
        int[] ints = solutionOffer060.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        ArrayList<Integer> collect = Arrays.stream(ints).collect(ArrayList::new, ArrayList::add,
                ArrayList::addAll);
        Assertions.assertTrue(new HashSet<>(collect).equals(Set.of(1,2)));
    }
    @Test
    public void case2() {
        int[] ints = solutionOffer060.topKFrequent(new int[]{1}, 1);
        Assertions.assertArrayEquals(ints, new int[]{1});
    }
    @Test
    public void case3() {
        int[] ints = solutionOffer060.topKFrequent(new int[]{5,3,1,1,1,3,73,1}, 1);
        Assertions.assertArrayEquals(ints, new int[]{1});
    }

}

/**
 * map+优先队列
 */
class SolutionOffer060 {
    Map<Integer, Integer> map = new HashMap<>();
    PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    public int[] topKFrequent(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int count = map.getOrDefault(nums[i], 0) + 1;
            map.put(nums[i], count);
        }
        for (Map.Entry<Integer, Integer> ent : map.entrySet()) {
            queue.add(new int[]{ent.getKey(), ent.getValue()});
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int[] ret = new int[k];
        while (!queue.isEmpty()) {
            ret[--k] = queue.poll()[0];
        }
        return ret;
    }
}

/**
 * 基于map+ 简化快排
 * 快排就是左侧小于nums[i],右侧大于；右侧的个数=k就满足条件。
 * 如果右侧<k说明有一部分在左侧。左侧还有，对左侧快排；k-右侧len
 * 如果右侧>k说明右侧有一部分不是结果，对右侧快排；还是找k
 * 就是把右侧<=k元素返回。
 */
class SolutionOffer060Quick {
    Map<Integer, Integer> map = new HashMap<>();
    int[] ret;
    int retIndex = 0;
    Random random = new Random();

    public int[] topKFrequent(int[] nums, int k) {
        ret = new int[k];
        for (int i = 0; i < nums.length; i++) {
            int count = map.getOrDefault(nums[i], 0) + 1;
            map.put(nums[i], count);
        }

        List<int[]> all = new ArrayList<>(map.size());
        for (Map.Entry<Integer, Integer> ent : map.entrySet()) {
            all.add(new int[]{ent.getKey(), ent.getValue()});
        }
        quickSort(all, 0, map.size() - 1, k);
        return ret;
    }

    /**
     *  快排思路 随机一个picked。与left交换。 作为pivot
     *  从left+1开始，对比与picked的值大小，index标记左侧大于pivot，可以与右侧出现的小于pivot交换的位置  如果从小到大排序，
     *      如果i < pivot说明需要把i移到pivot前面    交换index和i；index++表示index已经小于pivot，从下一个元素开始交换；
     *      否则跳过，说明此元素大于pivot。如果i遍历到了小于pivot的话，就与该元素交换。目前该元素的index存储了
     *  遍历完成就完成第一轮排序，排序后再把left与index交换
     *  这样就index左侧全部小于pivot，右侧全部大于pivot
     *  递归快排的时候，计划好pivot元素应该算在哪儿。
     *      如果算在右侧的话，quickSort(left, index-1) quickSort(index, right)
     *      如果算在左边的话，quickSort(left, index) quickSort(index+1, right)
     */

    public void quickSort(List<int[]> all, int left, int right, int k) {
        if (k == 0) {
            return;
        }
        // picked 算出随机值要加left
        int picked = random.nextInt(right - left + 1) + left;
        Collections.swap(all, left, picked);

        int pivot = all.get(left)[1];
        int index = left;
        for (int i = left + 1; i <= right; i++) {
            // 小于就交换 从小到大排序
            if (all.get(i)[1] < pivot) {
                Collections.swap(all, ++index, i);
            }
        }
        // 把pivot交换到中间
        Collections.swap(all, index, left);
        // 右侧的个数 (算自己) size=4   right=3 index=1  rightLen = 3-1+1 = 3个
        int rightLen = right - index + 1;
        // 右侧个数大于k，说明右侧中有k个是结果，右侧再快排
        if (rightLen > k) {
            quickSort(all, index, right, k);
        } else {
            // 如果右侧<=k,说明需要把右侧的返回(算自己 right-index+1 3-1+1),并且左侧再快排出 k-rightLen个
            for (int i = index; i <= right; i++) {
                ret[retIndex++] = all.get(i)[0];
            }
            quickSort(all, left, index - 1, k - rightLen);
        }
    }
}