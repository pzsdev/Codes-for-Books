package com.zhisheng.books.the_zen_of_design_patterns.chapter12proxypattern.version1;

/**
 * 游戏者
 */
public class GamePlayer implements IGamePlayer {
    private String name = "";

    // 通过构造函数传递名称
    public GamePlayer(String name) {
        this.name = name;
    }

    @Override
    public void login(String user, String password) {
        System.out.println("登录名为" + user + "的用户" + this.name + "登陆成功！");
    }

    @Override
    public void killBoss() {
        System.out.println(this.name + "在打怪！");
    }

    @Override
    public void upgrade() {
        System.out.println(this.name + " 又升一级了！");
    }
}
