package com.sail.foroffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 *
 * 提示：
 *
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO59_滑动窗口最大值1 {

    public static void main(String[] args) {
        int[] n = new int[]{1,3,-1,-3,5,3,6,7};
        System.out.println(maxSlidingWindowMaxStack(n,3));
    }

    /**
     * 单调队列，12ms 70.12%
     * 1、只存入依次减少的数，如果我新加入滑动框的一个数A比队列末尾的数要大，那么直接将队列末尾比A的数全都弹出来
     * 2、然后在队列末尾加上A，
     * 3、这样保证队列头一定是最大的数，并且保证的是目前窗口里的数的一个从大到小的序列
     * 4、当然这个序列不是连续的但是是按顺序的
     * 5、这样滑动窗口移动的时候，判断对头的数是否等于最左边的数，如果相等意味着这个数将移出窗口了，队列里面也需要移除对头元素
     * 6、如果不是窗口直接移动即可，
     * 7、当前新加的数仍然要做与 1、2步的操作
     */
    public static int[] maxSlidingWindowMaxStack(int[] nums, int k) {
        if(nums==null||nums.length==0){
            return new int[0];
        }
        int l=0;
        int r=0;
        int max = Integer.MIN_VALUE;
        List<Integer> list = new ArrayList<>();
        LinkedList<Integer> oneQueue = new LinkedList<>();
        while(l<=r){
            while(r-l<=k-1&&r<nums.length){
                while(!oneQueue.isEmpty()&&oneQueue.peekLast()<nums[r]){
                    oneQueue.removeLast();
                }
                oneQueue.addLast(nums[r]);
                r++;
            }
            if(r>nums.length-1){
                break;
            }
            list.add(oneQueue.peek());
            if(nums[l]==oneQueue.peekFirst()){
                oneQueue.removeFirst();
            }
            while (!oneQueue.isEmpty()&&nums[r]>oneQueue.peekLast()){
                oneQueue.removeLast();
            }
            l++;
            oneQueue.addLast(nums[r]);
            r++;
        }
        list.add(oneQueue.peek());
        int[] result = new int[list.size()];
        for(int i=0;i<list.size();i++){
            result[i]=list.get(i);
        }
        return result;
    }

    /**
     * 7ms 83.87%
     * 思路就是移动窗口，新来的值与旧窗口的最大值比较
     * 如果新值比旧窗口的最大值要大，那么直接赋值值最大值，并且移动窗口
     * 如果新值比旧窗口的最大值要小，就判断最左边的值是不是等于最大值的，如果最左边的值就是最大值，那么就要对新窗口重新求最大值
     * 如果最左边的值不是最大值，那窗口直接移动就可以
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null||nums.length==0){
            return new int[0];
        }
        int max = Integer.MIN_VALUE;
        int l=0;
        int r=0;
        List<Integer> list = new ArrayList<>();
        while(r<=nums.length-1){
            while(r-l<k&&r<nums.length){
                max = Math.max(max,nums[r]);
                r++;
            }
            if(r>nums.length-1){
                break;
            }
            list.add(max);
            if(nums[r]>=max){
                max = nums[r];
                l++;
                r++;
            }else if(nums[l]==max){
                l++;
                max = max(nums,l,r);
                r++;
            }else{
                l++;
                r++;
            }
        }
        list.add(max);
        int[] result = new int[list.size()];
        for(int i=0;i<list.size();i++){
            result[i] = list.get(i);
        }
        return result;
    }

    public int max(int[] nums,int l,int r){
        int max = Integer.MIN_VALUE;
        for(int i=l;i<=r;i++){
            max = Math.max(max,nums[i]);
        }
        return max;
    }
}
