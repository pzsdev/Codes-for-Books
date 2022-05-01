package com.zhisheng.books.ljp.c15;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName c002ShareMeoryDemo
 * @Description 多线程共享内存
 * @Author pengzhisheng
 * @Date 2022/5/1 21:23
 * @Version 1.0
 **/
public class c002ShareMemoryDemo {
    private static int shared = 0;
    private static void incrShared() {
        shared++;
    }

    static class ChildThread extends Thread {
        List<String> list;
        public ChildThread(List<String> list) {
            this.list = list;
        }
        @Override
        public void run() {
            incrShared();
            list.add(Thread.currentThread().getName());
        }

    }
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        Thread t1 = new ChildThread(list);
        Thread t2 = new ChildThread(list);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(shared);
        System.out.println(list);
    }

}
