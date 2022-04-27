package com.zhisheng.books.designpatterns.chapter11builderpattern;

/**
 * 建造者模式 Builder Pattern
 *
 * Separate the construction of a complex object from its representation
 * so that the same construction process can create different representations.
 *
 * 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示
 *
 * 场景类
 *
 * 根据客户的需求生产不同的汽车模型，同时还要能个性化定制汽车模型的一些具体功能的实现
 */
public class Client {
    public static void main(String[] args) {
        Director director = new Director();

        for (int i = 0; i < 10; i++) {
            System.out.println("A 类型的奔驰车，第" + (i + 1) + "辆。" );
            director.getABenzModel().run();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("B 类型的奔驰车，第" + (i + 1) + "辆。" );
            director.getBBenzModel().run();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("C 类型的宝马车，第" + (i + 1) + "辆。" );
            director.getCBMWModel().run();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("D 类型的宝马车，第" + (i + 1) + "辆。" );
            director.getDBMWMoel().run();
        }
    }

}
