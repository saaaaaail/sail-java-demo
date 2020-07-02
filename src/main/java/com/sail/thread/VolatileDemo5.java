package com.sail.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @program: JavaDemo
 * @description: TODO
 * @author: sail
 * @create: 2020/5/29 11:15
 */

public class VolatileDemo5 {

    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {


        /**
         * 可以使用LockSupoort
         */
        VolatileDemo3 v = new VolatileDemo3();

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("t2启动");
                while(true){
                    if(v.size()!=5){
                            LockSupport.park();
                    }
                    break;
                }
                System.out.println("t2结束");
                LockSupport.unpark(t1);
            }

        });
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("t1启动");
                for (int i=0;i<10;i++){
                    v.add(new Object());
                    System.out.println(i);
                    if (i==5){
                        LockSupport.unpark(t2);
                        LockSupport.park();
                    }

                }

            }
        });
        t2.start();

        t1.start();

    }

    List<Object> list = new ArrayList<>();
    public void add(Object i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }











}
