package com.zhisheng.books.ljp.c16;

import com.zhisheng.books.ljp.c15.c011ProducerAndConsumer;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName C006MyBlockingQueue
 * @Description 使用显式锁/条件实现阻塞队列
 * @Author pengzhisheng
 * @Date 2022/5/19 00:40
 * @Version 1.0
 **/
public class C006MyBlockingQueue<E> {
    private Queue<E> queue = null;
    private final int limit;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public C006MyBlockingQueue(int limit) {
        this.limit = limit;
        queue = new ArrayDeque<>(limit);
    }

    public void put(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (queue.size() == limit) {
                System.out.println("the queue is full, so wait take");
                notFull.await();
            }
            queue.add(e);
            System.out.println("the queue is add new element, so signal to take");
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (queue.isEmpty()) {
                System.out.println("the queue is empty, so wait put");
                notEmpty.await();
            }
            E e = queue.poll();
            System.out.println("the queue is not full, signal to put");
            notFull.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }

    public static class Producer extends Thread {
        C006MyBlockingQueue<String> queue;
        public Producer(C006MyBlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (true) {
                    String task = String.valueOf(num);
                    queue.put(task);
                    System.out.println("produce task " + task);
                    num++;
                    Thread.sleep((int) (Math.random() * 100));
                }
            } catch (InterruptedException e) {

            }
        }
    }

    public static class Consumer extends Thread {
        C006MyBlockingQueue<String> queue;
        public Consumer(C006MyBlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String task = queue.take();
                    System.out.println("handle task " + task);
                    Thread.sleep((int) (Math.random() * 100));
                }
            } catch (InterruptedException e) {

            }
        }
    }

    public static void main(String[] args) {
        C006MyBlockingQueue<String> queue = new C006MyBlockingQueue<>(10);
        new Producer(queue).start();
        new Consumer(queue).start();
    }
}
