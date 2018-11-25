package com.zt.sr.controller;

import com.zt.sr.dao.homeworkDao;
import com.zt.sr.pojo.Homework;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet(name = "com.zt.sr.controller.myPublishServlet", urlPatterns = "/myPublishServlet")
public class myPublishServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("account")) {
                String account = cookie.getValue();
                homeworkDao hd = new homeworkDao();
                List<Homework> homeworkList = hd.selectAll(account);
//                JSONArray json = JSONArray.fromObject(homeworkList);
//                out.print(json);
//                System.out.println(json);
//                out.close();
//                return;

//                for (String attention : attentions) {
//                    List<com.zt.sr.pojo.Homework> homeworkList = hd.selectAll(attention);
//                    homeworkAllList.addAll(homeworkList);
//                }

                for (Homework homework : homeworkList) {
                    System.out.println(homework.getHno());
                    if (homework.getBeginTime() != null) {//sql.date  to util.date
                        homework.setBeginTime(new Date(homework.getBeginTime().getTime()));
                    }
                    if (homework.getEndTime() != null) {
                        homework.setEndTime(new Date(homework.getEndTime().getTime()));
                    }
                }
                JSONArray json = JSONArray.fromObject(homeworkList);
                out.print(json);
                System.out.println(json.toString());
                out.close();
            }
        }
        response.sendRedirect("html/index.html");//没找到关于account的cookie就退出
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
