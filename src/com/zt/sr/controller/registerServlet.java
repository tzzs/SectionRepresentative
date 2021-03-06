package com.zt.sr.controller;

import com.zt.sr.dao.userDao;
import com.zt.sr.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "com.zt.sr.controller.registerServlet", urlPatterns = "/registerServlet")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        String repwd = request.getParameter("repwd");
//        System.out.println(account + pwd);
        if (pwd.equals(repwd)) {
            User user = new User();
            user.setAccount(account);
            user.setPassword(password.secure.Encrypt(pwd));
            userDao ud = new userDao();
            ud.add(user);
            ud.close();
            //
            Cookie cookie = new Cookie("account", user.getAccount());
            response.addCookie(cookie);
            response.sendRedirect("html/main.html");
        } else {
            response.sendRedirect("html/register.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
