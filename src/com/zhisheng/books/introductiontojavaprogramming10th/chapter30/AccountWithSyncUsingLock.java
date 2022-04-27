package com.zhisheng.books.introductiontojavaprogramming10th.chapter30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 30-5 The type Account with sync using lock.
 */
public class AccountWithSyncUsingLock {
    private static Account account = new Account();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            executorService.execute(new AddPennyTask());
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            System.out.print("the programming is running ... \n");
        }

        System.out.print("What is balance? is --------->   " + account.getBalance());
    }


    /**
     * The type Add penny task.
     */
    public static class AddPennyTask implements Runnable {
        public void run() {
            account.deposit(1);
        }
    }

    /**
     * 显式地在类中添加同步锁
     */
    public static class Account {
        private static Lock lock = new ReentrantLock();
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
         * Deposit.
         *
         * @param amount the amount
         */
        public void deposit(int amount) {
            lock.lock();

            try {
                int newBalance = balance + amount;

                Thread.sleep(5);
                balance = newBalance;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
