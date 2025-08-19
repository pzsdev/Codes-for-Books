package com.zhisheng.books.the_zen_of_design_patterns.chapter14mediatorpattern;

/**
 * @ClassName Client
 * @Description
 *
 * 中介者模式（Mediator Pattern）
 *
 * Define an object that encapsulates how a set of objects interact.
 * Mediator promotes loose coupling by keeping objects from referring to
 * each other explicitly, and it lets you vary their interaction independently.
 *
 * 用一个中介对象封装一系列的对象交互，中介者使各对象不需要显示地相互作用，
 * 从而使其耦合松散，而且可以独立地改变他们之间的交互。
 *
 * 场景类
 *
 * @Author pengzhisheng
 * @Date 2020/7/10 02:28
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        AbstractMediator mediator = new Mediator();

        // 采购人员采购电脑
        System.out.println("---------- 采购人员采购电脑 -----------");
        Purchase purchase = new Purchase(mediator);
        purchase.buyIMFComputer(100);

        // 销售人员销售电脑
        System.out.println("---------- 销售人员销售电脑 ----------");
        Sale sale = new Sale(mediator);
        sale.saleIBMComputer(1);

        // 库房人员管理库存
        System.out.println("---------- 库房人员管理库存 ----------");
        Stock stock = new Stock(mediator);
        stock.clearStock();
    }
}
