package com.zhisheng.books.the_logic_of_java_programming.chapter15;

/**
 * @ClassName c013WaitForTerminal
 * @Description 等待结束
 * @Author pengzhisheng
 * @Date 2022/5/3 02:25
 * @Version 1.0
 **/
public class c013WaitForTerminal {
    public static class MyLatch {
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

    public static class Worker extends Thread {
        MyLatch latch;
        public Worker(MyLatch myLatch) {
            this.latch = myLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep((int) (Math.random() * 100));
                this.latch.countDown();
            } catch (InterruptedException e) {

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int workerNum = 100;
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
}
