package com.zhisheng.books.designpatterns.chapter10templatemethodpattern;

/**
 * 抽象悍马模型
 */
public abstract class HummerModel {
    // 模型发动
    public abstract void start();

    // 模型停止
    public abstract void stop();

    // 喇叭会出声音
    public abstract void alarm();

    // 引擎会响
    public abstract void enginBoom();

    // 模型可以跑起来
    public void run() {
        this.start();

        this.enginBoom();

        this.alarm();

        this.stop();
    };
}
