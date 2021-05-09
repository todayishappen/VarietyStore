package com.example.demo.filter;

import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MyFilter2 implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(MyFilter2.class);

    @Override
    public void init(FilterConfig filterConfig){
        //初始化过滤器
        logger.info("初始化第二台过滤器", filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,ServletException {

        logger.info("准备第二次过滤器与处理");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String requsetURi = request.getRequestURI();
        System.out.println("获得的接口为："+requsetURi);
        long startTime = System.currentTimeMillis();
        //处理过滤器方法
        filterChain.doFilter(servletRequest,servletResponse);
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间为"+(endTime-startTime));

    }
    @Override
    public void destroy(){
        logger.info("销毁过滤器2");
    }

}
