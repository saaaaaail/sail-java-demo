package com.sail.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @program: JavaDemo
 * @description: TODO
 * @author: sail
 * @create: 2020/5/29 11:15
 */

public class VolatileDemo4 {

    public static void main(String[] args) {


        /**
         * 可以使用countDownLatch
         */
        VolatileDemo3 v = new VolatileDemo3();
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("t2启动");
                while(true){
                    if(v.size()!=5){
                        try {
                            latch2.await();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                System.out.println("t2结束");
                latch1.countDown();
            }

        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                    System.out.println("t1启动");
                    for (int i=0;i<10;i++){
                        v.add(new Object());
                        System.out.println(i);
                        if (i==5){
                            try {
                                latch2.countDown();
                                latch1.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }

                    }

            }
        }).start();


    }

    List<Object> list = new ArrayList<>();
    public void add(Object i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }











}
