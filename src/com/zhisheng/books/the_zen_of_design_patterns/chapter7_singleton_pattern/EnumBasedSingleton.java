package com.zhisheng.books.the_zen_of_design_patterns.chapter7_singleton_pattern;

/**
 * 基于枚举类型的单例模式
 */
public enum  EnumBasedSingleton {
    INSTANCE;

    // Java 枚举类型构造方法默认是私有的，可省略 private
    EnumBasedSingleton() {

    }

    // 类中其他方法
    public void someService() {

    }
}
