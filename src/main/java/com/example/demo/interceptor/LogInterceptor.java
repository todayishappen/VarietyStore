package com.example.demo.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
LogInterceptor用于过滤所有请求
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        long startTime = System.currentTimeMillis();
        System.out.println("拦截器Log.prehandle开始");
        System.out.println("Request的请求地址为："+request.getRequestURL());
        System.out.println("开始拦截时间为:"+System.currentTimeMillis());
        request.setAttribute("startTime", startTime);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        System.out.println("拦截器Log.posthandle开始");
        System.out.println("Request的请求地址为："+request.getRequestURL());

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
        long startTime = (long)request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        System.out.println("拦截器Log.afterhandle开始");
        System.out.println("Request的请求地址为："+request.getRequestURL());
        System.out.println("完成拦截时间为:"+endTime);
        System.out.println("总拦截时间为："+ (endTime - startTime));

    }
}
