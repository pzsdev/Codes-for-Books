package com.zhisheng.books.the_zen_of_design_patterns.chapter13prototypepattern;

/**
 * @ClassName Mail
 * @Description 邮件类代码
 * @Author pengzhisheng
 * @Date 2020/7/9 00:09
 * @Version 1.0
 */
public class Mail implements Cloneable{
    // 收件人
    private String receiver;
    // 邮件名称
    private String subject;
    // 称谓
    private String appllation;
    // 邮件内容
    private String context;
    // 邮件结尾签名信息，一般都是加上"XXX拥有最终解释权"等信息
    private String tail;
    //构造函数
    public Mail(AdvTemplate advTemplate) {
        this.context = advTemplate.getAdvContext();
        this.subject = advTemplate.getAdvSubject();
    }

    @Override
    public Mail clone() {
        Mail mail = null;
        try {
            mail = (Mail) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return mail;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAppllation() {
        return appllation;
    }

    public void setAppllation(String appllation) {
        this.appllation = appllation;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }
}
