package com.zhisheng.books.design_patterns.chapter11builderpattern;

import java.util.ArrayList;

/**
 * 奔驰车组装者
 */
public class BenzBuilder extends CarBuilder {
    private BenzModel benz = new BenzModel();

    public void setSequence(ArrayList<String> sequence) {
        this.benz.setSequence(sequence);
    }

    public CarModel getCarModel() {
        return this.benz;
    }
}
