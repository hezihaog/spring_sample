package com.itheima.test;

import java.util.Date;

/**
 * 构造方法注入数据来创建实例
 */
public class ConstructorInjectBean {
    private String name;
    private Integer age;
    private Date birthday;

    public ConstructorInjectBean(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void showInfo() {
        System.out.println("信息：=> name：" + name + " ,age：" + age + " ，birthday:" + birthday);
    }
}