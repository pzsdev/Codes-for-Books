package com.zhisheng.books.ljp.c15;

/**
 * @ClassName HelloThread
 * @Description TODO
 * @Author pengzhisheng
 * @Date 2022/4/28 01:09
 * @Version 1.0
 **/
public class HelloThread extends Thread {
    @Override
    public void run() {
        System.out.println("hello");
        Thread thread = Thread.currentThread();
        System.out.println("the current thread is " + thread.getName() + ", the id is " + thread.getId());
    }

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println("the main thread is " + thread.getName() + ", the id is " + thread.getId());

        Thread helloThread = new HelloThread();
        helloThread.start();
    }
}
