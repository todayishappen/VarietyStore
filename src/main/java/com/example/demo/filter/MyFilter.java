package com.example.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

@Component
public class MyFilter implements Filter{

    private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);
    /*
    filter拦截器重写三个方法：init(),doFilter(),destroy()
     */
    @Override
     public void init(FilterConfig filterconfig) throws ServletException {

        //初始化过滤器
        logger.info("初始化过滤器",filterconfig.getFilterName());

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,ServletException{
        //对请求进行过滤
        logger.info("开始过滤器对请求进行预处理：");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String requestURi =request.getRequestURI();
        System.out.println("获得的接口为："+requestURi);
        long startTime= System.currentTimeMillis();
        //调用doFilter（）方法实现过滤功能
        filterChain.doFilter(servletRequest,servletResponse);
        //结束后先返回时间
        long endTime = System.currentTimeMillis();
        System.out.println("该用户返回的时间为："+(endTime-startTime));
    }
    @Override
     public void destroy(){

        //销毁过滤器后执行的操作，用户对某些资源的回收
        logger.info("销毁过滤器");

    }
}
