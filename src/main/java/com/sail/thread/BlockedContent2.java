package com.sail.thread;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个固定容量同步容器队列，拥有put和get，以及getCount方法
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 */
public class BlockedContent2<T> {

    private final LinkedList<T> list = new LinkedList<>();
    private final int MAX = 10;
    private int size = 0;
    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    /**
     * 不足之处在于生产者 notifyAll既会叫醒生产者也会叫醒
     * @param t
     */
    public void put(T t){
        try{
            lock.lock();
            while (list.size()==MAX){
                producer.await();
            }
            list.offer(t);
            size++;
            consumer.signalAll();

        }catch (Exception e){
            e.printStackTrace();
        }finally {

            lock.unlock();
        }
    }

    public T get(){
        T t = null;
        try{
            lock.lock();
            while (list.size()==0){
                consumer.await();
            }
            t = list.removeFirst();
            size--;
            producer.signalAll();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        BlockedContent2<String> content = new BlockedContent2<>();

        for (int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("启动消费者线程"+Thread.currentThread().getName());
                    for (int j=0;j<5;j++){
                        String s = content.get();
                        System.out.println(Thread.currentThread().getName()+"消费"+s);
                        try {
                            TimeUnit.SECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "consumer-" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for (int i=0;i<3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j=0;j<25;j++){
                        System.out.println(Thread.currentThread().getName()+"生产");
                        content.put("生产资料");
                        try {
                            TimeUnit.SECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "produce-" + i).start();
        }


    }
}
