package com.sail.io.p_c_bqueuedemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Factory {

    public static void main(String[] args) {
        BlockingQueue<Data> queue = new LinkedBlockingQueue<Data>(10);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);
        Productor productor = new Productor(queue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(consumer1);
        service.submit(consumer2);
        service.submit(consumer3);
        service.submit(productor);
        try {
            Thread.sleep(10*1000);
            productor.close();
            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
