package com.xznn.servlet;


import com.xznn.bean.Hero;
import com.xznn.dao.HeroDAO;
import com.xznn.util.StringUtil;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HeroListServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //-------

        // 从session中取出userName，如果是空，就表示用户没有登录，或者登录已经超过了30分钟。
        // 客户端跳转到login.html，让用户重新登陆
        String name = (String) req.getSession().getAttribute("name");
        if (!StringUtil.isValidate(name)) {
            resp.sendRedirect("/login.html");
            return;
        }
        //-------


        int start = 0;
        int count = 5;
        String startStr = req.getParameter("start");
        if (startStr != null && !startStr.isEmpty()) {
            try {
                start = Integer.parseInt(startStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        List<Hero> list = new HeroDAO().list(start, count);
        int total = new HeroDAO().getTotal();
        req.setAttribute("heros", list);
        int pre = start - count;
        if (pre < 0) pre = 0;
        req.setAttribute("pre", pre);
        req.setAttribute("next", start + count);

        int last;
        if (total % count == 0) {
            last = total - count;
        } else {
            last = total - total % count;
        }
        req.setAttribute("last", last);

        req.getRequestDispatcher("/listHero.jsp").forward(req, resp);

    }
}