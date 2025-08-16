package com.zhisheng.books.the_logic_of_java_programming.chapter15;

import java.util.concurrent.Callable;

/**
 * @ClassName c015asynch
 * @Description  异步结果
 * @Author pengzhisheng
 * @Date 2022/5/3 03:34
 * @Version 1.0
 **/
public class c015asynch {
    public static void main(String[] args) {
        // 异步任务执行器
        c017MyExecutor executor = new c017MyExecutor();
        // 子任务
        Callable<Integer> subTask = () -> {
            // 执行异步任务
            int millis = (int) (Math.random() * 1000);
            Thread.sleep(millis);
            return millis;
        };

        // 异步调用，返回一个 MyFuture 对象
        c016MyFuture<Integer> future = executor.execute(subTask);
        // ...执行其他操作
        try {
            // 获取异步调用结果
            Integer result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
