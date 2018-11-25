package com.zt.sr.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "com.zt.sr.service.hfileDownloadServlet", urlPatterns = "/hfileDownloadServlet")
public class hfileDownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hno = request.getParameter("hno");
        System.out.println(hno);
        String hfile = request.getParameter("hfile");
        System.out.println(hfile);

        //使用cookie判断是否登录
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("account")) {
                String account = cookie.getValue();

                //获取文件夹
//                String filename = null;
                String dir = request.getServletContext().getRealPath("/WEB-INF/FILE/" + hno);
                System.out.println("dir:" + dir);
                File file = new File(dir, hfile);
                System.out.println(file.getPath());
                if (file.exists()) {
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "attachment;filename=" + hfile);
                    byte[] buffer = new byte[1024];
                    FileInputStream fis = null;
                    BufferedInputStream bis = null;

                    try {
                        fis = new FileInputStream(file);
                        bis = new BufferedInputStream(fis);
                        OutputStream os = response.getOutputStream();
                        int i = bis.read(buffer);
                        while (i != -1) {
                            os.write(buffer, 0, i);
                            i = bis.read(buffer);
                        }
                    } catch (IOException e) {
                        System.out.println(e);
                    } finally {
                        if (bis != null) {
                            bis.close();
                        }
                        if (fis != null) {
                            fis.close();
                        }
                    }
                }
                return;
            }
        }
        response.sendRedirect("html/index.html");//没找到关于account的cookie就退出
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
