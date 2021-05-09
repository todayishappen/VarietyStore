package com.example.demo.interceptor;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sun.reflect.annotation.ExceptionProxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OldLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //start interceptor
        System.out.println("拦截器oldInterceptor开始拦截:");
        long startTime = System.currentTimeMillis();
        System.out.println("开始拦截时间:" + startTime);
        System.out.println("拦截地址:" + request.getRequestURL());
        request.setAttribute("startTime", startTime);
        response.sendRedirect(request.getContextPath() + "/admin/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        //interceptor comment
        System.out.println("拦截器oldInterceptor.postHandle执行。");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
        //end interceptor
        long endTime = System.currentTimeMillis();
        long startTime = (long)request.getAttribute("stratTime");
        System.out.println("拦截器oldInterceptor完成拦截，QueryStringInterceptor.afterCompletion");
        System.out.println("拦截时间:"+(endTime - startTime));

    }
}
