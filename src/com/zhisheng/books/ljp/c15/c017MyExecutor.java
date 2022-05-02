package com.zhisheng.books.ljp.c15;

import java.util.concurrent.Callable;

/**
 * @ClassName c017MyExecutor
 * @Description TODO
 * @Author pengzhisheng
 * @Date 2022/5/3 03:41
 * @Version 1.0
 **/
public class c017MyExecutor {
    public <V> c016MyFuture<V> execute(final Callable<V> task) {
        final Object lock = new Object();
        final ExecuteThread<V> thread = new ExecuteThread<>(task, lock);
        thread.start();

        return () -> {
            synchronized (lock) {
                while (!thread.isDone()) {
                    try {
                        // 返回对象等待子线程执行结束
                        lock.wait();
                    } catch (InterruptedException e) {

                    }
                }

                if (thread.getException() != null) {
                    throw thread.getException();
                }

                return thread.getResult();
            }

        };
    }


    public static class ExecuteThread<V> extends Thread {
        private V result = null;
        private Exception exception = null;
        private boolean done = false;
        private Callable<V> task;
        private Object lock;
        public ExecuteThread(Callable<V> task, Object lock) {
            this.task = task;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                result = task.call();
            } catch (Exception e) {
                exception = e;
            } finally {
                synchronized (lock) {
                    done = true;
                    // 执行结束，通知等待结果的线程
                    lock.notifyAll();
                }
            }
        }

        public V getResult() {
            return result;
        }

        public boolean isDone() {
            return done;
        }

        public Exception getException() {
            return exception;
        }
    }
}
