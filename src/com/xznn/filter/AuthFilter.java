package com.xznn.filter;

import com.xznn.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String requestURI = req.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        //如果访问的资源是以css或者js结尾的，那么就不需要判断是否登录
        if (requestURI.endsWith(".css") || requestURI.endsWith(".js")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (requestURI.endsWith("/login.html") || requestURI.endsWith("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 从session中取出userName，如果是空，就表示用户没有登录，或者登录已经超过了30分钟。
        // 客户端跳转到login.html，让用户重新登陆
        String name = (String) req.getSession().getAttribute("name");
        if (!StringUtil.isValidate(name)) {
            resp.sendRedirect("/login.html");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
