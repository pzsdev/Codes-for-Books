package com.zhisheng.introductiontojavaprogramming10th.chapter30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 30-4 线程间的竞争状态
 * 100 个线程，对同一个数累加 100 次
 */
public class AccountWithoutSync {
    private static Account account = new Account();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            executorService.execute(new AddPeenTask());
        }

        // 关闭线程执行器，现有线程中的任务执行完成
        executorService.shutdown();

        while (!executorService.isTerminated()) {
            System.out.print("the programming is running ... \n");
        }

        System.out.print("What is balance? is ----->     " + account.getBalance() + "\n");
    }


    /**
     * 新线程内部类
     */
    private static class AddPeenTask implements Runnable {
        public void run() {
//            account.depositWithSleep(1);
//            account.deposit(1);
            account.depositWithSleepAndSync(1);
        }
    }

    private static class Account {
        private int balance = 0;

        /**
         * Gets balance.
         *
         * @return the balance
         */
        public int getBalance() {
            return balance;
        }

        /**
         * 每个线程故意休眠 5 毫秒，放大线程间的数据破坏的可能性
         *
         * @param amount the amount
         */
        public void depositWithSleep(int amount) {
            int newBalance = balance + amount;

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            balance = newBalance;
        }

        /**
         * Deposit.
         *
         * @param amount the amount
         */
        public void deposit(int amount) {
            int sum = balance + amount;
            balance = sum;
        }

        /**
         * 加锁
         *
         * @param amount 存的金额
         */
        public synchronized void depositWithSleepAndSync(int amount) {
            int newBalance = balance + amount;

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            balance = newBalance;
        }
    }
}
