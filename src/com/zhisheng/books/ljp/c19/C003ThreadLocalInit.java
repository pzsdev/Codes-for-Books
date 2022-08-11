package com.zhisheng.books.ljp.c19;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2022/8/10
 **/
public class C003ThreadLocalInit {
    static ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 100;
        }
    };

    public static void main(String[] args) {
        System.out.println(local.get());
        local.set(200);
        System.out.println(local.get());
        local.remove();
        System.out.println(local.get());
    }
}
