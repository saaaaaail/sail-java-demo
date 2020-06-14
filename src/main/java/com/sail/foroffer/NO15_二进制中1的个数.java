package com.sail.foroffer;

import java.util.Scanner;

/**
 * @program: JavaDemo
 * @description: 二进制中1的个数
 * 请实现一个函数，输入一个整数，输出该数二进制表示中1的个数。
 * 例如：
 * 把9表示成二进制1001，有2位是1.因此，如果输入9，则该函数输出2
 * @author: sail
 * @create: 2019/06/01 15:38
 */

public class NO15_二进制中1的个数 {
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());

        /**
         * 思路一:
         * 将标志位左移与N比较，不为0，表示N的那一位为1
         */
        int flag=1;//左移标志位
        int count=0;
        while (flag>0){
            //System.out.println("flag: "+flag);
            if ((N&flag)>0){
                //System.out.println(" ");
                count++;
            }
            flag=flag<<1;
        }
        System.out.println("count: "+count);

        /**
         * 思路二:
         * 将N-1与N进行与操作，能将N最右边的1消去
         * 进行几次操作认为N有多少个1，直到N为0
         */
        count=0;
        while (N>0){
            count++;
            N=N&(N-1);
        }
        System.out.println("count: "+count);
    }
}
