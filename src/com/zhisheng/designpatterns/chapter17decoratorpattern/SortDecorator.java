package com.zhisheng.designpatterns.chapter17decoratorpattern;

/**
 * @ClassName SortDecorator
 * @Description 排名情况修饰
 * @Author pengzhisheng
 * @Date 2020/7/20 23:47
 * @Version 1.0
 */
public class SortDecorator extends Decorator {
    // 构造函数
    public SortDecorator(SchoolReport schoolReport) {
        super(schoolReport);
    }
    // 告诉老爸排名情况
    private void reportSort() {
        System.out.println("我是排第36名。。。");
    }
    // 看完成绩单，再告诉他，加强作用
    @Override
    public void report() {
        super.report();
        this.reportSort();
    }
}
