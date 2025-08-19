package com.zhisheng.books.the_zen_of_design_patterns.chapter16chainofresponsibilitypattern;

/**
 * @ClassName Women
 * @Description 具体女性类
 * @Author pengzhisheng
 * @Date 2020/7/18 03:06
 * @Version 1.0
 */
public class Women implements IWomen {
    /**
     * 通过一个 int 类型的参数来描述妇女的个人状况
     * 1--未出嫁
     * 2--出嫁
     * 3--夫死
     */
    private int type = 0;
    // 妇女的请示
    private String request = "";
    // 构造函数传递过来的请求
    public Women(int type, String requset) {
        this.type = type;
        // 为了便于显示，这里显示了不同的状态时的状态表示
        switch(this.type) {
            case 1:
                this.request = "女儿的请求是：" + requset;
                break;
            case 2:
                this.request = "妻子的请求是：" + requset;
            case 3:
                this.request = "母亲的请求是：" + requset;
        }
    }
    // 获得自己的状况
    @Override
    public int getType() {
        return this.type;
    }
    // 获得妇女的请求
    @Override
    public String getRequest() {
        return this.request;
    }
}
