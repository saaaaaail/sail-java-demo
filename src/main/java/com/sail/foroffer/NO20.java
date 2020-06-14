package com.sail.foroffer;

import java.util.Scanner;

/**
 * @program: JavaDemo
 * @description: 表示数值的字符串
 * 请实现一个函数用来判断字符串是否表示数值(包括整数和小数)。
 * 例："+100"、"5e2"、"-123"、"3.1416"、"-1E-16"
 * 数字的字符符合的正则表达式为
 * A[.[B]][e|EC]
 * 或
 * .B[e|EC]
 * 其中A和C表示有+-号的0~9的数位串
 * B表示0~9的数位串,没有±
 * @author: sail
 * @create: 2019/06/03 19:34
 */

public class NO20 {
    static String num;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        num = sc.nextLine();
        System.out.println(isNumeric(0,num));

    }

    public static boolean isNumeric(int nl,String num){
        boolean numeric=false;
        int prel = scanInteger(nl,num);

        if (prel==-1){
            return false;
        }

        if (prel>nl){
            numeric=true;
        }

        nl=prel;
        System.out.println(num.substring(nl));
        if (nl<num.length()&&num.charAt(nl)=='.'){
            int midl = scanUnsighInt(nl+1,num);
            if (midl==-1){
                return false;
            }
            if (midl>nl+1){
                numeric = true;
            }
            nl=midl;
            System.out.println(num.substring(nl));
        }

        if (nl<num.length()&&(num.charAt(nl)=='E'||num.charAt(nl)=='e')){
            int postl = scanInteger(nl+1,num);
            if (postl==-1){
                return false;
            }
            if (postl>nl+1){
                numeric = numeric;
            }else {
                numeric=false;
            }
            System.out.println(num.substring(nl));
        }

        return numeric;
    }

    public static int scanInteger(int nl,String num){
        int first= nl;
        if (nl>=num.length()
                ||num.charAt(nl)=='e'||num.charAt(nl)=='E'){
            return -1;
        }
        if (nl>=num.length()||num.charAt(nl)=='.'){
            return nl;
        }
        while (nl<num.length()&&(num.charAt(nl)!='.'
                &&num.charAt(nl)!='e'&&num.charAt(nl)!='E')){
            if ((nl==first&&num.charAt(nl)=='+')||(nl==first&&num.charAt(nl)=='-')
                    ||(num.charAt(nl)>='0'&&num.charAt(nl)<='9')){
                nl++;
            }else{
                return -1;
            }
        }
        return nl;
    }

    public static int scanUnsighInt(int nl,String num){
        if (nl>=num.length()||num.charAt(nl)=='.'
                ||num.charAt(nl)=='e'||num.charAt(nl)=='E'){
            return -1;
        }

        while (nl<num.length()&&(num.charAt(nl)!='.'
                &&num.charAt(nl)!='e'&&num.charAt(nl)!='E')){
            if (num.charAt(nl)>='0'&&num.charAt(nl)<='9'){
                nl++;
            }else {
                return -1;
            }
        }
        return nl;
    }

}
