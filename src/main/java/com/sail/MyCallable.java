package com.sail;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        String value="I will come back soon!!!";
        System.out.println("start!!!");
        Thread.currentThread().sleep(5000);
        System.out.println("finish!!!");
        return value;
    }
}
