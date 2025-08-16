package com.zhisheng.books.the_logic_of_java_programming.chapter15;

/**
 * @ClassName c009VisibilityDemoSync
 * @Description 对于共享变量的所有操作都要加 synchronized
 * @Author pengzhisheng
 * @Date 2022/5/1 22:02
 * @Version 1.0
 **/
public class c009VisibilityDemoSync {
    private static boolean shutdown = false;

    protected static synchronized boolean getShutdown () {
        return shutdown;
    }

    protected static synchronized void setShutdown() {
        shutdown = true;
    }

    static class HelloThread extends Thread {
        @Override
        public void run() {
            while (!getShutdown()) {
            }
            System.out.println("exit hello");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new HelloThread().start();
        Thread.sleep(1000);
        setShutdown();
        System.out.println("exit main");
    }
}
