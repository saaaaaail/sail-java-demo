package com.sail.foroffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: JavaDemo
 * @description: 数值的整数次方
 * @author: sail
 * @create: 2019/06/01 16:15
 */

public class NO16_数值的整数次方 {
    static double base;
    static int exponent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        base = sc.nextInt();
        exponent = sc.nextInt();
        double res=1d;
        if (base==0.0d&&exponent<0){
            System.out.println("输入不合规范!!!");
        }


        if (exponent<0){
            exponent = Math.abs(exponent);
            base = 1/base;
        }
/**
 * 普通解法，时间复杂度不太行
 */
        for (int i=0;i<exponent;i++){
            res = res*base;
        }
        System.out.println("res:"+res);

        /**
         * 另一种解法，递归快速幂
         */
        System.out.println(power(base,exponent));

        /**
         * 另一种解法，迭代快递幂
         */
        base=2.0;
        exponent=-2;
        System.out.println(myPow(base,exponent));
    }

    public static double power(double base,int exponent){
        if (exponent==1){
            return base;
        }
        double res=0.0d;
        if (exponent%2==1){
            res = power(base,(exponent-1)/2)*power(base,(exponent-1)/2)*base;
        }else{
            res = power(base,exponent/2)*power(base,exponent/2);
        }
        return res;
    }

    public static double myPow(double x, int n) {
        if(n==0){
            return 1;
        }
        if(n==1){
            return x;
        }
        if(x==1.0||x==0.0){
            return x;
        }
        int tmp = n;
        double res = 1;
        while(n!=0){

            System.out.println("n:"+n+",x:"+x+",res:"+res);
            if((n&1)!=0){
                res*=x;
            }
            x*=x;
            n=n/2;
        }
        return tmp>0? res:1.0/res;
    }

}
