package com.sail;

import java.util.concurrent.atomic.AtomicInteger;

public class MyInterImp implements MyInter {
    @Override
    public Object add(Object num1, Object num2) {
        AtomicInteger n1 = new AtomicInteger((Integer) num1);
//        AtomicInteger n2 = new AtomicInteger((Integer) num2);

        return n1.addAndGet((Integer) num2);
    }

    @Override
    public Object sub(Object num1, Object num2) {
        AtomicInteger n1 = new AtomicInteger((Integer) num1);
        n1.addAndGet(-(Integer)num2);
        return null;
    }
}
