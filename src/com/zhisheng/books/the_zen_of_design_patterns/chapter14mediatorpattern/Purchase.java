package com.zhisheng.books.the_zen_of_design_patterns.chapter14mediatorpattern;

/**
 * @ClassName Purchase
 * @Description 采购人员
 * @Author pengzhisheng
 * @Date 2020/7/10 02:37
 * @Version 1.0
 */
public class Purchase extends AbstractColleague {
    public Purchase(AbstractMediator mediator) {
        super(mediator);
    }

    // 采购 IBM 电脑
    public void buyIMFComputer(int number) {
        super.mediator.execute("purchase.buy", number);
    }

    // 不再采购电脑
    public void refuseBuyIBM() {
        System.out.println("不再采购 IBM 电脑");
    }
}
