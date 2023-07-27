package com.zhisheng.books.the_logic_of_java_programming.c19;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2022/8/10
 **/
public class C002ThreadLocalBasic {
    static ThreadLocal<Integer> local = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Thread child = new Thread() {
            @Override
            public void run() {
                System.out.println("child thread initial: " + local.get());

                local.set(200);
                System.out.println("child thread final: " + local.get());
            }
        };

        local.set(100);
        child.start();
        child.join();
        System.out.println("main thread final: " + local.get());
    }
}
