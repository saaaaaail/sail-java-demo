package com.sail.foroffer;

/**
 * @program: JavaDemo
 * @description: TODO
 * @author: sail
 * @create: 2020/4/28 22:15
 */

public class NO2_Singleton {

    public static void main(String[] args) {

    }

    private NO2_Singleton(){}

    private static NO2_Singleton instance = null;

    /**
     * 双重校验法
     * @return
     */
    public static NO2_Singleton getInstance1(){
        if (instance == null){
            synchronized (NO2_Singleton.class){
                if (instance == null){
                    instance = new NO2_Singleton();
                }
            }
        }
        return instance;
    }

    public static class SINGLETON{
        private static final NO2_Singleton INSTANCE = new NO2_Singleton();
    }
    /**
     * 静态内部类
     * @return
     */
    public static NO2_Singleton getInstance2(){
        return SINGLETON.INSTANCE;
    }
}
