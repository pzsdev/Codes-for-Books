package com.zhisheng.books.designpatterns.chapter22observerpattern;

/**
 * 具体观察者，李斯
 */
public class LiSi implements Observer {
    @Override
    public void update(String string) {
        System.out.println("李斯：观察到韩非子活动，开始向老板汇报了。。。");
        this.reportToQinShiHuang(string);
        System.out.println("李斯：汇报完毕。。。\n");
    }

    private void reportToQinShiHuang(String reportContext) {
        System.out.println("李斯：报告，秦老板！韩非子有活动了--》" + reportContext);
    }
}
