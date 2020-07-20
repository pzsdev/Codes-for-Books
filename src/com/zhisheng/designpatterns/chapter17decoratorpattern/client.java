package com.zhisheng.designpatterns.chapter17decoratorpattern;

/**
 * @ClassName client
 * @Description
 * 装饰模式（Decorator Pattern）
 *
 * Attach additional responsibilities to an object dynamically keeping the same interface.
 * decorators proivde a flexible alternative to subclassing for extending functionality.
 * 动态地给一个对象加一些额外地职责。就增加功能来说，装饰模式相比生成子类更加灵活。
 *
 * 场景类
 * 老爸看儿子成绩单，儿子装饰一下成绩单，避免讨打
 *
 * @Author pengzhisheng
 * @Date 2020/7/20 23:07
 * @Version 1.0
 */
public class client {
    public static void main(String[] args) {
        // 把成绩单拿过来
        SchoolReport schoolReport;
        // 原装的成绩单
        schoolReport = new FouthGradeSchoolReport();
        // 加了最高成绩说明的成绩单
        schoolReport = new HighScoreDecorator(schoolReport);
        // 加了成绩排名的成绩单
        schoolReport = new SortDecorator(schoolReport);
        //看成绩单
        schoolReport.report();
        // 看了最高成绩，排名，一高兴，签字了
        schoolReport.sign("老彭");
    }
}
