package com.zhisheng.books.the_logic_of_java_programming.c4;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/8/31
 **/
public class Child extends Base {
    public static int s;

    private int a;

    static {
        System.out.println("子类静态代码块，s: " + s); // 这里打印的是，定义静态变量时赋值语句的值，即默认值
        s = 10;
    }

    {
        System.out.println("子类实例代码块，a: " + a);
        a = 10;
    }

    public Child() {
        System.out.println("子类构造函数, a: " + a);
        a = 20;
    }

    @Override
    protected void step() {
        System.out.println("child s: " + s + ", a: " + a);
    }
}
