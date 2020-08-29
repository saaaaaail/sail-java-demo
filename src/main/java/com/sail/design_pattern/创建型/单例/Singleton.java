package com.sail.design_pattern.创建型.单例;

import java.io.Serializable;

/**
 * @program: sail-java-demo
 * @description: TODO
 * @author: sail
 * @create: 2020/8/26 18:33
 */

public class Singleton implements Serializable {
    private static volatile Singleton singleton;
    private Singleton(){}
    private Object readResolve(){
        return singleton;
    }
    //###################################################################

    public static Singleton getInstance(){
        if (singleton==null){
            synchronized (Singleton.class){
                if(singleton==null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
//###################################################################


    private static class SINGLETON_HANDLER {
        public static Singleton singleton = new Singleton();
    }
    public static Singleton getInstance2(){
        return SINGLETON_HANDLER.singleton;
    }
}
