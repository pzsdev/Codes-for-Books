package com.zhisheng.books.ljp.c24;

/**
 * 类加载器演示
 *
 * @author pengzhisheng
 * @since 2022/8/11
 **/
public class C000ClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoader cl = C000ClassLoaderDemo.class.getClassLoader();

        while (cl != null) {
            System.out.println(cl.getClass().getName());
            cl = cl.getParent();
        }

        System.out.println(String.class.getClassLoader());
    }
}
