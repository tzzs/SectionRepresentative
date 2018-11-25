package com.zt.sr.controller;

import com.zt.sr.dao.homeworkDao;
import com.zt.sr.pojo.Homework;
import com.zt.sr.utils.getHno;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class com.zt.sr.controller.publishServlet
 */
@WebServlet(name = "com.zt.sr.controller.publishServlet", urlPatterns = "/publishServlet")
public class publishServlet extends HttpServlet {
    private String getFilename(Part part) {
        String contentDispositionHeader = part.getHeader("content-disposition");
        String[] elements = contentDispositionHeader.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        ArrayList<String> list = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        String account = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("account")) {
                account = cookie.getValue();
            }
        }
        if (account != null) {
            Homework homework = new Homework();

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh:ss");

            String hno = Integer.toString(getHno.gethno());

            String hcontent = "";
            String hdir = "/" + hno;
            String hfile = "";
            Date beginTime = null;
            Date endTime = null;
            String subInfo = "";

            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            if (items != null) {
                for (Object item1 : items) {
                    FileItem item = (FileItem) item1;
                    if (item.isFormField()) {
                        String value = item.getString("UTF-8");
                        switch (item.getFieldName()) {
                            case "hcontent":
                                hcontent = value;
//                                System.out.println(hcontent);
                                break;
                            case "beginTime":
                                String begin = value;
                                begin = begin.replace("T", "-");
//                                System.out.println(begin);

                                try {
                                    beginTime = new Date(format.parse(begin).getTime());
                                } catch (ParseException e) {
                                    System.out.println(e);
                                }

                                break;
                            case "endTime":
                                String end = value;
                                end = end.replace("T", "-");
//                                System.out.println(end);
                                try {
                                    endTime = new Date(format.parse(end).getTime());
                                } catch (ParseException e) {
                                    System.out.println(e);
                                }
                                break;
                        }
                    } else {
                        String fileName = item.getFieldName();
                        System.out.println("filedName:" + fileName);
                        String filename = item.getName();
                        hfile = filename;

                        String path = getServletContext().getRealPath("/WEB-INF") + "/FILE";//文件夹路径
                        if (hdir.substring(0, 1).equals("/")) {
                            path = path + hdir;//文件路径
                        } else {
                            path = path + "/" + hdir;//文件路径
                        }
                        File filePath = new File(path);
                        if (!filePath.exists()) {
                            if (!filePath.mkdirs()) {
                                System.out.println("文件储存地址创建失败");
                                return;
                            }
                        }
                        path = path + "/" + filename;//文件路径
                        File saveFile = new File(path);
                        try {
                            item.write(saveFile);
                        } catch (Exception e) {
                            System.out.println("文件为空");
                        }
                    }
                }
            } else {
                return;
            }
            homework.setHno(hno);//
            homework.setHcontent(hcontent);
            homework.setHfile(hfile);
            homework.setHdir(hdir);
            homework.setBeginTime(beginTime);
            homework.setEndTime(endTime);
            homework.setSubInfo(subInfo);
            homework.setIssuer(account);

            homeworkDao hd = new homeworkDao();
            hd.add(homework);
            out.println("公告完成");
            out.println("<br>Uploaded file name: " + hfile);

            out.println("<p>点击返回，<a href=\"html/publish3.html\">已发布公告...</a></p>");
            return;
        }
        response.sendRedirect("html/publish.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request, response);
    }

    /*
     * 获取文件的名字
     */
    public String getId() {
        //得到long类型当前时间
        long l = System.currentTimeMillis();
        //new日期对象
        Date d = new Date(l);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-ss");//转换格式
        System.out.println(sdf.format(d));//打印
        return sdf.format(d);

    }

}
