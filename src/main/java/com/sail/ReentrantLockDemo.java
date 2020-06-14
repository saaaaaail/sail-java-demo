package com.sail;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo implements Runnable {
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while(true){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+" get lock!");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        Thread thread1 = new Thread(reentrantLockDemo);
        Thread thread2 = new Thread(reentrantLockDemo);
        thread1.start();
        thread2.start();
    }
}
