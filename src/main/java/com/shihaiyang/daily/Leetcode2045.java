package com.shihaiyang.daily;

import java.util.*;

// 2045. 到达目的地的第二短时间.[BFS].
public class Leetcode2045 {
    public static void main(String[] args) {
        Solution2045 solution2045 = new Solution2045();
        int[][] edges = new int[][]{
                {1,2},
                {1,3},
                {1,4},
                {3,4},
                {4,5}
        };
        int secondMinimum = solution2045.secondMinimum(5, edges, 3, 5);
//        int secondMinimum = solution2045.secondMinimum(2, new int[][]{{1,2}}, 3, 2);
//        int secondMinimum = solution2045.secondMinimum(2, new int[][]{{1,2}}, 1, 2);
        System.out.println(secondMinimum);
    }
}

/**
 * 城市用一个 双向连通 图表示，图中有 n 个节点，从 1 到 n 编号（包含 1 和 n）。图中的边用一个二维整数数组 edges 表示，其中每个 edges[i] = [ui, vi] 表示一条节点 ui 和节点 vi 之间的双向连通边。每组节点对由 最多一条 边连通，顶点不存在连接到自身的边。穿过任意一条边的时间是 time 分钟。
 * 每个节点都有一个交通信号灯，每 change 分钟改变一次，从绿色变成红色，再由红色变成绿色，循环往复。所有信号灯都 同时 改变。你可以在 任何时候 进入某个节点，但是 只能 在节点 信号灯是绿色时 才能离开。如果信号灯是  绿色 ，你 不能 在节点等待，必须离开。
 * 第二小的值 是 严格大于 最小值的所有值中最小的值。
 * 例如，[2, 3, 4] 中第二小的值是 3 ，而 [2, 2, 4] 中第二小的值是 4 。
 * 给你 n、edges、time 和 change ，返回从节点 1 到节点 n 需要的 第二短时间 。
 * 注意：
 * 你可以 任意次 穿过任意顶点，包括 1 和 n 。
 * 你可以假设在 启程时 ，所有信号灯刚刚变成 绿色 。
 *
 * 输入：n = 5, edges = [[1,2],[1,3],[1,4],[3,4],[4,5]], time = 3, change = 5
 * 输出：13
 * 解释：
 * 上面的左图展现了给出的城市交通图。
 * 右图中的蓝色路径是最短时间路径。
 * 花费的时间是：
 * - 从节点 1 开始，总花费时间=0
 * - 1 -> 4：3 分钟，总花费时间=3
 * - 4 -> 5：3 分钟，总花费时间=6
 * 因此需要的最小时间是 6 分钟。
 *
 * 右图中的红色路径是第二短时间路径。
 * - 从节点 1 开始，总花费时间=0
 * - 1 -> 3：3 分钟，总花费时间=3
 * - 3 -> 4：3 分钟，总花费时间=6
 * - 在节点 4 等待 4 分钟，总花费时间=10
 * - 4 -> 5：3 分钟，总花费时间=13
 * 因此第二短时间是 13 分钟。
 */

/**
 * 广度优先 BFS
 * 求路径的方式  一次绿灯一次红灯   step % (2*change) > change  说明进入了红灯了。必须要等红灯的时间：2*change - [step % (2*change)]
 * 每一步是3 change=5 第二步的时候 step=6, 6%10=6>change(5)  那就需要等到2*change的时候，就是10 就需要额外加这个时间 6+[10-6%10]=10
 * 达到目的最快的路径，应该是BFS第一次达到节点的路径
 * 次快的，就是第二次达到节点的路径。
 * 1. 构建邻接表   edges[i][j]==1说明有边
 * 2. 按层级扫描 借助队列  扫描edges[i] ==1的节点，加入队列
 * 3. 如果遍历中出现了n节点   [i][j]==1说明达到了j、
 * 4. 剪枝 如果某一个层级出现过某个节点，就剪枝。
 * 数组存储节点，第几次访问，所在层级。
 * 每次遍历都可知本地遍历的层级，如果遍历到某个节点的时候，节点层级==遍历层级，说明这个节点之前遍历过
 */
class Solution2045 {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        // 每条边的权重是一样的，所以，我们只需要找到次短路就可以了
        // 一样使用BFS，一层一层的遍历，第二次遇到 n 才结束

        // 先构造 n 个节点
        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node();
        }

        // 整理边，设置到 Node 的 nexts 中
        for (int[] edge : edges) {
            nodes[edge[0]].nexts.add(nodes[edge[1]]);
            nodes[edge[1]].nexts.add(nodes[edge[0]]);
        }

        // BFS使用的队列
        Queue<Node> queue = new LinkedList<>();

        // 先把第 1 个节点入队，并设置其访问次数为 1
        queue.offer(nodes[1]);
        nodes[1].visited++;

        // ans 为返回的答案
        int ans = 0;
        // 遍历的层级，与二叉树的层序遍历类似
        int level = 0;
        while (!queue.isEmpty()) {
            // 层级加一
            level++;

            // 计算时间
            if (ans % (2 * change) >= change) {
                ans += 2 * change - ans % (2 * change);
            }
            ans += time;

            // 一层一层的遍历
            int size = queue.size();
            while (size-- > 0) {
                // 弹出当前层的节点
                Node node = queue.poll();

                // 遍历其下级的节点
                for (Node next : node.nexts) {
                    // 如果下级节点有等于 n 的，并且不是初始状态，并且不等于当前层级
                    // 说明之前已经遍历过一次了，那就直接返回吧
                    if (next == nodes[n] && next.level != 0 && next.level != level) {
                        return ans;
                    }
                    // 剪枝1：同一个层级同一个节点出现多次，只需要入队一次
                    // 剪枝2：同一个节点被访问了两次及以上（同一层级多次算一次），它肯定不可能是次短路径的一部分
                    if (next.level != level && next.visited < 2) {
                        queue.offer(next);
                        next.level = level;
                        next.visited++;
                    }
                }
            }

        }

        return -1;
    }

    static class Node {
        /**
         * 记录从起点到该点的层级
         */
        int level = 0;
        /**
         * 记录该点相连的节点
         */
        List<Node> nexts = new ArrayList<>();
        /**
         * 记录被访问过几次，同一个节点在同一层级被访问多次算一次
         */
        int visited = 0;
    }
}
