package com.zhisheng.designpatterns.chapter14mediatorpattern;

/**
 * @ClassName AbstractMediator
 * @Description 抽象中介者
 * @Author pengzhisheng
 * @Date 2020/7/10 02:34
 * @Version 1.0
 */
public abstract class AbstractMediator {
    // 采购
    protected Purchase purchase;

    // 销售
    protected Sale sale;
    // 库存
    protected Stock stock;

    // 构造函数
    public AbstractMediator() {
        purchase = new Purchase(this);
        sale = new Sale(this);
        stock = new Stock(this);
    }

    // 中介者最重要的方法叫做事件方法，处理多个对象之间的关系
    public abstract void execute(String str, Object... objects);
}
