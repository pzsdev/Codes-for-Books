package com.zhisheng.books.ljp.c15;

/**
 * @ClassName c010WaitThread
 * @Description wait/notify 示例
 * @Author pengzhisheng
 * @Date 2022/5/1 22:47
 * @Version 1.0
 **/
public class c010WaitThread extends Thread{
    private volatile boolean fire = false;

    @Override
    public void run() {
        try {
            synchronized (this) {
                System.out.println("before wait(), the thread status is " + Thread.currentThread().getState());
                System.out.println("check the fire");
                while (! fire) {
                    System.out.println("before wait(), after while, the thread status is " + Thread.currentThread().getState());
//                    wait();
                    wait(10000);
                    System.out.println("after wait(), the thread status is " + Thread.currentThread().getState());
                }
            }
            System.out.println("fired");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void fire() {
        this.fire = true;
        System.out.println("before notify(), the thread status is " + Thread.currentThread().getState());
        notify();
        System.out.println("after notify(), the thread status is " + Thread.currentThread().getState());
    }

    public static void main(String[] args) throws InterruptedException {
        c010WaitThread waitThread = new c010WaitThread();
        waitThread.start();
        System.out.println("before sleep(), the thread status is " + waitThread.getState());
        Thread.sleep(2000);
        System.out.println("after sleep(), the thread status is " + waitThread.getState());
        System.out.println("fire");
        waitThread.fire();
    }
}
