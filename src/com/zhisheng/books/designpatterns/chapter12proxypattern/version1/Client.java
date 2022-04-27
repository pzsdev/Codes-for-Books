package com.zhisheng.books.designpatterns.chapter12proxypattern.version1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * 动态代理的场景类
 */
public class Client {
    public static void main(String[] args) throws Throwable{
        // 定义一个痴迷的玩家
        IGamePlayer player = new GamePlayer("张三");

        // 定义一个 handler
        InvocationHandler handler = new GamePlayIH(player);

        // 开始打游戏，记下时间戳
        System.out.println("开始的时间是：" + new Date());

        // 获得类的 Class Loader
        ClassLoader cl = player.getClass().getClassLoader();

        // 动态产生一个代理者
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(cl, new Class[]{IGamePlayer.class}, handler);

        // 登陆
        proxy.login("zhangSan", "passWord");

        // 开始杀怪
        for (int i = 0; i < 3; i++) {
            proxy.killBoss();
            Thread.sleep(1000);
        }

        // 升级
        proxy.upgrade();

        // 记录结束游戏时间
        System.out.println("结束的时间是：" + new Date());
    }
}
