package com.zhisheng.books.the_zen_of_design_patterns.chapter22observerpattern;

/**
 * 具体观察者，刘斯
 */
public class LiuSi implements Observer {
    @Override
    public void update(String string) {
        System.out.println("刘斯：观察到韩非子活动，自己也开始活动了。。。");
        this.happy(string);
        System.out.println("刘斯：乐死了。。。\n");
    }

    private void happy(String reportContext) {
        System.out.println("刘斯：因为" + reportContext + "，所以我开心啊！");
    }
}
