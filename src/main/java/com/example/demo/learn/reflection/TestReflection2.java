package com.example.demo.learn.reflection;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflection2 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class c1 = Class.forName("ltd.newbee.mall.com.learn.reflection.User");

        //第一种
        User user = (User)c1.newInstance();//动态创建对象,无参构造器.//IllegalAccessException, InstantiationException
        //********************************************************
        //* 上面c1.newInstance()必须在User类中有无参构造器,否则报错  *
        //********************************************************


        //第二种*************************
        Constructor constructor = c1.getDeclaredConstructor(String.class, int.class);
        User user1 = (User) constructor.newInstance("小明", 18);//InstantiationException, InvocationTargetException

        Method setName = c1.getDeclaredMethod("setName", String.class);
        setName.invoke(user, "激活方法");
        System.out.println(user.getName());


        Field age = c1.getDeclaredField("age");
        age.setAccessible(true);//跳过private安全
        age.set(user, 20);
        System.out.println(user.getAge());

    }

}





