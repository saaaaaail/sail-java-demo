package com.sail.leetcode.interview2020.数组;


import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 *  
 *
 *
 *
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *
 *  
 *
 *
 *
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *
 *  
 *
 * 示例:
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class NO84_柱状图中最大的矩形 {

    public static void main(String[] args) {
        NO84_柱状图中最大的矩形 t = new NO84_柱状图中最大的矩形();
        t.largestRectangleArea2(new int[]{2,1,2});

    }
    /**
     *  11ms 84.5%
     *  思路就是保持一个单调栈，从小到大入栈，首先在首尾都插入0；
     *  当新来的元素比栈顶小，栈顶出栈计算面积，与结果比较大小
     *  再次用这个元素与栈顶比较，栈顶还是大，继续出栈计算面积，与结果比较大小
     *  直到栈顶元素比新来的小，入栈
     *
     *  注意的点: 面积的高度就是我出栈元素的限高，但是宽度的话怎么确定
     *  宽度等于 新来元素的索引 减去 栈顶元素的索引 再减 1（再减1是因为开区间）
     *  为什么?
     *  栈顶元素一定是当前元素左边比你小的元素，新来的元素一定是新来的比你小的元素
     *  栈顶元素与当前元素不一定相邻，但是一定是比当前元素小的那个位置，因为他们之间的元素都是大于等于当前元素出栈了，面积不会有空缺
     *  新来元素与当前元素一定相邻，
     */
    public int largestRectangleArea2(int[] heights) {
        if(heights==null||heights.length==0){
            return 0;
        }
        LinkedList<Integer> minStack = new LinkedList<>();
        int[] heights2 =new int[heights.length+2];
        System.arraycopy(heights,0,heights2,1,heights.length);
        heights2[heights2.length-1]=0;
        heights2[0]=0;
        int res = 0;
        for(int i=0;i<heights2.length;i++){
            if(minStack.isEmpty()){
                minStack.push(i);
            }else{

                while(!minStack.isEmpty()&&heights2[minStack.peek()]>heights2[i]){
                    int minI = minStack.pop();
                    /**
                     * 这里当前数minI是限高，minStack.peek是什么意思呢？
                     * 在单调栈里面，先入栈的元素（即在当前元素左边的元素）认为一定是高度比你小的那个元素（不管你们是不是相邻的），
                     * 如果不相邻的话，那么这个元素的索引位置右边的元素一定是大于等于你当前元素的，因为大的已经出栈了但是宽度还摆在这
                     * 所以现在三个数的关系是栈顶的那个高度比minI要小，新来的那个i要比minI要小，
                     * 那么宽度就是i-(minStack.peek()+1)，注意 i与栈顶元素都是开区间来求区间长度
                     */
                    int sum = (i-minStack.peek()-1)*heights2[minI];
                    res = Math.max(res,sum);
                }

                minStack.push(i);
            }
        }

        return res;
    }

    /**
     * 分治法 592ms 每次找区间里的最小高度拆分，优化点在于，每次都要遍历一下才知道哪个最小
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if(heights==null||heights.length==0){
            return 0;
        }
        return splitArea(heights,0,heights.length-1);
    }

    public int splitArea(int[] heights,int l,int r){
        if(l>r){
            return 0;
        }
        int minNum =0;
        int minHeight = Integer.MAX_VALUE;
        for(int i = l;i<=r;i++){
            minHeight = Math.min(minHeight,heights[i]);
            minNum = i;
        }
        int sum = minHeight*(r-l+1);
        sum = Math.max(sum,splitArea(heights,l,minNum-1));
        sum = Math.max(sum,splitArea(heights,minNum+1,r));
        return sum;
    }
}
