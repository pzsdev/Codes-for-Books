package com.zhisheng.books.the_logic_of_java_programming.chapter24;

/**
 * ClassLoader 的 loadClass 方法与 Class 的 forName 方法都可以加载类，它们有什么不同呢？
 * 基本是一样的，不过，ClassLoader 的 loadClass 不会执行类的初始化代码
 *
 * @author pengzhisheng
 * @since 2022/8/11
 **/
public class C001CLInitDemo {
    public static class Hello {
        static {
            System.out.println("hello");
        }
    }

    public static void main(String[] args) {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        String className = C001CLInitDemo.class.getName() + "$Hello";
        try {
            // 1 ClassLoader 的 loadClass() 加载类的同时不会执行类的初始化代码
//            Class<?> cls = cl.loadClass(className);

            // 2 Class 的 forName() 加载类的同时会执行类的初始化代码
            Class<?> cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
