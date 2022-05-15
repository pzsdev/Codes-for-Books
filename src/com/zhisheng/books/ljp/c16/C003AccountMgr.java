package com.zhisheng.books.ljp.c16;

import java.util.Random;

/**
 * @ClassName C003AccountMgr
 * @Description TODO
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
        from.lock();
        try {
            to.lock();
            try {
                if (from.getMoney() >= money) {
                    from.reduce(money);
                    to.add(money);
                } else {
                    throw new NoEnoughMoneyException();
                }
            } finally {
                to.unlock();
            }
        } finally {
            from.unlock();
        }
    }

    public static void simulateDeadLock() {
        final int accountNum = 10;
        final C002Account[] accounts = new C002Account[accountNum];

        final Random rnd = new Random();
        for (int i = 0; i < accountNum; i++) {
            accounts[i] = new C002Account(rnd.nextInt(10000));
        }

        int threadNum = 100;
        Thread[] threads = new Thread[threadNum];

        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(() -> {
                int loopNum = 100;
                for (int k = 0; k < loopNum; k++) {
                    int i1 = rnd.nextInt(accountNum);
                    int j = rnd.nextInt(accountNum);
                    int money = rnd.nextInt(10);
                    if (i1 != j) {
                        try {
                            deadLockTransfer(accounts[i1], accounts[j], money );
                        } catch (NoEnoughMoneyException e) {

                        }
                    }
                }
            });

            threads[i].start();
        }
    }
}
