package com.zhisheng.designpatterns.chapter18.version1;

/**
 * @program hello-world
 * @description: 策略枚举
 * @author: pengzhisheng
 * @create: 2019/11/06
 */
public enum Calculator {
    ADD("+"){
        public int exec(int a,int b){
            return a + b;
        }
    },

    SUB("-"){
        public int exec(int a,int b){
            return a - b;
        }
    };

    String value = "";

    private Calculator(String value){
        this.value = value;
    }

    private String getValue(){
        return this.value;
    }

    public abstract int exec(int a,int b);
}
