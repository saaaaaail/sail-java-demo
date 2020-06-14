package com.sail.io.p_c_sync_demo;

public class Consumer extends Thread{

    private Data data;
    private int size;
    private boolean isRunning;
    public Consumer(Data data){
        this.data = data;
        size = data.getSize();
        isRunning=true;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (isRunning){
                synchronized (data){
                    System.out.println(Thread.currentThread().getId()+"号消费者开始请求资源~~~");
                    while (data.getNum()<=0){
                        data.wait();
                    }
                    if(data.getNum()>0){
                        data.setNum(data.getNum()-1);

                        System.out.println(Thread.currentThread().getId()+"号消费者请求到产品，剩余产品数："+data.getNum());
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
