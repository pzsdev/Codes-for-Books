package com.zhisheng.books.the_logic_of_java_programming.chapter15;

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
        chapterFifteenth.waitThreadDemo();
    }

    private void waitThreadDemo () throws InterruptedException {
        WaitThread waitThread = new WaitThread();
        waitThread.start();
        Thread.sleep(1000);
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
                            Thread.sleep(1000);
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
                            Thread.sleep(1000);
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
