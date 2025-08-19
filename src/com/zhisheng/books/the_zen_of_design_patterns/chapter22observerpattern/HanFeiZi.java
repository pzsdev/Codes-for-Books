package com.zhisheng.books.the_zen_of_design_patterns.chapter22observerpattern;

import java.util.ArrayList;

/**
 * 被观察者实现类，韩非子
 */
public class HanFeiZi implements Observable, IHanFeiZi {
    // 存放所有观察者
    private ArrayList<Observer> observerArrayList = new ArrayList<Observer>();

    // 添加观察者
    @Override
    public void addObserver(Observer observer) {
        this.observerArrayList.add(observer);
    }

    // 删除观察者
    @Override
    public void deleteObserver(Observer observer) {
        this.observerArrayList.remove(observer);
    }

    // 通知所有观察者
    @Override
    public void notifyObservers(String context) {
        for (Observer observer : observerArrayList) {
            observer.update(context);
        }
    }

    // 韩非子开始吃饭
    @Override
    public void haveBreakfast() {
        System.out.println("韩非子：开始吃饭了。。。");
        this.notifyObservers("韩非子在吃饭");
    }

    // 韩非子开始娱乐
    @Override
    public void haveFun() {
        System.out.println("韩非子：开始娱乐了。。。");
        this.notifyObservers("韩非子在娱乐");
    }
}
