package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
//@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private int age;
    private double money;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", age=" + age + ", money=" + money + "]";
    }



}
