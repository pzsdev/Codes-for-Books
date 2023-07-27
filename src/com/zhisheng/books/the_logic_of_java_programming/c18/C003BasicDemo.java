package com.zhisheng.books.the_logic_of_java_programming.c18;

import java.util.Random;
import java.util.concurrent.*;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2022/8/10
 **/
public class C003BasicDemo {
    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("call()......start");
            int sleepSeconds = new Random().nextInt(1000);
            Thread.sleep(sleepSeconds);
            System.out.println("call()......end");
            return sleepSeconds;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Integer> future = executorService.submit(new C003BasicDemo.Task());

        // 模拟执行其他任务
        Thread.sleep(1000);
        System.out.println("main thread do something......");
        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
