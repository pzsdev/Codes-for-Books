package com.zhisheng.books.corejava.map;

/**
 * @program hello-world
 * @description:
 * @author: pengzhisheng
 * @create: 2019/10/30
 */
public class Employee {
    Employee(){

    }

    Employee(String name){
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
