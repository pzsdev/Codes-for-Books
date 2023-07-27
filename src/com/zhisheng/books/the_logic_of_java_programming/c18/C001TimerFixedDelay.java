package com.zhisheng.books.the_logic_of_java_programming.c18;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName C001TimerFixedDelay
 * @Description 创建两个定时任务，
 * 第一个运行一次，耗时 5 秒；
 * 第二个重复运行，1 秒一次，第一个先运行。
 *
 * 运行后会发现，第二个任务只有在第一个任务运行结束后才会开始运行，运行后 1 秒一次。
 *
 * @Author pengzhisheng
 * @Date 2022/5/26 23:55
 * @Version 1.0
 **/
public class C001TimerFixedDelay {
    static class LongRunningTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("long running start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("long running finished.");
        }
    }

    static class FixedDelayTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("current time millis is " + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        Timer timer = new Timer();

//        timer.schedule(new LongRunningTask(), 10);
//        timer.schedule(new FixedDelayTask(), 100, 1000);

        // Timer 固定频率
        // 运行下面两条，第二个任务同样只有在第一个结束后才运行，但它会把之前没有运行的次数补过来，一下子运行 5 次
//        timer.schedule(new LongRunningTask(), 10);
//        System.out.println("fixedDelayTask is start");
//        timer.scheduleAtFixedRate(new FixedDelayTask(), 100, 1000);


        // ScheduledExecutorService
        // 由于可以有多个线程执行定时任务，一般任务就不会被某个长时间运行的任务所延迟了
        // 这里两个任务时同时开始，第二个不用等第一个执行完再执行了
        ScheduledExecutorService timer = Executors.newScheduledThreadPool(10);
        timer.schedule(new LongRunningTask(), 10, TimeUnit.MILLISECONDS);
        timer.scheduleWithFixedDelay(new FixedDelayTask(), 100, 1000, TimeUnit.MILLISECONDS);
    }
}
