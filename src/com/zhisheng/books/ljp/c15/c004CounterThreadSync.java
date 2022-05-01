package com.zhisheng.books.ljp.c15;

/**
 * @ClassName c004CounterThreadSync
 * @Description 竞态条件，使用 synchronized 解决
 * @Author pengzhisheng
 * @Date 2022/5/1 21:40
 * @Version 1.0
 **/
public class c004CounterThreadSync extends Thread{
    private static int counter = 0;
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            synchronized (c004CounterThreadSync.class) {
                counter++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        Thread[] threads = new Thread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new c004CounterThreadSync();
            threads[i].start();
        }
        for (int i = 0; i < num; i++) {
            threads[i].join();
        }

        System.out.println(counter);
    }
}
