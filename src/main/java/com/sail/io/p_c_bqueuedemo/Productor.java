package com.sail.io.p_c_bqueuedemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Productor extends Thread{
    private BlockingQueue quque;
    private boolean isRunning;
    private AtomicInteger count;
    public Productor(BlockingQueue queue){
        this.quque = queue;
        isRunning=true;
        count = new AtomicInteger(0);
    }

    @Override
    public void run() {
        super.run();

        //产生产品
        while (isRunning){
            try {
                System.out.println("生产者:"+Thread.currentThread().getName()+" 开始生产产品~~~"
                        +"\t当前产品剩余数量:"+quque.size());
                Data data = new Data(count.getAndIncrement());
                if (quque.offer(data,2, TimeUnit.SECONDS)){
                    System.out.println("添加产品成功，当前产品数量："+quque.size());
                }else{
                    System.out.println("添加产品失败，当前产品数量："+quque.size());
                }
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public void close(){
        isRunning=false;
    }
}
