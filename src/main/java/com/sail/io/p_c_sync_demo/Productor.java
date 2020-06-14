package com.sail.io.p_c_sync_demo;

public class Productor extends Thread{

    private Data data;
    private int size;
    private boolean isRunning;
    public Productor(Data data){
        this.data = data;
        size=data.getSize();
        isRunning = true;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (isRunning){
                synchronized (data){
                    System.out.println(Thread.currentThread().getId()+"号生产者开始生产产品~~");
                    while (data.getNum()>=size){
                        data.wait();
                    }
                    if (data.getNum()<size){
                        data.setNum(data.getNum()+1);

                        System.out.println(Thread.currentThread().getId()+"号生产者成功生产产品,剩余产品数目："+data.getNum());
                    }
                    data.notifyAll();
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        isRunning=false;

    }
}
