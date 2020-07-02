package com.sail.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    public static void main(String[] args) {

    }

    static Lock lock = new ReentrantLock();
    static int value;
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readlock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock){
        try{
            lock.lock();
            Thread.sleep(1000);
            System.out.println("开始读书");
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public static void write(Lock lock){
        try{
            lock.lock();
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
