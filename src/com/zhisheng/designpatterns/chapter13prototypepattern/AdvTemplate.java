package com.zhisheng.designpatterns.chapter13prototypepattern;

/**
 * @ClassName AdvTemplate
 * @Description 广告信模版
 * @Author pengzhisheng
 * @Date 2020/7/9 00:04
 * @Version 1.0
 */
public class AdvTemplate {
    // 广告信名称
    private String advSubject = "XXX银行国庆信用卡抽奖活动";
    // 广告内容
    private String advContext = "国庆抽奖活动通知：只要刷卡就送你一百万！。。。";

    public String getAdvSubject() {
        return this.advSubject;
    }

    public String getAdvContext() {
        return this.advContext;
    }
}
