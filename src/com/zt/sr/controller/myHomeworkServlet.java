package com.zt.sr.controller;

import com.zt.sr.dao.homeworkDao;
import com.zt.sr.dao.userDao;
import com.zt.sr.pojo.Homework;
import com.zt.sr.pojo.User;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "com.zt.sr.controller.myHomeworkServlet", urlPatterns = "/myHomeworkServlet")
public class myHomeworkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("account")) {
                String account = cookie.getValue();
                userDao ud = new userDao();

                User user = ud.select(account);
                String[] attentions = user.getAttention().split(";");
                homeworkDao hd = new homeworkDao();

                List<Homework> homeworkAllList = new ArrayList<>();


                for (String attention : attentions) {
                    List<Homework> homeworkList = hd.selectAll(attention);
                    homeworkAllList.addAll(homeworkList);
                }

                for (Homework homework : homeworkAllList) {
                    System.out.println(homework.getHno());
                    if (homework.getBeginTime() != null) {//sql.date  to util.date
                        homework.setBeginTime(new Date(homework.getBeginTime().getTime()));
                    }
                    if (homework.getEndTime() != null) {
                        homework.setEndTime(new Date(homework.getEndTime().getTime()));
                    }
//                    System.out.println(homework.getBeginTime());
//                    System.out.println(homework.getEndTime());
                }
//                System.out.println(homeworkAllList.toString() + homeworkAllList.get(0) + homeworkAllList.get(0).gethno());
                JSONArray json = JSONArray.fromObject(homeworkAllList);
                out.print(json);
                System.out.println(json.toString());
                out.close();
            }
        }
//        out.print("<p>未登录，<a href=\"html/index.html\">点击返回...</a></p>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
