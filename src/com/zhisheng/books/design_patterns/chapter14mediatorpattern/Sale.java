package com.zhisheng.books.design_patterns.chapter14mediatorpattern;

import java.util.Random;

/**
 * @ClassName Sale
 * @Description 销售
 * @Author pengzhisheng
 * @Date 2020/7/10 02:55
 * @Version 1.0
 */
public class Sale extends AbstractColleague {
    public Sale(AbstractMediator mediator) {
        super(mediator);
    }

    // 销售 IBM 电脑
    public  void saleIBMComputer(int number) {
        super.mediator.execute("sale.sell", number);
        System.out.println("销售 IBM 电脑" + number + "台");
    }

    // 反馈销售情况，0～100 变化，0 代表没人买，100 代表非常畅销
    public int getSaleStatus() {
        Random random = new Random(System.currentTimeMillis());
        int saleStatus = random.nextInt(100);
        System.out.println("IBM 电脑的销售情况是：" + saleStatus);
        return saleStatus;
    }

    // 折价处理
    public  void offSale() {
        super.mediator.execute("sale.offSale");
    }
}
