package com.zhisheng.books.designpatterns.chapter11builderpattern;

import java.util.ArrayList;

/**
 * 宝马车组装者
 */
public class BMWBuilder extends CarBuilder {
    private BMWModel bmw = new BMWModel();

    public void setSequence(ArrayList<String> sequence) {
        this.bmw.setSequence(sequence);
    }

    public CarModel getCarModel() {
        return this.bmw;
    }
}
