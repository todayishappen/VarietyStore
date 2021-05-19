package com.example.demo;

import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class DemoApplicationTests {

    //private  final  UserService userservice;
    /*
    这儿也可以使用@RequiredArgsConstructor(onConstructor = @__(@Autowired))注解+private  final  UserService userservice;
    必须用final修饰
    或者直接@Autowired
     */
    @Autowired
    private   UserService userservice;

    @Test
    void contextLoads() {
    }

    @Test
    void selectByName(){
        userservice.selectUserByName("zhangsan");
        userservice.selectAllUser();
        userservice.selectUserByName("Daisy");
        userservice.selectUserByName("Daisy");


    }

}
