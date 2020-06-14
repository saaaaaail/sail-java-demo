package com.sail.foroffer;

import java.util.Scanner;

/**
 * @program: JavaDemo
 * @description: 打印从1到最大的n位数
 * 输入数字n,按顺序打印出从1到最大的n位十进制数。
 * 比如输入3，则打印出1、2、3一直到最大的3位数 999
 * @author: sail
 * @create: 2019/06/01 16:49
 */

public class NO17_打印从1到最大的n位数 {

    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        char[] num = new char[N+1];
        for (int i=0;i<num.length;i++){
            num[i]='0';
        }
        printToN(num);
    }

    public static void printToN(char[] num){
        /**
         * 用字符数组存
         * 进一个死循环，若最后把首位的0去掉后，截取的数长度大于N就break;
         * 每次都是数的最低位num[N]+1,
         * 然后从N~1检查判断每一位是否大于'9',大于的话就剪去10,给高位+1
         *
         */
        int subi=0;//记录截取的索引
        String res=null;
        //while (num[0]=='0'){
        while (true){
            num[N]++;
            for (int i=N;i>=0;i--){
                if (num[i]!='0'){//从最后往前遍历的话，记录的就是最前面不为0的数的索引
                    subi=i;
                }
                if (num[i]>'9'){
                    num[i-1]++;
                    num[i]=(char)(num[i]-10);
                    //
                }
            }
            res = new String(num).substring(subi);
            if (res.length()>N){break;}//两个跳出循环条件
            if (num[0]!='0'){break;}
            System.out.println(res);
        }
    }
}
