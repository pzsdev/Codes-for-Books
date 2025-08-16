package com.zhisheng.books.the_logic_of_java_programming.chapter16;

import java.util.Random;

/**
 * @ClassName C003AccountMgr
 * @Description 16-4
 * @Author pengzhisheng
 * @Date 2022/5/15 16:56
 * @Version 1.0
 **/
public class C003AccountMgr {
    public static class NoEnoughMoneyException extends Exception {

    }

    /**
     * 死锁
     * @param from
     * @param to
     * @param money
     * @throws NoEnoughMoneyException
     */
    public static void deadLockTransfer(C002Account from, C002Account to, double money) throws NoEnoughMoneyException{
        System.out.println("from account lock");
        from.lock();
        try {
            System.out.println("to account lock");
            to.lock();
            try {
                if (from.getMoney() >= money) {
                    from.reduce(money);
                    to.add(money);
                } else {
                    throw new NoEnoughMoneyException();
                }
            } finally {
                System.out.println("to account unlock");
                to.unlock();
            }
        } finally {
            System.out.println("to account unlock");
            from.unlock();
        }
    }

    /**
     * change method name to main to start
     * @param args
     */
    public static void main(String[] args) {
        final int accountNum = 10;
        final C002Account[] accounts = new C002Account[accountNum];
        System.out.println("create " + accountNum + " account");

        final Random rnd = new Random();
        for (int i = 0; i < accountNum; i++) {
            int money = rnd.nextInt(10000);
            System.out.println("the account " + i + " have money " + money);
            accounts[i] = new C002Account(money);
        }

        int threadNum = 100;
        Thread[] threads = new Thread[threadNum];
        System.out.println("init " + threadNum + " thread to transfer money");

        for (int i = 0; i < threadNum; i++) {

            threads[i] = new Thread(() -> {
                int loopNum = 100;
                for (int k = 0; k < loopNum; k++) {
                    int i1 = rnd.nextInt(accountNum);
                    int j = rnd.nextInt(accountNum);
                    int money = rnd.nextInt(10);
                    System.out.println("the from account is " + i1 + ", the to account is " + j + ", the transfer money is " + money);
                    if (i1 != j) {
                        try {
//                            deadLockTransfer(accounts[i1], accounts[j], money );
                            transfer(accounts[i1], accounts[j], money );
                        } catch (NoEnoughMoneyException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            System.out.println("the " + i + " thread is start");
            threads[i].start();
        }
        System.out.println("all transfer is done");
    }

    /**
     * 使用 tryLock 尝试转账
     * @param from
     * @param to
     * @param money
     * @return
     * @throws NoEnoughMoneyException
     */
    public static boolean tryTransfer(C002Account from, C002Account to, double money) throws NoEnoughMoneyException {
        if (from.tryLock()) {
            System.out.println("get the from lock");
            try {
                if (to.tryLock()) {
                    System.out.println("get the to lock");
                    try {
                        if (from.getMoney() > money) {
                            from.reduce(money);
                            to.add(money);
                        } else {
                            throw new NoEnoughMoneyException();
                        }
                        return true;
                    } finally {
                        System.out.println("release the to lock");
                        to.unlock();
                    }
                }
                System.out.println("not get the to lock");
            } finally {
                System.out.println("release the from lock");
                from.unlock();
            }
        }
        System.out.println("not get the from lock");
        return false;
    }

    public static void transfer(C002Account from, C002Account to, double money) throws NoEnoughMoneyException {
        boolean success = false;
        do {
            System.out.println("start to transfer the money");
            success = tryTransfer(from, to, money);
            if (! success) {
                System.out.println("fail, yield");
                Thread.yield();
            }
        } while (! success);
    }
}
