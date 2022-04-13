package com.rabbitmq.domain;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 729422417229778882L;
    private String name;
    private int age;
    private long exp;

    public User() {
    }

    public User(String name, int age, long exp) {
        this.name = name;
        this.age = age;
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", exp=" + exp +
                '}';
    }
}
