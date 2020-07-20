package com.zhisheng.designpatterns.chapter17decoratorpattern;

/**
 * @ClassName HighScoreDecorator
 * @Description 最高成绩装饰
 * @Author pengzhisheng
 * @Date 2020/7/20 23:43
 * @Version 1.0
 */
public class HighScoreDecorator extends Decorator {
    // 构造函数
    public HighScoreDecorator(SchoolReport schoolReport) {
        super(schoolReport);
    }
    // 我要汇报最高成绩
    private void reportHighScore() {
        System.out.println("这次考试语文的最高成绩是 75，数学是 87，体育是 97，自然是 86。");
    }
    // 汇报成绩前，现报告各科最高成绩
    @Override
    public void report() {
        this.reportHighScore();
        super.report();
    }
}
