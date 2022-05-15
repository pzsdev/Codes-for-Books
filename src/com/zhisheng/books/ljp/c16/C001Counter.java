package com.zhisheng.books.ljp.c16;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName C001Counter
 * @Description TODO
 * @Author pengzhisheng
 * @Date 2022/5/15 16:38
 * @Version 1.0
 **/
public class C001Counter {
    private final Lock lock = new ReentrantLock();

    private volatile int count;

    public void incr() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}
