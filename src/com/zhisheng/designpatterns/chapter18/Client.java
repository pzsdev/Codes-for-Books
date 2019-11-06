package com.zhisheng.designpatterns.chapter18;

import java.util.Arrays;

/**
 * @program hello-world
 * @description: 场景类
 * @author: pengzhisheng
 * @create: 2019/11/06
 */
public class Client {
    public static void main(String[] args){
        String[] params = {"1", "+", "2"};
        int a = Integer.parseInt(params[0]);
        String symbol = params[1];
        int b = Integer.parseInt(params[2]);

        System.out.println("输入的参数为：" + Arrays.toString(params));

        System.out.println("运行结果为：" + a + symbol + b + " = " + Calculator.ADD.exec(a, b));
        System.out.println("运行结果为：" + a + symbol + b + " = " + Calculator.SUB.exec(a, b));
    }
}
