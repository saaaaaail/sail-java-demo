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

public class VolatileDemo2 {

    public static void main(String[] args) {


        /**
         * volatile修饰list的时候，如果不sleep一下，另一个线程不具备可见性，很奇怪
         * 但是可以另设一个volatile修饰基本数据类型的size，每次添加一个数，t1更新一下这个值
         * 这样t2线程就能读到最新的值了，
         * 但是，只有两个线程没有问题，
         * 如果有多个线程add操作的话，还要给容器加锁才行
         */
        VolatileDemo2 v = new VolatileDemo2();
        final Object object = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {

                    System.out.println("t1启动");
                    for (int i=0;i<10;i++){
                        v.add(new Object());
                        v.size = v.size();
                        System.out.println(i);

                    }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2启动");
                while(true){
                    if(v.size==5){
                        break;
                    }
                }
                System.out.println("t2结束");
            }
        }).start();
    }
    volatile int size = 0;
    List<Object> list = new ArrayList<>();
    public void add(Object i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }











}
