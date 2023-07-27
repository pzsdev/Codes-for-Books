package com.zhisheng.books.the_logic_of_java_programming.c15;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @ClassName c011ProducerAndConsumer
 * @Description 生产者/消费者模式
 * @Author pengzhisheng
 * @Date 2022/5/2 19:49
 * @Version 1.0
 **/
public class c011ProducerAndConsumer {
    public static class MyBlockingQueue<E> {
        private Queue<E> queue = null;
        private int limit;
        public MyBlockingQueue(int limit) {
            this.limit = limit;
            queue = new ArrayDeque<>(limit);
        }

        public synchronized void put(E e) throws InterruptedException {
            while (queue.size() == limit) {
                wait();
            }
            queue.add(e);
            notifyAll();
        }

        public synchronized E take() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }
            E e = queue.poll();
            notifyAll();
            return e;
        }
    }

    public static class Producer extends Thread {
        MyBlockingQueue<String> queue;
        public Producer(MyBlockingQueue<String> queue) {
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
        MyBlockingQueue<String> queue;
        public Consumer(MyBlockingQueue<String> queue) {
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
        MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);
        new Producer(queue).start();
        new Consumer(queue).start();
    }
}
