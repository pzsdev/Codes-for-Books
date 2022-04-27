package com.zhisheng.books.designpatterns.chapter11builderpattern;

/**
 * 奔驰模型
 */
public class BenzModel extends CarModel {
    protected void start() {
        System.out.println("奔驰车跑起来是这样子的。。。");
    }

    protected void stop() {
        System.out.println("奔驰车应该这样停车。。。");
    }

    protected void alarm() {
        System.out.println("奔驰车的喇叭声音是这样紫的。。。");
    }

    protected void engineBoom() {
        System.out.println("奔驰车的引擎是这个声音。。。");
    }
}
