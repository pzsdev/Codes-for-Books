package com.zhisheng.books.ljp.c15;

/**
 * @ClassName c012StartInTheSameTime
 * @Description 同时开始
 * @Author pengzhisheng
 * @Date 2022/5/2 20:06
 * @Version 1.0
 **/
public class c012StartInTheSameTime {
    // 协作对象
    public static class FireFlag {
        private volatile boolean fired = false;

        public synchronized void waitForFire() throws InterruptedException {
            while (!fired) {
                wait();
            }
        }
        public synchronized void fire() {
            this.fired = true;
            notifyAll();
        }
    }

    // 比赛运动员类
    public static class Racer extends Thread {
        FireFlag fireFlag;
        public Racer(FireFlag fireFlag) {
            this.fireFlag = fireFlag;
        }

        @Override
        public void run() {
            try {
                System.out.println("racer in the start line, " + Thread.currentThread().getName());
                this.fireFlag.waitForFire();
                System.out.println("start run " + Thread.currentThread().getName());
            } catch (InterruptedException e) {

            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        int num = 10;
        FireFlag fireFlag = new FireFlag();
        Thread[] racers = new Thread[num];
        for (int i = 0; i < num; i++) {
            racers[i] = new Racer(fireFlag);
            racers[i].start();
        }
        Thread.sleep(1000);
        fireFlag.fire();
    }
}
