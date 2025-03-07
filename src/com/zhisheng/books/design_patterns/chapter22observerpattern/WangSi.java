package com.zhisheng.books.design_patterns.chapter22observerpattern;

/**
 * 具体观察者，王斯
 */
public class WangSi implements Observer {
    @Override
    public void update(String string) {
        System.out.println("王斯：观察到韩非子活动，自己也开始活动了。。。");
        this.cry(string);
        System.out.println("王斯：哭死了。。。\n");
    }

    private void cry(String reportContext) {
        System.out.println("王斯：因为" + reportContext + "，所以我悲伤啊！");
    }
}
