package com.sail.foroffer;

import java.util.*;

/**
 * 我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 *
 *  
 *
 * 示例:
 *
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:  
 *
 * 1 是丑数。
 * n 不超过1690。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/chou-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NO49_丑数 {

    public static void main(String[] args) {
        System.out.println( nthUglyNumber(10));
    }


    /**
     * 7%
     * 我们知道，丑数的排列肯定是1,2,3,4,5,6,8,10.... 然后有一个特点是，任意一个丑数都是由小于它的某一个丑数*2，*3或者*5得到的，那么如何得到所有丑数呢？ 现在假设有3个数组，分别是： A：{1*2，2*2，3*2，4*2，5*2，6*2，8*2，10*2......}
     *
     * B：{1*3，2*3，3*3，4*3，5*3，6*3，8*3，10*3......}
     *
     * C：{1*5，2*5，3*5，4*5，5*5，6*5，8*5，10*5......}
     *
     * 那么所有丑数的排列，必定就是上面ABC3个数组的合并结果然后去重得到的，那么这不就转换成了三个有序数组的无重复元素合并的问题了吗？而这三个数组就刚好是{1,2,3,4,5,6,8,10....}乘以2,3,5得到的。
     *
     * 合并有序数组的一个比较好的方法，就是每个数组都对应一个指针，然后比较这些指针所指的数中哪个最小，就将这个数放到结果数组中，然后该指针向后挪一位。
     *
     * 回到本题，要求丑数ugly数组中的第n项，而目前只知道ugly[0]=1，所以此时三个有序链表分别就只有一个元素：
     *
     * A ： {1*2......}
     *
     * B ： {1*3......}
     *
     * C ：{1*5......}
     *
     * @param n
     * @return
     */
    public static int nthUglyNumber(int n) {
        if(n==1){
            return n;
        }
        LinkedList<Integer>  twoList= new LinkedList<>();
        twoList.offer(2);
        LinkedList<Integer>  threeList= new LinkedList<>();
        threeList.offer(3);
        LinkedList<Integer>  fiveList= new LinkedList<>();
        fiveList.offer(5);
        Set<Integer> set = new HashSet<>();
        n--;
        int small = 0;
        while(n>0){
            while(small==0||set.contains(small)){
                small = selectSmallestOne(twoList,threeList,fiveList);
            }
            set.add(small);

            n--;
            twoList.offer(small*2);
            threeList.offer(small*3);
            fiveList.offer(small*5);
        }
        return small;
    }

    public static int selectSmallestOne(LinkedList<Integer> two, LinkedList<Integer> three, LinkedList<Integer> five){
        int min = Integer.MAX_VALUE;
        int twoPeek = two.peek();
        int threePeek = three.peek();
        int fivePeek = five.peek();
        min = Math.min(min,twoPeek);
        min = Math.min(min,threePeek);
        min = Math.min(min,fivePeek);
        if(min ==twoPeek){
            return two.poll();
        }else if(min == threePeek){
            return three.poll();
        }else if(min == fivePeek){
            return five.poll();
        }
        return 0;
    }

    /** 10%
     * 上面三条链的方式还可以优化为一条链在头部取，同一条链在末尾添加
     * 只要读出来的数分别乘以2、3、5即可
     */
    public int nthUglyNumber1(int n) {
        List<Integer> ugly = new ArrayList<>();
        ugly.add(1);

        int twol = 0;
        int threel = 0;
        int fivel = 0;
        int tmp = 1;
        while(n>1){
            tmp = Math.min(ugly.get(twol)*2,Math.min(ugly.get(threel)*3,ugly.get(fivel)*5));
            if(tmp==ugly.get(twol)*2){
                twol++;
            }
            if(tmp==ugly.get(threel)*3){
                threel++;
            }
            if(tmp==ugly.get(fivel)*5){
                fivel++;
            }
            ugly.add(tmp);
            n--;
        }
        return tmp;
    }
}
