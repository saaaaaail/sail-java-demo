package com.sail.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDemo
 * @description: TODO
 * @author: sail
 * @create: 2020/5/29 11:15
 */

public class VolatileDemo3 {

    public static void main(String[] args) {


        /**
         * 不使用volatile的话，可以使用notify和wait
         */
        VolatileDemo3 v = new VolatileDemo3();
        final Object object = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(object){
                    System.out.println("t1启动");
                    for (int i=0;i<10;i++){
                        v.add(new Object());
                        System.out.println(i);
                        if (i==5){
                            object.notify();
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    System.out.println("t2启动");
                    while(true){
                        if(v.size()!=5){
                            try{
                                object.wait();
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                        break;
                    }
                    System.out.println("t2结束");
                    object.notify();
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
