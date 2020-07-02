package com.sail.thread;

import java.util.LinkedList;

/**
 * 写一个固定容量同步容器队列，拥有put和get，以及getCount方法
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 */
public class BlockedContent1<T> {

    private final LinkedList<T> list = new LinkedList<>();
    private final int MAX = 10;
    private int size = 0;

    /**
     * 不足之处在于生产者 notifyAll既会叫醒生产者也会叫醒
     * @param t
     */
    public synchronized void put(T t){
        while (list.size()==MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.offer(t);
        size++;
        this.notifyAll();
    }

    public synchronized T get(){
        while (list.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = list.removeFirst();
        size--;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {

    }
}
