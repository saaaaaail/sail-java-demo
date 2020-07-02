package com.sail.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        useCountDownLatch();
    }

    private static void useCountDownLatch(){
        List<Thread> threadList = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(100);
        for (int i=0;i<100;i++){
            threadList.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    int result = 0;
                    for(int j=0;j<10000;j++){
                        result+=j;

                    }
                    System.out.println("线程结束一个");
                    latch.countDown();
                }
            }));
        }

        for (Thread t:threadList){
            t.start();
        }

        try{
            latch.await();
            System.out.println("都结束了");

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
