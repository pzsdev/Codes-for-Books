package com.zhisheng.books.designpatterns.chapter11builderpattern;

import java.util.ArrayList;

/**
 * 车辆模型的抽象类
 */
public abstract class CarModel {
    // 这个参数是各个基本方法执行顺序
    private ArrayList<String> sequence = new ArrayList<String>();
    // 模型是启动开始跑了
    protected abstract void start();
    // 模型停止
    protected abstract void stop();
    // 喇叭会发出声音
    protected abstract void alarm();
    // 引擎会轰隆隆地响
    protected abstract void engineBoom();
    // 模型可以跑起来
    final public void run() {
        // 循环一遍，按照设定的各种操作的顺序跑起来
        for (int i = 0; i < this.sequence.size(); i++) {
            String actionName = this.sequence.get(i);
            if (actionName.equalsIgnoreCase("start")) {
                this.start();
            } else if (actionName.equalsIgnoreCase("stop")) {
                this.stop();
            } else if (actionName.equalsIgnoreCase("alarm")) {
                this.alarm();
            } else if (actionName.equalsIgnoreCase("engine boom")) {
                this.engineBoom();
            }
        }
    }
    // 把使用时设定的各种操作的顺序传递到类内
    final public void setSequence(ArrayList<String> sequence) {
        this.sequence = sequence;
    }
}
