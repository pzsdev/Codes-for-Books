package com.zhisheng.designpatterns.chapter14mediatorpattern;

/**
 * @ClassName AbstractColleague
 * @Description 抽象同事类
 * @Author pengzhisheng
 * @Date 2020/7/10 02:35
 * @Version 1.0
 */
public class AbstractColleague {
    protected AbstractMediator mediator;
    public AbstractColleague(AbstractMediator mediator) {
        this.mediator = mediator;
    }
}
