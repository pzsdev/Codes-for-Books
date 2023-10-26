package com.zhisheng.books.the_logic_of_java_programming.chapter16;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/10/24
 **/
public class ChapterSixteenth {
    public static void main(String[] args) {

    }

    private void reentrantLockDemo() {
        Lock lock = new ReentrantLock();
        lock.lock();
    }
}
