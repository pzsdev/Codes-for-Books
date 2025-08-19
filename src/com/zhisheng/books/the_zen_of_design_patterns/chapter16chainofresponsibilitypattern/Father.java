package com.zhisheng.books.the_zen_of_design_patterns.chapter16chainofresponsibilitypattern;

/**
 * @ClassName Father
 * @Description 父亲类
 * @Author pengzhisheng
 * @Date 2020/7/18 03:36
 * @Version 1.0
 */
public class Father extends Handler {
    // 父亲只处理女儿的请求
    public Father() {
        super(Handler.FATHER_LEVEL_REQUEST);
    }
    // 父亲的答复
    @Override
    protected void response(IWomen women) {
        System.out.println("-----女儿向父亲请求-----");
        System.out.println(women.getRequest());
        System.out.println("父亲的答复是：同意\n");
    }
}
