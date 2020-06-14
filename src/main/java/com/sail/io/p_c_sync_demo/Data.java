package com.sail.io.p_c_sync_demo;

public class Data {


    //数量
    private int num;
    //资源上限
    private int size;

    public Data(int num,int size){
        this.num = num;
        this.size=size;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
