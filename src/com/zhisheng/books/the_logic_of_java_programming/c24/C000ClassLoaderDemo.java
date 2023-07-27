package com.zhisheng.books.the_logic_of_java_programming.c24;

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

        System.out.println("--------");
        // 获取默认的系统类加载器
        ClassLoader defaultClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(defaultClassLoader);

        try {
            Class<?> cls = defaultClassLoader.loadClass("java.util.ArrayList");
            // 双亲委派实例，Java 类库 java.util 由 Bootstrap ClassLoader 加载
            ClassLoader actualLoader = cls.getClassLoader();
            System.out.println(actualLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
