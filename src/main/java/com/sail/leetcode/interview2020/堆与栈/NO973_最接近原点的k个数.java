package com.sail.leetcode.interview2020.堆与栈;

import java.util.PriorityQueue;

/**
 * 最接近原点的K个点
 * 问题描述 We have a list of points on the plane. Find the K closest points to the origin (0, 0) . (Here, the distance between two points on a plane is the Euclidean distance.) You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in.) Example 1:
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just
 * [[-2,2]].
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 * Note: 1. 1 <= K <= points.length <= 10000 2. -10000 < points[i][0] < 10000 3. -10000 < points[i][1] < 10000
 */
public class NO973_最接近原点的k个数 {
    public static void main(String[] args) {
        NO973_最接近原点的k个数 yt = new NO973_最接近原点的k个数();
        int[][] points = new int[][]{{3,3},{5,-1},{-2,4}};
        yt.KCloser(points,2);
    }

    /**
     * 思路就是 大根堆，保证里面的数只有k个，遍历完整个队列 35ms 52.58%
     * 添加元素
     * 如果大根堆里元素数量小于k个，直接入队
     * 元素数量等于k个就判断，堆顶元素如果大于新来的元素就删除堆顶元素，加入新元素，否则丢弃新来的元素
     * 大于k个数，就直接删除堆顶元素即可
     * @param points
     * @param k
     */
    public void KCloser(int[][] points,int k){
        PriorityQueue<int[]> min = new PriorityQueue<>((o1, o2) -> {
            return (int) ((Math.pow(o2[0],2)+Math.pow(o2[1],2))-(Math.pow(o1[0],2)+Math.pow(o1[1],2)));
        });
        for(int i=0;i<points.length;i++){
            if(min.size()<k){
                min.add(points[i]);
            }else if(min.size()==k){
                int[] tmp = min.peek();
                if(Math.pow(points[i][0],2)+Math.pow(points[i][1],2)<(tmp[0]*tmp[0]+tmp[1]*tmp[1])){
                    min.poll();
                    min.add(points[i]);
                }
            }else{
                min.poll();
            }
        }
        int[][] result;
        result = min.toArray(new int[min.size()][]);
        for(int i=0;i<result.length;i++){

                System.out.println(result[i][0]+" "+result[i][1]);

        }
    }
}
