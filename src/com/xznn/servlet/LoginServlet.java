package com.xznn.servlet;

import com.xznn.bean.Hero;
import com.xznn.dao.HeroDAO;
import com.xznn.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");


        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if (StringUtil.isValidate(name) && StringUtil.isValidate(password)) {
            if ("admin".equals(name) && "123".equals(password)) {
                // 在LoginServlet 把验证成功的用户加入到 Session
                // 如果用户输入正确的账号密码，就跳转到 listHero，并且把用户名以"name"放进session
                req.getSession().setAttribute("name", name);
                resp.sendRedirect("/listHero");

            } else {
                // 如果用户输入错误的账号密码，就跳转到 login.html，让用户重新登陆
//                req.getRequestDispatcher("login.jsp").forward(req, resp);
                System.out.println("密码不正确");
                resp.sendRedirect("/login.html");
            }
        } else {
            System.out.println("输入有误");
            resp.sendRedirect("/login.html");
        }

    }

}
