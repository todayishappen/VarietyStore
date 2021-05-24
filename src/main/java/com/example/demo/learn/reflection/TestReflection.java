package com.example.demo.learn.reflection;


import java.lang.annotation.*;

//元注解
@MyAnnotation
public class TestReflection {
    @MyAnnotation
    public void test(){}
}


@Target(value = {ElementType.TYPE,ElementType.METHOD})//注解的使用范围
@Retention(value = RetentionPolicy.RUNTIME)//注解的作用范围
@Documented//注解放在javadoc
@Inherited//可继承
@interface MyAnnotation{
    String name() default "";//这只是一个参数，不是方法
}
