package com.zhisheng.books.designpatterns.chapter22observerpattern;

/**
 * 观察者接口
 */
public interface Observer {
    // 发现被观察者由行动，自己也做出相应行动
    void update(String context);
}
