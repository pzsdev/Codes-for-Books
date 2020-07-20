package com.zhisheng.designpatterns.chapter17decoratorpattern;

/**
 * @ClassName Decorator
 * @Description 修饰的抽象类
 * @Author pengzhisheng
 * @Date 2020/7/20 23:35
 * @Version 1.0
 */
public abstract class Decorator extends SchoolReport {
    // 首先要知道是哪个成绩单
    private SchoolReport schoolReport;
    // 构造函数，传递成绩单进来
    public Decorator(SchoolReport schoolReport) {
        this.schoolReport = schoolReport;
    }
    // 成绩单还是要被看到的
    public void report() {
        this.schoolReport.report();
    }
    // 看完还是要签字的
    public void sign(String name) {
        this.schoolReport.sign(name);
    }
}
