package com.zhisheng.designpatterns.chapter17decoratorpattern;

/**
 * @ClassName SchoolReport
 * @Description 抽象成绩单
 * @Author pengzhisheng
 * @Date 2020/7/20 23:33
 * @Version 1.0
 */
public abstract class SchoolReport {
    // 成绩单主要展示的就是你的成绩情况
    public abstract void report();
    // 成绩单要家长签字
    public abstract void sign(String name);
}
