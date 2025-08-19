package com.zhisheng.books.the_zen_of_design_patterns.chapter22observerpattern;

/**
 *
 * 被观察者接口
 */
public interface Observable {
    // 增加一个观察者
    void addObserver(Observer observer);
    // 删除一个观察者
    void deleteObserver(Observer observer);
    // 通知观察者
    void notifyObservers(String context);
}
