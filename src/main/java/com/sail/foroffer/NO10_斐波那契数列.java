package com.sail.foroffer;

/**
 * @program: JavaDemo
 * @description: 斐波那契数列
 * @author: sail
 * @create: 2019/05/30 15:03
 */

public class NO10_斐波那契数列 {
    public static void main(String[] args) {
        System.out.println(Fibonacci2(4));
    }

    public static Integer Fibonacci(Integer n){
        /**
         * f(n)=f(n-1)+f(n-2)
         */
        int pre=0;
        int post=1;
        int tmp=0;
        for (int i=2;i<=n;i++){
            tmp = pre+post;
            pre = post;
            post=tmp;
        }
        return tmp;
    }


    public static Integer Fibonacci2(Integer n){
        /**
         * f(n)=f(n-1)+f(n-2)
         */
        int[] num = new int[n+1];
        num[0]=0;
        num[1]=1;
        for (int i=2;i<=n;i++){
            num[i]=num[i-1]+num[i-2];
        }

        return num[n];
    }
}
