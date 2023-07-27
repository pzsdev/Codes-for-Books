package com.zhisheng.books.the_logic_of_java_programming.c18;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName C002ScheduledException
 * @Description TODO
 * @Author pengzhisheng
 * @Date 2022/5/27 01:50
 * @Version 1.0
 **/
public class C002ScheduledException {
    static class TaskA implements Runnable {
        @Override
        public void run() {
            System.out.println("task A");
        }
    }

    static class TaskB implements Runnable {
        @Override
        public void run() {
            System.out.println("task B");
            System.out.println("task B throw a exception");
            throw new RuntimeException();
        }
    }

    /**
     * TaskA 和 TaskB 都是每秒执行一次，TaskB两秒后执行，但一执行就抛出异常
     * 此时，定时任务 TaskB 被取消了，但 TaskA 不受影响，即使它们是由同一个线程执行的。
     * 不过，需要强调的是，与 Timer 不同，没有异常被抛出，TaskB 的异常没有在任何地方体现。
     *
     * @param args
     */
    public static void main(String[] args) {
        ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
        timer.scheduleWithFixedDelay(new TaskA(), 0, 1, TimeUnit.SECONDS);
        timer.scheduleWithFixedDelay(new TaskB(), 2, 1, TimeUnit.SECONDS);
    }
}
