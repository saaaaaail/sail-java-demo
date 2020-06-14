package com.sail;

import java.util.AbstractList;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class LockFreeVector<T> extends AbstractList<T> {

    private static final int FIRST_BUCKET_SIZE=8;

    private static final int N_BUCKET=30;

    private final AtomicReferenceArray<AtomicReferenceArray<T>> buckets=null;

    @Override
    public T get(int index) {
        return null;
    }


    @Override
    public int size() {
        return 0;
    }
}
