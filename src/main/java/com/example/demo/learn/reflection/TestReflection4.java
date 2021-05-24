package com.example.demo.learn.reflection;


import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

//注解
public class TestReflection4 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class c1 = Class.forName("ltd.newbee.mall.com.learn.reflection.Student");
        Annotation[] annotations = c1.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }


        //获取注解的value值
        Tablestudent tablestudent = (Tablestudent)c1.getAnnotation(Tablestudent.class);
        String value = tablestudent.value();
        System.out.println(value);

        Field field = c1.getDeclaredField("name");
        Fieldstudent fieldstudent = field.getAnnotation(Fieldstudent.class);
        System.out.println(fieldstudent.columnName());
        System.out.println(fieldstudent.type());
        System.out.println(fieldstudent.length());


    }
}

@Tablestudent("db_student")
class Student{
    @Fieldstudent(columnName = "db_name",type = "varchar",length = 10)
    private String name;
    @Fieldstudent(columnName = "db_age",type = "int",length = 10)
    private int age;

    Student(String name, int age) {
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

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

//类名注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Tablestudent{
    String value();
}

//属性注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Fieldstudent{
    String columnName();
    String type();
    int length();
}




