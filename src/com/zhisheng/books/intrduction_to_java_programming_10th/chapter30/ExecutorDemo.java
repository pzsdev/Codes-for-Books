package com.zhisheng.books.intrduction_to_java_programming_10th.chapter30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 30-3 线程池的使用
 */
public class ExecutorDemo {
    /**
     * 创建指定数目的线程，其中的任一线程用完后可以复用
     * 当数量为 1 时，直接用 Thread 类就可以
     *
     * @param sum 创建线程数量
     */
    public void newFixedThreadPoolDemo(int sum) {
        ExecutorService executorService = Executors.newFixedThreadPool(sum);

        executorService.execute(new PrintChar('a', 100));
        executorService.execute(new PrintChar('b', 100));
        executorService.execute(new PrintNum(100));

        // 通知执行器关闭，但现有的任务将继续执行完成
        executorService.shutdown();
    }

    /**
     * 只要有任务在等待线程，就会创建新的线程，运行的任务都是并发进行
     */
    public void newCachedThreadPoolDemo() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new PrintChar('a', 100));
        executorService.execute(new PrintChar('b', 100));
        executorService.execute(new PrintNum(100));

        executorService.shutdown();
    }
}

