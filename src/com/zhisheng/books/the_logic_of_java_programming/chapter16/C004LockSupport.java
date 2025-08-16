package com.zhisheng.books.the_logic_of_java_programming.chapter16;

import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName C004LockSupport
 * @Description LockSupport 类的使用示例
 * 主线程启动子线程 t，线程 t 启动后调用 park，放弃 CPU，
 * 主线程睡眠 1 秒以确保子线程已执行 LockSupport.park()后，调用 unpark，线程 t 恢复运行，输出 exit。
 *
 * @Author pengzhisheng
 * @Date 2022/5/18 23:40
 * @Version 1.0
 **/
public class C004LockSupport {
    public static void main(String[] args) throws InterruptedException{
        Thread t = new Thread(() -> {
            System.out.println("thread " + Thread.currentThread().getName() + " is " + Thread.currentThread().getState() + ", then will park");
            LockSupport.park();
            System.out.println("thread " + Thread.currentThread().getName() + " is " + Thread.currentThread().getState());
            System.out.println("thread " + Thread.currentThread().getName() + " exit");
        });

        t.start();

        System.out.println("thread " + Thread.currentThread().getName() + " will sleep 1 second");
        Thread.sleep(1000);

        System.out.println("thread " + Thread.currentThread().getName() + " is wake up from sleep");
        System.out.println("thread " + t.getName() + " is " + t.getState());
        System.out.println("thread " + Thread.currentThread().getName() + " invoke unpark for t");
        LockSupport.unpark(t);
    }
}
