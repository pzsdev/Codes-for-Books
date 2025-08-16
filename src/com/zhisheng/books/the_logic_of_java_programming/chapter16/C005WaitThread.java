package com.zhisheng.books.the_logic_of_java_programming.chapter16;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName C005WaitThread
 * @Description 使用显式条件进行协作
 * @Author pengzhisheng
 * @Date 2022/5/19 00:24
 * @Version 1.0
 **/
public class C005WaitThread extends Thread {
    private volatile boolean fire = false;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            System.out.println("thread " + Thread.currentThread().getName() + " will get lock");
            lock.lock();
            try {
                while (! fire) {
                    System.out.println("the condition fire is " + fire + ", so the thread " + Thread.currentThread().getName() + " will wait and release lock");
                    condition.await();
                }
            } finally {
                System.out.println("the thread "  + Thread.currentThread().getName() + " release the lock");
                lock.unlock();
            }
            System.out.println("fired");
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }

    public void fire() {
        System.out.println("thread " + Thread.currentThread().getName() + " will get lock");
        lock.lock();
        try {
            this.fire = true;
            System.out.println("the condition fire is " + fire + ", so the thread " + Thread.currentThread().getName() + " will invoke signal to the wait thread");
            condition.signal();
        } finally {
            System.out.println("the thread "  + Thread.currentThread().getName() + " release the lock");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        C005WaitThread waitThread = new C005WaitThread();
        waitThread.start();

        System.out.println("thread " + Thread.currentThread().getName() + " will sleep 1 second");
        Thread.sleep(1000);

        System.out.println("thread " + Thread.currentThread().getName() + " wake up");
        System.out.println("invoke fire()");
        waitThread.fire();
    }
}
