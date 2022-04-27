package com.zhisheng.books.introductiontojavaprogramming10th.chapter30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 30-6 存取线程交互的账户存取操作
 * 显示加锁，线程协作
 */
public class ThreadCooperation {
    private static Account account = new Account();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        System.out.println("Thread 1\t\t\tThread 2\t\t\tBalance");
        System.out.println("-------------------------------------------------------------------");

        executorService.execute(new DepositTask());
        executorService.execute(new WithdrawTask());
        executorService.shutdown();
    }

    /**
     * 存钱线程
     */
    public static class DepositTask implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    account.deposit((int) (Math.random() * 10) + 1);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 取钱线程
     */
    public static class WithdrawTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                account.withdraw((int) (Math.random() * 10) + 1);
            }
        }
    }

    /**
     * 账户类
     */
    private static class Account {
        private static Lock lock = new ReentrantLock();

        private static Condition newDepositAction = lock.newCondition();

        private int balance = 0;

        /**
         * Get balance int.
         *
         * @return the int
         */
        public int getBalance(){
            return balance;
        }

        /**
         * 取钱操作
         *
         * @param amount the amount
         */
        public void withdraw(int amount) {
            lock.lock();

            try {
                while (balance < amount) {
                    System.out.println("\t\t\t\tWant to withdraw " + amount + ", not enough, Wait for deposit...\n");
                    newDepositAction.await();
                }

//                balance =- amount; is fault
                balance -= amount;
                System.out.println("\t\t\t\t\tWithdraw " + amount + "\t\t\t" + getBalance());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        /**
         * 存钱操作
         *
         * @param amount the amount
         */
        public void deposit(int amount) {
            lock.lock();
             try {
                 balance += amount;
                 System.out.println("Deposit " + amount + "\t\t\t\t\t\t\t\t" + getBalance());

                 newDepositAction.signalAll();
             } finally {
                 lock.unlock();
             }
        }
    }
}
