package com.zhisheng.books.the_logic_of_java_programming.c15;

/**
 * @ClassName c014RacerWithLatchDemo
 * @Description 同时开始
 * @Author pengzhisheng
 * @Date 2022/5/3 02:36
 * @Version 1.0
 **/
public class c014RacerWithLatchDemo {
    static class Racer extends Thread {
        c013WaitForTerminal.MyLatch latch;
        public Racer(c013WaitForTerminal.MyLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                System.out.println("wait for start " + Thread.currentThread().getName());
                this.latch.await();
                System.out.println("start run " + Thread.currentThread().getName());
            }  catch (InterruptedException e) {

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 10;
        c013WaitForTerminal.MyLatch latch = new c013WaitForTerminal.MyLatch(1);
        Thread[] racers = new Thread[num];
        for (int i = 0; i < num; i++) {
            racers[i] = new Racer(latch);
            racers[i].start();
        }
        Thread.sleep(1000);
        System.out.println("start all");
        latch.countDown();
    }
}
