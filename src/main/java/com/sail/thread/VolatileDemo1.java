package com.sail.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemo
 * @description: TODO
 * @author: sail
 * @create: 2020/5/29 11:15
 */

public class VolatileDemo1 {

    public static void main(String[] args) {


        /**
         * volatile修饰list的时候，如果不sleep一下，另一个线程不具备可见性，很奇怪
         */
        VolatileDemo1 v = new VolatileDemo1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1启动");
                for (int i=0;i<10;i++){
                    v.add(new Object());
                    System.out.println(i);
                    try {
                        TimeUnit.NANOSECONDS.sleep(1);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2启动");
                while(true){
                    if(v.size()==5){
                        break;
                    }
                }
                System.out.println("t2结束");
            }
        }).start();
    }

    volatile List<Object> list = new ArrayList<>();
    public void add(Object i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }











}
