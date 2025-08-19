package com.zhisheng.books.the_zen_of_design_patterns.chapter7_singleton_pattern;

/**
 * 基于静态内部类的单例模式
 */
public class StaticHolderSingleton {
    // 私有构造方法
    private StaticHolderSingleton() {
    }

    private static class InstanceHolder {
        // 保存外部类的唯一实例
        final static StaticHolderSingleton INSTANCE = new StaticHolderSingleton();
    }
    // 获取唯一实例方法
    public static StaticHolderSingleton getInstance() {
        return InstanceHolder.INSTANCE;
    }

    // 类中其他方法
    public void someService() {

    }
}
