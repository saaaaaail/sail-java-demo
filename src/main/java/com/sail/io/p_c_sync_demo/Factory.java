package com.sail.io.p_c_sync_demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Factory {

    public static void main(String[] args) throws InterruptedException {
        Data data = new Data(0,10);
        Consumer consumer1 = new Consumer(data);
        Consumer consumer2 = new Consumer(data);
        Consumer consumer3 = new Consumer(data);
        Productor productor1 = new Productor(data);
        Productor productor2 = new Productor(data);
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(consumer1);
        service.submit(consumer2);
        service.submit(consumer3);
        service.submit(productor1);
        service.submit(productor2);

        Thread.sleep(20*1000);

        productor1.close();
        productor2.close();

        service.shutdown();
    }
}
