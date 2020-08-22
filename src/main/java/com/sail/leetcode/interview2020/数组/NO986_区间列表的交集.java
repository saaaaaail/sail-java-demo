package com.sail.leetcode.interview2020.数组;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定两个由一些 闭区间 组成的列表，每个区间列表都是成对不相交的，并且已经排序。
 *
 * 返回这两个区间列表的交集。
 *
 * （形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b。两个闭区间的交集是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3]。）
 *
 *  
 *
 * 示例：
 *
 *
 *
 * 输入：A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *  
 *
 * 提示：
 *
 * 0 <= A.length < 1000
 * 0 <= B.length < 1000
 * 0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interval-list-intersections
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO986_区间列表的交集 {

    public static void main(String[] args) {
        NO986_区间列表的交集 t = new NO986_区间列表的交集();
        t.intervalIntersection(
                new int[][]{{0,2},{5,10},{13,23},{24,25}},
                new int[][]{{1,5},{8,12},{15,24},{25,26}}
        );
    }

    /**
     * 9ms 12.20%
     * 采用了队列，队列里面进行合并区间操作
     * 对于有交集的部分在外面添加，到结果集里面，不放入队列
     * 新来一个区间就与队列尾的数值进行范围判断即可
     *
     * 写掉的地方:
     * 当一个区间走完以后，剩下的那个区间数组依然要全部与队列尾元素进行判断，看有没有交集
     * @param A
     * @param B
     * @return
     */
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if(A==null||A.length==0||A[0].length==0 || B==null||B.length==0||B[0].length==0){
            return new int[0][];
        }
        LinkedList<Integer> queue = new LinkedList<>();
        int a = 0;
        int b = 0;
        List<int[]> result = new ArrayList<>();
        while(a<A.length&&b<B.length){
            int[] atmp = A[a];
            int[] btmp = B[b];
            int[] tmp = null;
            if(atmp[0]<btmp[0]){
                tmp = atmp;
                a++;
            }else{
                tmp = btmp;
                b++;
            }
            if(queue.isEmpty()){
                queue.offer(tmp[0]);
                queue.offer(tmp[1]);
            }else{
                if(queue.peekLast()>=tmp[1]){
                    result.add(tmp);
                }else if(queue.peekLast()<tmp[1]&&queue.peekLast()>=tmp[0]){
                    result.add(new int[]{tmp[0],queue.removeLast()});
                    queue.offer(tmp[1]);
                }else{
                    queue.offer(tmp[0]);
                    queue.offer(tmp[1]);
                }
            }
        }
        while(a<A.length){
            int[] tmp = A[a++];
            if(queue.peekLast()>=tmp[1]){
                result.add(tmp);
            }else if(queue.peekLast()<tmp[1]&&queue.peekLast()>=tmp[0]){
                result.add(new int[]{tmp[0],queue.removeLast()});
                queue.offer(tmp[1]);
            }else{
                queue.offer(tmp[0]);
                queue.offer(tmp[1]);
            }
        }
        while(b<B.length){
            int[] tmp = B[b++];
            if(queue.peekLast()>=tmp[1]){
                result.add(tmp);
            }else if(queue.peekLast()<tmp[1]&&queue.peekLast()>=tmp[0]){
                result.add(new int[]{tmp[0],queue.removeLast()});
                queue.offer(tmp[1]);
            }else{
                queue.offer(tmp[0]);
                queue.offer(tmp[1]);
            }
        }
        return result.toArray(new int[0][]);
    }
}
