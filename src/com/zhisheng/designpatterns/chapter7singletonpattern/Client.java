package com.zhisheng.designpatterns.chapter7singletonpattern;

/**
 * 场景类
 */
public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    StaticHolderSingleton staticHolderSingleton = StaticHolderSingleton.getInstance();
                    System.out.println("the object is :" + staticHolderSingleton.toString());
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }

        for (int j = 0; j < 10; j++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    EnumBasedSingleton enumBasedSingleton = EnumBasedSingleton.INSTANCE;
                    System.out.println("the hashCode of singletion is : " + enumBasedSingleton.hashCode());
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
