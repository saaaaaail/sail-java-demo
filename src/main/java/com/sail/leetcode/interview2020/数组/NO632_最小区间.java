package com.sail.leetcode.interview2020.数组;

import java.util.List;
import java.util.PriorityQueue;

/**
 * 你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 *
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 *
 * 示例 1:
 *
 * 输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出: [20,24]
 * 解释:
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 * 注意:
 *
 * 给定的列表可能包含重复元素，所以在这里升序表示 >= 。
 * 1 <= k <= 3500
 * -105 <= 元素的值 <= 105
 * 对于使用Java的用户，请注意传入类型已修改为List<List<Integer>>。重置代码模板后可以看到这项改动。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-range-covering-elements-from-k-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO632_最小区间 {
    /**
     * 35ms 76.77%
     * 这一题思路 多指针+贪心+最小堆
     * 由于要求每一行都至少有一个元素，因此给每行都指向一个指针。用points[i]数组表示i行数的每一行的指针
     * 然后将每一行第一个元素入最小堆 记录最大值
     * 然后当最小堆不为空，就将最小值出堆，判断[最小值,最大值]区间是不是最小区间
     * 然后将最小值所在的那一列的下一个元素入堆，判断是否要更新最大值
     * 再次将堆顶最小值出栈，判断[最小值,最大值]区间是不是最小区间
     * 重复上面的操作，直到有一行元素全部加载完以后，这一行的最后一个元素从堆顶出栈，判断是否最小区间
     * 然后跳出循环，打印输出最小区间
     * 、
     * 因为当前行的最后一个元素已经是堆里的最小值了，
     * 即使要保证每一行都有一个元素，将倒数第二的数出栈，将新的数入栈，区间也只会越来越大
     * 因此直接跳出结束即可。
     *
     */
    public int[] smallestRange(List<List<Integer>> nums) {

        int[] points = new int[nums.size()];
        PriorityQueue<int[]> minStack = new PriorityQueue<>((o1, o2)->{
            return o1[1]-o2[1];
        });

        int max = 0;

        for(int i=0;i<nums.size();i++){
            List<Integer> list = nums.get(i);
            int num = list.get(points[i]++);
            minStack.add(new int[]{i,num});
            max = Math.max(max,num);
        }
        int[] result = null;

        while(minStack!=null){
            int[] tmp = minStack.poll();
            int i = tmp[0];
            int min = tmp[1];
            if(result==null||isSmallThanResult(min,max,result)){
                if(result==null){
                    result = new int[2];
                }
                result[0] = min;
                result[1] = max;
            }
            if(points[i]>=nums.get(i).size()){
                break;
            }
            if(min==max&&minStack.isEmpty()){
                max = 0;
            }
            int num = nums.get(i).get(points[i]++);
            max = Math.max(max,num);
            tmp[1]=num;
            minStack.add(tmp);
        }
        return result;


    }
    public boolean isSmallThanResult(int l1,int r1,int[] result){
        if(r1-l1<result[1]-result[0]){
            return true;
        }else if(r1-l1==result[1]-result[0]){
            if(l1<result[0]){
                return true;
            }
        }
        return false;
    }

}
