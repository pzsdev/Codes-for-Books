package com.zhisheng.books.designpatterns.chapter11builderpattern;

import java.util.ArrayList;

/**
 * 抽象汽车组装者
 */
public abstract class CarBuilder {
    // 建造一个模型，设置具体的操作执行顺序
    public abstract void setSequence(ArrayList<String> sequence);
    // 设置完毕，返回一个这个汽车模型
    public abstract CarModel getCarModel();
}
