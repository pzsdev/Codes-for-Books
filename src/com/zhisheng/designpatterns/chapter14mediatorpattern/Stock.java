package com.zhisheng.designpatterns.chapter14mediatorpattern;

/**
 * @ClassName Stock
 * @Description 库存管理
 * @Author pengzhisheng
 * @Date 2020/7/10 02:49
 * @Version 1.0
 */
public class Stock extends AbstractColleague {
    public Stock(AbstractMediator mediator) {
        super(mediator);
    }

    // 刚开始库存有 100 台电脑
    private static int COMPUTER_NUMBER = 100;

    // 库存增加
    public void increase(int number) {
        COMPUTER_NUMBER = COMPUTER_NUMBER + number;
        System.out.println("库存数量为： " + COMPUTER_NUMBER);
    }

    // 库存降低
    public void decrease(int number) {
        COMPUTER_NUMBER = COMPUTER_NUMBER - number;
        System.out.println("库存数量为：" + COMPUTER_NUMBER);
    }

    // 获得库存数量
    public int getStockNumber() {
        return COMPUTER_NUMBER;
    }

    // 存货太多，就要通知采购停止采购，销售尽快销售
    public void clearStock() {
        System.out.println("需要清理的库存为：" + COMPUTER_NUMBER);
        super.mediator.execute("stock.clear");
    }
}
