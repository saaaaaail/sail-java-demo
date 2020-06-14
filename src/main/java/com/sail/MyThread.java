package com.sail;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread implements Runnable {

    private int count;
    public MyThread(int count){this.count=count;}
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    @Override
    public void run() {
        long id = Thread.currentThread().getId();
        lock.lock();

        try {
            while (count<500) {
                if (id == 1) {
                    while (count % 5 != 1) {
                        condition.await();
                    }
                } else if (id == 2) {
                    while (count % 5 != 2) {
                        condition.await();
                    }
                } else if (id == 3) {
                    while (count % 5 != 3) {
                        condition.await();
                    }
                } else if (id == 4) {
                    while (count % 5 != 4) {
                        condition.await();
                    }
                } else if (id == 5) {
                    while (count % 5 != 0) {
                        condition.await();
                    }
                }

                if (count % 5 == 0) {
                    System.out.println(Thread.currentThread().getName()+" "+count);
                } else {
                    System.out.print(Thread.currentThread().getName()+" "+count);
                    System.out.print(" ");
                }
                count++;
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }


}
