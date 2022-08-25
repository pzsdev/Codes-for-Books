package com.zhisheng.books.understanding_the_jvm.c10;

/**
 * volatile test
 *
 * @author pengzhisheng
 * @since 2022/8/26
 **/
public class demo_12_1 {
    public static volatile int race = 0;
    public static void increase() {
        race++;
    }

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            });
            threads[i].start();
            System.out.println("thread " + threads[i].getName() + " start");
        }

        while (Thread.activeCount() > 2) {
//            System.out.println("the active thread count is " + Thread.activeCount());
//            ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
//            threadGroup.list();
            Thread.yield();
        }

        // 在 IDEA 中 使用 run 启动时，需要使用上面的代码
        //@see https://www.zhihu.com/question/59297272
//        while (Thread.activeCount() > 1) {
//            System.out.println("the active thread count is " + Thread.activeCount());
//            ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
//            threadGroup.list();
//            Thread.yield();
//        }

        System.out.println(race);
    }
}
