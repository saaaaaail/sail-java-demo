package com.sail.leetcode;

import java.util.Scanner;

/**
 * @program: SumOfManWork
 *
 * @description: 华为笔试题2019/5/8 计算修改字符串的工作量，添加、删除、修改均表示一个工作量
 * @author: sail
 * @create: 2019/5/8 19:55
 */

public class SumOfManWork {
    static int N;
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        N=Integer.parseInt(sc.nextLine());
        String[] before = new String[N];
        String[] after = new String[N];
        for (int i=0;i<N;i++){
            before[i]=sc.nextLine();
        }
        for (int i=0;i<N;i++){
            after[i]=sc.nextLine();
        }
        /**
         * 依次处理N个
         */
        int sum=0;
        for (int i=0;i<N;i++){
            String pre = before[i];
            String post = after[i];

            int res = step(0,pre,post);
            sum=sum+res;


        }
        System.out.println(sum);
    }

    public static int step(int count,String pre,String post){
        if (post.length()==0){
            return count+pre.length();
        }
        if (pre.length()==0){
            return count+post.length();
        }
        int i;
        for (i=0;i<pre.length()&&i<post.length();i++){

            if (post.charAt(i)==pre.charAt(i)){
                continue;
            }else {
                String tmp1=pre.substring(i+1);
                int subPre=step(count+1,tmp1,post);

                String tmp2=post.substring(i+1);
                int subPost=step(count+1,pre,tmp2);

                int subD=step(count+1,tmp1,tmp2);
                int min = Math.min(subD,Math.min(subPre,subPost));
                return min;
            }
        }
        if (i<post.length()){
            return count+post.length()-i;
        }
        if (i<pre.length()){
            return count+pre.length()-i;
        }
        return count;
    }
}
