package com.zhisheng.books.ljp.c15;

/**
 * @ClassName c003CounterThread
 * @Description 竞态条件
 * 预期 counter 是 1000000，但由于多线程的竞态条件，counter++ 不是原子操作
 *
 * 解决办法：1、使用 synchronized 在共享变量的操作处加锁；2、使用显示锁；3、使用原子变量
 * @Author pengzhisheng
 * @Date 2022/5/1 21:31
 * @Version 1.0
 **/
public class c003CounterThread extends Thread{
    private static int counter = 0;
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        Thread[] threads = new Thread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new c003CounterThread();
            threads[i].start();
        }
        for (int i = 0; i < num; i++) {
            threads[i].join();
        }

        System.out.println(counter);
    }
}
