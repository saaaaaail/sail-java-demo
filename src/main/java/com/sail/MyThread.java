package com.sail;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Main{

    public static void main(String[] args) {
        MyThread m = new MyThread();

    }

    public ReentrantLock test() {
       return new ReentrantLock();
    }
}
