package com.sail.foroffer;

import java.util.Scanner;

/**
 * @program: JavaDemo
 * @description: 剪绳子
 * 给一根长度为n的绳子，请把绳子剪成m段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]*k[1]*...*k[m]的最大乘积是多少？
 * 例如:
 * 当绳子长度为8，剪成长度分别为2、3、3的三段，为最大乘积18
 * @author: sail
 * @create: 2019/05/31 21:14
 */

public class NO14_剪绳子 {
    /**
     * 要计算f(n)，就要计算max(f(1)*f(n-1),f(2)*f(n-2),......)
     * 切长度为1的最大乘积f(1)乘上剩下n-1长度的最大乘积f(n-1)
     * 切长度为2的最大乘积f(2)乘上剩下n-2长度的最大乘积f(n-2)
     * 切长度为3的最大乘积f(3)乘上剩下n-3长度的最大乘积f(n-3)
     * ......
     * 比较最大值
     * @param args
     */
    static int[] plus;
    static int N;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        plus = new int[N+1];

        if (N<=1){
            System.out.println("plus[]:"+0);
        }
        if (N==2){
            System.out.println("plus["+2+"]:"+1);
        }
        if (N==3){
            System.out.println("plus["+3+"]:"+2);
        }
        /**
         * 要人为计算到i=4，因当i<=3时不切分的值可能比切分的值要更大
         * 要计算的是必须进行一次切分
         */
        /**
         * 动态规划
         */
        plus[1]=1;
        plus[2]=2;
        plus[3]=3;
        for (int i=4;i<=N;i++){
            for (int j=1;j<=i/2;j++){
                plus[i]=Math.max(plus[i],j*plus[i-j]);
            }
            System.out.println("plus["+i+"]:"+plus[i]);
        }

        System.out.println(" ");
        /**
         * 贪心
         * 尽可能多的剪去3
         * 当最后绳子为4时，剪去2
         */
        int tmpN=N;
        if (N==4){
            System.out.println("plus["+4+"]:"+4);
        }
        plus[4]=4;

        int res=1;
        while (N>=5){
            N=N-3;
            res*=3;
        }

        System.out.println("plus["+tmpN+"]:"+res*plus[N]);

        /**
         * 递归方式计算，与动规思路相同
         */
        int result3 = cuttingRope(N);
        System.out.println(result3);
    }

    public static int cuttingRope(int n) {
        //整根绳子必须剪切一次
        if(n<=1){
            return 0;
        }
        if(n==2){
            return 1;
        }
        if(n==3){
            return 2;
        }
        return func(n);
    }

    public static int func(int n){
        //绳子中段，可以选择不剪，数值更多
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        if(n==3){
            return 3;
        }

        int max = 0;
        for(int i=1;i<=n/2;i++){
            max = Math.max(max,func(i)*func(n-i));
        }
        return max;
    }
}
