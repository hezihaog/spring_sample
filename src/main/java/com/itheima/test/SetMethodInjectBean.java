package com.itheima.test;

import java.util.Date;

/**
 * Set方法注入
 */
public class SetMethodInjectBean {
    private String name;
    private Integer age;
    private Date birthday;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void showInfo() {
        System.out.println("信息：=> name：" + name + " ,age：" + age + " ，birthday:" + birthday);
    }
}