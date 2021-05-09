package com.example.demo.config;

import com.example.demo.filter.MyFilter2;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;
import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class MyFiletrConfig {

    @Autowired
    MyFilter myFilter;

    @Autowired
    MyFilter2 myFilter2;


    @Bean
    public FilterRegistrationBean<MyFilter> setUpMyFilter(){
        FilterRegistrationBean<MyFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.setFilter(myFilter);
        filterRegistrationBean.setUrlPatterns(new ArrayList<>(Arrays.asList("/api/*")));
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<MyFilter2> setMyFilter2(){
        FilterRegistrationBean<MyFilter2> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setFilter(myFilter2);
        filterRegistrationBean.setUrlPatterns(new ArrayList<>(Arrays.asList("/api/*")));
        return filterRegistrationBean;
    }


}
