package com.zhisheng.books.the_logic_of_java_programming.chapter15;

import java.util.*;
import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/10/20
 **/
public class ChapterFifteenth {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println(Thread.currentThread().getName() + " thread start");
//        HelloThread helloThread = new HelloThread();
//        helloThread.start();
////        try {
////            helloThread.join(); // 主线程等待分支线程结束
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//
//        System.out.println(Thread.currentThread().getName() + " thread exit");

//        DeaLockDemo deaLockDemo = new DeaLockDemo();
//        deaLockDemo.startThreadA();
//        deaLockDemo.startThreadB();

        ChapterFifteenth chapterFifteenth = new ChapterFifteenth();
//        chapterFifteenth.waitThreadDemo();

        // 生产者/消费者模式
//        MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);
//        new Producer(queue).start();
//        new Consumer(queue).start();

//        chapterFifteenth.startInTheSameTime();
//        chapterFifteenth.waitForFinish();
//        chapterFifteenth.executeDemo();
        chapterFifteenth.interruptDemo();
    }

    /**
     * 子线程状态为 RUNNABLE，设置中断位后，子线程根据中断位状态判断任务的执行
     * @throws InterruptedException
     */
    private void interruptDemo() throws InterruptedException {
        System.out.println("执行主逻辑");
        InterruptRunnableDemo interruptRunnableDemo = new InterruptRunnableDemo();
        interruptRunnableDemo.start();
        System.out.println("“等待");
        sleep(1000);
        System.out.println("中断");
        interruptRunnableDemo.interrupt();
        System.out.println("中断位是 " + interruptRunnableDemo.isInterrupted());
        interruptRunnableDemo.interrupt();
        System.out.println("中断位是 " + interruptRunnableDemo.isInterrupted());
    }

    public static class InterruptRunnableDemo extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
//            while (!interrupted()) {
                // ... 单次循环代码
                System.out.println(Thread.currentThread().getName() + " is running");
            }
            System.out.println("done");
        }
    }

    private void executeDemo() {
        MyExecutor executor = new MyExecutor();
        // 子任务
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("call()......start");
                int sleepSeconds = new Random().nextInt(1000);
                sleep(sleepSeconds);
                System.out.println("call()......end");
                return sleepSeconds;
            }
        };

        // 异步调用
        MyFuture<Integer> future = executor.execute(callable);
        // ...执行其他操作
        System.out.println("执行其他操作");
        try {
            // 获取异步结果
            Integer result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void waitForFinish() throws InterruptedException {
        int workerNum = 10;
        MyLatch latch = new MyLatch(workerNum);
        Worker[] workers = new Worker[workerNum];
        for (int i = 0; i < workerNum; i++) {
            workers[i] = new Worker(latch);
            workers[i].start();
        }
        System.out.println("main wait ...");
        latch.await();
        System.out.println("collect worker results");
    }

    private static class Worker extends Thread {
        MyLatch latch;
        public Worker(MyLatch myLatch) {
            this.latch = myLatch;
        }

        @Override
        public void run() {
            try {
                sleep((int) Math.random() * 1000);
                this.latch.countDown();
            } catch (InterruptedException e) {

            }
        }
    }

    /**
     * 等待结束协作类
     */
    public class MyLatch {
        private int count;
        public MyLatch(int count) {
            this.count = count;
        }
        public synchronized void await() throws InterruptedException {
            while (count > 0) {
                wait();
            }
        }
        public synchronized void countDown() {
            count--;
            System.out.println("count down ...");
            if (count <= 0) {
                System.out.println("notify all ...");
                notifyAll();
            }
        }
    }

    private void startInTheSameTime() throws InterruptedException {
        int num = 10;
        FireFlag fireFlag = new FireFlag();
        Thread[] threads = new Thread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new Racer(fireFlag);
            threads[i].start();
        }
        sleep(1000);
        fireFlag.fire();
    }
    private static class Racer extends Thread {
        private FireFlag fireFlag;
        public Racer(FireFlag fireFlag) {
            this.fireFlag = fireFlag;
        }

        @Override
        public void run() {
            try {
                this.fireFlag.waitForFire();
                System.out.println("start run " + Thread.currentThread().getName());
            } catch (InterruptedException e) {

            }
        }
    }

    /**
     * 同时开始模式
     */
    private static class FireFlag {
        private volatile boolean fired = false;
        public synchronized void waitForFire() throws InterruptedException {
            while (!fired) {
                wait();
            }
        }

        public synchronized void fire() {
            fired = true;
            notifyAll();
        }
    }

    private static class Consumer extends Thread {
        private MyBlockingQueue<String> queue;
        public Consumer(MyBlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String task = queue.take();
                    System.out.println("handle task " + task);
                    sleep((int) (Math.random() * 100));
                }
            } catch (InterruptedException e) {

            }
        }
    }

    private static class Producer extends Thread {
        private MyBlockingQueue<String> queue;
        public Producer(MyBlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (true) {
                    String task = String.valueOf(num);
                    queue.put(task);
                    System.out.println("produce task " + task);
                    num++;
                    sleep((int) (Math.random() * 100));
                }
            } catch (InterruptedException e) {

            }
        }
    }

    /**
     * 生产/消费者阻塞队列
     * @param <E>
     */
    private static class MyBlockingQueue<E> {
        private Queue<E> queue = null;
        private int limit;
        public MyBlockingQueue(int limit) {
            this.limit = limit;
            queue = new ArrayDeque<>(limit);
        }

        public synchronized void put(E e) throws InterruptedException {
            while (queue.size() == limit) {
                wait();
            }
            queue.add(e);
            notifyAll();
        }

        public synchronized E take() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }
            E e = queue.poll();
            notifyAll();
            return e;
        }
    }

    private void waitThreadDemo () throws InterruptedException {
        WaitThread waitThread = new WaitThread();
        waitThread.start();
        sleep(1000);
        System.out.println("fire");
        waitThread.fire();
    }

    private static class WaitThread extends Thread {
        private volatile boolean fire = false;

        @Override
        public void run() {
            try {
                synchronized (this) {
                    while (!fire) {
                        this.wait();
                    }
                }
                System.out.println("fired");
            } catch (InterruptedException e) {

            }
        }
        public synchronized void fire() {
            this.fire = true;
            this.notify();
        }
    }

    /**
     * 死锁演示，使用 jstack 查看
     */
    private static class DeaLockDemo {
        private static Object lockA = new Object();
        private static Object lockB = new Object();

        private void startThreadA() {
            Thread aThread = new Thread() {
                @Override
                public void run() {
                    synchronized (lockA) {
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {

                        }
                        synchronized (lockB) {
                            System.out.println("lockA and lockB");
                        }
                    }
                }
            };
            aThread.start();
        }

        private void startThreadB() {
            Thread bThread = new Thread() {
                @Override
                public void run() {
                    synchronized (lockB) {
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {

                        }
                        synchronized (lockA) {
                            System.out.println("lockB and lockA");
                        }
                    }
                }
            };
            bThread.start();
        }
    }

    private static class HelloThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " thread start");
            System.out.println("hello");
            System.out.println(Thread.currentThread().getName() + " thread exit");
        }
    }
}
