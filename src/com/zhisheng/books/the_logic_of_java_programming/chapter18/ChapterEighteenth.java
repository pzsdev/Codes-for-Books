package com.zhisheng.books.the_logic_of_java_programming.chapter18;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/10/28
 **/
public class ChapterEighteenth {
    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int sleepSeconds = new Random().nextInt(10000);
            Thread.sleep(sleepSeconds);
            return sleepSeconds;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(new Task());
        // 模拟执行其他任务
        Thread.sleep(1000);
        System.out.println("main thread do something......");
        try {
            System.out.println("call()......start");
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
