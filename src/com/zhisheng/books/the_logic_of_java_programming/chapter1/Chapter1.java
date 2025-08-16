package com.zhisheng.books.the_logic_of_java_programming.chapter1;

/**
 * 基本类型作为参数，按值传递，传的是基本类型值的副本；
 * 引用类型作为参数，也是按值传递，这个值是引用的地址值。
 *
 * @author pengzhisheng
 * @since 2025/8/15
 **/
public class Chapter1 {
    public static void main(String[] args) {
        int a = 1;
        System.out.println("a : " + a);
        plus(a);
        System.out.println("a : " + a);

        Integer b = 2;
        System.out.println("b : " + b);
        plus(b);
        System.out.println("b : " + b);

        Obj obj = new Obj();
        obj.a = 1;
        System.out.println("obj.a : " + obj.a);
        plus(obj);
        System.out.println("obj.a : " + obj.a);
    }

    public static void plus (int a) {
        a++;
    }

    public static void plus (Obj obj) {
        obj.a++;
    }

}
