package com.zhisheng.books.ljp.c19;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2022/8/10
 **/
public class C001CyclicBarrierDemo {
    static class Tourise extends Thread {
        CyclicBarrier barrier;

        public Tourise(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                // 模拟先各自独立运行
                Thread.sleep((int) (Math.random() * 1000));

                // 集合点 A
                barrier.await();
                System.out.println(this.getName() + " arrived A " + System.currentTimeMillis());
                // 集合后模拟再各自独立运行
                Thread.sleep((int) (Math.random() * 1000));

                // 集合点 B
                barrier.await();
                System.out.println(this.getName() + " arrived B " + System.currentTimeMillis());
            } catch (InterruptedException e) {

            } catch (BrokenBarrierException e) {

            }
        }
    }

    public static void main(String[] args) {
        int num = 3;
        Tourise[] tourises = new Tourise[num];
        CyclicBarrier barrier = new CyclicBarrier(num, new Runnable() {
            @Override
            public void run() {
                System.out.println("all arrived " + System.currentTimeMillis() + " executed by " + Thread.currentThread().getName());
            }
        });

        for (int i = 0; i < num; i++) {
            tourises[i] = new Tourise(barrier);
            tourises[i].start();
        }
    }
}
