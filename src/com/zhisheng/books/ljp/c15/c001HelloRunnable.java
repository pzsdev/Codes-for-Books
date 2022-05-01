package com.zhisheng.books.ljp.c15;

/**
 * @ClassName HelloRunnable
 * @Description TODO
 * @Author pengzhisheng
 * @Date 2022/4/28 01:15
 * @Version 1.0
 **/
public class c001HelloRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("hello runnable");
        Thread thread = Thread.currentThread();
        System.out.println("the current thread is " + thread.getName() + ", the id is " + thread.getId());

        System.out.println("the current thread priority is " + thread.getPriority());
        thread.setPriority(2);
        System.out.println("the current thread priority is " + thread.getPriority());

        System.out.println("the current thread status is " + thread.getState());

        System.out.println("the current thread isAlive is " + thread.isAlive());
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = Thread.currentThread();
        System.out.println("the main thread is " + thread.getName() + ", the id is " + thread.getId());
        System.out.println("the main priority is " + thread.getPriority());
        System.out.println("the main thread status is " + thread.getState());


        c001HelloRunnable c001HelloRunnable = new c001HelloRunnable();
        Thread otherThread = new Thread(c001HelloRunnable);
        System.out.println("the current thread status is " + otherThread.getState());
        System.out.println("the current thread isAlive is " + otherThread.isAlive());
        otherThread.start();
        System.out.println("\nthe otherThread thread start ");
//        otherThread.join();
//        System.out.println("after otherThread is join, the main thread status is " + thread.getState());

        System.out.println("the current thread status is " + otherThread.getState());
        System.out.println("the current thread isAlive is " + otherThread.isAlive());
        System.out.println("the current thread isDaemon is " + otherThread.isDaemon());

        Thread.sleep(1000L);
        System.out.println("\nthe main thread sleep ");
        System.out.println("the main thread status is " + thread.getState());

        System.out.println("the current thread status is " + otherThread.getState());
        System.out.println("the current thread isAlive is " + otherThread.isAlive());
        System.out.println("the current thread isDaemon is " + otherThread.isDaemon());
        System.out.println("the main thread status is " + thread.getState());
    }
}
