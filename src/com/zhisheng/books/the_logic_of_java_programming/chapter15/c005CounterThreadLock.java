package com.zhisheng.books.the_logic_of_java_programming.chapter15;

/**
 * @ClassName c005CounterThreadLock
 * @Description TODO 竞态条件，使用 显示锁 解决
 * @Author pengzhisheng
 * @Date 2022/5/1 21:43
 * @Version 1.0
 **/
public class c005CounterThreadLock extends Thread{
//    private static volatile int counter = 0;
//
//    private final Lock lock = new ReentrantLock();
//
//    @Override
//    public void run() {
//
//            lock.lock();
//            try {
//                for (int i = 0; i < 1000; i++) {
//                counter++;
//                }
//            } finally {
//                lock.unlock();
//            }
//
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        int num = 1000;
//        Thread[] threads = new Thread[num];
//        for (int i = 0; i < num; i++) {
//            threads[i] = new c005CounterThreadLock();
//            threads[i].start();
//        }
//        for (int i = 0; i < num; i++) {
//            threads[i].join();
//        }
//
//        System.out.println(counter);
//    }
}
