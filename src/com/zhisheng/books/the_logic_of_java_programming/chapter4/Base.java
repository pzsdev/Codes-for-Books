package com.zhisheng.books.the_logic_of_java_programming.chapter4;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/8/31
 **/
public class Base {
    /**
     * 静态变量
     */
    public static int s;

    /**
     * 实例变量
     */
    private int a;

    // 静态初始化代码块
    static {
        System.out.println("基类静态代码块，s: " + s);
        s = 1;
    }

    // 实例初始化代码块
    {
        System.out.println("基类实例代码块，a: " + a);
        a = 1;
    }

    // 构造函数
    public Base() {
        System.out.println("基类构造函数, a: " + a);
        a = 2;
    }

    // 实例方法
    protected void step() {
        System.out.println("base s: " + s + ", a: " + a);
    }

    // 实例方法
    public void action() {
        System.out.println("start");
        step();
        System.out.println("end");
    }
}
