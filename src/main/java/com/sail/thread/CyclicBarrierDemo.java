package com.sail.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {


    }

    private static void useCyclicBarrier(){
        CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人->发车");
            }
        });

        for (int i=0;i<100;i++){
            new Thread(() -> {
                try {
                    barrier.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
