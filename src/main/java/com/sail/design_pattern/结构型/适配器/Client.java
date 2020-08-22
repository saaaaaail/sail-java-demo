package com.sail.design_pattern.结构型.适配器;

/**
 * @program: sail-java-demo
 * @description: TODO
 * @author: sail
 * @create: 2020/8/5 15:15
 */

//用鸭子包装火鸡，鸭子已经定义好如何传入火鸡，可以继承鸭子实现子类，传入火鸡重写方法。
public class Client {
    public static void main(String[] args) {
        Turkey turkey = new WildTurkey();
        Duck duck = new TurkeyAdapter(turkey);
        duck.quack();
    }
}
