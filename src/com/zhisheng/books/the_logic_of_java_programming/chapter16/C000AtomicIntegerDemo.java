package com.zhisheng.books.the_logic_of_java_programming.chapter16;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName C000AtomicIntegerDemo
 * @Description 原子变量的使用
 * @Author pengzhisheng
 * @Date 2022/5/15 16:32
 * @Version 1.0
 **/
public class C000AtomicIntegerDemo {
    private static AtomicInteger counter = new AtomicInteger(0);

    static class Visitor extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        Thread[] threads = new Thread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new Visitor();
            threads[i].start();
        }
        for (int i = 0; i < num; i++) {
            threads[i].join();
        }

        System.out.println(counter.get());
    }
}
