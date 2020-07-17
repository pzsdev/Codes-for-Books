package com.zhisheng.designpatterns.chapter16chainofresponsibilitypattern;

import java.util.ArrayList;
import java.util.Random;

/**
 * @ClassName Client
 * @Description
 * 责任链模式（Chain of Responsibility Pattern)
 *
 * Avoid coupling the sender of a request to its receiver by giving more than one object a chance
 * to handle the request. Chain the receiving objects and pass the request along the chain until
 * an object handles it.
 * 使多个对象都有机会处理请求，从而避免了请求的发送者和接受者之间的耦合关系。将这些对象连成一条链，并沿着这条链
 * 传递该请求，直到有对象处理它为止。
 *
 * 场景类
 * 模拟古代女子的三从四德的场景：一位女性在结婚前要听从父亲的，结婚之后要听从丈夫的，如果丈夫过世了还要听从儿子的
 *
 * @Author pengzhisheng
 * @Date 2020/7/18 02:47
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        // 随机挑选极为女性
        Random random = new Random();
        ArrayList<IWomen> arrayList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            arrayList.add(new Women(random.nextInt(4), "我要出去逛街"));
        }
        // 定义三个请示的对象
        Handler father = new Father();
        Handler husband = new Husband();
        Handler son = new Son();
        // 设置请求顺序
        father.setNext(husband);
        husband.setNext(son);
        for (IWomen women : arrayList) {
            father.HandlerMessage(women);
        }
    }
}
