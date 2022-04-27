package com.zhisheng.books.designpatterns.chapter12proxypattern.version1;

/**
 * 代理模式（Proxy Pattern）
 * 游戏者接口
 */
public interface IGamePlayer {
    // 登陆游戏
    void login(String name, String password);

    // 杀怪，网络游戏的主要特色
    void killBoss();

    //升级
    void upgrade();
}
