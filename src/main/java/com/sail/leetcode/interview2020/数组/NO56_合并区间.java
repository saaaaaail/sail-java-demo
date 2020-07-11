package com.sail.leetcode.interview2020.数组;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO56_合并区间 {


    /**
     * 9ms 45.73%思路就是首先对 区间 从小到大排序  然后单调队列，将区间值入队
     * 入队的时候做判断
     * 如果队尾值小于区间左值，则两个区间不相交
     * 如果队尾值大于等于区间左值且小于区间右值，则两个区间相交，将队尾值移除，将区间右值加入合并为一个区间
     * 如果队尾值大于等于区间右值，队尾的区间包含了要入队的区间，不需要做操作，直接丢弃
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if(intervals==null||intervals.length==0||intervals[0].length==0){
            return intervals;
        }
        Arrays.sort(intervals,(o1,o2)->{
            if(o1[0]!=o2[0]){
                return o1[0]-o2[0];
            }else{
                return o1[1]-o2[1];
            }
        });

        LinkedList<Integer> queue = new LinkedList<>();
        for(int i=0;i<intervals.length;i++){
            if(queue.isEmpty()){
                queue.offer(intervals[i][0]);
                queue.offer(intervals[i][1]);
            }else{
                int last = queue.peekLast();
                if(last<intervals[i][0]){
                    queue.offerLast(intervals[i][0]);
                    queue.offerLast(intervals[i][1]);
                }else if(last>=intervals[i][0]&&last<intervals[i][1]){
                    queue.removeLast();
                    queue.offerLast(intervals[i][1]);
                }
            }
        }

        int[][] result = new int[queue.size()/2][2];
        for(int i=0;i<result.length;i++){
            result[i][0]=queue.removeFirst();
            result[i][1]=queue.removeFirst();
        }
        return result;
    }
}
