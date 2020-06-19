package com.sail.foroffer;

import java.util.*;

/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例 1：
 *
 * 输入：
 * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 * 示例 2：
 *
 * 输入：
 * ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 * [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 *  
 *
 * 限制：
 *
 * 最多会对 addNum、findMedia进行 50000 次调用。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO41_数据流的中位数 {
    /**
     * 用平衡二叉树做不了这题，除非将一个节点的前序节点数目与后续节点数目在插入的时候都记录下来
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * 思路：选择大根堆left与小根堆right将内存划分为两个区域
     * 保证 大根堆保存小一些的数据，小根堆保存大一些的数据，大根堆的最大值要小于小根堆的最大值
     * 保证大根堆的数量要么等于小根堆的数量要么比小根堆多一个
     * 那么新来一个数num 此时大根堆最大值leftPeek 小根堆最小值 rightPeek
     *
     * 如果左右数目相同，那么此时是要往大根堆left里面插入的，
     * 但是插入之前要判断num如果比rightPeek小，那就直接插入left
     * num如果比rightPeek要大，那么要插入小根堆right ，小根堆要保证数量不变，就得把其最小的数先取出来放到left，再把num放到right
     *
     * 如果左边比右边数目多一，那么此时是要往小根堆里面插入的，
     * 但是插入之前要判断num如果比leftPeek大，那就直接插入right
     * num如果比leftPeek要小，就说明要插入left中，为了保证数量不变，把left的最大值取出来移到right中，再把num放到left里面
     */
    class MedianFinder {


        //保证小根堆的最小值始终比大根堆的最大值要大
        //保证大根堆的数目等于小根堆的数目或者比小根堆多一个
        PriorityQueue<Integer> left = null;
        PriorityQueue<Integer> right = null;
        /** initialize your data structure here. */
        public MedianFinder() {
            left = new PriorityQueue<>((v1,v2)->(v2-v1));
            right = new PriorityQueue<>();

        }

        public void addNum(int num) {

            int leftPeek = left.size()==0?0:left.peek();
            int rightPeek = right.size()==0?Integer.MAX_VALUE:right.peek();
            if(left.size()==right.size()){
                //为了保证小根堆最小值也要大于大根堆最大值
                if(num<rightPeek){
                    left.offer(num);
                }else{
                    int extra = right.poll();
                    left.offer(extra);
                    right.offer(num);
                }
            }else if(left.size()-1==right.size()){
                if(num<leftPeek){
                    int extra = left.poll();
                    right.offer(extra);
                    left.offer(num);
                }else{
                    right.offer(num);
                }
            }

        }

        public double findMedian() {
            if(left==null||right==null){
                return 0L;
            }
            if(left.size()==right.size()){
                return (left.peek().doubleValue()+right.peek().doubleValue())/2;
            }
            if(left.size()>right.size()){
                return left.peek();
            }
            return 0L;
        }


    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
}
