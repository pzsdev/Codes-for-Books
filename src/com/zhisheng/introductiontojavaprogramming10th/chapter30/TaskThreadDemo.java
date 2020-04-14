package com.zhisheng.introductiontojavaprogramming10th.chapter30;

/**
 * 30-1
 */
public class TaskThreadDemo {
    public static void main(String[] args){
        Runnable printA = new PrintChar('a', 100);
        Runnable printB = new PrintChar('b', 100);
        Runnable print100 = new PrintNum(100);
        Runnable print4 = new printNumUsingJoin(100);
        Runnable printNumUsingYield = new printNumUsingYield(100);
        Runnable printNumUsingSleep = new printNumUsingSleep(100);

        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(print100);
        Thread thread4 = new Thread(print4);
        Thread thread5 = new Thread(printNumUsingYield);
        Thread thread6 = new Thread(printNumUsingSleep);

        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//        thread5.start();
        thread6.start();
    }
}

class PrintChar implements Runnable {
    private char charToPrint;
    private int times;

    public PrintChar(char charToPrint, int times) {
        this.charToPrint = charToPrint;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.print(charToPrint + "\t");
        }
    }
}

class PrintNum implements Runnable {
    private int lastNum;

    public PrintNum(int lastNum){
        this.lastNum = lastNum;
    }

    @Override
    public void run() {
        for (int i = 0; i <= lastNum; i++) {
            System.out.print(" " + i);
        }
    }
}

/**
 * yield() 方法让出 CPU 时间不明显，有时几乎和另一线程还是交替进行
 */
class printNumUsingYield implements Runnable {
    private int lastNum;

    public printNumUsingYield(int lastNum) {
        this.lastNum = lastNum;
    }

    @Override
    public void run() {
        for (int i = 1; i <= lastNum; i ++) {
            System.out.print(" " + i);
            Thread.yield();
        }
    }
}

/**
 * sleep() 方法可能抛出一个必检异常 InterruptedException，必须捕获
 * 当休眠线程的 interrupt() 方法被调用时，会抛出这个异常
 */
class printNumUsingSleep implements Runnable {
    private int lastNum;

    public printNumUsingSleep(int lastNum) {
        this.lastNum = lastNum;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= lastNum; i++) {
                System.out.print(" " + i);
                if (i >= 50) {
                    Thread.sleep(1);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * printUsingJoin 方法启动时，两个线程同时运行，
 * 此时交替打印出字符、数字，
 * 当数字打印到 50 的时候，就等待 字符全部打印完成后，再继续打印数字。
 *
 * join（）会阻塞当前线程，只有等阻塞的线程（这里的 thread5）结束了，当前线程才会继续进行
 */
class printNumUsingJoin implements Runnable {
    private int lastNum;

    public printNumUsingJoin(int lastNum){
        this.lastNum = lastNum;
    }

    @Override
    public void run() {
        Thread thread5 = new Thread(
                new PrintChar('c', 400)
        );
        thread5.start();

        try {
            for (int i = 1; i <= lastNum; i ++) {
                System.out.print(" " + i);
                if (i == 50) {
                    thread5.join();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}