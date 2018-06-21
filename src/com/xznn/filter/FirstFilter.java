package com.xznn.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FirstFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        System.out.printf("%s IP：%s 访问了 %s %s %n", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                req.getRemoteAddr(),
                req.getRequestURL(),
                req.getRequestURI());

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("=== init() ===");
//        int i = 10 / 0;
    }

    @Override
    public void destroy() {
        System.out.println("=== destroy() ===");
    }
}
