package com.zhisheng.books.intrduction_to_java_programming_10th.chapter30;

public class Client {
    public static void main(String[] args) {
        ExecutorDemo executorDemo = new ExecutorDemo();
//        executorDemo.newFixedThreadPoolDemo(3);
//        executorDemo.newFixedThreadPoolDemo(1);
        executorDemo.newCachedThreadPoolDemo();
    }
}
