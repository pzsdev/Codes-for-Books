package com.zhisheng.books.the_zen_of_design_patterns.chapter16chainofresponsibilitypattern;

/**
 * @ClassName Husband
 * @Description 丈夫类
 * @Author pengzhisheng
 * @Date 2020/7/18 03:43
 * @Version 1.0
 */
public class Husband extends Handler {
    // 丈夫只处理妻子的请求
    public Husband() {
        super(Handler.HUSBAND_LEVEL_REQUEST);
    }
    // 丈夫的答复
    @Override
    public void response(IWomen women) {
        System.out.println("-----妻子向丈夫请示----");
        System.out.println(women.getRequest());
        System.out.println("丈夫的答复是：同意\n");
    }
}
