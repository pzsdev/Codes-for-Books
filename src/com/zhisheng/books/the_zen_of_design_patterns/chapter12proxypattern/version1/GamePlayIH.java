package com.zhisheng.books.the_zen_of_design_patterns.chapter12proxypattern.version1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类
 *
 * InvocationHadler 是 JDK 提供的动态代理接口，invoke 方法必须实现，用于完成对真是方法的调用
 */
public class GamePlayIH implements InvocationHandler {
    // 被代理者
    Class cls = null;

    // 被代理的实例
    Object obj = null;

    // 我要代理谁
    public GamePlayIH(Object obj) {
        this.obj = obj;
    }

    // 调用被代理的方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        Object result = method.invoke(this.obj, args);

        return result;
    }
}
