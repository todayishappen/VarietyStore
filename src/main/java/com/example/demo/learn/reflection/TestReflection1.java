package com.example.demo.learn.reflection;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestReflection1 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        //Class cc=Person.class;
        //Class cc=person.getClass();
        Class cl = Class.forName("ltd.newbee.mall.com.learn.reflection.User");//ClassNotFoundException
        System.out.println(cl);
        System.out.println(cl.getName());
        System.out.println(cl.getSimpleName());

        Field[] fields = cl.getFields();
        Field[] fields1 =cl.getDeclaredFields();
        Field fields2 =cl.getDeclaredField("name");//NoSuchFieldException
        for (Field field:fields){
            System.out.println(field);
        }

        //*********************
        //Method一样，但获取单个的参数有所变化
        Method[] methods = cl.getMethods();
        Method method1 = cl.getDeclaredMethod("setAge", int.class);  //NoSuchMethodException
        Method method2 = cl.getDeclaredMethod("getAge",null);


        //获得构造器
        Constructor[] constructors = cl.getDeclaredConstructors();
        Constructor constructor = cl.getDeclaredConstructor(String.class,int.class);
        System.out.println(constructor);
    }

}


class User{

    private String name;
    private int age;

    public User(){
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
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
}


