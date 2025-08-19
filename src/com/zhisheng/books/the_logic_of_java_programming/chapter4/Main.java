package com.zhisheng.books.the_logic_of_java_programming.chapter4;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/8/31
 **/
public class Main {
    public static void main(String[] args) {
        System.out.println("--- new Child ---");
        Child c = new Child();
        System.out.println("\n---c.action()---");
        c.action();

        Base b = c;
        System.out.println("\n---b.action()---");
        b.action();
        System.out.println("\n---b.s: " + Base.s);
        System.out.println("\n---c.s: " + Child.s);
    }
}
