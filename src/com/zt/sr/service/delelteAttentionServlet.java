package com.zt.sr.service;

import com.zt.sr.dao.userDao;
import com.zt.sr.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "com.zt.sr.service.delelteAttentionServlet",urlPatterns = "/delelteAttentionServlet")
public class delelteAttentionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String deleteAtten = request.getParameter("");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("account")) {
                String account = cookie.getValue();
                userDao ud = new userDao();
                User user = ud.select(account);

                String[] attentions = user.getAttention().split(";");
                StringBuilder atten = new StringBuilder();
                for (String attention : attentions) {
                    if (!attention.equals(deleteAtten)) {
                        atten.append(attention).append(";");
                    }
                }
                user.setAttention(String.valueOf(atten));
                ud.update(user);
                ud.close();
                out.close();
                return;
            }
        }
        response.sendRedirect("html/index.html");//没找到关于account的cookie就退出

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
