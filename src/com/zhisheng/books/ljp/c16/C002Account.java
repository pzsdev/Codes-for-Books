package com.zhisheng.books.ljp.c16;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName C002Account
 * @Description 16-3代码清单
 * @Author pengzhisheng
 * @Date 2022/5/15 16:40
 * @Version 1.0
 **/
public class C002Account {
    private Lock lock = new ReentrantLock();
    private volatile double money;
    public C002Account(double initialMoney) {
        this.money = initialMoney;
    }

    public void add(double money) {
        lock.lock();
        try {
            this.money += money;
        } finally {
            lock.unlock();
        }
    }

    public void reduce(double money) {
        lock.lock();
        try {
            this.money -= money;
        } finally {
            lock.unlock();
        }
    }

    public double getMoney() {
        return money;
    }

    void lock() {
        lock.lock();
    }

    void unlock() {
        lock.unlock();
    }

    boolean tryLock() {
        return lock.tryLock();
    }
}
