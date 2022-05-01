package com.zhisheng.books.ljp.c15;

/**
 * @ClassName c007VisibilityDemo
 * @Description 内存可见性
 * helloThread线程中的 shutdown 可能永远不会为 true
 *
 * 解决：1、使用 volatile 关键字；2、使用 synchronized 关键字
 * @Author pengzhisheng
 * @Date 2022/5/1 21:53
 * @Version 1.0
 **/
public class c007VisibilityDemo {
    private static boolean shutdown = false;
    static class HelloThread extends Thread {
        @Override
        public void run() {
            while (!shutdown) {
            }
            System.out.println("exit hello");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new HelloThread().start();
        Thread.sleep(1000);
        shutdown = true;
        System.out.println("exit main");
    }
}
