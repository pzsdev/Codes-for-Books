package com.zhisheng.books.design_patterns.chapter14mediatorpattern;

/**
 * @ClassName Mediator
 * @Description 具体中介者
 * @Author pengzhisheng
 * @Date 2020/7/10 02:42
 * @Version 1.0
 */
public class Mediator extends AbstractMediator {
    // 中介者最重要的方法
    public void execute(String str, Object... objects) {
        if (str.equals("purchase.buy")) {
            // 采购电脑
            this.buyComputer((Integer) objects[0]);
        } else if (str.equals("sale.sell")) {
            // 销售电脑
            this.sellComputer((Integer) objects[0]);
        } else if (str.equals("sale.offSell")) {
            // 折价销售
            this.offSell();
        } else if (str.equals("stock.clear")) {
            // 清仓处理
            this.clearStock();
        }
    }

    // 采购电脑
    private void buyComputer(int number) {
        int saleStatus = super.sale.getSaleStatus();
        if (saleStatus > 80) {
            // 销售情况良好
            System.out.println("采购 IBM 电脑：" + number + "台");
            super.stock.increase(number);
        } else {
            // 销售情况不好，则折半采购
            int buyNumber = number/2;
            System.out.println("采购 IBM 电脑：" + buyNumber + "台");
            super.stock.increase(number);
        }
    }

    // 销售电脑
    private void sellComputer(int number) {
        if (super.stock.getStockNumber() < number) {
            // 库存数量不够销售
            super.purchase.buyIMFComputer(number);
        }
        super.stock.decrease(number);
    }

    // 折价销售电脑
    private void offSell() {
        System.out.println("折价销售 IBM 电脑" + stock.getStockNumber() + "台");
    }

    // 清仓处理
    private void clearStock() {
        super.sale.offSale();
        super.purchase.refuseBuyIBM();
    }
}
