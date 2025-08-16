package com.zhisheng.books.design_patterns.chapter13prototypepattern;

import java.util.Random;

/**
 * @ClassName Client
 * @Description
 *
 * 原型模式（Prototype Pattern）
 * Specify the kinds of objects to create using a prototypical instance,
 * and create new objects by copying this prototype.
 * 用原型实例制定创建对象的种类，并且通过拷贝这些原型创建新的对象。
 *
 * 场景类，个性话电子账单
 *
 * @Author pengzhisheng
 * @Date 2020/7/9 00:13
 * @Version 1.0
 */
public class Client {
    // 发送邮件的数量
    private static int MAX_COUNT = 20;
    public static void main(String[] args) {
        // 模拟发送邮件
        int i = 0;
        // 定义模版
        Mail mail = new Mail(new AdvTemplate());
        mail.setTail("XX银行拥有最终解释权");
        while (i < MAX_COUNT) {
            // 以下是每封邮件不同的地方
            Mail cloneMail = mail.clone();
            cloneMail.setAppllation(getRandodString(5) + " 先生（女士）");
            cloneMail.setReceiver(getRandodString(5) + "@" + getRandodString(6) + ".com");
            sendMai(cloneMail);
            i++;
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    // 发送邮件
    public static void sendMai(Mail mail) {
        System.out.println("标题：" + mail.getSubject()
                + "\t收件人：" + mail.getReceiver()
                + "\t正文：" + mail.getAppllation()
                + "\t" + mail.getContext()
                + "\t 签名：" + mail.getTail()
                + "\t。。。发送成功！");
    }

    // 获得制定长度的随机字符串模拟姓名和邮箱地址
    public static String getRandodString(int maxLength) {
        String source = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < maxLength; i++) {
            stringBuffer.append(source.charAt(random.nextInt(source.length())));
        }
        return stringBuffer.toString();
    }
}
