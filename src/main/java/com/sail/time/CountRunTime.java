package com.sail.time;

/**
 * @program: JavaDemo
 * @description: 计算代码运行时间
 * @author: sail
 * @create: 2019/05/29 11:18
 */

public class CountRunTime {
    public static void main(String[] args) {

        long startMs = System.currentTimeMillis();
        long startNs = System.nanoTime();
        doSomething();
        long endMs = System.currentTimeMillis();
        long endNs = System.nanoTime();
        System.out.println("程序运行的时间："+(endMs-startMs)+"ms");
        System.out.println("程序运行的时间："+(endNs-startNs)+"ns");
    }

    public static void doSomething(){
        for (int i=0;i<100000;i++){
            for (int j=0;j<100000;j++){
                for (int k=0;k<10000;k++){

                }
            }
        }
    }
}
