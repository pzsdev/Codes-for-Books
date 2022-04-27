package com.zhisheng.books.designpatterns.chapter16chainofresponsibilitypattern;

/**
 * @ClassName Son
 * @Description 儿子类
 * @Author pengzhisheng
 * @Date 2020/7/18 03:45
 * @Version 1.0
 */
public class Son extends Handler {
    // 儿子只处理母亲的请求
    public Son() {
        super(Handler.SON_LEVEL_REQUEST);
    }
    // 儿子的答复
    @Override
    protected void response(IWomen women) {
        System.out.println("-----母亲向儿子请求-----");
        System.out.println(women.getRequest());
        System.out.println("儿子的答复是：同意\n");
    }
}
