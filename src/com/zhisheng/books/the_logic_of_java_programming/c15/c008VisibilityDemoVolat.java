package com.zhisheng.books.the_logic_of_java_programming.c15;

/**
 * @ClassName c008VisibilityDemoVolat
 * @Description 内存可见性问题，使用 volatile 解决
 * @Author pengzhisheng
 * @Date 2022/5/1 22:00
 * @Version 1.0
 **/
public class c008VisibilityDemoVolat {
    private static volatile boolean shutdown = false;
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
