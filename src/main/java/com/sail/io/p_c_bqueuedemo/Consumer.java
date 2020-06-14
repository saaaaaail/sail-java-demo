package com.sail.io.p_c_bqueuedemo;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread{

    private BlockingQueue<Data> queue;

    public Consumer(BlockingQueue queue){
        this.queue = queue;
    }

    @Override
    public synchronized void run() {
        super.run();

        //
        System.out.println("消费者："+Thread.currentThread().getName()+" 开始请求产品~~~");
        while (true){
            try {
                Data data = queue.take();
                if (data!=null){
                    int dataName = data.getName();
                    System.out.println("消费者："+Thread.currentThread().getName()+"取到"+dataName+"号的产品");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
